package fr.afcepf.ai93.diag6.controler.gestionnaires;

import java.util.List;

import javax.annotation.PostConstruct;
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
import fr.afcepf.ai93.diag6.entity.travaux.Intervention;

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
	private List<EtatAvancementTravaux> listeEtatsComplete; 
	private int niveauMoyen; 
	private List<Erp> listeErpFiltree; 
	private int nbInterventions; 
	
	@PostConstruct
	private void init() {
		recupererTousLesErp();
		recupererTypesDiagnostic();
		recupererTypesErp(); 
		recupererEtatsAvancementTravaux();
	}
	
	private void recupererTousLesErp(){
		listeErpComplete = proxyBusinessErp.recupereToutErp();
		for(Erp e : listeErpComplete){
			List<Diagnostic> listDiag = proxyBusinessDiagnostic.recupereToutDiagnosticParErp(e); 
			e.setListeDiagnosticErp(listDiag);
			System.out.println("erp " + e.getNomErp()+" nb diag "+e.getListeDiagnosticErp().size());
		}
		listeErpFiltree = listeErpComplete;
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
	
	private void recupererEtatsAvancementTravaux(){
		listeEtatsComplete = proxyBusinessIntervention.recupererTousEtats(); 
		listeEtatsComplete.add(new EtatAvancementTravaux(5, "Sans intervention")); 
		System.out.println("je passe ici");
		for (EtatAvancementTravaux e : listeEtatsComplete)
			System.out.println(e.getIntituleEtatAvancement());
	}
	
	private int calculAnomaliesParDiag(Diagnostic d){
		List<Anomalie> listeAnomaliesParDiag = proxyBusinessAnomalie.recupereAnomalieParDiagnostic(d.getIdDiagnostic());
		//dans la meme methode, on calcule aussi le nombre d'interventions sur le diagnostic
		nbInterventions = 0; 
		for (Anomalie a : listeAnomaliesParDiag){
			List<Intervention> liste = proxyBusinessIntervention.rechercherInterventionSurAnomalie(a.getIdAnomalie()); 
			if(liste.size()>0) {
				nbInterventions++; 
			}
		}
		//fin du calcul du nb interventions
		return listeAnomaliesParDiag.size(); 
	}
	
	private int calculInterventionsParDiag(Diagnostic d){
		return nbInterventions; 
	}
	
	private int calculerMoyenneParDiag(Diagnostic d){
		List<Anomalie> listeAnomaliesParDiag = proxyBusinessAnomalie.recupereAnomalieParDiagnostic(d.getIdDiagnostic());
		int sommeValeurs =0; 
		for (Anomalie a : listeAnomaliesParDiag){
			sommeValeurs+=a.getIndicateur().getValeurIndicateur(); 
		}
		switch (d.getTypeDiagnostic().getIdTypeDiagnostic()){
			case(1): niveauMoyen = calculMoyAccessibilite(sommeValeurs, listeAnomaliesParDiag.size()); 
					 break; 
			case(2): niveauMoyen = calculMoyEnergie(sommeValeurs, listeAnomaliesParDiag.size()); 
					break; 
			case(3): niveauMoyen = calculMoySecurite(sommeValeurs, listeAnomaliesParDiag.size()); 
					break; 
			case(4): niveauMoyen = calculMoyHygiene(sommeValeurs, listeAnomaliesParDiag.size()); 
					break; 
		}
		return niveauMoyen; 
	}
	
	private int calculMoyAccessibilite(int somme, int nombre){
		double moy = somme/1.0*nombre; 
		return (int)moy; 
	}
	private int calculMoyEnergie(int somme, int nombre){
		double moy = somme/1.0*nombre; 
		if(moy>0&&moy<=2)
			return 1;
		if(moy>2&&moy<=4)
			return 2;
		if(moy>4&&moy<=6)
			return 3;
		return 4; 
	}
	private int calculMoySecurite(int somme, int nombre){
		double moy = somme/1.0*nombre; 
		if(moy>0&&moy<1)
			return 1;
		if(moy>=1&&moy<2)
			return 2;
		if(moy>=24&&moy<2.5)
			return 3;
		return 4; 
	}
	private int calculMoyHygiene(int somme, int nombre){
		double moy = somme/1.0*nombre; 
		if(moy>0&&moy<0.5)
			return 1;
		if(moy>=0.5&&moy<1)
			return 2;
		if(moy>=1&&moy<1.5)
			return 3;
		return 4;  
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

	public List<EtatAvancementTravaux> getListeEtatsComplete() {
		return listeEtatsComplete;
	}

	public void setListeEtatsComplete(List<EtatAvancementTravaux> listeEtatsComplete) {
		this.listeEtatsComplete = listeEtatsComplete;
	}

	public int getNbInterventions() {
		return nbInterventions;
	}

	public void setNbInterventions(int nbInterventions) {
		this.nbInterventions = nbInterventions;
	}

	public List<Erp> getListeErpFiltree() {
		return listeErpFiltree;
	}

	public void setListeErpFiltree(List<Erp> listeErpFiltree) {
		this.listeErpFiltree = listeErpFiltree;
	}

	public int getNiveauMoyen() {
		return niveauMoyen;
	}

	public void setNiveauMoyen(int niveauMoyen) {
		this.niveauMoyen = niveauMoyen;
	}


}
