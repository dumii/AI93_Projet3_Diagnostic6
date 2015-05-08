package fr.afcepf.ai93.diag6.api.data.travaux;

import java.util.List;

import fr.afcepf.ai93.diag6.entity.travaux.EtatAvancementTravaux;
import fr.afcepf.ai93.diag6.entity.travaux.Intervention;

public interface IDaoAvancementIntervention {
	
	public List<EtatAvancementTravaux> recupererTousEtats();
	
	public List<EtatAvancementTravaux> recupererEtatDisponibles(Intervention intervention);
	
	public EtatAvancementTravaux recupererEtatParIntervention (Intervention intervention);

}
