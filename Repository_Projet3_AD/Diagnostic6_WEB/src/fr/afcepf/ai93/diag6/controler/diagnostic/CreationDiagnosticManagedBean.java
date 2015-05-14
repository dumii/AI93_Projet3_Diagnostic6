package fr.afcepf.ai93.diag6.controler.diagnostic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import fr.afcepf.ai93.diag6.api.business.autres.IBusinessExpert;
import fr.afcepf.ai93.diag6.api.business.autres.IBusinessUtilisateur;
import fr.afcepf.ai93.diag6.api.business.diagnostic.IBusinessAnomalie;
import fr.afcepf.ai93.diag6.api.business.diagnostic.IBusinessDiagnostic;
import fr.afcepf.ai93.diag6.api.business.erp.IBusinessErp;
import fr.afcepf.ai93.diag6.entity.autres.Expert;
import fr.afcepf.ai93.diag6.entity.autres.Utilisateur;
import fr.afcepf.ai93.diag6.entity.diagnostic.Anomalie;
import fr.afcepf.ai93.diag6.entity.diagnostic.Diagnostic;
import fr.afcepf.ai93.diag6.entity.diagnostic.Indicateur;
import fr.afcepf.ai93.diag6.entity.diagnostic.TypeDiagnostic;
import fr.afcepf.ai93.diag6.entity.erp.Acces;
import fr.afcepf.ai93.diag6.entity.erp.Ascenceur;
import fr.afcepf.ai93.diag6.entity.erp.Batiment;
import fr.afcepf.ai93.diag6.entity.erp.Erp;
import fr.afcepf.ai93.diag6.entity.erp.Escalier;
import fr.afcepf.ai93.diag6.entity.erp.Etage;
import fr.afcepf.ai93.diag6.entity.erp.Piece;
import fr.afcepf.ai93.diag6.entity.erp.Voirie;

@ManagedBean(name="mbCreationDiagnostic")
@SessionScoped
public class CreationDiagnosticManagedBean{

	@EJB
	private IBusinessAnomalie proxyAnomalie;
	@EJB
	private IBusinessDiagnostic proxyDiagnostic;
	@EJB
	private IBusinessExpert proxyExpert;
	@EJB
	private IBusinessErp proxyERP;
	@EJB
	private IBusinessUtilisateur proxyUser;
	
	//A conserver
	private List<Erp> listeERP;
	private Erp monERP;
	private String nomErpEntre;
	private Utilisateur user;
	private Date dateSaisie;
	
	private Diagnostic nouveauDiagnostic;
	private int idDiagnostic;
	private Anomalie anomalie;
	
	private List<Expert> listeExperts;
	private Expert expert;
	private int idExpert;
	
	private List<TypeDiagnostic> listeTypeDiag;
	private int idTypeDiagnostic;
	
	private List<Indicateur> listeIndicateur;
	private int idIndicateur;
	
	private List<Anomalie> listeAnomalies;
	
	private List<Batiment> listeBatimentERP;
	private List<Piece> listePiece;
	private int idBatimentSelectionne;
	private int idItemSelectionne;
	
	//Stockage du choix de la première et de la seconde comboBox
	private String choixBatvoirie;
	
	private List<String> listeBatiment;
	private List<String> listeVoirie;
	private List<Item> listeItem;
	
	private String choixAccesEtageAscenceurEscalier;
	private String display;
	private String displayPiece;
	private int idPieceSelectionne;
	
	//Autres à supprimer
	private String resultat;	


