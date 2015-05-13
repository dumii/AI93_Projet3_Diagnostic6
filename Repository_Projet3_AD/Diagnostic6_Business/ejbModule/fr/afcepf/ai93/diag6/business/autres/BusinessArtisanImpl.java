package fr.afcepf.ai93.diag6.business.autres;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import fr.afcepf.ai93.diag6.api.business.autres.IBusinessArtisan;
import fr.afcepf.ai93.diag6.api.data.autres.IDaoArtisan;
import fr.afcepf.ai93.diag6.api.data.autres.IDaoTypeArtisan;
import fr.afcepf.ai93.diag6.api.data.travaux.IDaoIntervention;
import fr.afcepf.ai93.diag6.entity.autres.Artisan;
import fr.afcepf.ai93.diag6.entity.autres.TypeArtisan;
import fr.afcepf.ai93.diag6.entity.travaux.TypeIntervention;

@Stateless
@Remote(IBusinessArtisan.class)
public class BusinessArtisanImpl implements IBusinessArtisan {

	@EJB
	private IDaoArtisan proxyArtisan;
	@EJB
	private IDaoTypeArtisan proxyTypeArtisan;
	
	@Override
	public List<Artisan> recupererToutArtisan() {
		return proxyArtisan.recupererToutArtisan();
	}

	@Override
	public void ajouterArtisant(Artisan artisan) {
		proxyArtisan.ajouterArtisant(artisan);
	}

	@Override
	public boolean supprimerArtisan(Artisan artisan) {
		return proxyArtisan.supprimerArtisan(artisan);
	}

	@Override
	public List<TypeArtisan> recupererTypeArtisan() {
		return proxyTypeArtisan.recupererTypeArtisan();
	}

	@Override
	public List<Artisan> recupererArtisansParTypeIntervention(TypeIntervention type) {
		return proxyArtisan.recupererArtisansParTypeIntervention(type);
	}

}
