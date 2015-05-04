package fr.afcepf.ai93.diag6.data.autres;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.afcepf.ai93.diag6.api.data.autres.IDaoExpert;
import fr.afcepf.ai93.diag6.entity.autres.Expert;

public class DaoExpertImpl implements IDaoExpert {

	@PersistenceContext(unitName="Malak_Diag_Data")
	private EntityManager em;
	
	@Override
	public List<Expert> recupereToutExpert() {
		Query query = em.createQuery("SELECT e FROM Expert"); 
		return null;
	}

	@Override
	public void ajouterExpert(Expert expert) {
		em.persist(expert);
	}

	@Override
	public boolean supprimerExpert(Expert expert) {
		em.merge(expert);
		return true;
	}

}
