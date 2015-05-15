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
import fr.afcepf.ai93.diag6.entity.travaux.Intervention;
import fr.afcepf.ai93.diag6.entity.travaux.TypeIntervention;

@ManagedBean(name="mbPlanningTravaux")
@SessionScoped
public class PlanningTravauxManagedBean {

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
	private List<Diagnostic> listeDiagnosticERP;
	private List<Anomalie> listeAnomalieAvecInterventionERP;
	private List<Anomalie> listeAnomalieSansInterventionERP;
	private List<TypeIntervention> listeTypesERP;
	
	//Autres données
	private List<TypeIntervention> listeTousTypes;
	private List<Artisan> listeArtisans;
	private List<EtatAvancementTravaux> listeTousEtats;
	
	private SimpleDateFormat formater;
	private SimpleDateFormat shortFormater;
	
	private Date date1;
	private Date date2;
	private Date date3;
	private String moisAnnee;
	
	
	//Initialisation de la liste d'interventions au chargement de la page
	@PostConstruct
	public void init() throws ParseException
	{

	}
	
	//Sélection de l'ERP ou chargement de l'ERP en paramètres
	public void loadChantier() throws ParseException{
		String param = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
		if (param != null && !param.equals(""))
		{
			int idERP = Integer.parseInt(param);
			monERP = proxyERP.recupererErpParId(idERP);
			initialisationDesDonnees();
		}
	}
	
	//Méthodes
	public void initialisationDesDonnees() throws ParseException
	{
		formater = new SimpleDateFormat("dd/MM/yyyy");
		shortFormater = new SimpleDateFormat("dd/MM");
		
		déterminerDates();

		listeDiagnosticERP = proxyDiagnostic.recupereDiagnosticNonTraitesParErp(monERP);
		listeAnomalieAvecInterventionERP = new ArrayList<Anomalie>();
		listeAnomalieSansInterventionERP = new ArrayList<Anomalie>();
		listeTypesERP = new ArrayList<>();
		listeArtisans = proxyArtisan.recupererToutArtisan();
		listeTousEtats = proxyIntervention.recupererTousEtats();
		listeTousTypes = proxyIntervention.recupererTousTypesIntervention();
		
		chargerListeAnomalieEtIntervention();
		
		chargerTypesInterventionEtIntervention();
	}
	
	
	public String ajouterIntervention (Intervention intervention, Anomalie anom) throws ParseException
	{
		System.out.println("tot");
		System.out.println("intervention : " + intervention);
		
		if (intervention.getCoutIntervention() != 0)
		{
			if (intervention.getDateDebutIntervention() != null)
			{
				if (intervention.getDateFinIntervention() != null)
				{
					intervention.setAnomalie(anom);
					proxyIntervention.ajouterIntervention(intervention, anom);
					initialisationDesDonnees();
				}
			}
		}	
		
		return "";
	}
	
	public String modifier(Intervention intervention) throws ParseException
	{
		Utilisateur user = new Utilisateur();
		user.setIdUtilisateur(2);
		proxyIntervention.modifierIntervention(intervention, user);
		init();
		
		return "";
	}
	
	public void chargerTypesInterventionEtIntervention() {
		
		for (TypeIntervention type : listeTousTypes)
		{
			List<Intervention> liste = new ArrayList<>();

			int idType = type.getIdTypeIntervention();

			for (Anomalie anom : listeAnomalieAvecInterventionERP)
			{
				int idTypeIntervention = anom.getIntervention().getTypeIntervention().getIdTypeIntervention();

				if (idTypeIntervention == idType)
				{
					liste.add(anom.getIntervention());
				}
			}

			if (liste.size() > 0)
			{
				listeTypesERP.add(type);
				type.setListeInterventionTypeIntervention(liste);
			}
		}		
	}

