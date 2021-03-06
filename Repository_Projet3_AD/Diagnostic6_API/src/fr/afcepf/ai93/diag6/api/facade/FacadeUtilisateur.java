package fr.afcepf.ai93.diag6.api.facade;

import java.util.List;

import fr.afcepf.ai93.diag6.entity.autres.Favoris;
import fr.afcepf.ai93.diag6.entity.autres.ProfilUtilisateur;
import fr.afcepf.ai93.diag6.entity.autres.Utilisateur;

public interface FacadeUtilisateur {


    public boolean modifierNomProfil(ProfilUtilisateur nomProfil);
    
    public List<ProfilUtilisateur> recupereToutProfil();
    
    public List<Favoris> recupereErpFavoris();

    public List<Favoris> recupereDiagFavoris();

    public List<Favoris> recupereInterFavoris();
    
    public void ajouterFavoris(Favoris favoris);

    public boolean supprimerFavoris(Favoris favoris);
    
    public List<Favoris> recupereToutFavoris();
    
    public List<Utilisateur> recupereToutUtilisateur();

    public void ajouterUtilisateur(Utilisateur utilisateur);

    public boolean supprimerUtilisateur(Utilisateur utilisateur);

    public boolean modifierUtilisateur(Utilisateur utilisateur);

    public Utilisateur recupereUtilisateur(int idUtilisateur);

    public List<Utilisateur> trouverUtilisateur(String nom);
	
}
