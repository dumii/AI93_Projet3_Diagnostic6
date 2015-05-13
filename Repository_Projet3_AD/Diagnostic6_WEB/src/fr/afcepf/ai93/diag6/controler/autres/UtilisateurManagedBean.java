package fr.afcepf.ai93.diag6.controler.autres;


import java.io.Serializable;
import java.io.IOException;


import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;







import fr.afcepf.ai93.diag6.api.business.autres.IBusinessUtilisateur;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.richfaces.resource.PostConstructResource;

import fr.afcepf.ai93.diag6.api.business.autres.IBusinessUtilisateur;
import fr.afcepf.ai93.diag6.api.business.publics.IBusinessPublic;
import fr.afcepf.ai93.diag6.entity.autres.Utilisateur;
import fr.afcepf.ai93.diag6.entity.diagnostic.Diagnostic;



@ManagedBean(name="mbUtilisateur")
@SessionScoped
public class UtilisateurManagedBean implements Serializable {
	
	@EJB
	private IBusinessUtilisateur proxyBusinessUtilisateur;
	
	
	/*private String nomUtilisateur;
	private String prenomUtilisateur;
	private String emailUtilisateur;
	private int telUtilisateur;
	private Date dateEnregistrement;*/
	
	//ça c'est pour formater la date en dd/MM/yyyy et virer le 00:00:00.0
	private SimpleDateFormat formater;
	private SimpleDateFormat shortFormater;
	
	private String nomProfil;

	private String login;
	private String motDePasse;

	private String message = "";
	private Utilisateur utilisateur;
	private Utilisateur umodif = new Utilisateur();
	
	private int idUtil;
	
	@PostConstruct
	private void init() {
		idUtil=1;
		formater = new SimpleDateFormat("dd/MM/yyyy");
		recupUtilisateur();
		//System.out.println("Coucou  "+utilisateur.getDateEnregistrement());
		//System.out.println(formater.format(utilisateur.getDateEnregistrement()));
		
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
	
	public void recupUtilisateur()
	{
		System.out.println("1111111111111111111111 je suis entré dans ma méthode 1111111111111111111111111");
		utilisateur = proxyBusinessUtilisateur.recupereUtilisateur(idUtil);
		System.out.println("2222222222222222222222 je suis sorti de ma méthode 222222222222222222222222");
	}
	
	public void modificationUtilisateur(){
		System.out.println("je modifie l'utilisateur");
		System.out.println(umodif.getIdUtilisateur() + " = " + utilisateur.getIdUtilisateur());
		if(umodif.getIdUtilisateur() != utilisateur.getIdUtilisateur()) {
			System.out.println("Utilisateu pris en compte pour la modification : " + utilisateur.getIdUtilisateur());
			umodif = utilisateur;
		} else {
			System.out.println("je passe dans le else");
			proxyBusinessUtilisateur.modifierUtilisateur(utilisateur);
			umodif = new Utilisateur();
			recupUtilisateur();
		}
	}
	
	
////////////////////////////// GETTER / SETTER ///////////////////////////////


	public String getNomProfil() {
		return nomProfil;
	}

	public void setNomProfil(String nomProfil) {
		this.nomProfil = nomProfil;
	}

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

	public int getIdUtil() {
		return idUtil;
	}

	public void setIdUtil(int idUtil) {
		this.idUtil = idUtil;
	}


	public SimpleDateFormat getFormater() {
		return formater;
	}


	public void setFormater(SimpleDateFormat formater) {
		this.formater = formater;
	}


	public SimpleDateFormat getShortFormater() {
		return shortFormater;
	}


	public void setShortFormater(SimpleDateFormat shortFormater) {
		this.shortFormater = shortFormater;
	}


	public Utilisateur getUmodif() {
		return umodif;
	}


	public void setUmodif(Utilisateur umodif) {
		this.umodif = umodif;
	}

}
