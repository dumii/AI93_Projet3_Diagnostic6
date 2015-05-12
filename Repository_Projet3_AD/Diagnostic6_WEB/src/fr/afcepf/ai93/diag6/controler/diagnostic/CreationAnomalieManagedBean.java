package fr.afcepf.ai93.diag6.controler.diagnostic;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import fr.afcepf.ai93.diag6.api.business.diagnostic.IBusinessAnomalie;
import fr.afcepf.ai93.diag6.api.business.diagnostic.IBusinessDiagnostic;
import fr.afcepf.ai93.diag6.api.business.erp.IBusinessErp;
import fr.afcepf.ai93.diag6.entity.diagnostic.Anomalie;
import fr.afcepf.ai93.diag6.entity.diagnostic.Diagnostic;
import fr.afcepf.ai93.diag6.entity.diagnostic.Indicateur;
import fr.afcepf.ai93.diag6.entity.erp.Acces;
import fr.afcepf.ai93.diag6.entity.erp.Ascenceur;
import fr.afcepf.ai93.diag6.entity.erp.Erp;
import fr.afcepf.ai93.diag6.entity.erp.Escalier;
import fr.afcepf.ai93.diag6.entity.erp.Piece;
import fr.afcepf.ai93.diag6.entity.erp.Voirie;

@ManagedBean(name="mbCreationAnomalie")
@SessionScoped
public class CreationAnomalieManagedBean {
	
	@EJB
	private IBusinessAnomalie proxyBusinessAnom;
	@EJB
	private IBusinessDiagnostic proxyBusinessDiag;
	@EJB
	private IBusinessErp proxyBusinessErp;
	
	private List<Anomalie> listeAnom;
	private List<Diagnostic> listeDiag;
	private List<Erp> listeERP; 
	
	private String resultat;
	private Anomalie anomalieAdd;
	//Les clefs �trang�res
	private int idIndicateur;
	private int idAscenceur;
	private int idVoirie;
	private int idAcces;
	private int idEscalier;
	private int idPiece;
	private int idAnomalie;
	
	//Initialisation des listes au chargement de la page
	@PostConstruct
	public void init()
	{
		listeAnom = proxyBusinessAnom.recupereToutAnomalie();
		listeDiag = proxyBusinessDiag.recupereToutDiagnostic();
		listeERP = proxyBusinessErp.recupereToutErp();  
	}
	
	//Enregistrement de l'anomalie
	public String ajouterAnomalie()
	{
		System.out.println("d�but de la m�thode");
		System.out.println("1111111111111111111111111111111111111");
		
		//cr�ation des objets correspondant aux clefs �trang�res dans la table Diagnostic
		//attribution d'un num�ro id temporaire puisque la page xhtml n'est pas encore faite
		
		//indicateur positionnn� en "non-conforme"
		Indicateur indic = new Indicateur();
		indic.setIdIndicateur(13);
		
		Ascenceur ascen = new Ascenceur();
		
		Voirie voirie = new Voirie();
		
		Acces acces = new Acces();
		
		Escalier esca = new Escalier();
		
		//on va mettre le soucis de s�curit� dans une pi�ce, la chaufferie au 1er �tage (id 8)
		Piece piece = new Piece();
		piece.setIdPiece(26);
		
		//s�lection du premier des diag test cr�es sur l'Erp 2 Cinema Marcel Pagnol
		Diagnostic diag = new Diagnostic();
		diag.setIdDiagnostic(15);
		
		//maintenant on cr�e une instance d'Anomalie et on lui ajoute ses attributs.
		anomalieAdd = new Anomalie();
		anomalieAdd.setDiagnostic(diag);
		anomalieAdd.setIndicateur(indic);
		anomalieAdd.setPiece(piece);
		anomalieAdd.setDescriptionAnomalie("Il y a une fuite d'uranium dans le r�acteur droit");
		anomalieAdd.setPreconisationAnomalie("il faut mettre du gros scotch marron sur le trou");
		anomalieAdd.setCoutEstimeAnomalie(4.99);
		anomalieAdd.setTraite(0);
		
		try{
			//On ajoute dans la base de donn�es et on rafra�chit la liste de diagnostic � l'�cran
			resultat = proxyBusinessAnom.ajouterAnomalie(anomalieAdd);
		}
		catch (Exception e)
		{
			//Si un champ est null, qu'il y ait des caract�res inapropri�s dans les champs date ou co�t, une erreur sera g�n�r�e
			resultat="Erreur lors de l'enregistrement, veuillez v�rifier que toutes les valeurs sont correctement indiqu�es "
					+ "ou r�essayer dans quelques minutes.";
		}
		init();
		System.out.println("*****************************************************************************");
		System.err.println(resultat);
		System.out.println("*****************************************************************************");
		return "";
		
		
	}

	public IBusinessAnomalie getProxyBusinessAnom() {
		return proxyBusinessAnom;
	}

	public void setProxyBusinessAnom(IBusinessAnomalie proxyBusinessAnom) {
		this.proxyBusinessAnom = proxyBusinessAnom;
	}

	public IBusinessDiagnostic getProxyBusinessDiag() {
		return proxyBusinessDiag;
	}

	public void setProxyBusinessDiag(IBusinessDiagnostic proxyBusinessDiag) {
		this.proxyBusinessDiag = proxyBusinessDiag;
	}

	public IBusinessErp getProxyBusinessErp() {
		return proxyBusinessErp;
	}

	public void setProxyBusinessErp(IBusinessErp proxyBusinessErp) {
		this.proxyBusinessErp = proxyBusinessErp;
	}

	public List<Anomalie> getListeAnom() {
		return listeAnom;
	}

	public void setListeAnom(List<Anomalie> listeAnom) {
		this.listeAnom = listeAnom;
	}

	public List<Diagnostic> getListeDiag() {
		return listeDiag;
	}

	public void setListeDiag(List<Diagnostic> listeDiag) {
		this.listeDiag = listeDiag;
	}

	public List<Erp> getListeERP() {
		return listeERP;
	}

	public void setListeERP(List<Erp> listeERP) {
		this.listeERP = listeERP;
	}

	public String getResultat() {
		return resultat;
	}

	public void setResultat(String resultat) {
		this.resultat = resultat;
	}

	public Anomalie getAnomalieAdd() {
		return anomalieAdd;
	}

	public void setAnomalieAdd(Anomalie anomalieAdd) {
		this.anomalieAdd = anomalieAdd;
	}

	public int getIdIndicateur() {
		return idIndicateur;
	}

	public void setIdIndicateur(int idIndicateur) {
		this.idIndicateur = idIndicateur;
	}

	public int getIdAscenceur() {
		return idAscenceur;
	}

	public void setIdAscenceur(int idAscenceur) {
		this.idAscenceur = idAscenceur;
	}

	public int getIdVoirie() {
		return idVoirie;
	}

	public void setIdVoirie(int idVoirie) {
		this.idVoirie = idVoirie;
	}

	public int getIdAcces() {
		return idAcces;
	}

	public void setIdAcces(int idAcces) {
		this.idAcces = idAcces;
	}

	public int getIdEscalier() {
		return idEscalier;
	}

	public void setIdEscalier(int idEscalier) {
		this.idEscalier = idEscalier;
	}

	public int getIdPiece() {
		return idPiece;
	}

	public void setIdPiece(int idPiece) {
		this.idPiece = idPiece;
	}

	public int getIdAnomalie() {
		return idAnomalie;
	}

	public void setIdAnomalie(int idAnomalie) {
		this.idAnomalie = idAnomalie;
	}
	
	

}
