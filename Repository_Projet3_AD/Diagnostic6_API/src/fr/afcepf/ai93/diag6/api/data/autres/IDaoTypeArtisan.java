package fr.afcepf.ai93.diag6.api.data.autres;

import java.util.List;

import fr.afcepf.ai93.diag6.entity.autres.Artisan;
import fr.afcepf.ai93.diag6.entity.autres.TypeArtisan;

public interface IDaoTypeArtisan {

	public List<TypeArtisan> recupererTypeArtisan();
	
	public List<TypeArtisan> recupererTypeArtisanParArtisan(Artisan artisan);
}
