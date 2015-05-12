package fr.afcepf.ai93.diag6.controler.erp;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import fr.afcepf.ai93.diag6.api.business.diagnostic.IBusinessAnomalie;
import fr.afcepf.ai93.diag6.api.business.diagnostic.IBusinessDiagnostic;
import fr.afcepf.ai93.diag6.api.business.erp.IBusinessErp;
import fr.afcepf.ai93.diag6.entity.autres.Utilisateur;
import fr.afcepf.ai93.diag6.entity.diagnostic.Diagnostic;
import fr.afcepf.ai93.diag6.entity.erp.Batiment;
import fr.afcepf.ai93.diag6.entity.erp.Erp;
import fr.afcepf.ai93.diag6.entity.erp.Etage;

@ManagedBean(name="mbConsultationERP")
@SessionScoped
public class ConsultationErpManagedBean {

	@EJB
	private IBusinessDiagnostic proxyDiagnostic;
	@EJB
	private IBusinessAnomalie proxyAnomalie;
	@EJB
	private IBusinessErp proxyERP;
	
	//Utilisateur connecté
	private Utilisateur user;
	
	//Informations relatives à l'ERP
	private Erp monERP;
	private int idERP;
	
	//Autres données
	private SimpleDateFormat formater;
	private SimpleDateFormat shortFormater;
	
	//Initialisation de la liste d'interventions au chargement de la page
	@PostConstruct
	public void init()
	{
		monERP = proxyERP.recupererErpParId(10);
		initialisationDesDonnees();
	}
	
	//Sélection de l'ERP ou chargement de l'ERP en paramètres
	public void loadERP(){
		String param = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
		if (param != null && !param.equals(""))
		{
			idERP = Integer.parseInt(param);
			monERP = proxyERP.recupererErpParId(idERP);
			initialisationDesDonnees();
		}
	}
	
	public void clickNode (int idERP)
	{
		this.idERP = idERP;
		monERP = proxyERP.recupererErpParId(idERP);
		initialisationDesDonnees();
	}
	
	//Méthodes	
	public void initialisationDesDonnees()
	{
		formater = new SimpleDateFormat("dd/MM/yyyy");
		shortFormater = new SimpleDateFormat("dd/MM");
		
		//Récupération des informations sur la structure de l'ERP
		monERP.setListeBatimentsErp(proxyERP.recupererBatParErp(monERP.getIdErp()));
		for (Batiment batiment : monERP.getListeBatimentsErp())
		{
			batiment.setListeAccesBatiment(proxyERP.recupererAccesParBat(batiment.getIdBatiment()));
			batiment.setListeAscenceursBatiment(proxyERP.recupererAscenceursParBat(batiment.getIdBatiment()));
			batiment.setListeEscaliersBatiment(proxyERP.recupererEscaliersParBat(batiment.getIdBatiment()));
			batiment.setListeEtagesBatiment(proxyERP.recupererEtagesParBat(batiment.getIdBatiment()));
			
			for (Etage etage : batiment.getListeEtagesBatiment())
			{
				etage.setListePieces(proxyERP.recupererPiecesParEtage(etage.getIdEtage()));
			}
		}
		
		monERP.setListeVoiriesErp(proxyERP.recupererVoirieParErp(idERP));
		
		monERP.setListeDiagnosticErp(proxyDiagnostic.recupereToutDiagnosticParErp(monERP));
		for (Diagnostic diagnostic : monERP.getListeDiagnosticErp())
		{
			diagnostic.setListeAnomaliesDiagnostic(proxyAnomalie.recupereAnomalieParDiagnostic(diagnostic.getIdDiagnostic()));
		}
	}
	
	public String typeDiagnostic(int idTypeDiagnostic)
	{		
		switch (idTypeDiagnostic) {
		case 1:
			return "images/TypesDiagnosticIcones/TypeAcces.38.36.png";
		case 2:
			return "images/TypesDiagnosticIcones/TypeEnergie.75.71.png";
		case 3:
			return "images/TypesDiagnosticIcones/TypeSecurite.75.75.png";
		case 4:
			return "images/TypesDiagnosticIcones/TypeHygiene.75.71.png";
		default:
			return "images/TypesDiagnosticIcones/TypeHygiene.75.71.png";
		}
	}
	
	public String statutTraiteOuNon(int traite)
	{		
		if (traite == 1)
		{
			return "Diagnostic traité";
		}
		else
		{
			return "Diagnostic en cours de traitement";
		}
	}

	//Getters et setters
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

	public int getIdERP() {
		return idERP;
	}

	public void setIdERP(int idERP) {
		this.idERP = idERP;
	}

	public SimpleDateFormat getFormater() {
		return formater;
	}

	public void setFormater(SimpleDateFormat formater) {
		this.formater = formater;
	}

	public SimpleDateFormat getShortFormater() {
		return shortFormater;
	}

	public void setShortFormater(SimpleDateFormat shortFormater) {
		this.shortFormater = shortFormater;
	}
}
