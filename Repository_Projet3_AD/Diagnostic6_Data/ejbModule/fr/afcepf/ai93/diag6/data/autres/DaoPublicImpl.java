package fr.afcepf.ai93.diag6.data.autres;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


import fr.afcepf.ai93.diag6.api.data.publics.IDaoPublic;
import fr.afcepf.ai93.diag6.entity.erp.Erp;
import fr.afcepf.ai93.diag6.entity.travaux.Intervention;

@Stateless
@Remote(IDaoPublic.class)
public class DaoPublicImpl implements IDaoPublic {

	@PersistenceContext(unitName="Malak_Diag_Data")
	EntityManager em;
	
	
	@Override
	public List<Erp> afficherErpAuxNormes() {
		// TODO Auto-generated method stub
		return null;
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
	public List<Erp> calculErpParTypeDiagnostic(int idTypeDiagnostic) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Erp> calculErpParTraite(int traite) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void localisationErpGoogleMap() {
		// TODO Auto-generated method stub

	}

	
	
	@Override
	public Integer nbInterventionsTerminees() {
		
		Integer nbInterventionsTerminees = 0;
		
		Query query = em.createQuery("Select e from Intervention e where e.etatAvancementTravaux = 1");
		List<Intervention> liste = query.getResultList();
		
		for(Intervention i : liste){
			nbInterventionsTerminees++;
			System.out.println(i.getIdIntervention());
		}
		
		return nbInterventionsTerminees;
	}
	

	@Override
	public Integer nbInterventionsEnCours() {
		
		Integer nbInterventionsEnCours = 0;
		
		Query query = em.createQuery("Select e from Intervention e where e.etatAvancementTravaux = 2");
		List<Intervention> liste = query.getResultList();
		
		for(Intervention i : liste){
			nbInterventionsEnCours++;
			System.out.println(i.getIdIntervention());
		}
		
		return nbInterventionsEnCours;
	}
	

	@Override
	public Integer nbInterventionsPlanifiess() {
		
		Integer nbInterventionsPlanifiees = 0;
		
		Query query = em.createQuery("Select e from Intervention e where e.etatAvancementTravaux = 3");
		List<Intervention> liste = query.getResultList();
		
		for(Intervention i : liste){
			nbInterventionsPlanifiees++;
			System.out.println(i.getIdIntervention());
		}
		
		return nbInterventionsPlanifiees;
	}
	

	@Override
	public Integer nbInterventionsDiagnostiquees() {
		
		Integer nbInterventionsDiagnostiquees = 0;
		
		Query query = em.createQuery("Select e from Intervention e where e.etatAvancementTravaux = 4");
		List<Intervention> liste = query.getResultList();
		
		for(Intervention i : liste){
			nbInterventionsDiagnostiquees++;
			System.out.println(i.getIdIntervention());
		}
		
		return nbInterventionsDiagnostiquees;
	}

}
