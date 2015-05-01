package fr.afcepf.ai93.diag6.business.travaux;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import fr.afcepf.ai93.diag6.api.business.travaux.IBusinessIntervention;
import fr.afcepf.ai93.diag6.api.data.travaux.IDaoIntervention;
import fr.afcepf.ai93.diag6.api.data.travaux.IDaoTypeIntervention;
import fr.afcepf.ai93.diag6.entity.travaux.HistoriqueIntervention;
import fr.afcepf.ai93.diag6.entity.travaux.Intervention;
import fr.afcepf.ai93.diag6.entity.travaux.TypeIntervention;

@Stateless
@Remote(IBusinessIntervention.class)
public class BusinessInterventionImpl implements IBusinessIntervention {

	@EJB
	private IDaoIntervention proxyIntervention;
	
	@Override
	public List<HistoriqueIntervention> recupereToutHistoriqueIntervention() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Intervention> recupereToutesIntervention() {
		return proxyIntervention.recupereToutesIntervention();
	}

	@Override
	public void ajouterIntervention(Intervention intervention) {
		proxyIntervention.ajouterIntervention(intervention);
	}

	@Override
	public boolean modifierIntervention(Intervention intervention) {
		return proxyIntervention.modifierIntervention(intervention);
	}

	@Override
	public Intervention recupereIntervention(int idIntervention) {
		return proxyIntervention.recupereIntervention(idIntervention);
	}

	public IDaoIntervention getProxyIntervention() {
		return proxyIntervention;
	}

	public void setProxyIntervention(IDaoIntervention proxyIntervention) {
		this.proxyIntervention = proxyIntervention;
	}

}
