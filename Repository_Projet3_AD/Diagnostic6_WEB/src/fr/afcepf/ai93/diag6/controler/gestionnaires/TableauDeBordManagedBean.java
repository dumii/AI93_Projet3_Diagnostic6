package fr.afcepf.ai93.diag6.controler.gestionnaires;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import fr.afcepf.ai93.diag6.api.business.diagnostic.IBusinessAnomalie;
import fr.afcepf.ai93.diag6.api.business.diagnostic.IBusinessDiagnostic;
import fr.afcepf.ai93.diag6.api.business.erp.IBusinessErp;
import fr.afcepf.ai93.diag6.api.business.travaux.IBusinessIntervention;
import fr.afcepf.ai93.diag6.entity.diagnostic.Anomalie;
import fr.afcepf.ai93.diag6.entity.diagnostic.Diagnostic;
import fr.afcepf.ai93.diag6.entity.diagnostic.Indicateur;
import fr.afcepf.ai93.diag6.entity.diagnostic.TypeDiagnostic;
import fr.afcepf.ai93.diag6.entity.erp.CategorieErp;
import fr.afcepf.ai93.diag6.entity.erp.Erp;
import fr.afcepf.ai93.diag6.entity.erp.TypeErp;
import fr.afcepf.ai93.diag6.entity.travaux.EtatAvancementTravaux;

@ManagedBean(name="mbTableauDeBord")
@SessionScoped
public class TableauDeBordManagedBean {
	
	@EJB 
	private IBusinessErp proxyBusinessErp; 
	@EJB
	private IBusinessDiagnostic proxyBusinessDiagnostic;
	@EJB
	private IBusinessAnomalie proxyBusinessAnomalie; 
	@EJB
	private IBusinessIntervention proxyBusinessIntervention;
	
	private List<Erp> listeErpComplete; 
	private List<TypeDiagnostic> listeTypesDiagnosticComplete; 
	private List<TypeErp> listeTypesErpComplete; 
	private List<CategorieErp> listeCategoriesErpComplete; 
	private List<Indicateur> listeIndicateursParDiag; 
	private List<EtatAvancementTravaux> listeEtatsComplete; 
	
	private void recupererTousLesErp(){
		listeErpComplete = proxyBusinessErp.recupereToutErp(); 
	}
	
	private void recupererTypesDiagnostic(){
		listeTypesDiagnosticComplete = proxyBusinessDiagnostic.recupereTypeDiagnostic();
	}
	
	private void recupererTypesErp(){
		listeTypesErpComplete = proxyBusinessErp.recupererToutTypeErp(); 
	}
	
	private void recupererCategorieErp(){
		listeCategoriesErpComplete = proxyBusinessErp.recupererToutCategorieErp();
	}
	
	private void recupererIndicateursParDiag(Diagnostic diag){
		listeIndicateursParDiag = proxyBusinessDiagnostic.recupererIndicateursParDiag(diag);
	}
	
	private void recupererEtatsAvancementTravaux(){
		listeEtatsComplete = proxyBusinessIntervention.recupererTousEtats(); 
		listeEtatsComplete.add(new EtatAvancementTravaux(10, "Sans intervention")); 
	}
	
	private int calculAnomaliesParDiag(Diagnostic d){
		List<Anomalie> listeAnomaliesParDiag = proxyBusinessAnomalie.recupereAnomalieParDiagnostic(d.getIdDiagnostic());
		return listeAnomaliesParDiag.size(); 
	}
	
	private void calculInterventionsParDiag(Diagnostic d){
		
	}
	
	private void calculerMoyenneParDiag(Diagnostic d){
		
	}
	
////////////////////////getters et setters ////////////////////

	public IBusinessErp getProxyBusinessErp() {
		return proxyBusinessErp;
	}
	public void setProxyBusinessErp(IBusinessErp proxyBusinessErp) {
		this.proxyBusinessErp = proxyBusinessErp;
	}
	public IBusinessDiagnostic getProxyBusinessDiagnostic() {
		return proxyBusinessDiagnostic;
	}
	public void setProxyBusinessDiagnostic(
			IBusinessDiagnostic proxyBusinessDiagnostic) {
		this.proxyBusinessDiagnostic = proxyBusinessDiagnostic;
	}
	public IBusinessAnomalie getProxyBusinessAnomalie() {
		return proxyBusinessAnomalie;
	}
	public void setProxyBusinessAnomalie(IBusinessAnomalie proxyBusinessAnomalie) {
		this.proxyBusinessAnomalie = proxyBusinessAnomalie;
	}
	public IBusinessIntervention getProxyBusinessIntervention() {
		return proxyBusinessIntervention;
	}
	public void setProxyBusinessIntervention(
			IBusinessIntervention proxyBusinessIntervention) {
		this.proxyBusinessIntervention = proxyBusinessIntervention;
	}
	public List<Erp> getListeErpComplete() {
		return listeErpComplete;
	}
	public void setListeErpComplete(List<Erp> listeErpComplete) {
		this.listeErpComplete = listeErpComplete;
	}

	public List<TypeDiagnostic> getListeTypesDiagnosticComplete() {
		return listeTypesDiagnosticComplete;
	}

	public void setListeTypesDiagnosticComplete(
			List<TypeDiagnostic> listeTypesDiagnosticComplete) {
		this.listeTypesDiagnosticComplete = listeTypesDiagnosticComplete;
	}

	public List<TypeErp> getListeTypesErpComplete() {
		return listeTypesErpComplete;
	}

	public void setListeTypesErpComplete(List<TypeErp> listeTypesErpComplete) {
		this.listeTypesErpComplete = listeTypesErpComplete;
	}

	public List<CategorieErp> getListeCategoriesErpComplete() {
		return listeCategoriesErpComplete;
	}

	public void setListeCategoriesErpComplete(
			List<CategorieErp> listeCategoriesErpComplete) {
		this.listeCategoriesErpComplete = listeCategoriesErpComplete;
	}

	public List<Indicateur> getListeIndicateursParDiag() {
		return listeIndicateursParDiag;
	}

	public void setListeIndicateursParDiag(List<Indicateur> listeIndicateursParDiag) {
		this.listeIndicateursParDiag = listeIndicateursParDiag;
	} 
	

}
