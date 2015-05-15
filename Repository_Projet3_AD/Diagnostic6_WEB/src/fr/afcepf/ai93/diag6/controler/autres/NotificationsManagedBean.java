package fr.afcepf.ai93.diag6.controler.autres;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import fr.afcepf.ai93.diag6.api.business.autres.IBusinessUtilisateur;
import fr.afcepf.ai93.diag6.entity.autres.Notifications;
import fr.afcepf.ai93.diag6.entity.travaux.Intervention;

@ManagedBean(name="mbNotifs")
@SessionScoped
public class NotificationsManagedBean {
	@EJB
	private IBusinessUtilisateur proxyUtilisateur;
	
	private List<Notifications> listeNotifs; 
	private static final String  MESSAGE_NOTIF_DIAG ="Une intervention a été finalisée";
	private static final String  MESSAGE_NOTIF_TRAV ="Un nouveau diagnostic a été ajouté"; 
	private String messageDeNotif; 
	
	@ManagedProperty(value = "#{mbUtilisateur}")
	private UtilisateurManagedBean user; 
	
	@PostConstruct
	public void init()
	{
		recupererNotifications();
		for(Notifications n : listeNotifs){
			System.out.println("notif no :"+n.getIdNotification());
		}
	}
	public void recupererNotifications(){
		List<Notifications> listeComplete=proxyUtilisateur.recupereToutNotification();
		listeNotifs = new ArrayList<>();
		//if(user.getUtilisateur().getIdUtilisateur()==2){
			messageDeNotif=MESSAGE_NOTIF_DIAG; 
			for(Notifications n : listeComplete){
				listeNotifs.add(n); 
			}
		//}
		//else{
			if(user.getUtilisateur().getIdUtilisateur()==3){
				messageDeNotif=MESSAGE_NOTIF_TRAV; 
				for(Notifications n : listeComplete){
					listeNotifs.add(n); 
				}
			}
		//}
	}

	public IBusinessUtilisateur getProxyUtilisateur() {
		return proxyUtilisateur;
	}

	public void setProxyUtilisateur(IBusinessUtilisateur proxyUtilisateur) {
		this.proxyUtilisateur = proxyUtilisateur;
	}


	public List<Notifications> getListeNotifs() {
		return listeNotifs;
	}
	public void setListeNotifs(List<Notifications> listeNotifs) {
		this.listeNotifs = listeNotifs;
	}
	public UtilisateurManagedBean getUser() {
		return user;
	}

	public void setUser(UtilisateurManagedBean user) {
		this.user = user;
	}

	public static String getMessageNotifDiag() {
		return MESSAGE_NOTIF_DIAG;
	}

	public static String getMessageNotifTrav() {
		return MESSAGE_NOTIF_TRAV;
	}

	public String getMessageDeNotif() {
		return messageDeNotif;
	}

	public void setMessageDeNotif(String messageDeNotif) {
		this.messageDeNotif = messageDeNotif;
	}
}