	public void chargerListeAnomalieEtIntervention() {

		for (Diagnostic diag : listeDiagnosticERP)
		{
			diag.setListeAnomaliesDiagnostic(proxyAnomalie.recupereAnomalieParDiagnostic(diag.getIdDiagnostic()));

			for (Anomalie anom : diag.getListeAnomaliesDiagnostic())
			{
				//Liste 1 : recherche d'intervention(s) sur l'anomalie, le nombre d'intervention dans la liste peut varier de 0 à *

				List<Intervention> liste1 = new ArrayList<>();
				liste1 = proxyIntervention.rechercherInterventionSurAnomalie(anom.getIdAnomalie());
				
				if (liste1.size() != 0)
				{
					//Au moins une intervention est enregistrée sur cette anomalie
					listeAnomalieAvecInterventionERP.add(anom);
					Intervention intervention = liste1.get(0);
					
					anom.setIntervention(intervention);
					anom.getIntervention().setEtatAvancementTravaux(proxyIntervention.recupererEtatParIntervention(anom.getIntervention()));
					anom.getIntervention().setArtisan(proxyArtisan.recupererArtisansParIntervention(anom.getIntervention()));
					anom.getIntervention().setTypeIntervention(proxyIntervention.recupererTypeParIntervention(anom.getIntervention()));
				}
				else
				{
					//Aucune intervention d'enregistrée sur cette anomalie
					listeAnomalieSansInterventionERP.add(anom);
					
					anom.setIntervention(new Intervention());
					anom.getIntervention().setEtatAvancementTravaux(listeTousEtats.get(0));
					anom.getIntervention().setArtisan(listeArtisans.get(0));
					anom.getIntervention().setTypeIntervention(listeTousTypes.get(0));
				}
			}
		}		
	}

	public void déterminerDates() throws ParseException {

		Calendar c = Calendar.getInstance();
		int mois = c.get(Calendar.MONTH)+1;
		int annee = c.get(Calendar.YEAR);
		moisAnnee = getMonth(mois)+" "+annee;

		c.setTime(new Date());

		if (c.DATE >= 15)
		{
			c.set(Calendar.DAY_OF_MONTH, 15);
			date1 = c.getTime();

			c.set(Calendar.DAY_OF_MONTH, 7);
			date2 = c.getTime();

			c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
			date3 = c.getTime();
		}
		else
		{
			c.set(Calendar.DAY_OF_MONTH, 1);
			date1 = c.getTime();

			c.set(Calendar.DAY_OF_MONTH, 7);
			date2 = c.getTime();

			c.set(Calendar.DAY_OF_MONTH, 15);
			date3 = c.getTime();
		}
	}

	public String getMonth (int month)
	{
		String monthString;

		switch (month) {
		case 1:  monthString = "Janvier";      break;
		case 2:  monthString = "Février";      break;
		case 3:  monthString = "Mars";         break;
		case 4:  monthString = "Avril";         break;
		case 5:  monthString = "Mai";           break;
		case 6:  monthString = "Juin";          break;
		case 7:  monthString = "Juillet";          break;
		case 8:  monthString = "Août";        break;
		case 9:  monthString = "Septembre";     break;
		case 10: monthString = "Octobre";       break;
		case 11: monthString = "Novembre";      break;
		case 12: monthString = "Décembre";      break;
		default: monthString = "Invalid month"; break;
		}

		return monthString;
	}

