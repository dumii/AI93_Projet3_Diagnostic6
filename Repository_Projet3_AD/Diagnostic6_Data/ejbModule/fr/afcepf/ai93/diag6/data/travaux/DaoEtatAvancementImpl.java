package fr.afcepf.ai93.diag6.data.travaux;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.afcepf.ai93.diag6.api.data.travaux.IDaoAvancementIntervention;
import fr.afcepf.ai93.diag6.api.data.travaux.IDaoIntervention;
import fr.afcepf.ai93.diag6.api.data.travaux.IDaoTypeIntervention;
import fr.afcepf.ai93.diag6.entity.travaux.EtatAvancementTravaux;
import fr.afcepf.ai93.diag6.entity.travaux.Intervention;
import fr.afcepf.ai93.diag6.entity.travaux.TypeIntervention;

@Stateless
@Remote(IDaoAvancementIntervention.class)
public class DaoEtatAvancementImpl implements IDaoAvancementIntervention {

	@PersistenceContext(unitName="Malak_Diag_Data")
	private EntityManager em;
	
	@Override
	public List<EtatAvancementTravaux> recupererTousEtats() {
		Query query = em.createQuery("SELECT e from EtatAvancementTravaux e");
		List<EtatAvancementTravaux> liste = query.getResultList();
		return liste;
	}
}
