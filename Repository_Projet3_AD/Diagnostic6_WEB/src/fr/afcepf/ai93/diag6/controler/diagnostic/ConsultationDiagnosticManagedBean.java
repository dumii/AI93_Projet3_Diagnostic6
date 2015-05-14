package fr.afcepf.ai93.diag6.controler.diagnostic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.richfaces.resource.PostConstructResource;

import fr.afcepf.ai93.diag6.api.business.autres.IBusinessExpert;
import fr.afcepf.ai93.diag6.api.business.diagnostic.IBusinessAnomalie;
import fr.afcepf.ai93.diag6.api.business.diagnostic.IBusinessDiagnostic;
import fr.afcepf.ai93.diag6.api.business.erp.IBusinessErp;
import fr.afcepf.ai93.diag6.api.business.travaux.IBusinessIntervention;
import fr.afcepf.ai93.diag6.api.data.erp.IDaoErp;
import fr.afcepf.ai93.diag6.entity.autres.Expert;
import fr.afcepf.ai93.diag6.entity.autres.Utilisateur;
import fr.afcepf.ai93.diag6.entity.diagnostic.Anomalie;
import fr.afcepf.ai93.diag6.entity.diagnostic.Diagnostic;
import fr.afcepf.ai93.diag6.entity.diagnostic.Indicateur;
import fr.afcepf.ai93.diag6.entity.diagnostic.TypeDiagnostic;
import fr.afcepf.ai93.diag6.entity.erp.Acces;
import fr.afcepf.ai93.diag6.entity.erp.Ascenceur;
import fr.afcepf.ai93.diag6.entity.erp.Batiment;
import fr.afcepf.ai93.diag6.entity.erp.Erp;
import fr.afcepf.ai93.diag6.entity.erp.Escalier;
import fr.afcepf.ai93.diag6.entity.erp.Etage;
import fr.afcepf.ai93.diag6.entity.erp.Piece;
import fr.afcepf.ai93.diag6.entity.erp.Voirie;
import fr.afcepf.ai93.diag6.entity.travaux.Intervention;

@ManagedBean(name="mbDonneesGenerales")
@SessionScoped
public class ConsultationDiagnosticManagedBean implements Serializable {
	
	@EJB 
	private IBusinessErp proxyBusinessErp; 
	@EJB
	private IBusinessDiagnostic proxyBusinessDiagnostic;
	@EJB
	private IBusinessAnomalie proxyBusinessAnomalie; 
	@EJB
	private IBusinessIntervention proxyBusinessIntervention; 
	@EJB
	private IBusinessExpert proxyBusinessExpert; 

	private Erp erpDiagnosticSelectionne;  
	private Diagnostic diagnosticSelectionne;
	private int idDiag; 
	private Erp erpSelectionne; 
	List<Etage> listeEtagesBatSel;   
	List<Anomalie> listeAnomaliesParDiagnostic; 
	private Anomalie amodif = new Anomalie();
	private List<Indicateur> listeIndicateursParDiagnostic; 
	private List<Expert> listeExperts;
	private Diagnostic dmodif = new Diagnostic(); 
	
	@PostConstruct
	public void init()
	{

	}
	
	//Sélection de l'ERP ou chargement de l'ERP en paramètres
	public void loadDiagnostic(){
		String param = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
		if (param != null && !param.equals(""))
		{
			idDiag = Integer.parseInt(param);
			recupererDiagnostic();
		}
	}

	public void clickNode (int idDiag)
	{
		this.idDiag = idDiag;
		recupererDiagnostic();
	}
	
	public void recupererDiagnostic(){
		diagnosticSelectionne = proxyBusinessDiagnostic.recupereDiagnostic(idDiag); 
		recupErp();	
		recupererAnomaliesParDiagnostic();
		recupererIndicateurParTypeDiag(); 
		recupererExperts(); 
		amodif=new Anomalie(); 
	}

	private void recupererExperts() {
		listeExperts = proxyBusinessExpert.recupereToutExpert(); 
	}

	private void recupererIndicateurParTypeDiag() {
		listeIndicateursParDiagnostic=proxyBusinessDiagnostic.recupererIndicateursParDiag(diagnosticSelectionne); 
	}

	public void recupErp(){
		erpSelectionne = new Erp(); ;
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
		diagnosticSelectionne.setListeAnomaliesDiagnostic(listeAnomaliesParDiagnostic);
	}

