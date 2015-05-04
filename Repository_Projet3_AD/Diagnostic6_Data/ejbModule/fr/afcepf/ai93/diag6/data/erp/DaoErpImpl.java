package fr.afcepf.ai93.diag6.data.erp;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.afcepf.ai93.diag6.api.data.erp.IDaoErp;
import fr.afcepf.ai93.diag6.entity.erp.Batiment;
import fr.afcepf.ai93.diag6.entity.erp.Erp;
import fr.afcepf.ai93.diag6.entity.erp.Etage;

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
	public List<Batiment> recupererBatParErp(int idErp) {
		Query requete = em.createQuery("select bat from Batiment bat where erp.idErp = :pid"); 
		requete.setParameter("pid", idErp);
		List<Batiment> listeBatiments = requete.getResultList(); 
		return listeBatiments;
	}

	@Override
	public List<Etage> recupererEtagesParBat(int idBatiment) {
		Query requete = em.createQuery("select etg from Etage etg where batiment.idBatiment = :pid"); 
		requete.setParameter("pid", idBatiment);
		List<Etage> listeEtages = requete.getResultList(); 
		return listeEtages;
	}
}
