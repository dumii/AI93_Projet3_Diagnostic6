package fr.afcepf.ai93.diag6.controler.gestionnaires;

import java.util.ArrayList;
import java.util.Collection;
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
	List<Anomalie> listeAnomaliesParDiagTmp;
	private int niveauMoyen; 
	private List<Erp> listeErpFiltree; 
	private int nbInterventions; 
	private int idErpSelectionne; 
	private int idTypeErp; 
	private int idEtatAvancementSelectionne; 
	private int idTypeDiagnostic;
	private int idNiveau; 
	private int etatTmp;
	
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
			for(Diagnostic d : listDiag){
				List<Anomalie> listAnom = proxyBusinessAnomalie.recupereAnomalieParDiagnostic(d.getIdDiagnostic());
				for(Anomalie a : listAnom){
					List<Intervention> listeBidon = proxyBusinessIntervention.rechercherInterventionSurAnomalie(a.getIdAnomalie()); 
					a.setIntervention(listeBidon.get(0));
				}
				d.setListeAnomaliesDiagnostic(listAnom);
			}
			e.setListeDiagnosticErp(listDiag);
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
		for (EtatAvancementTravaux e : listeEtatsComplete)
			System.out.println(e.getIntituleEtatAvancement());
	}
	
	public int calculAnomaliesParDiag(Diagnostic d){
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
	
	public int calculInterventionsParDiag(Diagnostic d){
		return nbInterventions; 
	}
	
	public int calculerMoyenneParDiag(Diagnostic d){
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
		return somme/nombre; 
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
	
	public int calculEtatAvancement(Diagnostic d){
		List<Anomalie> listAnom = proxyBusinessAnomalie.recupereAnomalieParDiagnostic(d.getIdDiagnostic());
		for(Anomalie a : listAnom){
			List<Intervention> listeBidon = proxyBusinessIntervention.rechercherInterventionSurAnomalie(a.getIdAnomalie()); 
			a.setIntervention(listeBidon.get(0));
		}
		d.setListeAnomaliesDiagnostic(listAnom);
		List<Intervention> listeInterv = new ArrayList<>(); 
		for(Anomalie a : d.getListeAnomaliesDiagnostic()){
			listeInterv.add(a.getIntervention()); 
			if(a.getIntervention().getEtatAvancementTravaux().getIdEtatAvancement()==3)
				return 3; //="En cours", car si une seule intervention de ce diagnostic est en cours, tout le diagnostic est en cours
		}
		int tailleListeInterv = listeInterv.size(); 
		if(tailleListeInterv==0)
			return 5; //= "Sans intervention", car il n'y a aucune intervention sur les anomalies de ce diagnostic
		int nbIntervTerminees = 0;
		for(Intervention i : listeInterv){
			if(i.getEtatAvancementTravaux().getIdEtatAvancement()==1){
				nbIntervTerminees++;
			}
		}
		if(nbIntervTerminees==tailleListeInterv)
			return 1; //"Termin�", car si toutes les interventions ont l'�tat 1=termin�, alors tout le diagnostic prend cet �tat
		
		return 2; //"Suspendu ou planifi�", �a peut etre aussi return 4, attention � prendre les deux en compte; 
	}
	
	public void filtrerParErpNom(){
		System.out.println("oui, je rentre bien dans la m�thode filtrer par ERP nom");
		if(idErpSelectionne!=0){
			List<Erp> listE = new ArrayList<>();
			recupererTousLesErp();
			//listeErpFiltree = listeErpComplete; 
			for(Erp e : listeErpFiltree){
				if(e.getIdErp()==idErpSelectionne)
					listE.add(e);
			}
			for (Erp f : listE){
				System.out.println("Erp "+f.getNomErp());
			}
			listeErpFiltree = listE; 
			idErpSelectionne = 0;
		}
		else {
			recupererTousLesErp();
			//listeErpFiltree=listeErpComplete;
		}
	}
	
	public void filtrerParTypeErp(){
		System.out.println("oui, je rentre bien dans la m�thode filtrer par Type ERP");
		if(idTypeErp!=0){
			List<Erp> listE = new ArrayList<>();
			recupererTousLesErp();
			//listeErpFiltree = listeErpComplete; 
			for(Erp e : listeErpFiltree){
				if(e.getTypeErp().getIdTypeErp()==idTypeErp)
					listE.add(e);
			}
			for (Erp f : listE){
				System.out.println("Erp "+f.getNomErp());
			}
			listeErpFiltree = listE; 
			idTypeErp = 0;
		}
		else {
			recupererTousLesErp();
			//listeErpFiltree=listeErpComplete;
		}
	}

	public void filtrerParNiveau(){
		System.out.println("oui, je rentre bien dans la m�thode filtrer par Niveau");
		if(idNiveau!=0){
			List<Erp> listE = new ArrayList<>();
			recupererTousLesErp();
			//listeErpFiltree = listeErpComplete; 
			for(Erp e : listeErpFiltree){
				List<Diagnostic> listeDiag = new ArrayList<>(); 
				for(Diagnostic d : e.getListeDiagnosticErp()){
					if(calculerMoyenneParDiag(d)==idNiveau){
						listeDiag.add(d); 
					}
				}
				if(listeDiag.size()>0){
					listE.add(e); 
				}
			}
			for (Erp f : listE){
				System.out.println("Erp "+f.getNomErp());
			}
			listeErpFiltree = listE; 
			idNiveau = 0;
		}
		else {
			recupererTousLesErp();
			//listeErpFiltree=listeErpComplete;
		}
	}
	
	public void filtrerParTypeDiagnostic(){
		System.out.println("oui, je rentre bien dans la m�thode filtrer par Etat intervention");
		if(idTypeDiagnostic!=0){
			List<Erp> listE = new ArrayList<>();
			recupererTousLesErp();
			//listeErpFiltree = listeErpComplete; 
			for(Erp e : listeErpFiltree){
				List<Diagnostic> listDiagTypeSel = new ArrayList<>(); 
				for(Diagnostic d : e.getListeDiagnosticErp()){
					if(d.getTypeDiagnostic().getIdTypeDiagnostic()==idTypeDiagnostic){
						listDiagTypeSel.add(d);
					}
				}
				e.setListeDiagnosticErp(listDiagTypeSel);
				if(e.getListeDiagnosticErp().size()>0){
					listE.add(e);
				}	
			}
			for (Erp f : listE){
				System.out.println("Erp "+f.getNomErp());
			}
			listeErpFiltree = listE; 
			idTypeDiagnostic = 0;
		}
		else {
			recupererTousLesErp();
			//listeErpFiltree=listeErpComplete;
		}
	}
	
	public void filtrerParEtatIntervention(){
		System.out.println("oui, je rentre bien dans la m�thode filtrer par Etat intervention");
		if(idEtatAvancementSelectionne!=0){
			List<Erp> listE = new ArrayList<>();
			recupererTousLesErp();
			//listeErpFiltree = listeErpComplete; 
			if(idEtatAvancementSelectionne==1)
				listE = recupererLesErpInterventionsTerminees(); 
			if((idEtatAvancementSelectionne==2)||(idEtatAvancementSelectionne==4))
				listE = recupererLesErpInterventionsPlanifSuspendus(); 
			if(idEtatAvancementSelectionne==3)
				listE = recupererLesErpInterventionsEnCours(); 
			if(idEtatAvancementSelectionne==5)
				listE = recupererLesErpSansIntervention(); 
			for (Erp f : listE){
				System.out.println("Erp "+f.getNomErp());
			}
			listeErpFiltree = listE; 
		}
		else {
			 recupererTousLesErp();
			 idErpSelectionne = 0;
		}
	}
	
	private List<Erp> recupererLesErpInterventionsPlanifSuspendus() {
		List<Erp> liste = new ArrayList<>();
		for(Erp e : listeErpFiltree){
			List<Diagnostic> listD = new ArrayList<>(); 
			for(Diagnostic d : e.getListeDiagnosticErp()){
				if((calculEtatAvancement(d)==2)||(calculEtatAvancement(d)==4)){
					listD.add(d);
				}
			}
			e.setListeDiagnosticErp(listD);
			if(listD.size()>0){
				liste.add(e); 
			}
		}
		return liste;
	}

	private List<Erp> recupererLesErpInterventionsTerminees(){
		List<Erp> liste = new ArrayList<>();
		for(Erp e : listeErpFiltree){
			List<Diagnostic> listD = new ArrayList<>(); 
			for(Diagnostic d : e.getListeDiagnosticErp()){
				if(calculEtatAvancement(d)==1){
					listD.add(d);
				}
			}
			e.setListeDiagnosticErp(listD);
			if(listD.size()>0){
				liste.add(e); 
			}
		}
		return liste;
	}
	
	private List<Erp> recupererLesErpInterventionsEnCours(){
		List<Erp> liste = new ArrayList<>();
		for(Erp e : listeErpFiltree){
			List<Diagnostic> listD = new ArrayList<>(); 
			for(Diagnostic d : e.getListeDiagnosticErp()){
				if(calculEtatAvancement(d)==3){
					listD.add(d);
				}
			}
			e.setListeDiagnosticErp(listD);
			if(listD.size()>0){
				liste.add(e); 
			}
		}
		return liste;
	}
	
	private List<Erp> recupererLesErpSansIntervention(){
		List<Erp> liste = new ArrayList<>();
		for(Erp e : listeErpFiltree){
			List<Diagnostic> listD = new ArrayList<>(); 
			for(Diagnostic d : e.getListeDiagnosticErp()){
				if(calculEtatAvancement(d)==5){
					listD.add(d);
				}
			}
			e.setListeDiagnosticErp(listD);
			if(listD.size()>0){
				liste.add(e); 
			}
		}
		return liste;
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

	public int getIdErpSelectionne() {
		return idErpSelectionne;
	}

	public void setIdErpSelectionne(int idErpSelectionne) {
		this.idErpSelectionne = idErpSelectionne;
	}

	public int getIdEtatAvancementSelectionne() {
		return idEtatAvancementSelectionne;
	}

	public void setIdEtatAvancementSelectionne(int idEtatAvancementSelectionne) {
		this.idEtatAvancementSelectionne = idEtatAvancementSelectionne;
	}

	public int getIdTypeDiagnostic() {
		return idTypeDiagnostic;
	}

	public void setIdTypeDiagnostic(int idTypeDiagnostic) {
		this.idTypeDiagnostic = idTypeDiagnostic;
	}

	public int getIdTypeErp() {
		return idTypeErp;
	}

	public void setIdTypeErp(int idTypeErp) {
		this.idTypeErp = idTypeErp;
	}

	public int getIdNiveau() {
		return idNiveau;
	}

	public void setIdNiveau(int idNiveau) {
		this.idNiveau = idNiveau;
	}

	public List<Anomalie> getListeAnomaliesParDiagTmp() {
		return listeAnomaliesParDiagTmp;
	}

	public void setListeAnomaliesParDiagTmp(List<Anomalie> listeAnomaliesParDiagTmp) {
		this.listeAnomaliesParDiagTmp = listeAnomaliesParDiagTmp;
	}

	public int getEtatTmp() {
		return etatTmp;
	}

	public void setEtatTmp(int etatTmp) {
		this.etatTmp = etatTmp;
	}
	
}
