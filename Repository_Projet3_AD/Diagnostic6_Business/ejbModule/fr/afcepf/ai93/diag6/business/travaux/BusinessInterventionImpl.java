package fr.afcepf.ai93.diag6.business.travaux;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import fr.afcepf.ai93.diag6.api.business.travaux.IBusinessIntervention;
import fr.afcepf.ai93.diag6.api.data.autres.IDaoNotifs;
import fr.afcepf.ai93.diag6.api.data.travaux.IDaoAvancementIntervention;
import fr.afcepf.ai93.diag6.api.data.travaux.IDaoHistoriqueIntervention;
import fr.afcepf.ai93.diag6.api.data.travaux.IDaoIntervention;
import fr.afcepf.ai93.diag6.api.data.travaux.IDaoTypeIntervention;
import fr.afcepf.ai93.diag6.entity.autres.Utilisateur;
import fr.afcepf.ai93.diag6.entity.diagnostic.Anomalie;
import fr.afcepf.ai93.diag6.entity.erp.Erp;
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
	@EJB
	private IDaoNotifs proxyNotifs;
	
	@Override
	public List<HistoriqueIntervention> recupereToutHistoriqueIntervention() {
		return proxyHistorique.recupereToutHistoriqueIntervention();	
	}

	@Override
	public List<Intervention> recupereToutesIntervention() {
		return proxyIntervention.recupereToutesIntervention();
	}

	/*
	 * (non-Javadoc)
	 * @see fr.afcepf.ai93.diag6.api.data.travaux.IDaoIntervention#rechercherInterventionSurAnomalie(int)
	 * Recherche des interventions pour une anomalie
	 * ==> R�gle de gestion
	 */
	
	@Override
	public String ajouterIntervention(Intervention intervention, Anomalie anomalie) {
		
		//V�rification de l'existence ou non d'intervention sur l'anomalie (une seule intervention par anomalie autoris�e)
		boolean ajoutAutorise = true;
		List<Intervention> liste = proxyIntervention.rechercherInterventionSurAnomalie(anomalie.getIdAnomalie());
		if (liste.size() > 0)
		{
			ajoutAutorise = false;
		}
		
		//Ajout de l'intervention si l'ajout est autoris�
		if (ajoutAutorise)
		{		
			proxyIntervention.ajouterIntervention(intervention);
			return "Intervention enregistr�e avec succ�s";
		}
		else
		{
			return "Une intervention a d�j� �t� enregistr�e pour cette anomalie. Vous ne pouvez pas ajouter d'intervention dessus.";
		}
	}

	@Override
	public String modifierIntervention(Intervention intervention, int IdIntervention, Utilisateur user) {
		
		Intervention interventionInitiale = proxyIntervention.recupereIntervention(IdIntervention);
		
		//L'�tat d'avancement d'une intervention ne peut passer que de "En attente" vers "Termin�s"
		//Soit l'idEtatAvancement ne peut passer que de 4 � 1
		int idAvancementInitial = interventionInitiale.getEtatAvancementTravaux().getIdEtatAvancement();
		int idAvancementNouveau = intervention.getEtatAvancementTravaux().getIdEtatAvancement();
		
		if (idAvancementInitial >= idAvancementNouveau)
		{
			proxyIntervention.modifierIntervention(intervention);
			proxyHistorique.historiser(interventionInitiale, intervention, user);
			//dans le cas o� l'intervention passe en statut "Termin�", une notification est transmise au gestionnaire de diagnostic
			if(idAvancementNouveau==1){
				proxyNotifs.envoyerNotificationAuGDiag(2,intervention.getAnomalie().getDiagnostic().getErp(),intervention); 
			}
			return "Modification enregistr�e avec succ�s";
		}
		else
		{					
			return "Modification ill�gale de l'�tat d'avancement des travaux";
		}
	}
	
	@Override
	public List<Intervention> rechercherInterventionSurAnomalie(int idAnomalie) {
		return proxyIntervention.rechercherInterventionSurAnomalie(idAnomalie);
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
	
	@Override
	public List<Intervention> recupereInterventionParType(TypeIntervention type) {
		return proxyIntervention.recupereInterventionparType(type);
	}

	@Override
	public TypeIntervention recupererTypeParIntervention(
			Intervention intervention) {
		return proxyTypeIntervention.recupererTypeParIntervention(intervention);
	}

	@Override
	public List<EtatAvancementTravaux> recupererEtatDisponibles(
			Intervention intervention) {
		return proxyEtatAvancement.recupererEtatDisponibles(intervention);
	}

	@Override
	public EtatAvancementTravaux recupererEtatParIntervention(
			Intervention intervention) {
		return proxyEtatAvancement.recupererEtatParIntervention(intervention);
	}

	@Override
	public int nombreInterventionDiag(int idDiag) {
		return proxyIntervention.nombreInterventionDiag(idDiag);
	}

	public TypeIntervention recupererTypeParID(int idType) {
		return proxyTypeIntervention.recupererTypeParID(idType);
	}

	@Override
	public List<HistoriqueIntervention> recupereHistoriqueInterventionParERP(
			Erp erp) 
	{
		return proxyHistorique.recupereHistoriqueInterventionParERP(erp);
	}
		@Override
	public boolean voirSiInterventionEnCoursParErp(int idErp) {
		int a = 0;
		a = proxyIntervention.nombreInterventionEnCoursPlanifSuspParErp(idErp); 
		if(a>0)
			return true; 
		return false; 
	}
}
