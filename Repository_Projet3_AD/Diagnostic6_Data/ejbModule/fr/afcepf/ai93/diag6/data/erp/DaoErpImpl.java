package fr.afcepf.ai93.diag6.data.erp;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.afcepf.ai93.diag6.api.data.erp.IDaoErp;
import fr.afcepf.ai93.diag6.entity.erp.Erp;
import fr.afcepf.ai93.diag6.entity.travaux.Intervention;

@Stateless
@Remote(IDaoErp.class)
public class DaoErpImpl implements IDaoErp {
	@PersistenceContext(unitName="Malak_Diag_Data")
	private EntityManager em; 
	
	@Override
	public List<Erp> recupereToutErp() {
		Query requete = em.createQuery("SELECT e FROM Erp e"); 
		List<Erp> listeToutErp = requete.getResultList(); 
		return listeToutErp;
	}

	@Override
	public Erp recupererErpParId(int idErp) {
		Query query = em.createQuery("SELECT e from Erp e WHERE e.idErp = :pid");
		query.setParameter("pid", idErp);
		Erp erp = (Erp) query.getSingleResult();
		return erp;
	}
}
