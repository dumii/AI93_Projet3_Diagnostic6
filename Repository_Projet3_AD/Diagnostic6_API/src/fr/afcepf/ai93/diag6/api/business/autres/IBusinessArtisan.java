package fr.afcepf.ai93.diag6.api.business.autres;

import java.util.List;

import fr.afcepf.ai93.diag6.entity.autres.Artisan;
import fr.afcepf.ai93.diag6.entity.travaux.TypeArtisan;

public interface IBusinessArtisan {
	
	public List<Artisan> recupererToutArtisan();
	
	public void ajouterArtisant(Artisan artisan);
	
	public boolean supprimerArtisan(Artisan artisan);

	public List<TypeArtisan> recupererTypeArtisan();
}
