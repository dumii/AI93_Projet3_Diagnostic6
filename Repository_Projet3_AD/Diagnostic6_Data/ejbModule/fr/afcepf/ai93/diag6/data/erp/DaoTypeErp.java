package fr.afcepf.ai93.diag6.data.erp;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.afcepf.ai93.diag6.api.data.erp.IDaoTypeErp;
import fr.afcepf.ai93.diag6.entity.erp.TypeErp;

@Stateless
@Remote(IDaoTypeErp.class)
public class DaoTypeErp implements IDaoTypeErp {
	@PersistenceContext(unitName="Malak_Diag_Data")
	private EntityManager em; 
	
	@Override
	public List<TypeErp> recupererToutTypeErp() {
		Query requete = em.createQuery("SELECT tp FROM TypeErp tp"); 
		List<TypeErp> listeToutTypeErp = requete.getResultList(); 
		return listeToutTypeErp;
	}

}
