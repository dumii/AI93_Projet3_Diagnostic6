package fr.afcepf.ai93.diag6.controler.autres;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import fr.afcepf.ai93.diag6.api.business.autres.IBusinessUtilisateur;
import fr.afcepf.ai93.diag6.entity.autres.Notifications;

@ManagedBean(name="mbNotifs")
@SessionScoped
public class NotificationsManagedBean {
	@EJB
	private IBusinessUtilisateur proxyUtilisateur;
	
	private Notifications listeNotifs; 
	
	public void recupererNotifications(){
		
	}

	public IBusinessUtilisateur getProxyUtilisateur() {
		return proxyUtilisateur;
	}

	public void setProxyUtilisateur(IBusinessUtilisateur proxyUtilisateur) {
		this.proxyUtilisateur = proxyUtilisateur;
	}

	public Notifications getListeNotifs() {
		return listeNotifs;
	}

	public void setListeNotifs(Notifications listeNotifs) {
		this.listeNotifs = listeNotifs;
	} 

}


