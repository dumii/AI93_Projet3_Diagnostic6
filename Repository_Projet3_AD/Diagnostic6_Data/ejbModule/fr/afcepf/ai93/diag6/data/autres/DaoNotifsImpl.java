package fr.afcepf.ai93.diag6.data.autres;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.afcepf.ai93.diag6.api.data.autres.IDaoNotifs;
import fr.afcepf.ai93.diag6.entity.autres.Notifications;
import fr.afcepf.ai93.diag6.entity.autres.ProfilUtilisateur;
import fr.afcepf.ai93.diag6.entity.diagnostic.Diagnostic;
import fr.afcepf.ai93.diag6.entity.erp.Erp;
import fr.afcepf.ai93.diag6.entity.travaux.Intervention;

@Stateless
@Remote(IDaoNotifs.class)
public class DaoNotifsImpl implements IDaoNotifs {

	@PersistenceContext(unitName="Malak_Diag_Data")
	private EntityManager em; 
	
	@Override
	public void envoyerNotificationAuGDiag(int profilGestionnaireConcerne,
			Erp erp, Intervention interv) {
		
		Notifications notif = new Notifications();
		notif.setCheckee(0);
		notif.setDateCreation(new Date());
		ProfilUtilisateur prof = new ProfilUtilisateur(); 
		prof.setIdProfil(2);
		notif.setProfilUtilisateur(prof);
		notif.setErp(erp);
		notif.setIntervention(interv);
		em.merge(notif); 
	}

	@Override
	public void envoyerNotificationAuGTravaux(int idProfil, int idDiagnostic) {
		Notifications notif = new Notifications();
		notif.setCheckee(0);
		notif.setDateCreation(new Date());
		ProfilUtilisateur prof = new ProfilUtilisateur(); 
		prof.setIdProfil(3);
		notif.setProfilUtilisateur(prof);
		Diagnostic d = new Diagnostic();
		d.setIdDiagnostic(idDiagnostic);
		notif.setDiagnostic(d);;
		em.merge(notif); 
	}

	@Override
	public List<Notifications> recupereToutNotification() {
		Query requete = em.createQuery("SELECT notif FROM Notifications notif"); 
		List<Notifications> listeToutNotif = requete.getResultList(); 
		return listeToutNotif;
	}
}
