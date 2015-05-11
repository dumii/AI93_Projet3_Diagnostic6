package fr.afcepf.ai93.diag6.data.autres;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.ejb.Remote;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import fr.afcepf.ai93.diag6.api.data.autres.IDaoUtilisateur;
import fr.afcepf.ai93.diag6.api.data.publics.IDaoPublic;
import fr.afcepf.ai93.diag6.entity.autres.Utilisateur;
import fr.afcepf.ai93.diag6.entity.diagnostic.Diagnostic;




@Stateless
@Remote(IDaoUtilisateur.class)
public class DaoUtilisateurImpl implements IDaoUtilisateur {


	@PersistenceContext(unitName="Malak_Diag_Data")
	EntityManager em;
	
	@Override
	public List<Utilisateur> recupereToutUtilisateur() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void ajouterUtilisateur(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean supprimerUtilisateur(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modifierUtilisateur(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Utilisateur recupereUtilisateur(int idUtilisateur) {
		Query query = em.createQuery("SELECT u FROM Utilisateur u WHERE u.idUtilisateur = :pid");
		query.setParameter("pid", idUtilisateur);
		Utilisateur utilisateur = (Utilisateur) query.getSingleResult();
		return utilisateur;
	}

	@Override
	public List<Utilisateur> trouverUtilisateur(String nom) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utilisateur seConnecter(String login, String motDePasse) {
		
		Utilisateur utilisateur = null;
		
		Query query = em.createQuery("Select u from Utilisateur u where u.loginUtilisateur = :pid and u.motDePasseUtilisateur = :pid2");
		query.setParameter("pid", login);
		query.setParameter("pid2", motDePasse);
		
		List<Utilisateur> result = (List<Utilisateur>)query.getResultList();
		if(result.size() == 1) {
			utilisateur = result.get(0);
		}
		return utilisateur;
	}
	
	
}