	public String localisationAnomalie(Anomalie a){
		if(a.getPiece() != null)
		{
			return "Etage " + a.getPiece().getEtage().getNumeroEtage() + " - " + a.getPiece().getEtage().getNomEtage()+ "<br />" 
					+ "Pièce " + a.getPiece().getNumeroPiece()+ " - " + a.getPiece().getFonctionPiece().getLibelleFonctionPiece()+ " - " + a.getPiece().getDenominationPiece(); 
		}
		else
		{
			if(a.getVoirie() !=null)
			{
				return a.getVoirie().getTypeVoirie().getLibelleTypeVoirie()+ "<br />" +
						a.getVoirie().getDesignationVoirie()+ " - " + a.getVoirie().getIntituleVoirie(); 
			}
			else
			{
				if(a.getAcces() != null)
				{
					//return "Bâtiment "+a.getAcces().getBatiment().getNumBatiment()+ "<br />" +
					//"Accès " + a.getAcces().getTypeAcces().getLibelleTypeAcces(); 
					return "Accès " + a.getAcces().getTypeAcces().getLibelleTypeAcces(); 
				}
				else
				{
					if(a.getEscalier() != null){
						//return "Bâtiment "+a.getAcces().getBatiment().getNumBatiment()+ "<br />" +
						//"Escalier "+a.getEscalier().getDenominationEscalier();
						return "Escalier "+a.getEscalier().getDenominationEscalier();
					}
					else
					{
						if(a.getAscenceur() !=null)
						{
							//return "Bâtiment "+a.getAcces().getBatiment().getNumBatiment()+ "<br />" +
							//"Ascenceur "+a.getAscenceur().getDenominationAscenceur(); 
							return "Ascenceur "+a.getAscenceur().getDenominationAscenceur(); 
						}
						else {
							return "Localisation non définie"; 
						}
					}
				}
			}
		}
	}

	public List<Artisan> avoirListeArtisans(TypeIntervention typeInter)
	{
		return proxyArtisan.recupererArtisansParTypeIntervention(typeInter);
	}
	
	public List<Artisan> changerListeArtisans(int idtypeIntervention)
	{
		TypeIntervention type = proxyIntervention.recupererTypeParID(idtypeIntervention);
		return proxyArtisan.recupererArtisansParTypeIntervention(type);
	}

	public List<EtatAvancementTravaux> listeEtatDisponiblesParIntervention(Intervention intervention)
	{
		return proxyIntervention.recupererEtatDisponibles(intervention);
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

	public List<Anomalie> getListeAnomalieAvecInterventionERP() {
		return listeAnomalieAvecInterventionERP;
	}

	public void setListeAnomalieAvecInterventionERP(
			List<Anomalie> listeAnomalieAvecInterventionERP) {
		this.listeAnomalieAvecInterventionERP = listeAnomalieAvecInterventionERP;
	}

	public List<Anomalie> getListeAnomalieSansInterventionERP() {
		return listeAnomalieSansInterventionERP;
	}

	public void setListeAnomalieSansInterventionERP(
			List<Anomalie> listeAnomalieSansInterventionERP) {
		this.listeAnomalieSansInterventionERP = listeAnomalieSansInterventionERP;
	}

	public List<TypeIntervention> getListeTypesERP() {
		return listeTypesERP;
	}

	public void setListeTypesERP(List<TypeIntervention> listeTypesERP) {
		this.listeTypesERP = listeTypesERP;
	}

	public List<TypeIntervention> getListeTousTypes() {
		return listeTousTypes;
	}

	public void setListeTousTypes(List<TypeIntervention> listeTousTypes) {
		this.listeTousTypes = listeTousTypes;
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

	public Date getDate1() {
		return date1;
	}

	public void setDate1(Date date1) {
		this.date1 = date1;
	}

	public Date getDate2() {
		return date2;
	}

	public void setDate2(Date date2) {
		this.date2 = date2;
	}

	public Date getDate3() {
		return date3;
	}

	public void setDate3(Date date3) {
		this.date3 = date3;
	}

	public String getMoisAnnee() {
		return moisAnnee;
	}

	public void setMoisAnnee(String moisAnnee) {
		this.moisAnnee = moisAnnee;
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

	public List<Artisan> getListeArtisans() {
		return listeArtisans;
	}

	public void setListeArtisans(List<Artisan> listeArtisans) {
		this.listeArtisans = listeArtisans;
	}

	public List<EtatAvancementTravaux> getListeTousEtats() {
		return listeTousEtats;
	}

	public void setListeTousEtats(List<EtatAvancementTravaux> listeTousEtats) {
		this.listeTousEtats = listeTousEtats;
	}


}
