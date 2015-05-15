package fr.afcepf.ai93.diag6.controler.testElsa;

import java.text.SimpleDateFormat;
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
import fr.afcepf.ai93.diag6.entity.autres.Utilisateur;
import fr.afcepf.ai93.diag6.entity.diagnostic.Anomalie;
import fr.afcepf.ai93.diag6.entity.diagnostic.Diagnostic;
import fr.afcepf.ai93.diag6.entity.erp.Erp;
import fr.afcepf.ai93.diag6.entity.travaux.Intervention;

@ManagedBean(name="mbConsultationTravaux")
@SessionScoped
public class EssaiTableauDeBordManagedBean {

	@EJB
	private IBusinessIntervention proxyIntervention;
	@EJB
	private IBusinessDiagnostic proxyDiagnostic;
	@EJB
	private IBusinessAnomalie proxyAnomalie;	
	@EJB
	private IBusinessArtisan proxyArtisan;
	@EJB
	private IBusinessErp proxyERP;
	
	//Utilisateur connecté
	private Utilisateur user;
	
	//Informations relatives à l'ERP
	private Erp monERP;
	private int idERP;
	private List<Diagnostic> listeDiagnosticERP;
	
	//Statistiques du chantier
	private int nombreTotalIntervention;
	private int nombreInterventionTerminees;
	private int nombreInterventionEnCours;
	private int nombreInterventionEnAttente;
	private int nombreInterventionSuspendues;
	
	private double coutTotal;
	
	private Date dateDebut;
	private Date dateFin;
	
	//Essai
	private int[][] tableauNombreInterventions;
	
	//Autres données
	private SimpleDateFormat formater;
	private SimpleDateFormat shortFormater;
	
	
	//Initialisation de la liste d'interventions au chargement de la page
	@PostConstruct
	public void init()
	{

	}
	
	//Sélection de l'ERP ou chargement de l'ERP en paramètres
	public void loadChantier(){
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
		
		listeDiagnosticERP = proxyDiagnostic.recupereToutDiagnosticParErp(monERP);
		
		nombreTotalIntervention = 0;
		nombreInterventionTerminees = 0;
		nombreInterventionEnCours = 0;
		nombreInterventionEnAttente = 0;
		nombreInterventionSuspendues = 0;
		coutTotal = 0;

		dateDebut = new Date();
		dateFin = new Date();
		
		tableauNombreInterventions = new int[listeDiagnosticERP.size()][5];
		int index = 0;
		
		for (Diagnostic diagnostic : listeDiagnosticERP)
		{
			diagnostic.setListeAnomaliesDiagnostic(proxyAnomalie.recupereAnomalieParDiagnostic(diagnostic.getIdDiagnostic()));
			compterInterventions(diagnostic.getListeAnomaliesDiagnostic(), index);
			index++;
		}
		
		for (int i=0 ; i<tableauNombreInterventions.length ; i++)
		{
			nombreTotalIntervention += tableauNombreInterventions[i][0];
			nombreInterventionTerminees += tableauNombreInterventions[i][1];
			nombreInterventionEnCours += tableauNombreInterventions[i][2];
			nombreInterventionEnAttente += tableauNombreInterventions[i][3];
			nombreInterventionSuspendues += tableauNombreInterventions[i][4];
		}
	}

	public void compterInterventions(List<Anomalie> liste, int index)
	{
		int nombreTotal = 0;
		int nombreTermines = 0;
		int nombreEnCours = 0;
		int nombreEnAttente = 0;
		int nombreSuspendes = 0;
		
		for (Anomalie anomalie : liste)
		{
			List<Intervention> listeIntervention = proxyIntervention.rechercherInterventionSurAnomalie(anomalie.getIdAnomalie()); 
			if (listeIntervention.size() > 0)
			{
				Intervention intervention = listeIntervention.get(0);
				int idEtatAvancement = intervention.getEtatAvancementTravaux().getIdEtatAvancement();
				coutTotal += intervention.getCoutIntervention();
				
				switch (idEtatAvancement) {
				//Termines
				case 1:
					nombreTotal++;
					nombreTermines++;
					break;
				//Suspendues
				case 2:
					nombreTotal++;
					nombreSuspendes++;
					break;
				//En cours
				case 3:
					nombreTotal++;
					nombreEnCours++;
					break;
				//En attente
				case 4:
					nombreTotal++;
					nombreEnAttente++;
					break;
				}
				
				if (intervention.getDateDebutIntervention().compareTo(dateDebut) == -1)
				{
					dateDebut = intervention.getDateDebutIntervention();
				}
				if (intervention.getDateFinIntervention().compareTo(dateFin) == 1)
				{
					dateFin = intervention.getDateFinIntervention();
				}
			}
		}
		tableauNombreInterventions[index][0] = nombreTotal;
		tableauNombreInterventions[index][1] = nombreTermines;
		tableauNombreInterventions[index][2] = nombreEnCours;
		tableauNombreInterventions[index][3] = nombreEnAttente;
		tableauNombreInterventions[index][4] = nombreSuspendes;
	}
	