	public String localisationAnomalie(Anomalie a){
		if(a.getPiece() != null){
			return a.getPiece().getEtage().getBatiment().getIntituleBatiment()+" "+a.getPiece().getEtage().getBatiment().getNumBatiment()+", Etage : " +a.getPiece().getEtage().getNomEtage()+ " "+a.getPiece().getEtage().getNumeroEtage()+", "
					+ a.getPiece().getFonctionPiece().getLibelleFonctionPiece()+": "+a.getPiece().getDenominationPiece()+" " +a.getPiece().getNumeroPiece(); 
		} else {
			if(a.getVoirie() !=null) {
				return a.getVoirie().getTypeVoirie().getLibelleTypeVoirie()+" : "+a.getVoirie().getDesignationVoirie()+" " +a.getVoirie().getIntituleVoirie(); 
			}
			else{
				if(a.getAcces() != null){
					return a.getAcces().getBatiment().getIntituleBatiment()+" "+a.getAcces().getBatiment().getNumBatiment() +", Accès : " + a.getAcces().getTypeAcces().getLibelleTypeAcces(); 
				}
				else{
					if(a.getEscalier() != null){
						return a.getEscalier().getBatiment().getIntituleBatiment()+" "+a.getEscalier().getBatiment().getNumBatiment() +", Escalier : "+a.getEscalier().getDenominationEscalier();
					}
					else{
						if(a.getAscenceur() !=null){
							return a.getAscenceur().getBatiment().getIntituleBatiment()+" "+a.getAscenceur().getBatiment().getNumBatiment()+", Ascenceur : "+a.getAscenceur().getDenominationAscenceur(); 
						}
						else {
							return "Localisation non définie"; 
						}
					}
				}
			}	
		}
	}

	public String recupererEtatIntervention(Anomalie a){
		Intervention interventionAnomalieEnCours = null;
		List<Intervention> listeInterventionAnomalieEnCours = proxyBusinessIntervention.rechercherInterventionSurAnomalie(a.getIdAnomalie()); 
		if (listeInterventionAnomalieEnCours.size() == 0){
			return "Il n'y a pas d'intervention prévue sur cette anomalie"; 
		}
		else {
			for (Intervention i : listeInterventionAnomalieEnCours){
				interventionAnomalieEnCours = i; 	
			}
			if(interventionAnomalieEnCours.getEtatAvancementTravaux().getIdEtatAvancement()==3){
				return "Une intervention est planifiée sur cette anomalie à partir du "+interventionAnomalieEnCours.getDateDebutIntervention(); 
			}
			else{
				if(interventionAnomalieEnCours.getEtatAvancementTravaux().getIdEtatAvancement()==2){
					return "Une intervention est en cours sur cette anomalie depuis le "+ interventionAnomalieEnCours.getDateDebutIntervention() +
							" et prendra fin le "+interventionAnomalieEnCours.getDateFinIntervention(); 
				}
				else{
					return "L'intervention sur cette anomalie a pris fin le "+ interventionAnomalieEnCours.getDateFinIntervention(); 
				}
			}
		}
	}
	
	public String traiteAnomalie(Anomalie a){
		if(a != null && a.getTraite()!=0)
			return "Traité"; 
		else
			return "Non traité"; 
	}

	public String traiteDiagnostic(){
		if(diagnosticSelectionne != null && diagnosticSelectionne.getTraite()!=0)
			return "Traité"; 
		else
			return "Non traité"; 
	}
	
	public void changerEtatDiagnostic(){
		if(diagnosticSelectionne.getTraite()==0)
				diagnosticSelectionne.setTraite(1);
		else
			diagnosticSelectionne.setTraite(0);
	}
	
	public void modificationAnomalie(Anomalie a){
		System.out.println(amodif.getIdAnomalie() + " = " + a.getIdAnomalie());
		if(amodif.getIdAnomalie() != a.getIdAnomalie()) {
			System.out.println("Anomalie prise en compte pour la modification : " + a.getIdAnomalie());
			amodif = a;
		} else {
			System.out.println("je passe ici");
			Utilisateur user = new Utilisateur();
			user.setIdUtilisateur(1);
			proxyBusinessAnomalie.modifierAnomalie(a, user); 
			amodif=new Anomalie(); 
			recupererDiagnostic(); 
		}
	}
	
