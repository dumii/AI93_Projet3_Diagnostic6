package fr.afcepf.ai93.diag6.controler.testElsa;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

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
	private List<Intervention> travaux;
	private Intervention intervention;
	private int idIntervention;
	
	private Intervention interventionAdd;
	
	//Initialisation de la liste d'interventions au chargement de la page
	@PostConstruct
	public void init()
	{
		travaux = proxyBusiness.recupereToutesIntervention();
	}
	
	//Recherche d'une intervention via son id
	public String rechercher()
	{
		intervention = proxyBusiness.recupereIntervention(idIntervention);
		return "";
	}
	
	public String ajouter()
	{
		//Comme il y a pas mal de clés étrangères dans la table intervention
		//on est obligé de créer les objets correspondants à ces clés pour ajouter
		//l'objet intervention dans la base de données
		
		Anomalie anom = new Anomalie();
		anom.setIdAnomalie(2);
		
		TypeIntervention type = new TypeIntervention();
		type.setIdTypeIntervention(2);
		
		Artisan artisan = new Artisan();
		artisan.setIdArtisan(2);
		
		EtatAvancementTravaux etat = new EtatAvancementTravaux();
		etat.setIdEtatAvancement(2);
		
		Date date1 = new Date();
		
		//Maintenant je créé mon instance intervention et je lui ajoute les attributs
		//On peut le faire sur une seule ligne mais je trouve ça plus propre comme ça
		interventionAdd.setAnomalie(anom);
		interventionAdd.setTypeIntervention(type);
		interventionAdd.setArtisan(artisan);
		interventionAdd.setEtatAvancementTravaux(etat);
		interventionAdd.setDateDebutIntervention(date1);
		interventionAdd.setDateFinIntervention(date1);
		interventionAdd.setCoutIntervention(2222.22);

		//On ajoute dans la base de données et on rafraîchit la liste d'interventions à l'écran
		proxyBusiness.ajouterIntervention(interventionAdd);
		init();
		
		return "";
	}
	
	public String modifier(Intervention intervention)
	{
		intervention.setCoutIntervention(5555.55);
		proxyBusiness.modifierIntervention(intervention);
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
}
