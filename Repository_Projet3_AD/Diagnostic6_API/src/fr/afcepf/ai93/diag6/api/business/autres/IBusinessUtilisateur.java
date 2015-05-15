package fr.afcepf.ai93.diag6.api.business.autres;

import java.util.List;

import fr.afcepf.ai93.diag6.entity.autres.Favoris;
import fr.afcepf.ai93.diag6.entity.autres.Notifications;
import fr.afcepf.ai93.diag6.entity.autres.ProfilUtilisateur;
import fr.afcepf.ai93.diag6.entity.autres.Utilisateur;

public interface IBusinessUtilisateur {
	
	public List<Utilisateur> recupereToutUtilisateur();

    public void ajouterUtilisateur(Utilisateur utilisateur);

    public boolean supprimerUtilisateur(Utilisateur utilisateur);

    public String modifierUtilisateur(Utilisateur utilisateur);

    public List<ProfilUtilisateur> recupereToutProfil();

    public boolean modifierNomProfil(ProfilUtilisateur nomProfil);

    public List<Favoris> recupereToutFavoris();

    public void ajouterFavoris(Favoris favoris);

    public boolean supprimerFavoris(Favoris favoris);

    public List<Favoris> recupereErpFavoris();

    public List<Favoris> recupereDiagFavoris();

    public List<Favoris> recupereInterFavoris();

    public Utilisateur recupereUtilisateur(int idUtilisateur);

    public List<Utilisateur> trouverUtilisateur(String nom);
    
    public Utilisateur seConnecter(String login, String motDePasse);

	public List<Notifications> recupereToutNotification();
}