	public void modificationDiagnostic(){
		
		System.out.println("je modifie le diagnostic");
		System.out.println(dmodif.getIdDiagnostic() + " = " + diagnosticSelectionne.getIdDiagnostic());
		if(dmodif.getIdDiagnostic() != diagnosticSelectionne.getIdDiagnostic()) {
			System.out.println("Diag pris en compte pour la modification : " + diagnosticSelectionne.getIdDiagnostic());
			dmodif = diagnosticSelectionne;
		} else {
			System.out.println("je passe ici");
			Utilisateur user = new Utilisateur();
			user.setIdUtilisateur(1);
			proxyBusinessDiagnostic.modifierDiagnostic(diagnosticSelectionne,user);
			dmodif=new Diagnostic(); 
			recupererDiagnostic(); 
		}
	}
	
	public void annulerModifAnomalie(Anomalie a){
		amodif=new Anomalie(); 
		recupererDiagnostic();
	}
	
	public void annulerModifDiagnostic(){
		dmodif=new Diagnostic(); 
		recupererDiagnostic();
	}
	
	public boolean isEnable(Anomalie a) {
		if(a.getIdAnomalie() == amodif.getIdAnomalie()) {
			return false;
		}
		return true;
	}
	
	public boolean isEnableDiag() {
		
		System.out.println("*******************************diag modif : "+dmodif.getIdDiagnostic());
		System.out.println("diagnostic selectionné++++++++++++++++++++++++++++++++++++++++++++++"+diagnosticSelectionne.getIdDiagnostic());
		if(diagnosticSelectionne.getIdDiagnostic() == dmodif.getIdDiagnostic()) {
			return false;
		}
		return true;
	}
	
	public String clickChangeBouton(Anomalie a){
		if(a.getIdAnomalie() == amodif.getIdAnomalie()) {
			return "Valider";
		}
		return "Modifier"; 
	}
	
	public String clickChangeBouton(){
		if(diagnosticSelectionne.getIdDiagnostic() == dmodif.getIdDiagnostic()) {
			return "Valider";
		}
		return "Modifier"; 
	}
	
	public String suppressionAnomalie(Anomalie a){
		System.out.println("delete debut");
		if(verifInterventionSurAnomalie(a)==true){
			System.out.println("Vous ne pouvez pas supprimer");
			return "Vous ne pouvez pas supprimer une anomalie ayant une intervention dessous"; 
		}
		else{
			Utilisateur user = new Utilisateur();
			user.setIdUtilisateur(1);
			proxyBusinessAnomalie.supprimerAnomalie(a, user); 
			//amodif=new Anomalie(); 
			recupererDiagnostic(); 
			return "Suppression réalisée"; 
		}

	}
	
	public boolean verifInterventionSurAnomalie(Anomalie a){
		List<Intervention> listeInterventionAnomalieEnCours = proxyBusinessIntervention.rechercherInterventionSurAnomalie(a.getIdAnomalie()); 
		if (listeInterventionAnomalieEnCours.size() != 0){
			return true; //s'il y a une intervention sur l'anomalie, return true
		}
		else{
			return false; 
		}
	}
	
	public boolean isSelected(){
		if(diagnosticSelectionne != null){
			System.out.println("il a été sélectionné");
			return true;
		}
		System.out.println("pas sélectionné");
		return false; 
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

	public IBusinessIntervention getProxyBusinessIntervention() {
		return proxyBusinessIntervention;
	}

	public void setProxyBusinessIntervention(
			IBusinessIntervention proxyBusinessIntervention) {
		this.proxyBusinessIntervention = proxyBusinessIntervention;
	}

	public Anomalie getAmodif() {
		return amodif;
	}

	public void setAmodif(Anomalie amodif) {
		this.amodif = amodif;
	}

	public List<Indicateur> getListeIndicateursParDiagnostic() {
		return listeIndicateursParDiagnostic;
	}

	public void setListeIndicateursParDiagnostic(
			List<Indicateur> listeIndicateursParDiagnostic) {
		this.listeIndicateursParDiagnostic = listeIndicateursParDiagnostic;
	}

	public List<Expert> getListeExperts() {
		return listeExperts;
	}

	public void setListeExperts(List<Expert> listeExperts) {
		this.listeExperts = listeExperts;
	}

	public IBusinessExpert getProxyBusinessExpert() {
		return proxyBusinessExpert;
	}

	public void setProxyBusinessExpert(IBusinessExpert proxyBusinessExpert) {
		this.proxyBusinessExpert = proxyBusinessExpert;
	}

	public Diagnostic getDmodif() {
		return dmodif;
	}

	public void setDmodif(Diagnostic dmodif) {
		this.dmodif = dmodif;
	}
	

}
