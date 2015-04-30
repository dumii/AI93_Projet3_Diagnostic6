package fr.afcepf.ai93.diag6.api.data.autres;

import java.util.List;

import fr.afcepf.ai93.diag6.entity.travaux.TypeArtisan;

public interface IDaoTypeArtisan {

	public List<TypeArtisan> recupererTypeArtisan();
}
