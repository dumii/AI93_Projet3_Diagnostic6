package fr.afcepf.ai93.diag6.business.diagnostic;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import fr.afcepf.ai93.diag6.api.business.diagnostic.IBusinessAnomalie;
import fr.afcepf.ai93.diag6.api.data.diagnostic.IDaoAnomalie;
import fr.afcepf.ai93.diag6.entity.diagnostic.Anomalie;
import fr.afcepf.ai93.diag6.entity.diagnostic.HistoriqueAnomalie;
import fr.afcepf.ai93.diag6.entity.diagnostic.Indicateur;



@Stateless
@Remote(IBusinessAnomalie.class)
public class BusinessAnomalieImpl implements IBusinessAnomalie {

	@EJB
	private IDaoAnomalie proxyDaoAnomalie; 
	
	@Override
	public List<Anomalie> recupereToutAnomalie() {
		return proxyDaoAnomalie.recupereToutAnomalie();
	}

	@Override
	public void ajouterAnomalie(Anomalie anomalie) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifierAnomalie(Anomalie anomalie) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void historiserAnomalie(Anomalie anomalie) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean supprimerAnomalie(Anomalie anomalie) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<HistoriqueAnomalie> recupereToutHistoriqueAnomalie() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Indicateur> recupereIndicateur() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Anomalie recupereAnomalie(int idAnomalie) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Anomalie> rechercheAnomalies(String nomAnomalie) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Anomalie> rechercheAnomaliesErp(String nomERP) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Anomalie> recupereAnomalieParDiagnostic(int idDiagnostic) {
		return proxyDaoAnomalie.recupereAnomalieParDiagnostic(idDiagnostic);
	}

	public IDaoAnomalie getProxyDaoAnomalie() {
		return proxyDaoAnomalie;
	}

	public void setProxyDaoAnomalie(IDaoAnomalie proxyDaoAnomalie) {
		this.proxyDaoAnomalie = proxyDaoAnomalie;
	}

}
