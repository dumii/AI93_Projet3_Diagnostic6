package fr.afcepf.ai93.diag6.api.business.autres;

import java.util.List;

import fr.afcepf.ai93.diag6.entity.autres.Artisan;
import fr.afcepf.ai93.diag6.entity.autres.TypeArtisan;

public interface IBusinessArtisan {
	
	//Artisan
	public List<Artisan> recupererToutArtisan();
	
	public void ajouterArtisant(Artisan artisan);
	
	public boolean supprimerArtisan(Artisan artisan);
	
	//!!!!Type artisan = type d'intervention (les deux tables sont coh�rentes entre elles)!!!!
	public List<Artisan> recupererArtisanParTypeIntervention(int idTypeIntervention);

	//Type d'artisan
	public List<TypeArtisan> recupererTypeArtisan();	
	
}
