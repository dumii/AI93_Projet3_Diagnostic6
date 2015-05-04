package fr.afcepf.ai93.diag6.data.autres;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.afcepf.ai93.diag6.api.data.autres.IDaoArtisan;
import fr.afcepf.ai93.diag6.entity.autres.Artisan;

public class DaoArtisanImpl implements IDaoArtisan {

	@PersistenceContext(unitName="Malak_Diag_Data")
	private EntityManager em;
	
	@Override
	public List<Artisan> recupererToutArtisan() {
		Query query = em.createQuery("SELECT a FROM Artisan a");
		List <Artisan> liste = query.getResultList();
		return liste;
	}

	@Override
	public void ajouterArtisant(Artisan artisan) {
		em.persist(artisan);
		
	}

	@Override
	public boolean supprimerArtisan(Artisan artisan) {
		em.merge(artisan);
		return false;
	}

}
