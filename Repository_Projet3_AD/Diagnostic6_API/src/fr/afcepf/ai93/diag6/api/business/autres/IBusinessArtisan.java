package fr.afcepf.ai93.diag6.api.business.autres;

import java.util.List;

import fr.afcepf.ai93.diag6.entity.autres.Artisan;
import fr.afcepf.ai93.diag6.entity.autres.TypeArtisan;
import fr.afcepf.ai93.diag6.entity.travaux.Intervention;
import fr.afcepf.ai93.diag6.entity.travaux.TypeIntervention;

public interface IBusinessArtisan {
	
	//Artisan
	public List<Artisan> recupererToutArtisan();
	
	public String ajouterArtisan(Artisan artisan);
	
	public String supprimerArtisan(Artisan artisan);
	
	public List<Artisan> recupererArtisansParTypeIntervention(TypeIntervention type);
	
	public Artisan recupererArtisansParIntervention(Intervention intervention);

	//Type d'artisan
	public List<TypeArtisan> recupererTypeArtisan();	
	
}