	@PostConstruct
	public void init(){
		nomErpEntre = "";
		listeERP = proxyERP.rechercheErpParNom(nomErpEntre);	
		monERP = new Erp();
		user = proxyUser.recupereUtilisateur(2);
		System.out.println(user.getIdUtilisateur());
		System.out.println(user.getNomUtilisateur());
		
		nouveauDiagnostic = new Diagnostic();
		idDiagnostic = proxyDiagnostic.getMaxId() + 1;	
		
		listeAnomalies = new ArrayList<>();	
		
		listeVoirie = new ArrayList<>();		
		listePiece = new ArrayList<>();
		
		listeTypeDiag = proxyDiagnostic.recupereTypeDiagnostic();		
		listeExperts = proxyExpert.recupereToutExpert();
		
		initialisationDonnées();
	}
	//Méthodes	
	public void initialisationDonnées() {
		
		anomalie = new Anomalie();

		choixBatvoirie = "batiment";
		choixAccesEtageAscenceurEscalier = "";
		display = "";
		displayPiece = "";
	}
	
	public List<Item> chargerListeItems()
	{
		listeItem = new ArrayList<>();
		if (choixBatvoirie.equals("batiment"))
		{
			if(choixAccesEtageAscenceurEscalier.equals("Acces"))
			{
				List<Acces> liste1 = proxyERP.recupererAccesParBat(idBatimentSelectionne);
				for (Acces acces : liste1)
				{
					listeItem.add(new Item(acces.getTypeAcces().getLibelleTypeAcces(), acces.getIdAcces()));
				}
			}
			else if(choixAccesEtageAscenceurEscalier.equals("Etage"))
			{
				List<Etage> liste2 = proxyERP.recupererEtagesParBat(idBatimentSelectionne);
				for (Etage etage : liste2)
				{
					listeItem.add(new Item(etage.getNomEtage(), etage.getIdEtage()));
				}
			}
			else if(choixAccesEtageAscenceurEscalier.equals("Escalier"))
			{
				List<Escalier> liste3 = proxyERP.recupererEscaliersParBat(idBatimentSelectionne);
				for (Escalier escalier : liste3)
				{
					listeItem.add(new Item(escalier.getDenominationEscalier(), escalier.getIdEscalier()));
				}
			}
			else if(choixAccesEtageAscenceurEscalier.equals("Ascenceur"))
			{
				List<Ascenceur> liste4 = proxyERP.recupererAscenceursParBat(idBatimentSelectionne);
				for (Ascenceur ascenceur : liste4)
				{
					listeItem.add(new Item(ascenceur.getDenominationAscenceur(), ascenceur.getIdAscenceur()));
				}
			}
		}
		else
		{
			List<Voirie> liste5 = proxyERP.recupererVoirieParErp(monERP.getIdErp());
			for (Voirie voirie : liste5)
			{
				listeItem.add(new Item(voirie.getDesignationVoirie(), voirie.getIdVoirie()));
			}
		}
		return listeItem;
	}

	public List<String> chargerListeStructure()
	{
		if (choixBatvoirie.equals("batiment"))
		{
			display = cacherSelect();
			return listeBatiment;
		}
		else
		{
			display = cacherSelect();
			return listeVoirie;
		}
	}
	
	public String cacherSelect()
	{
		if (choixBatvoirie.equals("batiment"))
		{
			return "";
		}
		else
		{
			return "none";
		}
	}
	
	public String cacherSelectPiece()
	{
		if (listePiece.size() >0 )
		{
			if (choixAccesEtageAscenceurEscalier.equals("Etage"))
			{
				return "";
			}
			else
			{
				return "none";
			}
		}
		else
		{
			return "none";
		}
	}
	
	public void recupererPieceParEtage()
	{
		listePiece = proxyERP.recupererPiecesParEtage(idItemSelectionne);
	}
	
	public void recupererIndicateurParType()
	{
		listeIndicateur = proxyDiagnostic.recupereIndicateurParTypeDiagnostic(idTypeDiagnostic);
	}
	
	public void chargerInfosExpert()
	{
		expert = proxyExpert.recupererExpertParId(idExpert);
	}
	
	public void recupererBatiment()
	{
		listeBatiment = new ArrayList<>();
		listeBatiment.add("Acces");
		listeBatiment.add("Ascenceur");
		listeBatiment.add("Escalier");
		listeBatiment.add("Etage");
	}
	
