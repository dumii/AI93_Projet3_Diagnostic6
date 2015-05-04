package fr.afcepf.ai93.diag6.controler.gestionnaires;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.richfaces.resource.PostConstructResource;

import fr.afcepf.ai93.diag6.api.business.diagnostic.IBusinessDiagnostic;
import fr.afcepf.ai93.diag6.api.business.erp.IBusinessErp;
import fr.afcepf.ai93.diag6.api.data.erp.IDaoErp;
import fr.afcepf.ai93.diag6.entity.diagnostic.Diagnostic;
import fr.afcepf.ai93.diag6.entity.erp.Batiment;
import fr.afcepf.ai93.diag6.entity.erp.Erp;
import fr.afcepf.ai93.diag6.entity.erp.Etage;

@ManagedBean(name="mbDonneesGenerales")
@SessionScoped
public class InfosDiagEtErpManagedBean implements Serializable {
	@EJB 
	private IBusinessErp proxyBusinessErp; 
	@EJB
	private IBusinessDiagnostic proxyBusinessDiagnostic; 
	
	private Erp erpDiagnosticSelectionne; 
	private Diagnostic diagnosticSelectionne;
	private int idDiag; 
	private Erp erpSelectionne; 
	
	
	@PostConstructResource
	private void init() {

	}

	public void recupererDiagnostic(){
		diagnosticSelectionne = proxyBusinessDiagnostic.recupereDiagnostic(idDiag); 
		recupErp();
		
	}


	public void recupErp(){
		erpSelectionne = new Erp(); 
		erpSelectionne.setIdErp(diagnosticSelectionne.getErp().getIdErp());
		
		List<Batiment> listeBatimentsErpSel = new ArrayList<Batiment>();
		listeBatimentsErpSel = proxyBusinessErp.recupererBatParErp(erpSelectionne.getIdErp());
		erpSelectionne.setListeBatimentsErp(listeBatimentsErpSel);
	
		List<Etage> listeEtagesBatSel = new ArrayList<Etage>();
		for (Batiment b : listeBatimentsErpSel){
			listeEtagesBatSel = proxyBusinessErp.recupererEtagesParBat(b.getIdBatiment());
			b.setListeEtagesBatiment(listeEtagesBatSel);
		}
		
		
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
}
