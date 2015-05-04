package fr.afcepf.ai93.diag6.business.erp;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import fr.afcepf.ai93.diag6.api.business.erp.IBusinessErp;
import fr.afcepf.ai93.diag6.api.data.erp.IDaoErp;
import fr.afcepf.ai93.diag6.entity.erp.Batiment;
import fr.afcepf.ai93.diag6.entity.erp.Erp;
import fr.afcepf.ai93.diag6.entity.erp.Etage;

@Stateless
@Remote(IBusinessErp.class)
public class BusinessErp implements IBusinessErp {
	@EJB
	private IDaoErp proxyErp; 
	
	@Override
	public List<Erp> recupereToutErp() {
		return proxyErp.recupereToutErp();
	}

	public IDaoErp getProxyErp() {
		return proxyErp;
	}

	public void setProxyErp(IDaoErp proxyErp) {
		this.proxyErp = proxyErp;
	}

	@Override
	public List<Batiment> recupererBatParErp(int idErp) {
		return proxyErp.recupererBatParErp(idErp);
	}

	@Override
	public List<Etage> recupererEtagesParBat(int idBatiment) {
		return proxyErp.recupererEtagesParBat(idBatiment);
	}
	
}
