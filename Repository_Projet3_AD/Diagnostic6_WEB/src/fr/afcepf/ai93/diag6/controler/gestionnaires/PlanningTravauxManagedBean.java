package fr.afcepf.ai93.diag6.controler.gestionnaires;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import fr.afcepf.ai93.diag6.api.business.autres.IBusinessArtisan;
import fr.afcepf.ai93.diag6.api.business.diagnostic.IBusinessAnomalie;
import fr.afcepf.ai93.diag6.api.business.travaux.IBusinessIntervention;
import fr.afcepf.ai93.diag6.entity.autres.Artisan;
import fr.afcepf.ai93.diag6.entity.diagnostic.Anomalie;
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
	private IBusinessAnomalie proxyAnomalie;	
	@EJB
	private IBusinessArtisan proxyArtisan;
	
	//La liste des types d'interventions et des anomalies par interventions
	private List<TypeIntervention> listeTypesInterventions;
	
	//L'ERP concerné par le chantier
	private Erp erp;
	
	/*
	 * Ce dont on a besoin :
	 * ***la liste des anomalies sans interventions
	 * ***la liste des anomalies avec interventions par type d'intervention (on part du type d'intervention pour obtenir la liste d'anomalie
	 *    sur l'erp concerné)
	 */	
	
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
		
	private Intervention interventionAdd;
	
	//Initialisation ddes données au chargement de la page
	@PostConstruct
	public void init()
	{
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
			}
		}
		listeEtats = proxyIntervention.recupererTousEtats();
		listeAnomalies = proxyAnomalie.recupereToutAnomalie();
	}
	
	public String rechercheInterventionParAnomalie()
	{
		travaux = proxyIntervention.rechercherInterventionSurAnomalie(idAnomalie);
		
		type = new TypeIntervention();
		type.setIdTypeIntervention(2);
		listeArtisans = proxyArtisan.recupererArtisansParTypeIntervention(type);
		return "";
	}
	
	public String avoirListeArtisansParType()
	{
		type.setIdTypeIntervention(idType);
		listeArtisans = proxyArtisan.recupererArtisansParTypeIntervention(type);
		return "";
	}
		
	public String ajouterIntervention()
	{
		//Anomalie à récupérer dans la liste des anomalies sans intervention
		Anomalie anom = new Anomalie();
		anom.setIdAnomalie(4);
		
		TypeIntervention type = new TypeIntervention();
		type.setIdTypeIntervention(2);
		
		Artisan artisan = new Artisan();
		artisan.setIdArtisan(2);
		
		EtatAvancementTravaux etat = new EtatAvancementTravaux();
		etat.setIdEtatAvancement(2);
		
		//Dates sélectionnées dans des dateTimePicker
		Date dateDebut = new Date();
		
		//Maintenant je créé mon instance intervention et je lui ajoute les attributs
		//On peut le faire sur une seule ligne mais je trouve ça plus propre comme ça
		interventionAdd = new Intervention();
		interventionAdd.setAnomalie(anom);
		interventionAdd.setTypeIntervention(type);
		interventionAdd.setArtisan(artisan);
		interventionAdd.setEtatAvancementTravaux(etat);
		interventionAdd.setDateDebutIntervention(dateDebut);
		interventionAdd.setDateFinIntervention(dateChoisie);
		interventionAdd.setCoutIntervention(2222.22);

		try
		{
			//On ajoute dans la base de données et on rafraîchit la liste d'interventions à l'écran
			resultat = proxyIntervention.ajouterIntervention(interventionAdd);			
		}
		catch (Exception e)
		{
			//Si un champ est null, qu'il y ait des caractères inapropriés dans les champs date ou coût, une erreur sera générée
			resultat="Erreur lors de l'enregistrement, veuillez vérifier que toutes les valeurs sont correctement indiquées ou réessayer dans quelques minutes.";
		}
		init();
		return "";
	}
	
	public String modifierIntervention(Intervention intervention)
	{
		intervention.setCoutIntervention(5555.55);
		//proxyBusiness.modifierIntervention(intervention);
		init();
		
		return "";
	}
	
}
