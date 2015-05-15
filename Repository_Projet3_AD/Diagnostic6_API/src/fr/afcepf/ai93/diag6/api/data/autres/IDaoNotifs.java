package fr.afcepf.ai93.diag6.api.data.autres;

import fr.afcepf.ai93.diag6.entity.travaux.Intervention;

public interface IDaoNotifs {

	void envoyerNotificationAuGDiag(int profilGestionnaireConcerne, Intervention interv);

	void envoyerNotificationAuGTravaux(int idProfil, int idDiagnostic);

}
