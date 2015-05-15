package fr.afcepf.ai93.diag6.data.autres;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.afcepf.ai93.diag6.api.data.autres.IDaoTypeArtisan;
import fr.afcepf.ai93.diag6.entity.autres.Artisan;
import fr.afcepf.ai93.diag6.entity.autres.TypeArtisan;

@Stateless
@Remote(IDaoTypeArtisan.class)
public class DaoTypeArtisanImpl implements IDaoTypeArtisan {

	@PersistenceContext(unitName="Malak_Diag_Data")
	private EntityManager em;

	@Override
	public List<TypeArtisan> recupererTypeArtisan() {
		Query query = em.createQuery("SELECT e from TypeArtisan e");
		List<TypeArtisan> liste = query.getResultList();
		return liste;
	}

	@Override
	public List<TypeArtisan> recupererTypeArtisanParArtisan(Artisan artisan) {
		// TODO Auto-generated method stub
		return null;
	}

}
