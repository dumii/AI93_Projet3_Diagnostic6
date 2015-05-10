package fr.afcepf.ai93.diag6.controler.testElsa;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.jboss.resteasy.util.DateUtil;

import fr.afcepf.ai93.diag6.api.business.diagnostic.IBusinessAnomalie;
import fr.afcepf.ai93.diag6.api.business.autres.IBusinessArtisan;
import fr.afcepf.ai93.diag6.api.business.travaux.IBusinessIntervention;
import fr.afcepf.ai93.diag6.entity.autres.Artisan;
import fr.afcepf.ai93.diag6.entity.autres.Utilisateur;
import fr.afcepf.ai93.diag6.entity.diagnostic.Anomalie;
import fr.afcepf.ai93.diag6.entity.travaux.EtatAvancementTravaux;
import fr.afcepf.ai93.diag6.entity.travaux.Intervention;
import fr.afcepf.ai93.diag6.entity.travaux.TypeIntervention;

@ManagedBean(name="mbTestTravaux")
@SessionScoped
public class TestTravauxManagedBean {

	@EJB
	private IBusinessIntervention proxyIntervention;
	@EJB
	private IBusinessAnomalie proxyAnomalie;	
	@EJB
	private IBusinessArtisan proxyArtisan;
	
	private List<Intervention> travaux;
	private List<TypeIntervention> listeTypes;
	private List<EtatAvancementTravaux> listeEtats;
	private List<Anomalie> listeAnomalies;
	private List<Artisan> listeArtisans;
	private Intervention intervention;
	private TypeIntervention type;
	private int idIntervention;
	private int idType;
	private int idEtat;
	private int idAnomalie;
	private String resultat;
	
	private Date dateChoisie;
	
	private SimpleDateFormat formater;
	private SimpleDateFormat shortFormater;
	
	private Date dateDuJour;
	private Date date1;
	private Date date2;
	private Date date3;
	private String mois;
	private String moisAnnee;
		
	private Intervention interventionAdd;
	
	//Initialisation de la liste d'interventions au chargement de la page
	@PostConstruct
	public void init() throws ParseException
	{
		déterminerDates();

		formater = new SimpleDateFormat("dd/MM/yyyy");
		shortFormater = new SimpleDateFormat("dd/MM");
		
		travaux = proxyIntervention.recupereToutesIntervention();
		listeTypes = proxyIntervention.recupererTousTypesIntervention();
		for (TypeIntervention t : listeTypes)
		{
			t.setListeInterventionTypeIntervention(proxyIntervention.recupereInterventionParType(t));
			
			for (Intervention i : t.getListeInterventionTypeIntervention())
			{
				i.setAnomalie(proxyAnomalie.recupereAnomalie(i.getAnomalie().getIdAnomalie()));
				i.setEtatAvancementTravaux(proxyIntervention.recupererEtatParIntervention(i));
				i.setArtisan(proxyArtisan.recupererArtisansParIntervention(i));
				i.setTypeIntervention(proxyIntervention.recupererTypeParIntervention(i));
			}
		}
		listeEtats = proxyIntervention.recupererTousEtats();
		listeAnomalies = proxyAnomalie.recupereToutAnomalie();
		listeArtisans = proxyArtisan.recupererToutArtisan();
	}
	
	public void testModifier(Intervention item) throws ParseException
	{
		System.out.println("ID : " + item.getIdIntervention());
		System.out.println("Cout : " + item.getCoutIntervention());
		String test = formater.format(item.getDateDebutIntervention());
		Date dateTest = formater.parse(test);
		System.out.println("Date test : " + dateTest);
		System.out.println("Date début : " + item.getDateDebutIntervention());
		System.out.println("Date fin : " + item.getDateFinIntervention());
		System.out.println("Type intervention : " + item.getTypeIntervention().getIdTypeIntervention());
		System.out.println("Artisan : " + item.getArtisan().getIdArtisan());
		System.out.println("Type etat avancement : " + item.getEtatAvancementTravaux().getIdEtatAvancement());
	}
	
