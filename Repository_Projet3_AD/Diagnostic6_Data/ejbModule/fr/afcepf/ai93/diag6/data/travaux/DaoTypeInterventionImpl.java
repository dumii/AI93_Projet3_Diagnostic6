package fr.afcepf.ai93.diag6.data.travaux;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.afcepf.ai93.diag6.api.data.travaux.IDaoIntervention;
import fr.afcepf.ai93.diag6.api.data.travaux.IDaoTypeIntervention;
import fr.afcepf.ai93.diag6.entity.travaux.Intervention;
import fr.afcepf.ai93.diag6.entity.travaux.TypeIntervention;

@Stateless
@Remote(IDaoTypeIntervention.class)
public class DaoTypeInterventionImpl implements IDaoTypeIntervention {

	@PersistenceContext(unitName="Malak_Diag_Data")
	private EntityManager em;

	@Override
	public List<TypeIntervention> recupererTousTypesIntervention() {
		Query query = em.createQuery("SELECT e from TypeIntervention e");
		List<TypeIntervention> liste = query.getResultList();
		return liste;
	}
}
