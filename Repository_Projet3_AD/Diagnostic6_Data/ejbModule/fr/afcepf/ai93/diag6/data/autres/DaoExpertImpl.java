package fr.afcepf.ai93.diag6.data.autres;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.afcepf.ai93.diag6.api.data.autres.IDaoExpert;
import fr.afcepf.ai93.diag6.entity.autres.Expert;
import fr.afcepf.ai93.diag6.entity.autres.Localisation;

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
		Localisation local = expert.getLocalisation();
		try {
			local = (Localisation) em.createQuery("SELECT l FROM Localisation l WHERE l.codePostal =:paramCp AND l.ville =:pville")
					.setParameter("paramCp", expert.getLocalisation().getCodePostal())
					.setParameter("pville", expert.getLocalisation().getVille()).getSingleResult();
			expert.setLocalisation(local);
		} catch (Exception e) {
			e.printStackTrace();
			em.persist(local);
			expert.setLocalisation(local);
		}
		em.persist(expert);
	}

	@Override
	public String supprimerExpert(Expert expert) {
		System.out.println("début methode delete dao");
		try {
			expert = (Expert)em.find(Expert.class, expert.getIdExpert());
			em.remove(expert);
			System.out.println("requete delete dao effectuée");
			return "L'expert a été supprimé";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ail";
	}

}
