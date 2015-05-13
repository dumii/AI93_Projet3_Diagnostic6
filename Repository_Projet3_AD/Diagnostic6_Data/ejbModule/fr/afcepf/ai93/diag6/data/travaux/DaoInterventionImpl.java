package fr.afcepf.ai93.diag6.data.travaux;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.afcepf.ai93.diag6.api.data.diagnostic.IDaoDiagnostic;
import fr.afcepf.ai93.diag6.api.data.travaux.IDaoIntervention;
<<<<<<< HEAD
import fr.afcepf.ai93.diag6.entity.autres.Utilisateur;
import fr.afcepf.ai93.diag6.entity.travaux.Intervention;
=======
import fr.afcepf.ai93.diag6.entity.autres.Utilisateur;
import fr.afcepf.ai93.diag6.entity.travaux.Intervention;
import fr.afcepf.ai93.diag6.entity.travaux.TypeIntervention;
>>>>>>> branch 'master' of https://github.com/dumii/AI93_Projet3_Diagnostic6.git

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
	
	@Override
<<<<<<< HEAD
	public boolean rechercherInterventionSurAnomalie(int idAnomalie) {
		Query query = em.createQuery("SELECT e from Intervention e WHERE e.id = :pid");
		query.setParameter("pid", idAnomalie);
		List<Intervention> liste = query.getResultList();
		
		if (liste.size() > 0)
		{
			return false;
		}
		else
		{
			return true;
		}
=======
	public List<Intervention> rechercherInterventionSurAnomalie(int idAnomalie) {
		Query query = em.createQuery("SELECT e from Intervention e WHERE e.anomalie.idAnomalie = :pid");
		query.setParameter("pid", idAnomalie);
		List<Intervention> liste = new ArrayList<>();
		liste = query.getResultList();
		return liste;
>>>>>>> branch 'master' of https://github.com/dumii/AI93_Projet3_Diagnostic6.git
	}
<<<<<<< HEAD
}
=======
>>>>>>> branch 'master' of https://github.com/dumii/AI93_Projet3_Diagnostic6.git

	@Override
	public List<Intervention> recupereInterventionparType(TypeIntervention type) {
		Query query = em.createQuery("SELECT e from Intervention e WHERE e.typeIntervention.idTypeIntervention = :pid");
		query.setParameter("pid", type.getIdTypeIntervention());
		List<Intervention> liste = query.getResultList();
		return liste;
	}

	@Override
	public int nombreInterventionDiag(int idDiag) {
		Query sql = em.createNativeQuery(
				"select count(intervention.id_intervention) from  anomalie, intervention WHERE anomalie.no_diagnostic = 1 "
				+ " and anomalie.id_anomalie = intervention.id_anomalie");
		BigInteger retour = (BigInteger) sql.getSingleResult();
		return retour.intValue();
	}
}
