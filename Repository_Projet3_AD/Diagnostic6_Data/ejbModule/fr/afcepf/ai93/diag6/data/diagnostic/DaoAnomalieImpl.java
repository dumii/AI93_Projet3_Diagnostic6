package fr.afcepf.ai93.diag6.data.diagnostic;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.afcepf.ai93.diag6.api.data.diagnostic.IDaoAnomalie;
import fr.afcepf.ai93.diag6.entity.diagnostic.Anomalie;

@Stateless
@Remote(IDaoAnomalie.class)
public class DaoAnomalieImpl implements IDaoAnomalie {

	@PersistenceContext(unitName="Malak_Diag_Data")
	private EntityManager em;

	@Override
	public List<Anomalie> recupereToutAnomalie() {
		Query query = em.createQuery("SELECT a FROM Anomalie a");
		List<Anomalie> liste = query.getResultList();
		return liste;
	}

	@Override
	public void ajouterAnomalie(Anomalie anomalie) {
		em.persist(anomalie);
	}

	@Override
	public void modifierAnomalie(Anomalie anomalie) {
		em.merge(anomalie);
	}

	@Override
	public Anomalie recupereAnomalie(int idAnomalie) {
		Query query = em.createQuery("SELECT a FROM Anomalie WHERE a.id = :pid");
		query.setParameter("pid", idAnomalie);
		Anomalie anomalie = (Anomalie) query.getSingleResult();
		return anomalie;
	}

	@Override
	public List<Anomalie> rechercheAnomaliesErp(String nomERP) {
		Query query = em.createQuery("SELECT a FROM Anomalie WHERE a.id = :pid");
		query.setParameter("pid", nomERP);
		List<Anomalie> liste = query.getResultList();
		
		if (liste.size() > 0) {
			return null;
		}
		else {
			return null;
		// je me suis inspiré de ce qu'a fait Elsa pour la rechercheInterventionSurAnomalie()
		// mais je ne suis pas sur de mon coup ;)
		}
	}

	@Override
	public void historiserAnomalie(Anomalie anomalie) {
		// l'historisation est automatique, la methode n'est pas utile
	}

	@Override
	public boolean supprimerAnomalie(Anomalie anomalie) {
		em.merge(anomalie);
		return true;
	}

	@Override
	public List<Anomalie> rechercheAnomalies(String nomAnomalie) {
		Query query = em.createQuery("SELECT a FROM Anomalie a Where a.nomAnomalie = :pid");
		query.setParameter("pid", nomAnomalie);
		List <Anomalie> liste = query.getResultList();
		return liste;
	}
}

