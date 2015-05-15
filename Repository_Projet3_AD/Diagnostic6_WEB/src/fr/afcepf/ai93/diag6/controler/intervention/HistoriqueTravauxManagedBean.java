package fr.afcepf.ai93.diag6.controler.intervention;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import fr.afcepf.ai93.diag6.api.business.autres.IBusinessArtisan;
import fr.afcepf.ai93.diag6.api.business.diagnostic.IBusinessAnomalie;
import fr.afcepf.ai93.diag6.api.business.diagnostic.IBusinessDiagnostic;
import fr.afcepf.ai93.diag6.api.business.erp.IBusinessErp;
import fr.afcepf.ai93.diag6.api.business.travaux.IBusinessIntervention;
import fr.afcepf.ai93.diag6.entity.autres.Artisan;
import fr.afcepf.ai93.diag6.entity.autres.Utilisateur;
import fr.afcepf.ai93.diag6.entity.diagnostic.Anomalie;
import fr.afcepf.ai93.diag6.entity.diagnostic.Diagnostic;
import fr.afcepf.ai93.diag6.entity.erp.Erp;
import fr.afcepf.ai93.diag6.entity.travaux.EtatAvancementTravaux;
import fr.afcepf.ai93.diag6.entity.travaux.HistoriqueIntervention;
import fr.afcepf.ai93.diag6.entity.travaux.Intervention;
import fr.afcepf.ai93.diag6.entity.travaux.TypeIntervention;

@ManagedBean(name="mbHistoriqueTravaux")
@SessionScoped
public class HistoriqueTravauxManagedBean {

	@EJB
	private IBusinessIntervention proxyIntervention;
	@EJB
	private IBusinessErp proxyERP;
	
	//Utilisateur connecté
	private Utilisateur user;
	
	//Informations relatives à l'ERP
	private Erp monERP;
	private List<HistoriqueIntervention> listeInterventions;
	
	@PostConstruct
	public void init()
	{

	}

	//Sélection de l'ERP ou chargement de l'ERP en paramètres
	public void loadHistorique(){
		String param = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
		if (param != null && !param.equals(""))
		{
			System.out.println("coucou");
			int idERP2 = Integer.parseInt(param);
			monERP = proxyERP.recupererErpParId(idERP2);
			initialisationDesDonnees();
		}
	}
	
	public void clickNode (int idERP)
	{
		monERP = proxyERP.recupererErpParId(idERP);
		initialisationDesDonnees();
	}

	public void initialisationDesDonnees()
	{
		listeInterventions = proxyIntervention.recupereHistoriqueInterventionParERP(monERP);		
	}

	public IBusinessErp getProxyERP() {
		return proxyERP;
	}

	public void setProxyERP(IBusinessErp proxyERP) {
		this.proxyERP = proxyERP;
	}

	public Utilisateur getUser() {
		return user;
	}

	public void setUser(Utilisateur user) {
		this.user = user;
	}

	public Erp getMonERP() {
		return monERP;
	}

	public void setMonERP(Erp monERP) {
		this.monERP = monERP;
	}

	public List<HistoriqueIntervention> getListeInterventions() {
		return listeInterventions;
	}

	public void setListeInterventions(
			List<HistoriqueIntervention> listeInterventions) {
		this.listeInterventions = listeInterventions;
	}
}