	public void ajouterAnomalieAuTableau()
	{
		if (choixBatvoirie.equals("batiment"))
		{
			if(choixAccesEtageAscenceurEscalier.equals("Acces"))
			{
				Acces acces = proxyERP.recupereAccesParID(idItemSelectionne);			
				anomalie.setAcces(acces);
			}
			else if(choixAccesEtageAscenceurEscalier.equals("Etage"))
			{
				Piece piece = proxyERP.recuperePieceParID(idItemSelectionne);
				anomalie.setPiece(piece);
			}
			else if(choixAccesEtageAscenceurEscalier.equals("Escalier"))
			{
				Escalier escalier = proxyERP.recupereEscalierParID(idItemSelectionne);				
				anomalie.setEscalier(escalier);
			}
			else if(choixAccesEtageAscenceurEscalier.equals("Ascenceur"))
			{
				Ascenceur ascenceur = proxyERP.recupereAscenceurParID(idItemSelectionne);			
				anomalie.setAscenceur(ascenceur);
			}
		}
		else
		{
			Voirie voirie = proxyERP.recupereVoirieParID(idItemSelectionne);
			anomalie.setVoirie(voirie);
		}
		
		Indicateur indicateur = proxyAnomalie.recupereIndicateurParID(idIndicateur);
		anomalie.setIndicateur(indicateur);
		
		listeAnomalies.add(anomalie); 
		initialisationDonnées();
	}

	public void retirerAnomalieDuTableau(Anomalie anomalieRetire)
	{
		listeAnomalies.remove(anomalieRetire);
	}
	
	public String localisationAnomalieOne(Anomalie anom){
		if(anom.getVoirie() != null)
		{
			return anom.getVoirie().getDesignationVoirie();
		}
		else
		{
			if (anom.getPiece() != null)
			{
				return anom.getPiece().getEtage().getBatiment().getIntituleBatiment();
			}
			else
			{
				if (anom.getAscenceur() != null)
				{
					return anom.getAscenceur().getBatiment().getIntituleBatiment();
				}
				else
				{
					if (anom.getEscalier() != null)
					{
						return anom.getEscalier().getBatiment().getIntituleBatiment();
					}
					else
					{
						return anom.getAcces().getBatiment().getIntituleBatiment();
					}
				}
			} 
		}
	}
	
	public String localisationAnomalieTwo(Anomalie anom){
		if(anom.getVoirie() != null)
		{
			return anom.getVoirie().getTypeVoirie().getLibelleTypeVoirie();
		}
		else
		{
			if (anom.getPiece() != null)
			{
				return anom.getPiece().getDenominationPiece() + " - " + anom.getPiece().getEtage().getNomEtage();
			}
			else
			{
				if (anom.getAscenceur() != null)
				{
					return anom.getAscenceur().getDenominationAscenceur();
				}
				else
				{
					if (anom.getEscalier() != null)
					{
						return anom.getEscalier().getDenominationEscalier();
					}
					else
					{
						return anom.getAcces().getTypeAcces().getLibelleTypeAcces();
					}
				}
			} 
		}
	}

	public void enregistrerDiagnostic()
	{
		monERP = proxyERP.recupererErpParId(monERP.getIdErp());
		monERP.setListeDiagnosticErp(proxyDiagnostic.recupereToutDiagnosticParErp(monERP));
		Date dateSaisie = new Date();
		nouveauDiagnostic.setDateSaisieDiagnostic(dateSaisie);
		nouveauDiagnostic.setTraite(0);
		nouveauDiagnostic.setNomDiagnostiqueur(user.getNomUtilisateur());
		nouveauDiagnostic.setListeAnomaliesDiagnostic(listeAnomalies);	
		nouveauDiagnostic.setErp(monERP);
		monERP.getListeDiagnosticErp().add(nouveauDiagnostic);
		
		init();
	}
	
