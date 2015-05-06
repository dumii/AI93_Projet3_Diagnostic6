package fr.afcepf.ai93.diag6.controler.autres;

import java.io.IOException;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import fr.afcepf.ai93.diag6.api.business.autres.IBusinessUtilisateur;
import fr.afcepf.ai93.diag6.api.business.publics.IBusinessPublic;
import fr.afcepf.ai93.diag6.entity.autres.Utilisateur;


@ManagedBean(name="mbUtilisateur")
@SessionScoped
public class UtilisateurManagedBean {

	private String login;
	private String motDePasse;
	
	private Utilisateur utilisateur;
	
	@EJB
	private IBusinessUtilisateur proxyBusinessUtilisateur;
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public IBusinessUtilisateur getProxyBusinessUtilisateur() {
		return proxyBusinessUtilisateur;
	}

	public void setProxyBusinessUtilisateur(
			IBusinessUtilisateur proxyBusinessUtilisateur) {
		this.proxyBusinessUtilisateur = proxyBusinessUtilisateur;
	}

	public void seConnecter(){
		
		utilisateur = proxyBusinessUtilisateur.seConnecter(login, motDePasse);
		int idProfil = utilisateur.getProfilUtilisateur().getIdProfil(); 
		
		if (idProfil == 5){
			
				ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
				try {
					ec.redirect(ec.getRequestContextPath() + "/consultationDiagnostic.jsf");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		};
		
	}
	
	public void logout() throws IOException {
	    ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	    ec.invalidateSession();
	    ec.redirect(ec.getRequestContextPath() + "/Accueil.jsf");
	}
	
}
