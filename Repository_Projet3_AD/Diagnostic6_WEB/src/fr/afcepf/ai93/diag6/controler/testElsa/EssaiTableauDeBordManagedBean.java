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
import fr.afcepf.ai93.diag6.entity.diagnostic.TypeDiagnostic;
import fr.afcepf.ai93.diag6.entity.erp.Erp;
import fr.afcepf.ai93.diag6.entity.erp.TypeErp;
import fr.afcepf.ai93.diag6.entity.travaux.EtatAvancementTravaux;
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
	
	//Partie filtre
	private String nomErpEntre;
	
	private List<TypeErp> listeTypes;
	private int idTypeSelectionne;
	
	private List<TypeDiagnostic> listeTypesDiagnostic;
	private int idTypeDiagSelectionne;
	
	private List<Integer> listeIndicateur;
	private int idIndicateurSelectionne;
	
	private List<EtatAvancementTravaux> listeEtatAvancement;
	private int idEtatAvancement;
	
	
	//ERP // Diagnostic // Statistiques[0] Indicateur moyen / Statistiques[1] Etat d'avancement
	private int tableau [][][];
	
	@PostConstruct
	public void init()
	{
		listeBrute = proxyERP.recupereToutErp();
		listeNette = new ArrayList<>();
		
		//Filtres
		listeTypes = proxyERP.recupererListeTypeERP();
		listeTypesDiagnostic = proxyDiagnostic.recupereTypeDiagnostic();
		listeEtatAvancement = proxyIntervention.recupererTousEtats();
		//L'état suspendu n'est pas représenté sur le tableau de bord
		//On le retire de la liste donc
		listeEtatAvancement.remove(1);
		
		listeIndicateur = new ArrayList<>();
		listeIndicateur.add(1);
		listeIndicateur.add(2);
		listeIndicateur.add(3);
		listeIndicateur.add(4);
		
		//Filtres sélectionnés
		idEtatAvancement = 0;
		idIndicateurSelectionne = 0;
		idTypeDiagSelectionne = 0;
		idTypeSelectionne = 0;
		nomErpEntre = "";
		
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
				int nombreEnCours = 0;
				
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
						
						if (idEtatAvancement == 3 || idEtatAvancement == 4)
						{
							nombreEnCours++;
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
				
				int idEtatAvancement = terminesOuTravauxEnCours(nombreTermines, nombreInterventions, nombreEnCours);
				
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
		
		boolean afficherDansResutlat = true;
		//Et on teste chacun des filtres pour chacun des erp
		for (Erp erp : listeNette)
		{			
			List<Diagnostic> listeDiagnostic = new ArrayList<>();
			
			afficherDansResutlat = filtreNomEmp(erp);
			
			if (afficherDansResutlat)
			{			
				afficherDansResutlat = filtreTypeErp(erp);
			}
			
			if (afficherDansResutlat)
			{				
				listeDiagnostic = filtresDiagnostic(erp);
				
				if (listeDiagnostic.size() == 0)
				{
					afficherDansResutlat = false;
				}
			}
			
			if (afficherDansResutlat)
			{
				listeNetteAffichee.add(erp);
			}
		}
	}
	
	public List<Diagnostic> filtresDiagnostic(Erp erp)
	{	
		List<Diagnostic> liste = new ArrayList<>();

		for (Diagnostic diagnostic : erp.getListeDiagnosticErp())
		{
			boolean afficherDansResutlat = true;
			
			afficherDansResutlat = filtreTypeDiagnostic(diagnostic);
			
			if (afficherDansResutlat)
			{
				afficherDansResutlat = filtreIndicateurMoyenDiagnsotic(erp, diagnostic);
			}
			
			if (afficherDansResutlat)
			{
				afficherDansResutlat = filtreEtatAvancementTravaux(erp, diagnostic);
			}
			
			if (afficherDansResutlat)
			{
				liste.add(diagnostic);
			}
		}
		return liste;
	}
	
	public boolean filtreNomEmp(Erp erp)
	{
		if (!nomErpEntre.equals(""))
		{
			if (toLowerCasePerso(erp.getNomErp()).contains(toLowerCasePerso(nomErpEntre)))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return true;
		}
	}
	
	public boolean filtreTypeErp(Erp erp)
	{
		if (idTypeSelectionne != 0)
		{
			if (erp.getTypeErp().getIdTypeErp() == idTypeSelectionne)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return true;
		}
	}
	
	public boolean filtreTypeDiagnostic(Diagnostic diagnostic)
	{
		if (idTypeDiagSelectionne != 0)
		{
			if (diagnostic.getTypeDiagnostic().getIdTypeDiagnostic() == idTypeDiagSelectionne)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return true;
		}
	}
	
	public boolean filtreIndicateurMoyenDiagnsotic(Erp erp, Diagnostic diagnostic)
	{
		if (idIndicateurSelectionne != 0)
		{
			int indexErp = listeBrute.indexOf(erp);
			int indexDiag = erp.getListeDiagnosticErp().indexOf(diagnostic);
			
			int indicateurDiagnostic = tableau[indexErp][indexDiag][0];
			
			if (indicateurDiagnostic == idIndicateurSelectionne)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return true;
		}
	}
	
	public boolean filtreEtatAvancementTravaux(Erp erp, Diagnostic diagnostic)
	{
		if (idEtatAvancement != 0)
		{
			int indexErp = listeBrute.indexOf(erp);
			int indexDiag = erp.getListeDiagnosticErp().indexOf(diagnostic);
			
			int etatAvancement = tableau[indexErp][indexDiag][1];
			
			if (etatAvancement == idEtatAvancement)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return true;
		}
	}

	public String displayDiagnostic(Erp erp, Diagnostic diagnostic)
	{
		boolean display = true;
		
		display = filtreTypeDiagnostic(diagnostic);
		
		if (display)
		{
			display = filtreIndicateurMoyenDiagnsotic(erp, diagnostic);
		}
		
		if (display)
		{
			display = filtreEtatAvancementTravaux(erp, diagnostic);
		}
		
		if (display)
		{
			return "";
		}
		else
		{
			return "none";
		}
	}
	
	public int terminesOuTravauxEnCours(int nombreTermines, int nombreTotal, int nombreEnCours)
	{		
		if (nombreTotal == 0)
		{
			//Pas de travaux
			return 0;
		}
		else if (nombreTermines == nombreTotal)
		{
			//Terminés
			return 1;
		}
		else if (nombreEnCours > 0)
		{
			//Cours
			return 3;
		}
		else
		{
			//Travaux en attente ou suspendus
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
		case 4: return "images/tableauDeBord/Indicateur1.png";
		case 3: return "images/tableauDeBord/Indicateur2.png";
		case 2: return "images/tableauDeBord/Indicateur3.png";
		case 1: return "images/tableauDeBord/Indicateur4.png";
		default : return "images/tableauDeBord/Indicateur1.png";
		//prévoir icône pas d'anomalie
		}
	}
	
	public String imageEtatAvancement(int idEtatAvancement)
	{		
		switch (idEtatAvancement) {
		case 1: return "images/tableauDeBord/iconeValider5.png";
		case 3: return "images/tableauDeBord/iconeEnCours5.png";
		case 2: return "images/tableauDeBord/iconeEnAttente5.png";
		case 0: return "images/tableauDeBord/pasDeTravauxTableauBord.50.50.png";
		default : return "images/tableauDeBord/pasDeTravauxTableauBord.50.50.png";		
		}
	}
	
	static public String toLowerCasePerso (String string)
	{
		char [] charsData = new char [string.length ()];
		string.getChars (0, charsData.length, charsData, 0);

		char c;
		for (int i = 0; i < charsData.length; i++) 
			if ( (c = charsData [i]) >= 'A' && c <= 'Z')
				charsData [i] = (char)(c - 'A' + 'a');
			else
				switch (c)
				{
				case '\u00e0' :
				case '\u00e2' :
				case '\u00e4' : charsData [i] = 'a';
				break;
				case '\u00e7' : charsData [i] = 'c';
				break;
				case '\u00e8' :
				case '\u00e9' :
				case '\u00ea' :
				case '\u00eb' : charsData [i] = 'e';
				break;
				case '\u00ee' :
				case '\u00ef' : charsData [i] = 'i';
				break;
				case '\u00f4' :
				case '\u00f6' : charsData [i] = 'o';
				break;
				case '\u00f9' :
				case '\u00fb' :
				case '\u00fc' : charsData [i] = 'u';
				break;
				}

		return new String (charsData);
	}

	//Getters et Setters

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

	public String getNomErpEntre() {
		return nomErpEntre;
	}

	public void setNomErpEntre(String nomErpEntre) {
		this.nomErpEntre = nomErpEntre;
	}

	public List<TypeDiagnostic> getListeTypesDiagnostic() {
		return listeTypesDiagnostic;
	}

	public void setListeTypesDiagnostic(List<TypeDiagnostic> listeTypesDiagnostic) {
		this.listeTypesDiagnostic = listeTypesDiagnostic;
	}

	public int getIdTypeDiagSelectionne() {
		return idTypeDiagSelectionne;
	}

	public void setIdTypeDiagSelectionne(int idTypeDiagSelectionne) {
		this.idTypeDiagSelectionne = idTypeDiagSelectionne;
	}

	public List<Integer> getListeIndicateur() {
		return listeIndicateur;
	}

	public void setListeIndicateur(List<Integer> listeIndicateur) {
		this.listeIndicateur = listeIndicateur;
	}

	public int getIdIndicateurSelectionne() {
		return idIndicateurSelectionne;
	}

	public void setIdIndicateurSelectionne(int idIndicateurSelectionne) {
		this.idIndicateurSelectionne = idIndicateurSelectionne;
	}

	public List<EtatAvancementTravaux> getListeEtatAvancement() {
		return listeEtatAvancement;
	}

	public void setListeEtatAvancement(
			List<EtatAvancementTravaux> listeEtatAvancement) {
		this.listeEtatAvancement = listeEtatAvancement;
	}

	public int getIdEtatAvancement() {
		return idEtatAvancement;
	}

	public void setIdEtatAvancement(int idEtatAvancement) {
		this.idEtatAvancement = idEtatAvancement;
	}
}
