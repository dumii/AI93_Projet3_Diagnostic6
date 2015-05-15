package fr.afcepf.ai93.diag6.api.data.autres;

import java.util.List;

import fr.afcepf.ai93.diag6.entity.autres.Notifications;
import fr.afcepf.ai93.diag6.entity.erp.Erp;
import fr.afcepf.ai93.diag6.entity.travaux.Intervention;

public interface IDaoNotifs {

	void envoyerNotificationAuGDiag(int profilGestionnaireConcerne,Erp erp, Intervention interv);

	void envoyerNotificationAuGTravaux(int idProfil, int idDiagnostic);
	
	public List<Notifications> recupereToutNotification(); 

}
