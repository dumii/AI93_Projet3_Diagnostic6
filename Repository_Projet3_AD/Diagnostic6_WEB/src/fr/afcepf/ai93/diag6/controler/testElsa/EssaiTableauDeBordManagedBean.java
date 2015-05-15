package fr.afcepf.ai93.diag6.controler.testElsa;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import fr.afcepf.ai93.diag6.api.business.autres.IBusinessArtisan;
import fr.afcepf.ai93.diag6.api.business.diagnostic.IBusinessAnomalie;
import fr.afcepf.ai93.diag6.api.business.diagnostic.IBusinessDiagnostic;
import fr.afcepf.ai93.diag6.api.business.erp.IBusinessErp;
import fr.afcepf.ai93.diag6.api.business.travaux.IBusinessIntervention;
import fr.afcepf.ai93.diag6.entity.autres.Utilisateur;
import fr.afcepf.ai93.diag6.entity.diagnostic.Anomalie;
import fr.afcepf.ai93.diag6.entity.diagnostic.Diagnostic;
import fr.afcepf.ai93.diag6.entity.erp.Erp;
import fr.afcepf.ai93.diag6.entity.erp.TypeErp;
import fr.afcepf.ai93.diag6.entity.travaux.Intervention;

@ManagedBean(name="mbTestTableauBord")
@SessionScoped
public class EssaiTableauDeBordManagedBean {

	@EJB
	private IBusinessIntervention proxyIntervention;
	@EJB
	private IBusinessDiagnostic proxyDiagnostic;
	@EJB
	private IBusinessAnomalie proxyAnomalie;	
	@EJB
	private IBusinessArtisan proxyArtisan;
	@EJB
	private IBusinessErp proxyERP;
	
	private List<Erp> listeBrute;
	private List<Erp> listeNette;
	private List<Erp> listeNetteAffichee;
	private static final int MAX_INDICATEUR_TYPE_1 = 4;
	private static final int MAX_INDICATEUR_TYPE_2 = 7;
	private static final int MAX_INDICATEUR_TYPE_3 = 2;
	private static final int MAX_INDICATEUR_TYPE_4 = 3;
	
	private List<TypeErp> listeTypes;
	private int idTypeSelectionne;
	
	
	//ERP // Diagnostic // Statistiques[0] Indicateur moyen / Statistiques[1] Etat d'avancement
	private int tableau [][][];
	
	@PostConstruct
	public void init()
	{
		listeBrute = proxyERP.recupereToutErp();
		listeTypes = proxyERP.recupererListeTypeERP();
		
		listeNette = new ArrayList<>();
		
		idTypeSelectionne = 0;
		initialisationDesDonnees();
	}
	
	//Méthodes	
	public void initialisationDesDonnees()
	{
		tableau = new int[listeBrute.size()][4][3];
		
		int indexERP = 0;
		
		for (Erp erp : listeBrute)
		{			
			erp.setListeDiagnosticErp(proxyDiagnostic.recupereToutDiagnosticParErp(erp));
			
			int indexDiagnostic = 0;
			
			for (Diagnostic diagnostic : erp.getListeDiagnosticErp())
			{
				int idTypeDiagnostic = diagnostic.getTypeDiagnostic().getIdTypeDiagnostic();
				
				
				int sommeIndicateur = 0;
				
				int nombreInterventions = 0;
				int nombreTermines = 0;
				
				diagnostic.setListeAnomaliesDiagnostic(proxyAnomalie.recupereAnomalieParDiagnostic(diagnostic.getIdDiagnostic()));
				
				for (Anomalie anomalie : diagnostic.getListeAnomaliesDiagnostic())
				{
					anomalie.setIndicateur(proxyAnomalie.recupereIndicateurParID(anomalie.getIndicateur().getIdIndicateur()));
					sommeIndicateur += anomalie.getIndicateur().getValeurIndicateur();
					
					List<Intervention> listeIntervention = proxyIntervention.rechercherInterventionSurAnomalie(anomalie.getIdAnomalie()); 
					if (listeIntervention.size() > 0)
					{
						Intervention intervention = listeIntervention.get(0);
						int idEtatAvancement = intervention.getEtatAvancementTravaux().getIdEtatAvancement();
						
						nombreInterventions++;
						
						if (idEtatAvancement == 1)
						{
							nombreTermines++;
						}
					}
				}
				
				int moyenneIndicateur = 0;
				
				if (diagnostic.getListeAnomaliesDiagnostic().size() > 0)
				{
					switch (idTypeDiagnostic) {
						case 1: moyenneIndicateur = (sommeIndicateur*4) / (MAX_INDICATEUR_TYPE_1*diagnostic.getListeAnomaliesDiagnostic().size());				
						break;
						case 2: moyenneIndicateur = (sommeIndicateur*4) / (MAX_INDICATEUR_TYPE_2*diagnostic.getListeAnomaliesDiagnostic().size());					
						break;
						case 3: moyenneIndicateur = (sommeIndicateur*4) / (MAX_INDICATEUR_TYPE_3*diagnostic.getListeAnomaliesDiagnostic().size());			
						break;
						case 4: moyenneIndicateur = (sommeIndicateur*4) / (MAX_INDICATEUR_TYPE_4*diagnostic.getListeAnomaliesDiagnostic().size());		
						break;
					}
				}
				
				int idEtatAvancement = terminesOuTravauxEnCours(nombreTermines, nombreInterventions);
				
				//Indicateur moyen
				tableau[indexERP][indexDiagnostic][0] = moyenneIndicateur;
				//Etat d'avancement moyen
				tableau[indexERP][indexDiagnostic][1] = idEtatAvancement;
				//Nombre d'interventions
				tableau[indexERP][indexDiagnostic][2] = nombreInterventions;
				
				indexDiagnostic++;
			}
			indexERP++;
			
			if (erp.getListeDiagnosticErp().size() > 0)
			{
				listeNette.add(erp);
			}
		}
		listeNetteAffichee = listeNette;
	}
	
