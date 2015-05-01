package fr.afcepf.ai93.diag6.api.data.travaux;

import java.util.List;

import fr.afcepf.ai93.diag6.entity.travaux.TypeIntervention;

public interface IDaoTypeIntervention {
		
	public List<TypeIntervention> recupererTousTypesIntervention();

}
