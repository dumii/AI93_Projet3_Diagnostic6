package fr.afcepf.ai93.diag6.data.travaux;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.afcepf.ai93.diag6.api.data.travaux.IDaoIntervention;
import fr.afcepf.ai93.diag6.entity.travaux.Intervention;

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
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean modifierIntervention(Intervention intervention) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Intervention recupereIntervention(int idIntervention) {
		Query query = em.createQuery("SELECT e from Intervention e WHERE e.id = :pid");
		query.setParameter("pid", idIntervention);
		Intervention intervention = (Intervention) query.getSingleResult();
		return intervention;
	}
}
