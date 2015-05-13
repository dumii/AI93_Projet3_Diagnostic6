package fr.afcepf.ai93.diag6.api.data.autres;

import java.util.List;

import fr.afcepf.ai93.diag6.entity.autres.Favoris;
import fr.afcepf.ai93.diag6.entity.autres.ProfilUtilisateur;
import fr.afcepf.ai93.diag6.entity.autres.Utilisateur;

public interface IDaoUtilisateur {
	
	public List<Utilisateur> recupereToutUtilisateur();

    public void ajouterUtilisateur(Utilisateur utilisateur);

    public boolean supprimerUtilisateur(Utilisateur utilisateur);

    public void modifierUtilisateur(Utilisateur utilisateur);

    public Utilisateur recupereUtilisateur(int idUtilisateur);

    public List<Utilisateur> trouverUtilisateur(String nom);
    
    
    public Utilisateur seConnecter(String login, String motDePasse);
    
}