	private void déterminerDates() throws ParseException {
		
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
	
	
	public String rechercheInterventionParAnomalie()
	{
		travaux = proxyIntervention.rechercherInterventionSurAnomalie(idAnomalie);
		
		type = new TypeIntervention();
		type.setIdTypeIntervention(2);
		listeArtisans = proxyArtisan.recupererArtisansParTypeIntervention(type);
		return "";
	}
	
	public List<Artisan> avoirListeArtisans(TypeIntervention typeInter)
	{
		return proxyArtisan.recupererArtisansParTypeIntervention(typeInter);
	}
	
	//Recherche d'une intervention via son id
	public String rechercher()
	{
		intervention = proxyIntervention.recupereIntervention(idIntervention);		
		resultat = "";
		return "";
	}
	
	public String ajouter() throws ParseException
	{
		//Comme il y a pas mal de clés étrangères dans la table intervention
		//on est obligé de créer les objets correspondants à ces clés pour ajouter
		//l'objet intervention dans la base de données
		
		Anomalie anom = new Anomalie();
		anom.setIdAnomalie(4);
		
		TypeIntervention type = new TypeIntervention();
		type.setIdTypeIntervention(2);
		
		Artisan artisan = new Artisan();
		artisan.setIdArtisan(2);
		
		EtatAvancementTravaux etat = new EtatAvancementTravaux();
		etat.setIdEtatAvancement(2);
		
		Date date1 = new Date();
		
		//Maintenant je créé mon instance intervention et je lui ajoute les attributs
		//On peut le faire sur une seule ligne mais je trouve ça plus propre comme ça
		interventionAdd = new Intervention();
		interventionAdd.setAnomalie(anom);
		interventionAdd.setTypeIntervention(type);
		interventionAdd.setArtisan(artisan);
		interventionAdd.setEtatAvancementTravaux(etat);
		interventionAdd.setDateDebutIntervention(date1);
		interventionAdd.setDateFinIntervention(dateChoisie);
		interventionAdd.setCoutIntervention(2222.22);

		try
		{
			//On ajoute dans la base de données et on rafraîchit la liste d'interventions à l'écran
			resultat = proxyIntervention.ajouterIntervention(interventionAdd, anom);			
		}
		catch (Exception e)
		{
			//Si un champ est null, qu'il y ait des caractères inapropriés dans les champs date ou coût, une erreur sera générée
			resultat="Erreur lors de l'enregistrement, veuillez vérifier que toutes les valeurs sont correctement indiquées ou réessayer dans quelques minutes.";
		}
		init();
		System.out.println("*****************************************************************************");
		System.err.println(resultat);
		System.out.println("*****************************************************************************");
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
	
	public List<EtatAvancementTravaux> listeEtatDisponiblesParIntervention(Intervention intervention)
	{
		return proxyIntervention.recupererEtatDisponibles(intervention);
	}
	
	public IBusinessIntervention getProxyBusiness() {
		return proxyIntervention;
	}
	public void setProxyBusiness(IBusinessIntervention proxyBusiness) {
		this.proxyIntervention = proxyBusiness;
	}
	public List<Intervention> getTravaux() {
		return travaux;
	}
	public void setTravaux(List<Intervention> travaux) {
		this.travaux = travaux;
	}
	public Intervention getIntervention() {
		return intervention;
	}
	public void setIntervention(Intervention intervention) {
		this.intervention = intervention;
	}
	public int getIdIntervention() {
		return idIntervention;
	}
	public void setIdIntervention(int idIntervention) {
		this.idIntervention = idIntervention;
	}

	public Intervention getInterventionAdd() {
		return interventionAdd;
	}

	public void setInterventionAdd(Intervention interventionAdd) {
		this.interventionAdd = interventionAdd;
	}

	public String getResultat() {
		return resultat;
	}

	public void setResultat(String resultat) {
		this.resultat = resultat;
	}

	public Date getDateChoisie() {
		return dateChoisie;
	}

	public void setDateChoisie(Date dateChoisie) {
		this.dateChoisie = dateChoisie;
	}

	public List<TypeIntervention> getListeTypes() {
		return listeTypes;
	}

	public void setListeTypes(List<TypeIntervention> listeTypes) {
		this.listeTypes = listeTypes;
	}

	public int getIdType() {
		return idType;
	}

	public void setIdType(int idType) {
		this.idType = idType;
	}

	public List<EtatAvancementTravaux> getListeEtats() {
		return listeEtats;
	}

	public void setListeEtats(List<EtatAvancementTravaux> listeEtats) {
		this.listeEtats = listeEtats;
	}

	public int getIdEtat() {
		return idEtat;
	}

	public void setIdEtat(int idEtat) {
		this.idEtat = idEtat;
	}

	public int getIdAnomalie() {
		return idAnomalie;
	}

	public IBusinessAnomalie getProxyAnomalie() {
		return proxyAnomalie;
	}

	public void setProxyAnomalie(IBusinessAnomalie proxyAnomalie) {
		this.proxyAnomalie = proxyAnomalie;
	}

	public List<Anomalie> getListeAnomalies() {
		return listeAnomalies;
	}

	public void setListeAnomalies(List<Anomalie> listeAnomalies) {
		this.listeAnomalies = listeAnomalies;
	}

	public void setIdAnomalie(int idAnomalie) {
		this.idAnomalie = idAnomalie;
	}
	
	public IBusinessArtisan getProxyArtisan() {
		return proxyArtisan;
	}

	public void setProxyArtisan(IBusinessArtisan proxyArtisan) {
		this.proxyArtisan = proxyArtisan;
	}

	public List<Artisan> getListeArtisans() {
		return listeArtisans;
	}

	public void setListeArtisans(List<Artisan> listeArtisans) {
		this.listeArtisans = listeArtisans;
	}

	public TypeIntervention getType() {
		return type;
	}

	public void setType(TypeIntervention type) {
		this.type = type;
	}

	public IBusinessIntervention getProxyIntervention() {
		return proxyIntervention;
	}

	public void setProxyIntervention(IBusinessIntervention proxyIntervention) {
		this.proxyIntervention = proxyIntervention;
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

	public Date getDateDuJour() {
		return dateDuJour;
	}

	public void setDateDuJour(Date dateDuJour) {
		this.dateDuJour = dateDuJour;
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

	public String getMois() {
		return mois;
	}

	public void setMois(String mois) {
		this.mois = mois;
	}

	public String getMoisAnnee() {
		return moisAnnee;
	}

	public void setMoisAnnee(String moisAnnee) {
		this.moisAnnee = moisAnnee;
	}
}
