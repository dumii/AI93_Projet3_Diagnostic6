package fr.afcepf.ai93.diag6.business.autres;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import fr.afcepf.ai93.diag6.api.business.autres.IBusinessExpert;
import fr.afcepf.ai93.diag6.api.data.autres.IDaoExpert;
import fr.afcepf.ai93.diag6.entity.autres.Expert;

@Stateless
@Remote(IBusinessExpert.class)
public class BusinessExpertImpl implements IBusinessExpert {

	@EJB
	private IDaoExpert proxyExpert;
	
	@Override
	public List<Expert> recupereToutExpert() {
		return proxyExpert.recupereToutExpert();
	}

	@Override
	public String ajouterExpert(Expert expert) {
		
		proxyExpert.ajouterExpert(expert);
		return "Ajout effectué avec succès";
	}

	@Override
	public String supprimerExpert(Expert expert) {
		proxyExpert.supprimerExpert(expert);
		return "Supression effectuée avec succès";
	}

}
