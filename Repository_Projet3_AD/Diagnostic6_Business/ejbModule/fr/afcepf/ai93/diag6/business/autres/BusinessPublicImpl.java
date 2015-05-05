package fr.afcepf.ai93.diag6.business.autres;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import fr.afcepf.ai93.diag6.api.business.publics.IBusinessPublic;
import fr.afcepf.ai93.diag6.api.data.publics.IDaoPublic;
import fr.afcepf.ai93.diag6.entity.diagnostic.Diagnostic;
import fr.afcepf.ai93.diag6.entity.diagnostic.TypeDiagnostic;
import fr.afcepf.ai93.diag6.entity.erp.Erp;

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

}
