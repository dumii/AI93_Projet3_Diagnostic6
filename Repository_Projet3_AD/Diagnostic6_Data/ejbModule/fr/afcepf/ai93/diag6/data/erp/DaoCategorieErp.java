package fr.afcepf.ai93.diag6.data.erp;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.afcepf.ai93.diag6.api.data.erp.IDaoCategorieErp;
import fr.afcepf.ai93.diag6.entity.erp.CategorieErp;

@Stateless
@Remote(IDaoCategorieErp.class)
public class DaoCategorieErp implements IDaoCategorieErp {
	@PersistenceContext(unitName="Malak_Diag_Data")
	private EntityManager em; 
	
	@Override
	public List<CategorieErp> recupererToutCategorieErp() {
		Query requete = em.createQuery("SELECT categ FROM CategorieErp categ"); 
		List<CategorieErp> listeToutCategorieErp = requete.getResultList(); 
		return listeToutCategorieErp;
	}
}
