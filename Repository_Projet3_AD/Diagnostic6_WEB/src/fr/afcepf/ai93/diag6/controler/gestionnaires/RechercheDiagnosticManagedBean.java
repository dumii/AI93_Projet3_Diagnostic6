package fr.afcepf.ai93.diag6.controler.gestionnaires;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import fr.afcepf.ai93.diag6.api.business.diagnostic.IBusinessDiagnostic;
import fr.afcepf.ai93.diag6.entity.diagnostic.Diagnostic;

@ManagedBean(name="mbRechercheDiagnostic")
@SessionScoped
public class RechercheDiagnosticManagedBean implements Serializable  {

	@EJB
	private IBusinessDiagnostic proxyBusiness; 
	
	private List<Diagnostic> listeDiag; 
	private List<Diagnostic> listeDiagnosticsIntervEnCours; 
	private List<Diagnostic> listeDiagnosticsIntervAttente;
	private List<Diagnostic> listeDiagnosticsIntervArchive;
	
	@PostConstruct
	public void init() {
		listeDiag = proxyBusiness.recupereToutDiagnostic();
		listeDiagnosticsIntervEnCours = proxyBusiness.recupereToutDiagnosticIntervEnCours(); 
		listeDiagnosticsIntervAttente = proxyBusiness.recupereToutDiagnosticEnAttente(); 		
		listeDiagnosticsIntervArchive = proxyBusiness.recupereToutDiagnosticArchives(); 
	}

	public IBusinessDiagnostic getProxyBusiness() {
		return proxyBusiness;
	}

	public void setProxyBusiness(IBusinessDiagnostic proxyBusiness) {
		this.proxyBusiness = proxyBusiness;
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
	
	
	
}