	public void rechercheErpParNom()
	{
		listeERP = proxyERP.rechercheErpParNom(nomErpEntre);
	}
	
	public void rechercheTypeDiagnosticDispo()
	{
		monERP = proxyERP.recupererErpParId(monERP.getIdErp());
		
		listeBatimentERP = proxyERP.recupererBatParErp(monERP.getIdErp());		
		recupererBatiment();
		chargerListeStructure();
		
		listeTypeDiag = proxyDiagnostic.recupereTypeDiagnosticDospoParERP(monERP);
	}
	
	//Getters et setters

	public IBusinessDiagnostic getProxyDiagnostic() {
		return proxyDiagnostic;
	}

	public void setProxyDiagnostic(IBusinessDiagnostic proxyDiagnostic) {
		this.proxyDiagnostic = proxyDiagnostic;
	}

	public IBusinessErp getProxyERP() {
		return proxyERP;
	}

	public void setProxyERP(IBusinessErp proxyERP) {
		this.proxyERP = proxyERP;
	}

	public Erp getERPSelectionne() {
		return monERP;
	}

	public void setERPSelectionne(Erp eRPSelectionne) {
		monERP = eRPSelectionne;
	}

	public IBusinessAnomalie getProxyAnomalie() {
		return proxyAnomalie;
	}

	public void setProxyAnomalie(IBusinessAnomalie proxyAnomalie) {
		this.proxyAnomalie = proxyAnomalie;
	}

	public List<Anomalie> getListeAnomalies() {
		return listeAnomalies;
	}

	public void setListeAnomalies(List<Anomalie> listeAnomalies) {
		this.listeAnomalies = listeAnomalies;
	}

	public Anomalie getAnomalie() {
		return anomalie;
	}

	public void setAnomalie(Anomalie anomalie) {
		this.anomalie = anomalie;
	}

	public IBusinessAnomalie getProxyBusinessAnom() {
		return proxyAnomalie;
	}

	public void setProxyBusinessAnom(IBusinessAnomalie proxyBusinessAnom) {
		this.proxyAnomalie = proxyBusinessAnom;
	}

	public String getResultat() {
		return resultat;
	}

	public void setResultat(String resultat) {
		this.resultat = resultat;
	}

	public List<Expert> getListeExperts() {
		return listeExperts;
	}

	public void setListeExperts(List<Expert> listeExperts) {
		this.listeExperts = listeExperts;
	}

	public IBusinessExpert getProxyExpert() {
		return proxyExpert;
	}

	public void setProxyExpert(IBusinessExpert proxyExpert) {
		this.proxyExpert = proxyExpert;
	}

	public List<Piece> getListePiece() {
		return listePiece;
	}

	public void setListePiece(List<Piece> listePiece) {
		this.listePiece = listePiece;
	}

	public List<TypeDiagnostic> getListeTypeDiag() {
		return listeTypeDiag;
	}

	public void setListeTypeDiag(List<TypeDiagnostic> listeTypeDiag) {
		this.listeTypeDiag = listeTypeDiag;
	}

	public int getIdBatimentSelectionne() {
		return idBatimentSelectionne;
	}

	public void setIdBatimentSelectionne(int idBatimentSelectionne) {
		this.idBatimentSelectionne = idBatimentSelectionne;
	}
	
	public String getChoixBatvoirie() {
		return choixBatvoirie;
	}

	public void setChoixBatvoirie(String choixBatvoirie) {
		this.choixBatvoirie = choixBatvoirie;
	}

	public String getChoixAccesEtageAscenceurEscalier() {
		return choixAccesEtageAscenceurEscalier;
	}

	public void setChoixAccesEtageAscenceurEscalier(
			String choixAccesEtageAscenceurEscalier) {
		this.choixAccesEtageAscenceurEscalier = choixAccesEtageAscenceurEscalier;
	}

	public Erp getMonERP() {
		return monERP;
	}

