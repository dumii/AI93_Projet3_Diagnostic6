package fr.afcepf.ai93.diag6.data.diagnostic;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.afcepf.ai93.diag6.api.data.diagnostic.IDaoAnomalie;
import fr.afcepf.ai93.diag6.api.data.diagnostic.IDaoDiagnostic;
import fr.afcepf.ai93.diag6.entity.diagnostic.Anomalie;

@Stateless
@Remote(IDaoAnomalie.class)

public class DaoAnomalieImpl implements IDaoAnomalie{
	
	@PersistenceContext(unitName="Malak_Diag_Data")
	private EntityManager em; 

	@Override
	public List<Anomalie> recupereToutAnomalie() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void ajouterAnomalie(Anomalie anomalie) {
		em.persist(anomalie);
		
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

}
