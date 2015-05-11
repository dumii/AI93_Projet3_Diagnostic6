package fr.afcepf.ai93.diag6.controler.autres;


import java.io.Serializable;

import java.io.IOException;


import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


import fr.afcepf.ai93.diag6.api.business.autres.IBusinessUtilisateur;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import fr.afcepf.ai93.diag6.api.business.autres.IBusinessUtilisateur;
import fr.afcepf.ai93.diag6.api.business.publics.IBusinessPublic;
import fr.afcepf.ai93.diag6.entity.autres.Utilisateur;



@ManagedBean(name="mbUtilisateur")
@SessionScoped
public class UtilisateurManagedBean implements Serializable {
	
	@EJB
	private IBusinessUtilisateur proxyBusinessUtilisateur;
	
	

	private String login;
	private String motDePasse;

	private String message = "";
	private Utilisateur utilisateur;

	@EJB
	private IBusinessUtilisateur proxyBusinessUtilisateur;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

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

	public String seConnecter(){

		utilisateur = proxyBusinessUtilisateur.seConnecter(login, motDePasse);
		

		if (this.utilisateur != null){ 
			
			int idProfil = this.utilisateur.getProfilUtilisateur().getIdProfil(); 
			
			if (idProfil == 5){

				ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
				try {
					ec.redirect(ec.getRequestContextPath() + "/TestTravaux.jsf");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (idProfil == 3){

				ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
				try {
					ec.redirect(ec.getRequestContextPath() + "/planningTravaux.jsf");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (idProfil == 2){

				ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
				try {
					ec.redirect(ec.getRequestContextPath() + "/consultationDiagnostic.jsf");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else{
			 message = "Identifiants invalides";
		}
		return message;

	}

	public void logout() throws IOException {
		
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		ec.invalidateSession();
		ec.redirect("http://localhost:9090/Diagnostic6_WEB/Accueil.jsf");
	}

}
