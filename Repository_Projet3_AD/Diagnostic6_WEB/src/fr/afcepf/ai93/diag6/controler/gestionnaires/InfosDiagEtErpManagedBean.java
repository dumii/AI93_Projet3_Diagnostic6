package fr.afcepf.ai93.diag6.controler.gestionnaires;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.richfaces.resource.PostConstructResource;

import fr.afcepf.ai93.diag6.api.business.diagnostic.IBusinessAnomalie;
import fr.afcepf.ai93.diag6.api.business.diagnostic.IBusinessDiagnostic;
import fr.afcepf.ai93.diag6.api.business.erp.IBusinessErp;
import fr.afcepf.ai93.diag6.api.data.erp.IDaoErp;
import fr.afcepf.ai93.diag6.entity.diagnostic.Anomalie;
import fr.afcepf.ai93.diag6.entity.diagnostic.Diagnostic;
import fr.afcepf.ai93.diag6.entity.erp.Acces;
import fr.afcepf.ai93.diag6.entity.erp.Ascenceur;
import fr.afcepf.ai93.diag6.entity.erp.Batiment;
import fr.afcepf.ai93.diag6.entity.erp.Erp;
import fr.afcepf.ai93.diag6.entity.erp.Escalier;
import fr.afcepf.ai93.diag6.entity.erp.Etage;
import fr.afcepf.ai93.diag6.entity.erp.Piece;
import fr.afcepf.ai93.diag6.entity.erp.Voirie;

@ManagedBean(name="mbDonneesGenerales")
@SessionScoped
public class InfosDiagEtErpManagedBean implements Serializable {
	@EJB 
	private IBusinessErp proxyBusinessErp; 
	@EJB
	private IBusinessDiagnostic proxyBusinessDiagnostic;
	@EJB
	private IBusinessAnomalie proxyBusinessAnomalie; 
	
	private Erp erpDiagnosticSelectionne;  
	private Diagnostic diagnosticSelectionne;
	private int idDiag; 
	private Erp erpSelectionne; 
	List<Etage> listeEtagesBatSel;   
	List<Anomalie> listeAnomaliesParDiagnostic; 
	
	@PostConstructResource
	private void init() {
	}

	public void recupererDiagnostic(){
		diagnosticSelectionne = proxyBusinessDiagnostic.recupereDiagnostic(idDiag); 
		recupErp();	
		recupererAnomaliesParDiagnostic();
	}

	public void recupErp(){
		erpSelectionne = new Erp(); 
		erpSelectionne.setIdErp(diagnosticSelectionne.getErp().getIdErp());
		
		List<Batiment> listeBatimentsErpSel = new ArrayList<Batiment>();
		listeBatimentsErpSel = proxyBusinessErp.recupererBatParErp(erpSelectionne.getIdErp());
		erpSelectionne.setListeBatimentsErp(listeBatimentsErpSel);
		
		List<Voirie> listeVoiriesParErp = new ArrayList<Voirie>(); 
		listeVoiriesParErp = proxyBusinessErp.recupererVoirieParErp(erpSelectionne.getIdErp()); 
		erpSelectionne.setListeVoiriesErp(listeVoiriesParErp);	
		
		listeEtagesBatSel = new ArrayList<Etage>();
		List<Acces> listeAccesBatSel = new ArrayList<Acces>(); 
		List<Escalier> listeEscaliersBatSel = new ArrayList<Escalier>(); 
		List<Ascenceur> listeAscenceursBatSel = new ArrayList<Ascenceur>(); 
		for (Batiment b : listeBatimentsErpSel){
			
			listeEtagesBatSel = proxyBusinessErp.recupererEtagesParBat(b.getIdBatiment());
			b.setListeEtagesBatiment(listeEtagesBatSel);
			
			listeAccesBatSel = proxyBusinessErp.recupererAccesParBat(b.getIdBatiment());
			b.setListeAccesBatiment(listeAccesBatSel);
	
			listeEscaliersBatSel = proxyBusinessErp.recupererEscaliersParBat(b.getIdBatiment());
			b.setListeEscaliersBatiment(listeEscaliersBatSel);
		
			listeAscenceursBatSel = proxyBusinessErp.recupererAscenceursParBat(b.getIdBatiment());
			b.setListeAscenceursBatiment(listeAscenceursBatSel);
		}	
	}
	
