package fr.afcepf.ai93.diag6.controler.gestionnaires;

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
		
		//cr�ation des objets correspondant aux clefs �trang�res dans la table Diagnostic
		//attribution d'un num�ro id temporaire puisque la page xhtml n'est pas encore faite
		Erp erp = new Erp();
		//cinema Marcel Pagnol
		erp.setIdErp(2);
		
		Expert expert = new Expert();
		//expert Tony Opinal
		expert.setIdExpert(8);
		
		TypeDiagnostic typeDiag = new TypeDiagnostic();
		//diagnostic portant sur la s�curit�
		typeDiag.setIdTypeDiagnostic(3);
		
		Date dateSaisie = new Date();
		
		//maintenant on cr�e une instance de Diagnostic
		//et on lui ajoute ses attributs.
		diagnosticAdd = new Diagnostic();
		diagnosticAdd.setErp(erp);
		diagnosticAdd.setExpert(expert);
		diagnosticAdd.setTypeDiagnostic(typeDiag);
		diagnosticAdd.setNomDiagnostiqueur("SteevyS");
		diagnosticAdd.setDateSaisieDiagnostic(dateSaisie);
		diagnosticAdd.setDateRealisationDiagnostic(dateChoisie);
		diagnosticAdd.setIntituleDiagnostic("g�n�r�ParUneM�thodeNonFaite");
		diagnosticAdd.setTraite(0);
		
		try{
			//On ajoute dans la base de donn�es et on rafra�chit la liste de diagnostic � l'�cran
			resultat.proxyBusinessDiag.ajouterDiagnostic(diagnosticAdd);
		}
		catch (Exception e)
		{
			//Si un champ est null, qu'il y ait des caract�res inapropri�s dans les champs date ou co�t, une erreur sera g�n�r�e
			resultat="Erreur lors de l'enregistrement, veuillez v�rifier que toutes les valeurs sont correctement indiqu�es "
					+ "ou r�essayer dans quelques minutes.";
		}
		
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
	
	
	

}
