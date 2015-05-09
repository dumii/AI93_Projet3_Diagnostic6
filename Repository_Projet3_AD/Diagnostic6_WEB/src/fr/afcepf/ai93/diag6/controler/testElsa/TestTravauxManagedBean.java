package fr.afcepf.ai93.diag6.controler.testElsa;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import fr.afcepf.ai93.diag6.api.business.diagnostic.IBusinessAnomalie;
import fr.afcepf.ai93.diag6.api.business.autres.IBusinessArtisan;
import fr.afcepf.ai93.diag6.api.business.travaux.IBusinessIntervention;
import fr.afcepf.ai93.diag6.controler.autres.UtilisateurManagedBean;
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
	private IBusinessIntervention proxyBusiness;
	@EJB
	private IBusinessAnomalie proxyAnomalie;
	
	//récupération de la valeur "login" de la session utilisateur 
	@ManagedProperty(value="#{mbUtilisateur}")
	private UtilisateurManagedBean mbUtilisateur;

	private String login;

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
	

	private Intervention interventionAdd;
	
	//Initialisation de la liste d'interventions au chargement de la page
	@PostConstruct
	public void init()
	{
		login = mbUtilisateur.getUtilisateur().getLoginUtilisateur();
		
		travaux = proxyBusiness.recupereToutesIntervention();
		listeTypes = proxyBusiness.recupererTousTypesIntervention();
		for (TypeIntervention t : listeTypes)
		{
			t.setListeInterventionTypeIntervention(proxyBusiness.recupereInterventionParType(t));
		}
		listeEtats = proxyBusiness.recupererTousEtats();
		listeAnomalies = proxyAnomalie.recupereToutAnomalie();
	}
	
	public String rechercheInterventionParAnomalie()
	{
		travaux = proxyBusiness.rechercherInterventionSurAnomalie(idAnomalie);
		
		type = new TypeIntervention();
		type.setIdTypeIntervention(2);
		listeArtisans = proxyArtisan.recupererArtisansParTypeIntervention(type);
		return "";
	}
	
	public String avoirListeArtisans()
	{
		type.setIdTypeIntervention(idType);
		System.out.println("ManagedBean, idType : " + idType);
		listeArtisans = proxyArtisan.recupererArtisansParTypeIntervention(type);
		return "";
	}
	
	//Recherche d'une intervention via son id
	public String rechercher()
	{
		intervention = proxyBusiness.recupereIntervention(idIntervention);		
		resultat = "";
		return "";
	}
	
	public String ajouter()
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
			resultat = proxyBusiness.ajouterIntervention(interventionAdd);			
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
	
	public String modifier(Intervention intervention)
	{
		intervention.setCoutIntervention(5555.55);
		//proxyBusiness.modifierIntervention(intervention);
		init();
		
		return "";
	}
	
	public IBusinessIntervention getProxyBusiness() {
		return proxyBusiness;
	}
	public void setProxyBusiness(IBusinessIntervention proxyBusiness) {
		this.proxyBusiness = proxyBusiness;
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
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public UtilisateurManagedBean getMbUtilisateur() {
		return mbUtilisateur;
	}

	public void setMbUtilisateur(UtilisateurManagedBean mbUtilisateur) {
		this.mbUtilisateur = mbUtilisateur;
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
	

}
