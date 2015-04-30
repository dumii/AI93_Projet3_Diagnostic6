package fr.afcepf.ai93.diag6.data.diagnostic;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import fr.afcepf.ai93.diag6.api.data.diagnostic.IDaoAnomalie;
import fr.afcepf.ai93.diag6.entity.diagnostic.Anomalie;

public class DaoAnomalieImpl implements IDaoAnomalie {
	private EntityManager em;
	
	@Override
	public List<Anomalie> recupereToutAnomalie() {
		Query requete = em.createQuery("SELECT a FROM Anomalie a");
		List<Anomalie> listeAnomalie = requete.getResultList();
		
		return listeAnomalie;
	}

	@Override
	public void ajouterAnomalie(Anomalie anomalie) {
		Anomalie a = new Anomalie();
		Query requete = em.createQuery("INSERT a IN Diagnostic");
		Anomalie Anomalie = (Anomalie)requete.getSingleResult();
		
		
		
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
