package fr.afcepf.ai93.diag6.controler.gestionnaires;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import fr.afcepf.ai93.diag6.api.business.diagnostic.IBusinessDiagnostic;
import fr.afcepf.ai93.diag6.api.business.erp.IBusinessErp;
import fr.afcepf.ai93.diag6.api.business.travaux.IBusinessIntervention;
import fr.afcepf.ai93.diag6.entity.diagnostic.Diagnostic;
import fr.afcepf.ai93.diag6.entity.erp.Erp;

@ManagedBean(name="mbRecherche")
@SessionScoped
public class BarreRechercheMultiCritManagedBean implements Serializable  {

	@EJB
	private IBusinessDiagnostic proxyBusinessDiag; 
	@EJB
	private IBusinessIntervention proxyIntervention; 
	@EJB
	private IBusinessErp proxyBusinessErp; 
	
	private String stringCherche; 
	private List<Diagnostic> listeDiagTrouves; 
	private List<Chantier> listeChantiersTrouves = new ArrayList<>(); 
	private List<Erp> listeErpTrouves; 
	
	@PostConstruct
	public void init() {
		stringCherche=""; 
	}

	public void rechercherString(){
		listeDiagTrouves=new ArrayList<>(); 
		listeChantiersTrouves=new ArrayList<>(); 
		listeErpTrouves=new ArrayList<>(); 
		rechercheDiagnostic();
		rechercheErp(); 
		rechercheChantiers(); 
	}
	
	private void rechercheDiagnostic(){
		listeDiagTrouves = proxyBusinessDiag.rechercheDiagnostics(stringCherche); 
		for(Diagnostic d : listeDiagTrouves)
			System.out.println("diagnostic trouvé : "+d.getIntituleDiagnostic());
	}
	
	private void rechercheErp(){
		listeErpTrouves = proxyBusinessErp.recupereErpParNom(stringCherche); 
		for(Erp e : listeErpTrouves)
			System.out.println("erp trouvé :" + e.getNomErp());
	}
	
	private void rechercheChantiers(){
		int i=0; 
		for (Erp e : listeErpTrouves){
			//pour les erp pour lesquels il y a intervention, les rajouter à la liste des chantiers
			if(proxyIntervention.voirSiInterventionEnCoursParErp(e.getIdErp())){ 
				Chantier c = new Chantier(++i,e.getNomErp(),e);
				listeChantiersTrouves.add(c); 
			}
		}
	}
	
	/////////////////////////////////GETTERS ET SETTERS//////////////////////////////////
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

	public String getStringCherche() {
		return stringCherche;
	}

	public void setStringCherche(String stringCherche) {
		this.stringCherche = stringCherche;
	}

	public List<Diagnostic> getListeDiagTrouves() {
		return listeDiagTrouves;
	}

	public void setListeDiagTrouves(List<Diagnostic> listeDiagTrouves) {
		this.listeDiagTrouves = listeDiagTrouves;
	}

	public List<Erp> getListeErpTrouves() {
		return listeErpTrouves;
	}

	public void setListeErpTrouves(List<Erp> listeErpTrouves) {
		this.listeErpTrouves = listeErpTrouves;
	}

	public List<Chantier> getListeChantiersTrouves() {
		return listeChantiersTrouves;
	}

	public void setListeChantiersTrouves(List<Chantier> listeChantiersTrouves) {
		this.listeChantiersTrouves = listeChantiersTrouves;
	}

	public IBusinessIntervention getProxyIntervention() {
		return proxyIntervention;
	}

	public void setProxyIntervention(IBusinessIntervention proxyIntervention) {
		this.proxyIntervention = proxyIntervention;
	}
}
