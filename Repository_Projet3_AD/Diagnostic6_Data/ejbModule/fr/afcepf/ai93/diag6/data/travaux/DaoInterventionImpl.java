package fr.afcepf.ai93.diag6.data.travaux;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.afcepf.ai93.diag6.api.data.diagnostic.IDaoDiagnostic;
import fr.afcepf.ai93.diag6.api.data.travaux.IDaoIntervention;
import fr.afcepf.ai93.diag6.entity.autres.Utilisateur;
import fr.afcepf.ai93.diag6.entity.travaux.Intervention;
import fr.afcepf.ai93.diag6.entity.travaux.TypeIntervention;

@Stateless
@Remote(IDaoIntervention.class)
public class DaoInterventionImpl implements IDaoIntervention {

	@PersistenceContext(unitName="Malak_Diag_Data")
	private EntityManager em;
	
	@Override
	public List<Intervention> recupereToutesIntervention() {
		Query query = em.createQuery("SELECT e from Intervention e");
		List<Intervention> liste = query.getResultList();
		return liste;
	}

	@Override
	public void ajouterIntervention(Intervention intervention) {
		em.persist(intervention);
	}

	@Override
	public boolean modifierIntervention(Intervention intervention) {
		// Merges changes to the database
		em.merge(intervention);
		return true;
	}

	@Override
	public Intervention recupereIntervention(int idIntervention) {
		Query query = em.createQuery("SELECT e from Intervention e WHERE e.idIntervention = :pid");
		query.setParameter("pid", idIntervention);
		Intervention intervention = (Intervention) query.getSingleResult();
		return intervention;
	}
	
	//M�thode � modifier lors du push
	@Override
	public List<Intervention> rechercherInterventionSurAnomalie(int idAnomalie) {
		Query query = em.createQuery("SELECT e from Intervention e WHERE e.anomalie.idAnomalie = :pid");
		query.setParameter("pid", idAnomalie);
		List<Intervention> liste = query.getResultList();
		return liste;
	}

	@Override
	public List<Intervention> recupereInterventionparType(TypeIntervention type) {
		Query query = em.createQuery("SELECT e from Intervention e WHERE e.typeIntervention.idTypeIntervention = :pid");
		query.setParameter("pid", type.getIdTypeIntervention());
		List<Intervention> liste = query.getResultList();
		return liste;
	}
}