	public int calculNbPiecesBat(){
		int nbPiecesParBat = 0; 
		List<Piece> listePiecesParEtage = new ArrayList<Piece>();
		for(Etage e : listeEtagesBatSel){
			listePiecesParEtage = proxyBusinessErp.recupererPiecesParEtage(e.getIdEtage());
			e.setListePieces(listePiecesParEtage);
			nbPiecesParBat++; 
		}
		return nbPiecesParBat; 
	}
	
	public void recupererAnomaliesParDiagnostic(){
		listeAnomaliesParDiagnostic = proxyBusinessAnomalie.recupereAnomalieParDiagnostic(idDiag); 
	}
	
	public String localisationAnomalie(){
		for(Anomalie a : listeAnomaliesParDiagnostic){
			if(a.getDiagnostic().getIdDiagnostic() == idDiag){
				if(a.getPiece() != null){
					return "Etage : " +a.getPiece().getEtage().getNomEtage()+ " "+a.getPiece().getEtage().getNumeroEtage()+" "
							+ a.getPiece().getFonctionPiece().getLibelleFonctionPiece()+" "+a.getPiece().getDenominationPiece()+" " +a.getPiece().getNumeroPiece(); 
				}
				else{
					if(a.getVoirie() !=null) {
						return a.getVoirie().getTypeVoirie().getLibelleTypeVoirie()+" "+a.getVoirie().getDesignationVoirie()+" " +a.getVoirie().getIntituleVoirie(); 
					}
					else{
						if(a.getAcces() != null){
							return "Accès " + a.getAcces().getTypeAcces().getLibelleTypeAcces(); 
						}
						else{
							if(a.getEscalier() != null){
								return "Escalier "+a.getEscalier().getDenominationEscalier();
							}
							else{
								if(a.getAscenceur() !=null){
									return "Ascenceur "+a.getAscenceur().getDenominationAscenceur(); 
								}
								else {
									return "Localisation non définie"; 
								}
							}
						}
					}
					
				}
			}
		}
		return "Il n'y a pas d'anomalie définie sur ce diagnostic"; 
	}
	

	////////////////////////getters et setters ////////////////////
	
	public int getIdDiag() {
		return idDiag;
	}

	public void setIdDiag(int idDiag) {
		this.idDiag = idDiag;
	}
	public Diagnostic getDiagnosticSelectionne() {
		return diagnosticSelectionne;
	}
	public void setDiagnosticSelectionne(Diagnostic diagnosticSelectionne) {
		this.diagnosticSelectionne = diagnosticSelectionne;
	}
	public IBusinessErp getProxyBusinessErp() {
		return proxyBusinessErp;
	}
	public void setProxyBusinessErp(IBusinessErp proxyBusinessErp) {
		this.proxyBusinessErp = proxyBusinessErp;
	}
	public Erp getErpDiagnosticSelectionne() {
		return erpDiagnosticSelectionne;
	}
	public void setErpDiagnosticSelectionne(Erp erpDiagnosticSelectionne) {
		this.erpDiagnosticSelectionne = erpDiagnosticSelectionne;
	}
	public Erp getErpSelectionne() {
		return erpSelectionne;
	}

	public void setErpSelectionne(Erp erpSelectionne) {
		this.erpSelectionne = erpSelectionne;
	}

	public IBusinessDiagnostic getProxyBusinessDiagnostic() {
		return proxyBusinessDiagnostic;
	}

	public void setProxyBusinessDiagnostic(
			IBusinessDiagnostic proxyBusinessDiagnostic) {
		this.proxyBusinessDiagnostic = proxyBusinessDiagnostic;
	}

	public List<Etage> getListeEtagesBatSel() {
		return listeEtagesBatSel;
	}

	public void setListeEtagesBatSel(List<Etage> listeEtagesBatSel) {
		this.listeEtagesBatSel = listeEtagesBatSel;
	}
							 
	public List<Anomalie> getListeAnomaliesParDiagnostic() {
		return listeAnomaliesParDiagnostic;
	}

	public void setListeAnomaliesParDiagnostic(
			List<Anomalie> listeAnomaliesParDiagnostic) {
		this.listeAnomaliesParDiagnostic = listeAnomaliesParDiagnostic;
	}

	public IBusinessAnomalie getProxyBusinessAnomalie() {
		return proxyBusinessAnomalie;
	}

	public void setProxyBusinessAnomalie(IBusinessAnomalie proxyBusinessAnomalie) {
		this.proxyBusinessAnomalie = proxyBusinessAnomalie;
	}
	
}
