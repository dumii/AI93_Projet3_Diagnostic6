package fr.afcepf.ai93.diag6.business.autres;


import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import fr.afcepf.ai93.diag6.api.business.autres.IBusinessUtilisateur;
import fr.afcepf.ai93.diag6.api.business.publics.IBusinessPublic;
import fr.afcepf.ai93.diag6.api.data.autres.IDaoNotifs;
import fr.afcepf.ai93.diag6.api.data.autres.IDaoUtilisateur;
import fr.afcepf.ai93.diag6.entity.autres.Favoris;
import fr.afcepf.ai93.diag6.entity.autres.Notifications;
import fr.afcepf.ai93.diag6.entity.autres.ProfilUtilisateur;
import fr.afcepf.ai93.diag6.entity.autres.Utilisateur;
import fr.afcepf.ai93.diag6.entity.diagnostic.Diagnostic;


@Stateless
@Remote(IBusinessUtilisateur.class)
public class BusinessUtilisateurImpl implements IBusinessUtilisateur {

	@EJB
	public IDaoUtilisateur proxyUser;
	@EJB
	public IDaoNotifs proxyNotif;
	
	@Override
	public List<Utilisateur> recupereToutUtilisateur() {
		
		List<Utilisateur> liste = proxyUser.recupereToutUtilisateur();
		
		return liste;
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
	public String modifierUtilisateur(Utilisateur utilisateur) {

		//Utilisateur utiliInitial = proxiDaoUtilisateur.recupereUtilisateur(utilisateur.getIdUtilisateur());
		proxyUser.modifierUtilisateur(utilisateur);
		System.out.println("appel fonction dans biz");

		Utilisateur utiliInitial = proxyUser.recupereUtilisateur(utilisateur.getIdUtilisateur());
		proxyUser.modifierUtilisateur(utilisateur);

		return "Ok modif";
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
		return proxyUser.recupereUtilisateur(idUtilisateur);
	}

	@Override
	public List<Utilisateur> trouverUtilisateur(String nom) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utilisateur seConnecter(String login, String motDePasse) {
		return proxyUser.seConnecter(login, motDePasse);
	}

	@Override
	public List<Notifications> recupereToutNotification() {
		return proxyNotif.recupereToutNotification();
	}

	
}
