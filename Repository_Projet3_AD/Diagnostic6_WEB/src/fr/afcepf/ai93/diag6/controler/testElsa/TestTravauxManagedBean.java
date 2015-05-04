package fr.afcepf.ai93.diag6.controler.testElsa;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import fr.afcepf.ai93.diag6.api.business.autres.IBusinessArtisan;
import fr.afcepf.ai93.diag6.api.business.travaux.IBusinessIntervention;
import fr.afcepf.ai93.diag6.entity.autres.Artisan;
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
	private IBusinessArtisan proxyArtisan;
	private List<Intervention> travaux;
	private List<TypeIntervention> listeTypes;
	private List<EtatAvancementTravaux> listeEtats;
	private List<Artisan> listeArtisans;
	private Intervention intervention;
	private TypeIntervention type;
	private int idIntervention;
	private int idType;
	private int idEtat;
	private String resultat;
	private Date dateChoisie;
	
	private Intervention interventionAdd;
	
	//Initialisation de la liste d'interventions au chargement de la page
	@PostConstruct
	public void init()
	{
		travaux = proxyBusiness.recupereToutesIntervention();
		listeTypes = proxyBusiness.recupererTousTypesIntervention();
		listeEtats = proxyBusiness.recupererTousEtats();
		
		type = new TypeIntervention();
		type.setIdTypeIntervention(2);
		listeArtisans = proxyArtisan.recupererArtisansParTypeIntervention(type);
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
		//Comme il y a pas mal de cl�s �trang�res dans la table intervention
		//on est oblig� de cr�er les objets correspondants � ces cl�s pour ajouter
		//l'objet intervention dans la base de donn�es
		
		Anomalie anom = new Anomalie();
		anom.setIdAnomalie(4);
		
		TypeIntervention type = new TypeIntervention();
		type.setIdTypeIntervention(2);
		
		Artisan artisan = new Artisan();
		artisan.setIdArtisan(2);
		
		EtatAvancementTravaux etat = new EtatAvancementTravaux();
		etat.setIdEtatAvancement(2);
		
		Date date1 = new Date();
		
		//Maintenant je cr�� mon instance intervention et je lui ajoute les attributs
		//On peut le faire sur une seule ligne mais je trouve �a plus propre comme �a
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
			//On ajoute dans la base de donn�es et on rafra�chit la liste d'interventions � l'�cran
			resultat = proxyBusiness.ajouterIntervention(interventionAdd);			
		}
		catch (Exception e)
		{
			//Si un champ est null, qu'il y ait des caract�res inapropri�s dans les champs date ou co�t, une erreur sera g�n�r�e
			resultat="Erreur lors de l'enregistrement, veuillez v�rifier que toutes les valeurs sont correctement indiqu�es ou r�essayer dans quelques minutes.";
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
