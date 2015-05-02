package fr.afcepf.ai93.diag6.api.data.autres;

import java.util.List;

import fr.afcepf.ai93.diag6.entity.autres.Artisan;

public interface IDaoArtisan {

	public List<Artisan> recupererToutArtisan();
	
	public void ajouterArtisant(Artisan artisan);
	
	public boolean supprimerArtisan(Artisan artisan);
	
	public List<Artisan> recupererArtisanParTypeIntervention(int idTypeIntervention);
}
