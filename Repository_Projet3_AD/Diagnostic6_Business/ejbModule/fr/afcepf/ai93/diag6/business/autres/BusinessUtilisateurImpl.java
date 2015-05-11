package fr.afcepf.ai93.diag6.business.autres;

import java.util.List;

import fr.afcepf.ai93.diag6.api.business.autres.IBusinessUtilisateur;
import fr.afcepf.ai93.diag6.entity.autres.Favoris;
import fr.afcepf.ai93.diag6.entity.autres.ProfilUtilisateur;
import fr.afcepf.ai93.diag6.entity.autres.Utilisateur;

public class BusinessUtilisateurImpl implements IBusinessUtilisateur{

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
	public List<ProfilUtilisateur> recupereToutProfil() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean modifierNomProfil(ProfilUtilisateur nomProfil) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Favoris> recupereToutFavoris() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void ajouterFavoris(Favoris favoris) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean supprimerFavoris(Favoris favoris) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Favoris> recupereErpFavoris() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Favoris> recupereDiagFavoris() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Favoris> recupereInterFavoris() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utilisateur recupereUtilisateur(int idUtilisateur) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Utilisateur> trouverUtilisateur(String nom) {
		// TODO Auto-generated method stub
		return null;
	}

}
