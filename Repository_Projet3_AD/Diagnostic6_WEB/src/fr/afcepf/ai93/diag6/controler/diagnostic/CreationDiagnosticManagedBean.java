package fr.afcepf.ai93.diag6.controler.diagnostic;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import fr.afcepf.ai93.diag6.api.business.diagnostic.IBusinessDiagnostic;
import fr.afcepf.ai93.diag6.api.business.erp.IBusinessErp;
import fr.afcepf.ai93.diag6.entity.autres.Expert;
import fr.afcepf.ai93.diag6.entity.diagnostic.Diagnostic;
import fr.afcepf.ai93.diag6.entity.diagnostic.TypeDiagnostic;
import fr.afcepf.ai93.diag6.entity.erp.Erp;

@ManagedBean(name="mbCreationDiagnostic")
@SessionScoped
public class CreationDiagnosticManagedBean {
	@EJB
	private IBusinessDiagnostic proxyBusinessDiag; 
	@EJB
	private IBusinessErp proxyBusinessErp;
	private List<Diagnostic> listeDiag;
	private List<Erp> listeERP; 
	
	private String resultat;
	private Diagnostic diagnosticAdd;
	
	private int idErp;
	private int idExpert;
	private int idTypeDiagnostic;
	//normalement ici d'un dateTimePicker
	private Date dateChoisie;
	
	//Initialisation de la liste diagnostic au chargement de la page
	@PostConstruct
	public void init()
	{
		listeDiag = proxyBusinessDiag.recupereToutDiagnostic();
		listeERP = proxyBusinessErp.recupereToutErp();  
	}
	
	//Ajout d'un diagnostic :
	public String ajouterDiagnostic(){
		System.out.println("début de la méthode");
		System.out.println("1111111111111111111111111111111111111");
		
		//création des objets correspondant aux clefs étrangères dans la table Diagnostic
		//attribution d'un numéro id temporaire puisque la page xhtml n'est pas encore faite
		Erp erp = new Erp();
		//cinema Marcel Pagnol
		erp.setIdErp(2);
		
		Expert expert = new Expert();
		//expert Tony Opinal
		expert.setIdExpert(8);
		
		TypeDiagnostic typeDiag = new TypeDiagnostic();
		//diagnostic portant sur la sécurité
		typeDiag.setIdTypeDiagnostic(3);
		
		Date dateSaisie = new Date();
		
		//maintenant on crée une instance de Diagnostic
		//et on lui ajoute ses attributs.
		diagnosticAdd = new Diagnostic();
		diagnosticAdd.setErp(erp);
		diagnosticAdd.setExpert(expert);
		diagnosticAdd.setTypeDiagnostic(typeDiag);
		diagnosticAdd.setNomDiagnostiqueur("SteevyS");
		diagnosticAdd.setDateSaisieDiagnostic(dateSaisie);
		diagnosticAdd.setDateRealisationDiagnostic(new Date());
		diagnosticAdd.setIntituleDiagnostic("Louis Test Creation Diagnostic ");
		diagnosticAdd.setTraite(0);
		
		try{
			//On ajoute dans la base de données et on rafraîchit la liste de diagnostic à l'écran
			resultat = proxyBusinessDiag.ajouterDiagnostic(diagnosticAdd);
		}
		catch (Exception e)
		{
			//Si un champ est null, qu'il y ait des caractères inapropriés dans les champs date ou coût, une erreur sera générée
			resultat="Erreur lors de l'enregistrement, veuillez vérifier que toutes les valeurs sont correctement indiquées "
					+ "ou réessayer dans quelques minutes.";
		}
		init();
		System.out.println("*****************************************************************************");
		System.err.println(resultat);
		System.out.println("*****************************************************************************");
		return "";
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
	public String getResultat() {
		return resultat;
	}
	public void setResultat(String resultat) {
		this.resultat = resultat;
	}
	public Diagnostic getDiagnosticAdd() {
		return diagnosticAdd;
	}
	public void setDiagnosticAdd(Diagnostic diagnosticAdd) {
		this.diagnosticAdd = diagnosticAdd;
	}
	public int getIdErp() {
		return idErp;
	}
	public void setIdErp(int idErp) {
		this.idErp = idErp;
	}
	public int getIdExpert() {
		return idExpert;
	}
	public void setIdExpert(int idExpert) {
		this.idExpert = idExpert;
	}
	public int getIdTypeDiagnostic() {
		return idTypeDiagnostic;
	}
	public void setIdTypeDiagnostic(int idTypeDiagnostic) {
		this.idTypeDiagnostic = idTypeDiagnostic;
	}
	public Date getDateChoisie() {
		return dateChoisie;
	}
	public void setDateChoisie(Date dateChoisie) {
		this.dateChoisie = dateChoisie;
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
	
	
	

}