	public void filtresERP()
	{
		//la liste nette affichée est régénérée à chaque fois
		listeNetteAffichee = new ArrayList<>();
		
		//Et on teste chacun des filtres
		if (idTypeSelectionne != 0)
		{
			for (Erp erp : listeNette)
			{
				if (erp.getTypeErp().getIdTypeErp() == idTypeSelectionne)
				{
					listeNetteAffichee.add(erp);
				}
			}
		}
	}

	public int terminesOuTravauxEnCours(int nombreTermines, int nombreTotal)
	{		
		if (nombreTotal == 0)
		{
			//Pas de travaux
			return 4;
		}
		else if (nombreTermines == nombreTotal)
		{
			//Terminés
			return 1;
		}
		else
		{
			//En attente ou suspendus
			return 2;
		}
	}
	
	public String imageTypeDiagnostic (int idTypeDiagnostic)
	{
		switch (idTypeDiagnostic) {
			case 1: return "images/TypesDiagnosticIcones/TypeAcces.75.71.png";
			case 2: return "images/TypesDiagnosticIcones/TypeEnergie.75.71.png";
			case 3: return "images/TypesDiagnosticIcones/TypeSecurite.75.75.png";
			case 4: return "images/TypesDiagnosticIcones/TypeHygiene.75.71.png";
			default : return "images/TypesDiagnosticIcones/TypeAcces.75.71.png";
			//prévoir icône pas de travaux
		}
	}
	
	public String imageIndicateurMoyen (int indicateur)
	{
		switch (indicateur) {
		case 1: return "images/tableauDeBord/Indicateur1.png";
		case 2: return "images/tableauDeBord/Indicateur2.png";
		case 3: return "images/tableauDeBord/Indicateur3.png";
		case 4: return "images/tableauDeBord/Indicateur4.png";
		default : return "images/tableauDeBord/Indicateur1.png";
		//prévoir icône pas d'anomalie
		}
	}
	
	public String imageEtatAvancement(int idEtatAvancement)
	{		
		switch (idEtatAvancement) {
		case 1: return "images/tableauDeBord/iconeValider5.png";
		case 2: return "images/tableauDeBord/iconeEnCours5.png";
		case 4: return "images/tableauDeBord/iconeEnAttente5.png";
		default : return "images/tableauDeBord/iconeEnAttente5.png";		
		}
	}

	public IBusinessIntervention getProxyIntervention() {
		return proxyIntervention;
	}

	public void setProxyIntervention(IBusinessIntervention proxyIntervention) {
		this.proxyIntervention = proxyIntervention;
	}

	public IBusinessDiagnostic getProxyDiagnostic() {
		return proxyDiagnostic;
	}

	public void setProxyDiagnostic(IBusinessDiagnostic proxyDiagnostic) {
		this.proxyDiagnostic = proxyDiagnostic;
	}

	public IBusinessAnomalie getProxyAnomalie() {
		return proxyAnomalie;
	}

	public void setProxyAnomalie(IBusinessAnomalie proxyAnomalie) {
		this.proxyAnomalie = proxyAnomalie;
	}

	public IBusinessArtisan getProxyArtisan() {
		return proxyArtisan;
	}

	public void setProxyArtisan(IBusinessArtisan proxyArtisan) {
		this.proxyArtisan = proxyArtisan;
	}

	public IBusinessErp getProxyERP() {
		return proxyERP;
	}

	public void setProxyERP(IBusinessErp proxyERP) {
		this.proxyERP = proxyERP;
	}

	public List<Erp> getListeBrute() {
		return listeBrute;
	}

	public void setListeBrute(List<Erp> listeBrute) {
		this.listeBrute = listeBrute;
	}

	public List<Erp> getListeNette() {
		return listeNette;
	}

	public void setListeNette(List<Erp> listeNette) {
		this.listeNette = listeNette;
	}

	public int[][][] getTableau() {
		return tableau;
	}

	public void setTableau(int[][][] tableau) {
		this.tableau = tableau;
	}

	public static int getMaxIndicateurType1() {
		return MAX_INDICATEUR_TYPE_1;
	}

	public static int getMaxIndicateurType2() {
		return MAX_INDICATEUR_TYPE_2;
	}

	public static int getMaxIndicateurType3() {
		return MAX_INDICATEUR_TYPE_3;
	}

	public static int getMaxIndicateurType4() {
		return MAX_INDICATEUR_TYPE_4;
	}

	public List<Erp> getListeNetteAffichee() {
		return listeNetteAffichee;
	}

	public void setListeNetteAffichee(List<Erp> listeNetteAffichee) {
		this.listeNetteAffichee = listeNetteAffichee;
	}

	public List<TypeErp> getListeTypes() {
		return listeTypes;
	}

	public void setListeTypes(List<TypeErp> listeTypes) {
		this.listeTypes = listeTypes;
	}

	public int getIdTypeSelectionne() {
		return idTypeSelectionne;
	}

	public void setIdTypeSelectionne(int idTypeSelectionne) {
		this.idTypeSelectionne = idTypeSelectionne;
	}
	
	//Getters et Setters
	
	
}
