package fr.afcepf.ai93.diag6.data.autres;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.afcepf.ai93.diag6.api.data.autres.IDaoArtisan;
import fr.afcepf.ai93.diag6.entity.autres.Artisan;
import fr.afcepf.ai93.diag6.entity.travaux.Intervention;

@Stateless
@Remote(IDaoArtisan.class)
public class DaoArtisanImpl implements IDaoArtisan {

	@PersistenceContext(unitName="Malak_Diag_Data")
	private EntityManager em;
	
	@Override
	public List<Artisan> recupererToutArtisan() {
		Query query = em.createQuery("SELECT e from Artisan e");
		List<Artisan> liste = query.getResultList();
		return liste;
	}

	@Override
	public void ajouterArtisant(Artisan artisan) {
		em.persist(artisan);
		
	}
	
	@Override
	public boolean supprimerArtisan(Artisan artisan) {
		em.merge(artisan);
		return true;
	}

	@Override
	public List<Artisan> recupererArtisanParTypeIntervention(
			int idTypeIntervention) {
		// TODO Auto-generated method stub
		return null;
	}

	//Méthode à modifier
	/*
	@Override
	public List<Artisan> recupererArtisanParTypeIntervention(
			int idTypeIntervention) {
		Query query = em.createQuery("SELECT Artisan e from TypeArtisan e WHERE e.idTypeIntervention = :pid");
		query.setParameter("pid", idTypeIntervention);
		List<Artisan> liste = query.getResultList();
		return liste;
	}
	*/

}
