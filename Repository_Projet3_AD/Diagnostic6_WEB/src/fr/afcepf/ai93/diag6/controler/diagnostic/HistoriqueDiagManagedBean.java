package fr.afcepf.ai93.diag6.controler.diagnostic;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import fr.afcepf.ai93.diag6.api.business.diagnostic.IBusinessAnomalie;
import fr.afcepf.ai93.diag6.api.business.diagnostic.IBusinessDiagnostic;
import fr.afcepf.ai93.diag6.entity.diagnostic.Diagnostic;
import fr.afcepf.ai93.diag6.entity.diagnostic.HistoriqueAnomalie;
import fr.afcepf.ai93.diag6.entity.diagnostic.HistoriqueDiagnostic;

@ManagedBean(name="mbHistoriqueDiag")
@SessionScoped
public class HistoriqueDiagManagedBean {

	@EJB
	private IBusinessDiagnostic proxyBusinessDiagnostic;
	@EJB
	private IBusinessAnomalie proxyBusinessAnomalie; 
	
	private List<HistoriqueDiagnostic> listeDiagnostics;
	private List<HistoriqueAnomalie> listeAnomalies; 
	private Diagnostic monDiagnostic;

	@PostConstruct
	private void init() {
		 
	}
	
	public void loadHistorique(){
		String param = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
		if (param != null && !param.equals(""))
		{
			int idDiagnostic = Integer.parseInt(param);
			monDiagnostic = proxyBusinessDiagnostic.recupereDiagnostic(idDiagnostic);
			initialisationDesDonnees();
		}
	}
	
	public void clickNode (int idERP)
	{
		monDiagnostic = proxyBusinessDiagnostic.recupereDiagnostic(idERP);
		initialisationDesDonnees();
	}

	public void initialisationDesDonnees()
	{
		listeDiagnostics = proxyBusinessDiagnostic.recupereHistoriqueDiagnosticParDiag(monDiagnostic.getIdDiagnostic());
		listeAnomalies = proxyBusinessAnomalie.recupereHistoriqueAnomalieParDiag(monDiagnostic.getIdDiagnostic());		
	}

	public List<HistoriqueDiagnostic> getListeDiagnostics() {
		return listeDiagnostics;
	}

	public void setListeDiagnostics(List<HistoriqueDiagnostic> listeDiagnostics) {
		this.listeDiagnostics = listeDiagnostics;
	}

	public List<HistoriqueAnomalie> getListeAnomalies() {
		return listeAnomalies;
	}

	public void setListeAnomalies(List<HistoriqueAnomalie> listeAnomalies) {
		this.listeAnomalies = listeAnomalies;
	}

	public IBusinessDiagnostic getProxyBusinessDiagnostic() {
		return proxyBusinessDiagnostic;
	}

	public void setProxyBusinessDiagnostic(
			IBusinessDiagnostic proxyBusinessDiagnostic) {
		this.proxyBusinessDiagnostic = proxyBusinessDiagnostic;
	}


	public IBusinessAnomalie getProxyBusinessAnomalie() {
		return proxyBusinessAnomalie;
	}

	public void setProxyBusinessAnomalie(IBusinessAnomalie proxyBusinessAnomalie) {
		this.proxyBusinessAnomalie = proxyBusinessAnomalie;
	}

	public Diagnostic getMonDiagnostic() {
		return monDiagnostic;
	}

	public void setMonDiagnostic(Diagnostic monDiagnostic) {
		this.monDiagnostic = monDiagnostic;
	}
	
}
