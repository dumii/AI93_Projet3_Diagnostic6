package fr.afcepf.ai93.diag6.data.travaux;

import java.util.ArrayList;
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

	@Override
	public List<EtatAvancementTravaux> recupererEtatDisponibles(Intervention intervention) {
		
		int idEtatIntervention = intervention.getEtatAvancementTravaux().getIdEtatAvancement();
		Query query = em.createQuery("SELECT e from EtatAvancementTravaux e");
		
		List<EtatAvancementTravaux> listeBrute = query.getResultList();
		List<EtatAvancementTravaux> listeARetourner = new ArrayList<>();
		
		for (EtatAvancementTravaux etat : listeBrute)
		{
			if (etat.getIdEtatAvancement() <= idEtatIntervention)
			{
				listeARetourner.add(etat);
			}
		}				
		return listeARetourner;
	}

	@Override
	public EtatAvancementTravaux recupererEtatParIntervention(
			Intervention intervention) {
		
		int idEtatIntervention = intervention.getEtatAvancementTravaux().getIdEtatAvancement();
		Query query = em.createQuery("SELECT e from EtatAvancementTravaux e WHERE e.idEtatAvancement = :pid");
		query.setParameter("pid", idEtatIntervention);
		
		EtatAvancementTravaux etat = (EtatAvancementTravaux) query.getSingleResult();
		
		return etat;
	}
}
