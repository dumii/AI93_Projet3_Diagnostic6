package fr.afcepf.ai93.diag6.api.data.diagnostic;

import java.util.List;

import fr.afcepf.ai93.diag6.entity.autres.Utilisateur;
import fr.afcepf.ai93.diag6.entity.diagnostic.Anomalie;
import fr.afcepf.ai93.diag6.entity.diagnostic.HistoriqueAnomalie;
import fr.afcepf.ai93.diag6.entity.travaux.Intervention;

public interface IDaoHistoriqueAnomalie {

	
	public List<HistoriqueAnomalie> recupereToutHistoriqueAnomalie();
	
	public void historiser(Anomalie anomalieInitiale, Anomalie anomalie, Utilisateur user);

	public void historiserSuppression(Anomalie anomalieInitiale,
			Anomalie anomalie, Utilisateur user);
}
