package fr.afcepf.ai93.diag6.data.autres;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.afcepf.ai93.diag6.api.data.autres.IDaoArtisan;
import fr.afcepf.ai93.diag6.entity.autres.Artisan;
import fr.afcepf.ai93.diag6.entity.autres.Expert;
import fr.afcepf.ai93.diag6.entity.autres.Localisation;
import fr.afcepf.ai93.diag6.entity.travaux.Intervention;
import fr.afcepf.ai93.diag6.entity.autres.TypeArtisan;
import fr.afcepf.ai93.diag6.entity.travaux.TypeIntervention;

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
	public void ajouterArtisan(Artisan artisan) {
		Localisation local = artisan.getLocalisation();
		try {
			local = (Localisation) em.createQuery("SELECT l FROM Localisation l WHERE l.codePostal =:paramCp AND l.ville =:pville")
					.setParameter("paramCp", artisan.getLocalisation().getCodePostal())
					.setParameter("pville", artisan.getLocalisation().getVille()).getSingleResult();
			artisan.setLocalisation(local);
		} catch (Exception e) {
			e.printStackTrace();
			em.persist(local);
			artisan.setLocalisation(local);
		}
		em.persist(artisan);
		
	}
	
	@Override
	public String supprimerArtisan(Artisan artisan) {
		System.out.println("début methode delete dao");
		try {
			artisan = (Artisan)em.find(Artisan.class, artisan.getIdArtisan());
			em.merge(artisan);
			System.out.println("requete delete dao effectuée");
			return "L'expert a été supprimé";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ail";
	}

	@Override
	public List<Artisan> recupererArtisansParTypeIntervention(
			TypeIntervention type) {
		Query query = em.createQuery("SELECT a.artisan from TypeArtisan a WHERE a.typeIntervention.idTypeIntervention = :pid");
		query.setParameter("pid", type.getIdTypeIntervention());
		List<Artisan> listeArtisan = query.getResultList();
		return listeArtisan;
	}

	@Override
	public Artisan recupererArtisansParIntervention(
			Intervention intervention) {
		Query query = em.createQuery("SELECT a from Artisan a WHERE a.idArtisan = :pid");
		query.setParameter("pid", intervention.getArtisan().getIdArtisan());
		Artisan artisan = (Artisan)query.getSingleResult();
		return artisan;
	}
}
