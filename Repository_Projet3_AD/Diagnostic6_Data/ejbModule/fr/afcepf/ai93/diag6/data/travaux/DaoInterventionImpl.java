package fr.afcepf.ai93.diag6.data.travaux;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.afcepf.ai93.diag6.api.data.diagnostic.IDaoDiagnostic;
import fr.afcepf.ai93.diag6.api.data.travaux.IDaoIntervention;
import fr.afcepf.ai93.diag6.entity.autres.Utilisateur;
import fr.afcepf.ai93.diag6.entity.travaux.Intervention;

public class DaoInterventionImpl implements IDaoIntervention{

	@PersistenceContext(unitName="Malak_Diag_Data")
	private EntityManager em;

	@Override
	public List<Intervention> recupereToutesIntervention() {
		Query query = em.createQuery("SELECT i FROM Intervention i");
		List <Intervention> liste = query.getResultList();
		return liste;
	}

	@Override
	public void ajouterIntervention(Intervention intervention) {
		em.persist(intervention);

	}

	@Override
	public boolean modifierIntervention(Intervention intervention) {
		em.merge(intervention);
		return true;
	}

	@Override
	public Intervention recupereIntervention(int idIntervention) {
		Query query = em.createQuery("SELECT i FROM Intervention i WHERE i.idIntervention = :pid");
		query.setParameter("pid", idIntervention);
		Intervention intervention =(Intervention) query.getSingleResult();
		return intervention;
	}

	@Override
	public List<Intervention> rechercheInterventions(String nomIntervention) {
		Query query = em.createQuery("SELECT i FROM Intervention i");
		List <Intervention> liste = query.getResultList();
		return liste;
	}

	
}

