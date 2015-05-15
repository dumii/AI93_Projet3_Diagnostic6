package fr.afcepf.ai93.diag6.data.travaux;

import java.util.ArrayList;
import java.util.ArrayList;

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
import fr.afcepf.ai93.diag6.entity.diagnostic.Anomalie;
import fr.afcepf.ai93.diag6.entity.erp.Erp;
import fr.afcepf.ai93.diag6.entity.travaux.EtatAvancementTravaux;
import fr.afcepf.ai93.diag6.entity.travaux.HistoriqueIntervention;
import fr.afcepf.ai93.diag6.entity.travaux.Intervention;
import fr.afcepf.ai93.diag6.entity.travaux.TypeIntervention;

@Stateless
@Remote(IDaoHistoriqueIntervention.class)
public class DaoHistoriqueInterventionImpl implements IDaoHistoriqueIntervention {

	

	private final static String MODIF_ETAT_AVANCEMENT = "Etat avancement travaux";
	private final static String MODIF_DATE_DEBUT = "Date début intervention";
	private final static String MODIF_DATE_FIN = "Date fin intervention";
	private final static String MODIF_COUT_INTERVENTION = "Coût intervention";

	private final static String MODIF_ARTISAN = "Artisan";
	private final static String MODIF_TYPE_INTERVENTION = "Type intervention";

	
	@PersistenceContext(unitName="Malak_Diag_Data")
	private EntityManager em;

	@Override
	public List<HistoriqueIntervention> recupereToutHistoriqueIntervention() {
		Query query = em.createQuery("SELECT h from HistoriqueIntervention h");
		List<HistoriqueIntervention> liste = query.getResultList();
		return liste;
	}
	
	
	///changer ici! (j'ai peut être fait une connerie en récupérant un ERP alors que seul son ID me suffit
	@Override
	public List<HistoriqueIntervention> recupereHistoriqueInterventionParERP(
			Erp erp) {
		
		int coucou = erp.getIdErp();
		
		Query req = em.createQuery("SELECT h.intervention.listeHistoriqueIntervention FROM Anomalie h WHERE h.diagnostic.erp.idErp = :id");
		req.setParameter("id", coucou);
		List<HistoriqueIntervention> liste = req.getResultList();	
		return liste;
		
		
		// ne fonctionne pas
		
		
		/*
		Query req = em.createQuery("SELECT h FROM "+
			"(((HistoriqueIntervention h "+
			"inner join fetch intervention "+
			"on h.idIntervention = intervention.idIntervention) "+
			"inner join Anomalie "+
			"on intervention.idAnomalie = anomalie.idAnomalie) "+
			"inner join fetch Diagnostic "+
			"on anomalie.idDiagnostic = diagnostic.idDiagnostic) "+
			"inner join fetch Erp "+
			"on diagnostic.idErp = erp.idErp "+
			"where erp.idErp = :id");
		req.setParameter("id", coucou);
		List<HistoriqueIntervention> liste = req.getResultList();	
		return liste;
		*/	
		/*
		//recuperation de
		Query reqERP = em.createQuery("select e.id from Erp e where e.id = :id");
		reqERP.setParameter("id", coucou);
		//recuperation des interventions ayant un historique
		Query reqHisto = em.createQuery("Select h.idIntervention from HistoriqueIntervention h");
		List<Integer> listeHisto = reqHisto.getResultList();
		//par chaque intervention, on fais une boucle
		List<Integer> listeAnom = new ArrayList();
		List<Integer> tmp = new ArrayList();
		for(Integer id : listeHisto)
		{
			//pour chaque intervention de la liste, on cherche les anomalies
			Query reqIntervention = em.createQuery("select i.idAnomalie from Intervention i where :id = i.idIntervention");
			reqIntervention.setParameter("id", id);
			tmp = reqIntervention.getResultList();
			// qu'on ajoute à une liste
			for (Integer lol : tmp)
			{
				listeAnom.add(lol);
			}
		}
		//pour chaque anomalie, on cherche le diagnostics
		List<Integer> listeDiag= new ArrayList();
		List<Integer> tampon = new ArrayList();
		for (Integer i : listeAnom)
		{
			Query reqAnom = em.createQuery("select a.idDiagnostic from Anomalie a where a.idAnomalie = :id");
			reqAnom.setParameter("id", i);
			tampon = reqAnom.getResultList();
			for (Integer ok : tampon)
			{
				listeDiag.add(ok);
			}
		}
		//pour chaque diagnostic on cherche l'erp
		List<Integer> listeErp = new ArrayList();
		List<Integer> wesh = new ArrayList();
		for (Integer d : listeDiag)
		{
			Query reqDiag =em.createQuery("select d.idErp from Diagnostic d where d.idDiagnostic = :id");
			reqDiag.setParameter("id", d);
			wesh = reqDiag.getResultList();
			for (Integer yo : listeErp)
			{
				listeErp.add(yo);
			}
		}
		
		Query req = em.createQuery("select e from Erp e where e.idErp = :id");
		req.setParameter("id", coucou);
		List<HistoriqueIntervention> list = req.getResultList();
		return list;*/
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
		
		if (dateDebutInitiale.compareTo(dateDebutNouvelle) != 0)
		{
			historique.setTypeModification(MODIF_DATE_DEBUT);
			historique.setAncienneDonnee(""+dateDebutInitiale);
			historique.setNouvelleDonnee(""+dateDebutNouvelle);
			em.persist(historique);
		}
		
		Date dateFinInitiale = interventionInitiale.getDateFinIntervention();
		Date dateFinNouvelle = intervention.getDateFinIntervention();
		
		if (dateFinInitiale.compareTo(dateFinNouvelle) != 0)
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
		
		int idArtisanInitial = interventionInitiale.getArtisan().getIdArtisan();
		System.out.println("id artisan 1 : " + idArtisanInitial);
		int idArtisanNouveau = intervention.getArtisan().getIdArtisan();
		System.out.println("id artisan 2 : " + idArtisanNouveau);
		
		if (idArtisanInitial != idArtisanNouveau)
		{
			System.out.println("entrée dans le if");
			historique.setTypeModification(MODIF_ARTISAN);
			historique.setAncienneDonnee(""+idArtisanInitial);
			historique.setNouvelleDonnee(""+idArtisanNouveau);
			em.persist(historique);
		}
		
		int idTypeInterventionInitial = interventionInitiale.getTypeIntervention().getIdTypeIntervention();
		int idTypeInterventionNouveau = intervention.getTypeIntervention().getIdTypeIntervention();
		
		if (idTypeInterventionInitial != idTypeInterventionNouveau)
		{
			historique.setTypeModification(MODIF_TYPE_INTERVENTION);
			historique.setAncienneDonnee(""+idTypeInterventionInitial);
			historique.setNouvelleDonnee(""+idTypeInterventionNouveau);
			em.persist(historique);
		}		
	}


}