	public String terminesOuTravauxEnCours(int nombreTermines, int nombreTotal)
	{		
		if (nombreTotal == 0)
		{
			return "images/pasDeTravaux.png";
		}
		else if (nombreTermines == nombreTotal)
		{
			return "images/travauxTermines.56.64.png";
		}
		else
		{
			return "images/travauxEnCours.53.65.png";
		}
	}
	
	public String terminesOuTravauxEnCoursToString(int nombreTermines, int nombreTotal)
	{		
		if (nombreTotal == 0)
		{
			return "Aucunes interventions de programmées";
		}
		else if (nombreTermines == nombreTotal)
		{
			return "Interventions terminées";
		}
		else
		{
			return "Interventions en cours";
		}
	}
	
	//Getters et Setters
	
	public IBusinessIntervention getProxyIntervention() {
		return proxyIntervention;
	}

	public void setProxyIntervention(IBusinessIntervention proxyIntervention) {
		this.proxyIntervention = proxyIntervention;
	}

	public IBusinessDiagnostic getProxyDiagnostic() {
		return proxyDiagnostic;
	}

	public void setProxyDiagnostic(IBusinessDiagnostic proxyDiagnostic) {
		this.proxyDiagnostic = proxyDiagnostic;
	}

	public IBusinessAnomalie getProxyAnomalie() {
		return proxyAnomalie;
	}

	public void setProxyAnomalie(IBusinessAnomalie proxyAnomalie) {
		this.proxyAnomalie = proxyAnomalie;
	}

	public IBusinessArtisan getProxyArtisan() {
		return proxyArtisan;
	}

	public void setProxyArtisan(IBusinessArtisan proxyArtisan) {
		this.proxyArtisan = proxyArtisan;
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

	public IBusinessErp getProxyERP() {
		return proxyERP;
	}

	public void setProxyERP(IBusinessErp proxyERP) {
		this.proxyERP = proxyERP;
	}

	public List<Diagnostic> getListeDiagnosticERP() {
		return listeDiagnosticERP;
	}

	public void setListeDiagnosticERP(List<Diagnostic> listeDiagnosticERP) {
		this.listeDiagnosticERP = listeDiagnosticERP;
	}

	public int getNombreTotalIntervention() {
		return nombreTotalIntervention;
	}

	public void setNombreTotalIntervention(int nombreTotalIntervention) {
		this.nombreTotalIntervention = nombreTotalIntervention;
	}

	public int getNombreInterventionTerminees() {
		return nombreInterventionTerminees;
	}

	public void setNombreInterventionTerminees(int nombreInterventionTerminees) {
		this.nombreInterventionTerminees = nombreInterventionTerminees;
	}

	public int getNombreInterventionEnCours() {
		return nombreInterventionEnCours;
	}

	public void setNombreInterventionEnCours(int nombreInterventionEnCours) {
		this.nombreInterventionEnCours = nombreInterventionEnCours;
	}

	public int getNombreInterventionEnAttente() {
		return nombreInterventionEnAttente;
	}

	public void setNombreInterventionEnAttente(int nombreInterventionEnAttente) {
		this.nombreInterventionEnAttente = nombreInterventionEnAttente;
	}

	public int getNombreInterventionSuspendues() {
		return nombreInterventionSuspendues;
	}

	public void setNombreInterventionSuspendues(int nombreInterventionSuspendues) {
		this.nombreInterventionSuspendues = nombreInterventionSuspendues;
	}

	public double getCoutTotal() {
		return coutTotal;
	}

	public void setCoutTotal(double coutTotal) {
		this.coutTotal = coutTotal;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public int[][] getTableauNombreInterventions() {
		return tableauNombreInterventions;
	}

	public void setTableauNombreInterventions(int[][] tableauNombreInterventions) {
		this.tableauNombreInterventions = tableauNombreInterventions;
	}
}
