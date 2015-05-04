package fr.afcepf.ai93.diag6.api.data.travaux;

import java.util.List;

import fr.afcepf.ai93.diag6.entity.autres.Utilisateur;
import fr.afcepf.ai93.diag6.entity.travaux.HistoriqueIntervention;
import fr.afcepf.ai93.diag6.entity.travaux.Intervention;

public interface IDaoHistoriqueIntervention {
	
	public List<HistoriqueIntervention> recupereToutHistoriqueIntervention();
	
	public void historiser(Intervention interventionInitiale, Intervention intervention, Utilisateur user);
}
