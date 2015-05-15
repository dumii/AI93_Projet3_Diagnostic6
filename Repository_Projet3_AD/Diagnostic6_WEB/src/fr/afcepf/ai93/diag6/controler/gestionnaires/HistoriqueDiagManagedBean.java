package fr.afcepf.ai93.diag6.controler.gestionnaires;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import fr.afcepf.ai93.diag6.api.business.diagnostic.IBusinessAnomalie;
import fr.afcepf.ai93.diag6.api.business.diagnostic.IBusinessDiagnostic;
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

	@PostConstruct
	private void init() {
		recupererHistoriqueDiagParDiag(1); 
		recupererHistoriqueAnomalieParDiag(1); 
	}
	
	private void recupererHistoriqueDiagParDiag(int idDiagEnCours) {
		listeDiagnostics = proxyBusinessDiagnostic.recupereHistoriqueDiagnosticParDiag(idDiagEnCours);
		
	}
	
	private void recupererHistoriqueAnomalieParDiag(int idDiagEnCours) {
		listeAnomalies = proxyBusinessAnomalie.recupereHistoriqueAnomalieParDiag(idDiagEnCours);
		
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
	
}
