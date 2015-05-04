package fr.afcepf.ai93.diag6.business.travaux;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import fr.afcepf.ai93.diag6.api.business.travaux.IBusinessIntervention;
import fr.afcepf.ai93.diag6.api.data.travaux.IDaoAvancementIntervention;
import fr.afcepf.ai93.diag6.api.data.travaux.IDaoHistoriqueIntervention;
import fr.afcepf.ai93.diag6.api.data.travaux.IDaoIntervention;
import fr.afcepf.ai93.diag6.api.data.travaux.IDaoTypeIntervention;
import fr.afcepf.ai93.diag6.entity.autres.Utilisateur;
import fr.afcepf.ai93.diag6.entity.travaux.EtatAvancementTravaux;
import fr.afcepf.ai93.diag6.entity.travaux.HistoriqueIntervention;
import fr.afcepf.ai93.diag6.entity.travaux.Intervention;
import fr.afcepf.ai93.diag6.entity.travaux.TypeIntervention;

@Stateless
@Remote(IBusinessIntervention.class)
public class BusinessInterventionImpl implements IBusinessIntervention {

	@EJB
	private IDaoIntervention proxyIntervention;
	@EJB
	private IDaoTypeIntervention proxyTypeIntervention;
	@EJB
	private IDaoAvancementIntervention proxyEtatAvancement;
	@EJB
	private IDaoHistoriqueIntervention proxyHistorique;
	
	@Override
	public List<HistoriqueIntervention> recupereToutHistoriqueIntervention() {
		return proxyHistorique.recupereToutHistoriqueIntervention();	
	}

	@Override
	public List<Intervention> recupereToutesIntervention() {
		return proxyIntervention.recupereToutesIntervention();
	}

	@Override
	public String ajouterIntervention(Intervention intervention) {
		boolean ajoutAutorise = proxyIntervention.rechercherInterventionSurAnomalie(intervention.getAnomalie().getIdAnomalie());
		if (ajoutAutorise)
		{		
			proxyIntervention.ajouterIntervention(intervention);
			return "Intervention enregistrée avec succès";
		}
		else
		{
			return "Une intervention a déjà été enregistrée pour cette anomalie. Vous ne pouvez pas ajouter d'intervention dessus.";
		}
	}

	@Override
	public String modifierIntervention(Intervention intervention, Utilisateur user) {
		
		Intervention interventionInitiale = proxyIntervention.recupereIntervention(intervention.getIdIntervention());
		
		
		int idAvancementInitial = interventionInitiale.getEtatAvancementTravaux().getIdEtatAvancement();
		int idAvancementNouveau = intervention.getEtatAvancementTravaux().getIdEtatAvancement();
		
		if (idAvancementInitial <= idAvancementNouveau)
		{
			proxyIntervention.modifierIntervention(intervention);
			proxyHistorique.historiser(interventionInitiale, intervention, user);
			return "Modification enregistrée avec succès";
		}
		else
		{		
			return "Modification illégale de l'état d'avancement des travaux";
		}
	}

	@Override
	public Intervention recupereIntervention(int idIntervention) {
		return proxyIntervention.recupereIntervention(idIntervention);
	}

	@Override
	public List<TypeIntervention> recupererTousTypesIntervention() {
		return proxyTypeIntervention.recupererTousTypesIntervention();
	}

	@Override
	public List<EtatAvancementTravaux> recupererTousEtats() {
		return proxyEtatAvancement.recupererTousEtats();
	}

	public IDaoIntervention getProxyIntervention() {
		return proxyIntervention;
	}

	public void setProxyIntervention(IDaoIntervention proxyIntervention) {
		this.proxyIntervention = proxyIntervention;
	}

	public IDaoTypeIntervention getProxyTypeIntervention() {
		return proxyTypeIntervention;
	}

	public void setProxyTypeIntervention(IDaoTypeIntervention proxyTypeIntervention) {
		this.proxyTypeIntervention = proxyTypeIntervention;
	}

	public IDaoAvancementIntervention getProxyEtatAvancement() {
		return proxyEtatAvancement;
	}

	public void setProxyEtatAvancement(
			IDaoAvancementIntervention proxyEtatAvancement) {
		this.proxyEtatAvancement = proxyEtatAvancement;
	}

}
