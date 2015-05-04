package fr.afcepf.ai93.diag6.data.autres;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.afcepf.ai93.diag6.api.data.autres.IDaoUtilisateur;
import fr.afcepf.ai93.diag6.entity.autres.Utilisateur;
import fr.afcepf.ai93.diag6.entity.diagnostic.Anomalie;

public class DaoUtilisateurImpl implements IDaoUtilisateur {

	@PersistenceContext(unitName="Malak_Diag_Data")
	private EntityManager em;

	@Override
	public List<Utilisateur> recupereToutUtilisateur() {
		Query query = em.createQuery("SELECT u FROM Utilisateur");
		List <Utilisateur> liste = query.getResultList();
		return liste;
	}

	@Override
	public void ajouterUtilisateur(Utilisateur utilisateur) {
		em.persist(utilisateur);	
	}

	@Override
	public boolean supprimerUtilisateur(Utilisateur utilisateur) {
		em.merge(utilisateur);
		return true;
	}

	@Override
	public boolean modifierUtilisateur(Utilisateur utilisateur) {
		em.merge(utilisateur);
		return true;
	}

	@Override
	public Utilisateur recupereUtilisateur(int idUtilisateur) {
		Query query = em.createQuery("SELECT u FROM Anomalie WHERE u.id = :pid");
		query.setParameter("pid", idUtilisateur);
		Utilisateur utilisateur = (Utilisateur) query.getSingleResult();
		return utilisateur;
	}

	@Override
	public List<Utilisateur> trouverUtilisateur(String nom) {
		Query query = em.createQuery("SELECT u FROM Utilisateur");
		List <Utilisateur> liste = query.getResultList();
		return liste;
	}
}