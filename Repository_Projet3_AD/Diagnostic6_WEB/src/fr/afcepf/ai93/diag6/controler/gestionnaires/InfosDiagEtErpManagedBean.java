package fr.afcepf.ai93.diag6.controler.gestionnaires;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import fr.afcepf.ai93.diag6.api.business.diagnostic.IBusinessDiagnostic;
import fr.afcepf.ai93.diag6.api.business.erp.IBusinessErp;
import fr.afcepf.ai93.diag6.api.data.erp.IDaoErp;
import fr.afcepf.ai93.diag6.entity.diagnostic.Diagnostic;
import fr.afcepf.ai93.diag6.entity.erp.Erp;

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
	private int idErp; 
	
	
	public int getIdDiag() {
		return idDiag;
	}

	public void setIdDiag(int idDiag) {
		this.idDiag = idDiag;
	}
	
	public void recupererDiagnostic(){
		diagnosticSelectionne = proxyBusinessDiagnostic.recupereDiagnostic(idDiag); 
	}

	public IBusinessDiagnostic getProxyBusinessDiagnostic() {
		return proxyBusinessDiagnostic;
	}


	public void setProxyBusinessDiagnostic(
			IBusinessDiagnostic proxyBusinessDiagnostic) {
		this.proxyBusinessDiagnostic = proxyBusinessDiagnostic;
	}




	////////////////////////getters et setters ////////////////////
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
}