	public void setMonERP(Erp monERP) {
		this.monERP = monERP;
	}

	public Diagnostic getNouveauDiagnostic() {
		return nouveauDiagnostic;
	}

	public void setNouveauDiagnostic(Diagnostic nouveauDiagnostic) {
		this.nouveauDiagnostic = nouveauDiagnostic;
	}

	public void setListeBatiment(List<String> listeBatiment) {
		this.listeBatiment = listeBatiment;
	}

	public void setListeVoirie(List<String> listeVoirie) {
		this.listeVoirie = listeVoirie;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public List<String> getListeBatiment() {
		return listeBatiment;
	}

	public List<String> getListeVoirie() {
		return listeVoirie;
	}

	public List<Batiment> getListeBatimentERP() {
		return listeBatimentERP;
	}

	public void setListeBatimentERP(List<Batiment> listeBatimentERP) {
		this.listeBatimentERP = listeBatimentERP;
	}

	public int getIdItemSelectionne() {
		return idItemSelectionne;
	}

	public void setIdItemSelectionne(int idItemSelectionne) {
		this.idItemSelectionne = idItemSelectionne;
	}

	public List<Item> getListeItem() {
		return listeItem;
	}

	public void setListeItem(List<Item> listeItem) {
		this.listeItem = listeItem;
	}

	public String getDisplayPiece() {
		return displayPiece;
	}

	public void setDisplayPiece(String displayPiece) {
		this.displayPiece = displayPiece;
	}

	public int getIdPieceSelectionne() {
		return idPieceSelectionne;
	}

	public void setIdPieceSelectionne(int idPieceSelectionne) {
		this.idPieceSelectionne = idPieceSelectionne;
	}

	public int getIdTypeDiagnostic() {
		return idTypeDiagnostic;
	}

	public void setIdTypeDiagnostic(int idTypeDiagnostic) {
		this.idTypeDiagnostic = idTypeDiagnostic;
	}

	public List<Indicateur> getListeIndicateur() {
		return listeIndicateur;
	}

	public void setListeIndicateur(List<Indicateur> listeIndicateur) {
		this.listeIndicateur = listeIndicateur;
	}

	public int getIdExpert() {
		return idExpert;
	}

	public void setIdExpert(int idExpert) {
		this.idExpert = idExpert;
	}

	public Utilisateur getUser() {
		return user;
	}

	public void setUser(Utilisateur user) {
		this.user = user;
	}

	public Date getDateSaisie() {
		return dateSaisie;
	}

	public void setDateSaisie(Date dateSaisie) {
		this.dateSaisie = dateSaisie;
	}

	public Expert getExpert() {
		return expert;
	}

	public void setExpert(Expert expert) {
		this.expert = expert;
	}
	
	//Classe interne
	public class Item{
		
		private String label;
		private int value;
		
		public Item(String label, int value)
		{
			this.label = label;
			this.value = value;
		}

		public String getLabel() {
			return label;
		}

		public void setLabel(String label) {
			this.label = label;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}
	}

	public int getIdIndicateur() {
		return idIndicateur;
	}
	public void setIdIndicateur(int idIndicateur) {
		this.idIndicateur = idIndicateur;
	}
	public IBusinessUtilisateur getProxyUser() {
		return proxyUser;
	}
	public void setProxyUser(IBusinessUtilisateur proxyUser) {
		this.proxyUser = proxyUser;
	}
	public int getIdDiagnostic() {
		return idDiagnostic;
	}
	public void setIdDiagnostic(int idDiagnostic) {
		this.idDiagnostic = idDiagnostic;
	}
	public List<Erp> getListeERP() {
		return listeERP;
	}
	public void setListeERP(List<Erp> listeERP) {
		this.listeERP = listeERP;
	}
	public String getNomErpEntre() {
		return nomErpEntre;
	}
	public void setNomErpEntre(String nomErpEntre) {
		this.nomErpEntre = nomErpEntre;
	}
}
