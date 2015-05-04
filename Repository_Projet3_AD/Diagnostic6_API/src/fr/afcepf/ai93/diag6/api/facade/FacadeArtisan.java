package fr.afcepf.ai93.diag6.api.facade;

import java.util.List;

import fr.afcepf.ai93.diag6.entity.autres.Artisan;
import fr.afcepf.ai93.diag6.entity.autres.TypeArtisan;

public interface FacadeArtisan {

	public List<Artisan> recupererToutArtisan();
	
	public void ajouterArtisant(Artisan artisan);
	
	public boolean supprimerArtisan(Artisan artisan);

	public List<TypeArtisan> recupererTypeArtisan();
	
}
