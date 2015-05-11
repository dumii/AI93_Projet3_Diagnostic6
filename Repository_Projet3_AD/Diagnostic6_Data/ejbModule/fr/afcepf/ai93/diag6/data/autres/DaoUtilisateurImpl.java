package fr.afcepf.ai93.diag6.data.autres;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import fr.afcepf.ai93.diag6.api.data.autres.IDaoUtilisateur;
import fr.afcepf.ai93.diag6.entity.autres.Utilisateur;
import fr.afcepf.ai93.diag6.entity.diagnostic.Diagnostic;

public class DaoUtilisateurImpl implements IDaoUtilisateur{
	
	@PersistenceContext(unitName="Malak_Diag_Data")
	private EntityManager em; 

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
	
	
}
