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
//	private List<Diagnostic> listeDiagnosticsIntervEnCours =null; 
//	private List<Diagnostic> listeDiagnosticsIntervAttente =null;
//	private List<Diagnostic> listeDiagnosticsIntervArchive =null;
//	private List<Diagnostic> listeDiagnostics; 
	
	@PostConstruct
	public void init() {
		System.out.println("appel");
		listeDiag = proxyBusiness.recupereToutDiagnostic();
		for (Diagnostic d : listeDiag)
			System.out.println(d.getIntituleDiagnostic());
	}

	public IBusinessDiagnostic getProxyBusiness() {
		return proxyBusiness;
	}

	public void setProxyBusiness(IBusinessDiagnostic proxyBusiness) {
		this.proxyBusiness = proxyBusiness;
	}

	public List<Diagnostic> getListeDiag() {
		return listeDiag;
	}

	public void setListeDiag(List<Diagnostic> listeDiag) {
		this.listeDiag = listeDiag;
	}	
}
