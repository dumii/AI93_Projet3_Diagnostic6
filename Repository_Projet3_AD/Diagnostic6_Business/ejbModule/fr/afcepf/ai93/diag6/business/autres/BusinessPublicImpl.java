package fr.afcepf.ai93.diag6.business.autres;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import sun.awt.image.SurfaceManager.ProxiedGraphicsConfig;
import fr.afcepf.ai93.diag6.api.business.publics.IBusinessPublic;
import fr.afcepf.ai93.diag6.api.data.publics.IDaoPublic;
import fr.afcepf.ai93.diag6.entity.diagnostic.Diagnostic;
import fr.afcepf.ai93.diag6.entity.diagnostic.TypeDiagnostic;
import fr.afcepf.ai93.diag6.entity.erp.Erp;
import fr.afcepf.ai93.diag6.entity.erp.TypeErp;

@Stateless
@Remote(IBusinessPublic.class)
public class BusinessPublicImpl implements IBusinessPublic {

	@EJB
	private IDaoPublic proxyDaoPublic;
	
	@Override
	public List<Erp> afficherErpAuxNormes() {
		// TODO Auto-generated method stub
		return null;
	}

	public IDaoPublic getProxyDaoPublic() {
		return proxyDaoPublic;
	}

	public void setProxyDaoPublic(IDaoPublic proxyDaoPublic) {
		this.proxyDaoPublic = proxyDaoPublic;
	}

	@Override
	public List<Erp> moyenneErpAuxNormes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Erp> calculGraphiquePatrimoine() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Erp> calculErpParTypeDiagnostic(TypeDiagnostic idTypeDiagnostic) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Erp> calculErpParTraite(Diagnostic traite) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void localisationErpGoogleMap() {
		// TODO Auto-generated method stub

	}

	
	
	@Override
	public Integer nbInterventionsTerminees() {
		// TODO Auto-generated method stub
		return proxyDaoPublic.nbInterventionsTerminees();
	}

	@Override
	public Integer nbInterventionsEnCours() {
		// TODO Auto-generated method stub
		return proxyDaoPublic.nbInterventionsEnCours();
	}

	@Override
	public Integer nbInterventionsPlanifiess() {
		// TODO Auto-generated method stub
		return proxyDaoPublic.nbInterventionsPlanifiess();
	}

	@Override
	public Integer nbInterventionsDiagnostiquees() {
		// TODO Auto-generated method stub
		return proxyDaoPublic.nbInterventionsDiagnostiquees();
	}

	@Override
	public Integer nbDiagnosticAccessibilitéTotal() {
		// TODO Auto-generated method stub
		return proxyDaoPublic.nbDiagnosticAccessibilitéTotal();
	}

	@Override
	public Integer nbDiagnosticEnergieTotal() {
		// TODO Auto-generated method stub
		return proxyDaoPublic.nbDiagnosticEnergieTotal();
	}

	@Override
	public Integer nbDiagnosticSecuriteTotal() {
		// TODO Auto-generated method stub
		return proxyDaoPublic.nbDiagnosticSecuriteTotal();
	}

	@Override
	public Integer nbDiagnosticHygieneTotal() {
		// TODO Auto-generated method stub
		return proxyDaoPublic.nbDiagnosticHygieneTotal();
	}

	@Override
	public Integer nbDiagnosticAccessibilitéTraites() {
		// TODO Auto-generated method stub
		return proxyDaoPublic.nbDiagnosticAccessibilitéTraites();
	}

	@Override
	public Integer nbDiagnosticEnergieTraites() {
		// TODO Auto-generated method stub
		return proxyDaoPublic.nbDiagnosticEnergieTraites();
	}

	@Override
	public Integer nbDiagnosticSecuriteTraites() {
		// TODO Auto-generated method stub
		return proxyDaoPublic.nbDiagnosticSecuriteTraites();
	}

	@Override
	public Integer nbDiagnosticHygieneTraites() {
		// TODO Auto-generated method stub
		return proxyDaoPublic.nbDiagnosticHygieneTraites();
	}

	@Override
	public Integer nbERpAuxNormes() {
		// TODO Auto-generated method stub
		return proxyDaoPublic.nbERpAuxNormes();
	}

	@Override
	public Integer nbErpEnCoursDeNormalité() {
		// TODO Auto-generated method stub
		return proxyDaoPublic.nbErpEnCoursDeNormalité();
	}

	@Override
	public List<TypeErp> listerTypeErp() {
		// TODO Auto-generated method stub
		return proxyDaoPublic.listerTypeErp();
	}

	@Override
	public List<TypeDiagnostic> listerTypeDiagnostic() {
		// TODO Auto-generated method stub
		return proxyDaoPublic.listerTypeDiagnostic();
	}

	@Override
	public List<Erp> recupererErpParTypeDiagnostic(Integer idTypeDiagSelect) {
		// TODO Auto-generated method stub
		return proxyDaoPublic.recupererErpParTypeDiagnostic(idTypeDiagSelect);
	}

	@Override
	public List<Erp> recupererErpParTravauxEnCours(boolean booleenTravaux) {
		// TODO Auto-generated method stub
		return proxyDaoPublic.recupererErpParTravauxEnCours(booleenTravaux);
	}

}
