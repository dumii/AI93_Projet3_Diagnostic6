package fr.afcepf.ai93.diag6.business.erp;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import fr.afcepf.ai93.diag6.api.business.erp.IBusinessErp;
import fr.afcepf.ai93.diag6.api.data.erp.IDaoCategorieErp;
import fr.afcepf.ai93.diag6.api.data.erp.IDaoErp;
import fr.afcepf.ai93.diag6.api.data.erp.IDaoTypeErp;
import fr.afcepf.ai93.diag6.entity.erp.Acces;
import fr.afcepf.ai93.diag6.entity.erp.Ascenceur;
import fr.afcepf.ai93.diag6.entity.erp.Batiment;
import fr.afcepf.ai93.diag6.entity.erp.CategorieErp;
import fr.afcepf.ai93.diag6.entity.erp.Erp;
import fr.afcepf.ai93.diag6.entity.erp.Escalier;
import fr.afcepf.ai93.diag6.entity.erp.Etage;
import fr.afcepf.ai93.diag6.entity.erp.Piece;
import fr.afcepf.ai93.diag6.entity.erp.TypeErp;
import fr.afcepf.ai93.diag6.entity.erp.Voirie;

@Stateless
@Remote(IBusinessErp.class)
public class BusinessErp implements IBusinessErp {
	
	@EJB
	private IDaoErp proxyErp; 

	
	@EJB
	private IDaoTypeErp proxyTypeErp;

	@EJB
	private IDaoCategorieErp proxyCategorieErp;
	
	

	
	@Override
	public Erp recupererErpParId(int idErp) {
		return proxyErp.recupererErpParId(idErp);
	}
	
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

	@Override
	public List<Piece> recupererPiecesParEtage(int idEtage) {
		return proxyErp.recupererPiecesParEtage(idEtage); 
	}

	@Override
	public List<Acces> recupererAccesParBat(int idBatiment) {
		return proxyErp.recupererAccesParBat(idBatiment);
	}

	@Override
	public List<Escalier> recupererEscaliersParBat(int idBatiment) {
		return proxyErp.recupererEscaliersParBat(idBatiment);
	}

	@Override
	public List<Ascenceur> recupererAscenceursParBat(int idBatiment) {
		return proxyErp.recupererAscenceursParBat(idBatiment);
	}

	@Override
	public List<Voirie> recupererVoirieParErp(int idErp) {
		return proxyErp.recupererVoirieParErp(idErp);
	}

	@Override
	public List<TypeErp> recupererListeTypeERP() {
		return proxyTypeErp.recupererListeTypeERP();
	}

	public IDaoTypeErp getProxyTypeErp() {
		return proxyTypeErp;
	}

	public void setProxyTypeErp(IDaoTypeErp proxyTypeErp) {
		this.proxyTypeErp = proxyTypeErp;
	}

	public IDaoCategorieErp getProxyCategorieErp() {
		return proxyCategorieErp;
	}

	public void setProxyCategorieErp(IDaoCategorieErp proxyCategorieErp) {
		this.proxyCategorieErp = proxyCategorieErp;
	}

	@Override
	public List<TypeErp> recupererToutTypeErp() {
		return proxyTypeErp.recupererToutTypeErp();
	}

	@Override
	public List<CategorieErp> recupererToutCategorieErp() {
		return proxyCategorieErp.recupererToutCategorieErp();
	}

}
