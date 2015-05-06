package fr.afcepf.ai93.diag6.data.travaux;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.afcepf.ai93.diag6.api.data.travaux.IDaoAvancementIntervention;
import fr.afcepf.ai93.diag6.api.data.travaux.IDaoHistoriqueIntervention;
import fr.afcepf.ai93.diag6.api.data.travaux.IDaoIntervention;
import fr.afcepf.ai93.diag6.api.data.travaux.IDaoTypeIntervention;
import fr.afcepf.ai93.diag6.entity.autres.Utilisateur;
import fr.afcepf.ai93.diag6.entity.travaux.EtatAvancementTravaux;
import fr.afcepf.ai93.diag6.entity.travaux.HistoriqueIntervention;
import fr.afcepf.ai93.diag6.entity.travaux.Intervention;
import fr.afcepf.ai93.diag6.entity.travaux.TypeIntervention;

@Stateless
@Remote(IDaoHistoriqueIntervention.class)
public class DaoHistoriqueImpl implements IDaoHistoriqueIntervention {

	
	private final static String MODIF_ETAT_AVANCEMENT = "etatAvancementTravaux";
	private final static String MODIF_DATE_DEBUT = "dateDebutIntervention";
	private final static String MODIF_DATE_FIN = "dateFinIntervention";
	private final static String MODIF_COUT_INTERVENTION = "coutIntervention";
	
	@PersistenceContext(unitName="Malak_Diag_Data")
	private EntityManager em;

	@Override
	public List<HistoriqueIntervention> recupereToutHistoriqueIntervention() {
		Query query = em.createQuery("SELECT h from HistoriqueIntervention h");
		List<HistoriqueIntervention> liste = query.getResultList();
		return liste;
	}

	@Override
	public void historiser(Intervention interventionInitiale,
			Intervention intervention, Utilisateur user) {
		HistoriqueIntervention historique = new HistoriqueIntervention();
		historique.setIntervention(intervention);
		historique.setUtilisateur(user);
		historique.setDateModification(new Date());
		
		int etatInitial = interventionInitiale.getEtatAvancementTravaux().getIdEtatAvancement();
		int etatNouveau = intervention.getEtatAvancementTravaux().getIdEtatAvancement();
		
		if (etatInitial != etatNouveau)
		{
			historique.setTypeModification(MODIF_ETAT_AVANCEMENT);
			historique.setAncienneDonnee(""+etatInitial);
			historique.setNouvelleDonnee(""+etatNouveau);
			em.persist(historique);
		}
		
		Date dateDebutInitiale = interventionInitiale.getDateDebutIntervention();
		Date dateDebutNouvelle = intervention.getDateDebutIntervention();
		
		if (dateDebutInitiale != dateDebutNouvelle)
		{
			historique.setTypeModification(MODIF_DATE_DEBUT);
			historique.setAncienneDonnee(""+dateDebutInitiale);
			historique.setNouvelleDonnee(""+dateDebutNouvelle);
			em.persist(historique);
		}
		
		Date dateFinInitiale = interventionInitiale.getDateFinIntervention();
		Date dateFinNouvelle = intervention.getDateFinIntervention();
		
		if (dateFinInitiale != dateFinNouvelle)
		{
			historique.setTypeModification(MODIF_DATE_FIN);
			historique.setAncienneDonnee(""+dateFinInitiale);
			historique.setNouvelleDonnee(""+dateFinNouvelle);
			em.persist(historique);
		}
		
		double coutInitialIntervention = interventionInitiale.getCoutIntervention();
		double coutNouveauIntervention = intervention.getCoutIntervention();
		
		if (coutInitialIntervention != coutNouveauIntervention)
		{
			historique.setTypeModification(MODIF_COUT_INTERVENTION);
			historique.setAncienneDonnee(""+coutInitialIntervention);
			historique.setNouvelleDonnee(""+coutNouveauIntervention);
			em.persist(historique);
		}
	}
}
