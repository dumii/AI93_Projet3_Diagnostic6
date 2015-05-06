package fr.afcepf.ai93.diag6.data.autres;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.afcepf.ai93.diag6.api.data.autres.IDaoExpert;
import fr.afcepf.ai93.diag6.entity.autres.Expert;

@Stateless
@Remote(IDaoExpert.class)
public class DaoExpertImpl implements IDaoExpert {

	@PersistenceContext(unitName="Malak_Diag_Data")
	private EntityManager em;
	
	@Override
	public List<Expert> recupereToutExpert() {
		Query query = em.createQuery("SELECT e from Expert e");
		List<Expert> liste = query.getResultList();
		return liste;
	}

	@Override
	public void ajouterExpert(Expert expert) {
		em.persist(expert);
	}

	@Override
	public void supprimerExpert(Expert expert) {
		em.remove(expert);
	}

}
