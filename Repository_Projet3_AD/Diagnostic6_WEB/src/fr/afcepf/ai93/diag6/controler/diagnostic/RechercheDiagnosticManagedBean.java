package fr.afcepf.ai93.diag6.controler.diagnostic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import fr.afcepf.ai93.diag6.api.business.diagnostic.IBusinessDiagnostic;
import fr.afcepf.ai93.diag6.api.business.erp.IBusinessErp;
import fr.afcepf.ai93.diag6.entity.diagnostic.Diagnostic;
import fr.afcepf.ai93.diag6.entity.erp.Erp;

@ManagedBean(name="mbRechercheDiagnostic")
@SessionScoped
public class RechercheDiagnosticManagedBean implements Serializable  {

	@EJB
	private IBusinessDiagnostic proxyBusinessDiag; 
	@EJB
	private IBusinessErp proxyBusinessErp; 
	
	private List<Diagnostic> listeDiag; 
	private List<Diagnostic> listeDiagnosticsIntervEnCours; 
	private List<Diagnostic> listeDiagnosticsIntervAttente;
	private List<Diagnostic> listeDiagnosticsIntervArchive;
	private List<Erp> listeERP; 
	
	@PostConstruct
	public void init() {
		listeDiag = proxyBusinessDiag.recupereToutDiagnostic();
		listeDiagnosticsIntervEnCours = proxyBusinessDiag.recupereToutDiagnosticIntervEnCours(); 
		listeDiagnosticsIntervAttente = proxyBusinessDiag.recupereToutDiagnosticEnAttente(); 		
		listeDiagnosticsIntervArchive = proxyBusinessDiag.recupereToutDiagnosticArchives(); 
		listeERP = proxyBusinessErp.recupereToutErp();  
	}

//	public List<Diagnostic> diagnosticParErp(){
//		List<Diagnostic> listeResultat = new ArrayList<Diagnostic>();
//		for (Diagnostic d : listeDiagnosticsIntervEnCours)
//			if(d.getIntituleDiagnostic().contains(d.getErp().getNomErp()))
//				listeResultat.add(d);
//		return listeResultat; 
//	}
	
	public IBusinessDiagnostic getProxyBusinessDiag() {
		return proxyBusinessDiag;
	}

	public void setProxyBusinessDiag(IBusinessDiagnostic proxyBusiness) {
		this.proxyBusinessDiag = proxyBusiness;
	}

	public List<Diagnostic> getListeDiagnosticsIntervEnCours() {
		return listeDiagnosticsIntervEnCours;
	}

	public void setListeDiagnosticsIntervEnCours(
			List<Diagnostic> listeDiagnosticsIntervEnCours) {
		this.listeDiagnosticsIntervEnCours = listeDiagnosticsIntervEnCours;
	}

	public List<Diagnostic> getListeDiag() {
		return listeDiag;
	}

	public void setListeDiag(List<Diagnostic> listeDiag) {
		this.listeDiag = listeDiag;
	}

	public List<Diagnostic> getListeDiagnosticsIntervAttente() {
		return listeDiagnosticsIntervAttente;
	}

	public void setListeDiagnosticsIntervAttente(
			List<Diagnostic> listeDiagnosticsIntervAttente) {
		this.listeDiagnosticsIntervAttente = listeDiagnosticsIntervAttente;
	}

	public List<Diagnostic> getListeDiagnosticsIntervArchive() {
		return listeDiagnosticsIntervArchive;
	}

	public void setListeDiagnosticsIntervArchive(
			List<Diagnostic> listeDiagnosticsIntervArchive) {
		this.listeDiagnosticsIntervArchive = listeDiagnosticsIntervArchive;
	}

	public IBusinessErp getProxyBusinessErp() {
		return proxyBusinessErp;
	}

	public void setProxyBusinessErp(IBusinessErp proxyBusinessErp) {
		this.proxyBusinessErp = proxyBusinessErp;
	}

	public List<Erp> getListeERP() {
		return listeERP;
	}

	public void setListeERP(List<Erp> listeERP) {
		this.listeERP = listeERP;
	}
}
