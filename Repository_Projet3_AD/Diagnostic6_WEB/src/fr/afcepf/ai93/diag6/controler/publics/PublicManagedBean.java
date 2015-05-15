package fr.afcepf.ai93.diag6.controler.publics;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


import fr.afcepf.ai93.diag6.api.business.diagnostic.IBusinessDiagnostic;
import fr.afcepf.ai93.diag6.api.business.erp.IBusinessErp;
import fr.afcepf.ai93.diag6.api.business.publics.IBusinessPublic;
import fr.afcepf.ai93.diag6.entity.diagnostic.TypeDiagnostic;
import fr.afcepf.ai93.diag6.entity.erp.Erp;
import fr.afcepf.ai93.diag6.entity.erp.TypeErp;

@ManagedBean(name="mbPublic")
@SessionScoped
public class PublicManagedBean{

	@EJB
	private IBusinessPublic proxyBusinessPublic;

	@EJB
	private IBusinessErp proxyBusinessErp;

	@EJB
	private IBusinessDiagnostic proxyBusinessDiagnostic;
	private Integer nbInterventionTerminees;
	private Integer nbInterventionEnCours;
	private Integer nbInterventionPlanifiees;
	private Integer nbInterventionDiagnostiquees;

	private Integer nbDiagnosticAccessibilitéTotal;
	private Integer nbDiagnosticEnergieTotal;
	private Integer nbDiagnosticSecuriteTotal;
	private Integer nbDiagnosticHygieneTotal;

	private Integer nbDiagnosticAccessibilitéTraites;
	private Integer nbDiagnosticEnergieTraites;
	private Integer nbDiagnosticSecuriteTraites;
	private Integer nbDiagnosticHygieneTraites;

	private Integer nbERpAuxNormes;
	private Integer nbErpEnCoursDeNormalité;

	private List<TypeErp> listeTypeErp;
	private List<TypeDiagnostic> listeTypeDiagnostic;

	private List<Erp> listeErp;
	private Integer idTypeErpSelect;
	private Integer idTypeDiagSelect;
	private boolean booleenTravaux;
	private Integer idErpRecherche;
	private String adressErp;

	public Integer getIdTypeErpSelect() {
		return idTypeErpSelect;
	}

	public void setIdTypeErpSelect(Integer idTypeErpSelect) {
		this.idTypeErpSelect = idTypeErpSelect;
	}

	public void setIdErpRecherche(Integer idErpRecherche) {
		this.idErpRecherche = idErpRecherche;
	}

	public String getAdressErp() {
		return adressErp;
	}

	public void setAdressErp(String adressErp) {
		this.adressErp = adressErp;
	}

	public Integer getIdTypeDiagSelect() {
		return idTypeDiagSelect;
	}

	public void setIdTypeDiagSelect(Integer idTypeDiagSelect) {
		this.idTypeDiagSelect = idTypeDiagSelect;
	}

	public Integer getIdErpRecherche() {
		return idErpRecherche;
	}

	public void setIdTypeErpRecherche(Integer idTypeErpRecherche) {
		this.idErpRecherche = idTypeErpRecherche;
	}

	public List<Erp> getListeErp() {
		return listeErp;
	}

	public void setListeErp(List<Erp> listeErp) {
		this.listeErp = listeErp;
	}

	public boolean isBooleenTravaux() {
		return booleenTravaux;
	}

	public void setBooleenTravaux(boolean booleenTravaux) {
		this.booleenTravaux = booleenTravaux;
	}

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



	public List<TypeErp> getListeTypeErp() {
		return listeTypeErp;
	}

	public void setListeTypeErp(List<TypeErp> listeTypeErp) {
		this.listeTypeErp = listeTypeErp;
	}

	public List<TypeDiagnostic> getListeTypeDiagnostic() {
		return listeTypeDiagnostic;
	}

	public void setListeTypeDiagnostic(List<TypeDiagnostic> listeTypeDiagnostic) {
		this.listeTypeDiagnostic = listeTypeDiagnostic;
	}

	public Integer getNbERpAuxNormes() {
		return nbERpAuxNormes;
	}

	public void setNbERpAuxNormes(Integer nbERpAuxNormes) {
		this.nbERpAuxNormes = nbERpAuxNormes;
	}

	public Integer getNbErpEnCoursDeNormalité() {
		return nbErpEnCoursDeNormalité;
	}

	public void setNbErpEnCoursDeNormalité(Integer nbErpEnCoursDeNormalité) {
		this.nbErpEnCoursDeNormalité = nbErpEnCoursDeNormalité;
	}

	public Integer getNbDiagnosticAccessibilitéTotal() {
		return nbDiagnosticAccessibilitéTotal;
	}

	public void setNbDiagnosticAccessibilitéTotal(
			Integer nbDiagnosticAccessibilitéTotal) {
		this.nbDiagnosticAccessibilitéTotal = nbDiagnosticAccessibilitéTotal;
	}

	public Integer getNbDiagnosticEnergieTotal() {
		return nbDiagnosticEnergieTotal;
	}

	public void setNbDiagnosticEnergieTotal(Integer nbDiagnosticEnergieTotal) {
		this.nbDiagnosticEnergieTotal = nbDiagnosticEnergieTotal;
	}

	public Integer getNbDiagnosticSecuriteTotal() {
		return nbDiagnosticSecuriteTotal;
	}

	public void setNbDiagnosticSecuriteTotal(Integer nbDiagnosticSecuriteTotal) {
		this.nbDiagnosticSecuriteTotal = nbDiagnosticSecuriteTotal;
	}

	public Integer getNbDiagnosticHygieneTotal() {
		return nbDiagnosticHygieneTotal;
	}

	public void setNbDiagnosticHygieneTotal(Integer nbDiagnosticHygieneTotal) {
		this.nbDiagnosticHygieneTotal = nbDiagnosticHygieneTotal;
	}

	public Integer getNbDiagnosticAccessibilitéTraites() {
		return nbDiagnosticAccessibilitéTraites;
	}

	public void setNbDiagnosticAccessibilitéTraites(
			Integer nbDiagnosticAccessibilitéTraites) {
		this.nbDiagnosticAccessibilitéTraites = nbDiagnosticAccessibilitéTraites;
	}

	public Integer getNbDiagnosticEnergieTraites() {
		return nbDiagnosticEnergieTraites;
	}

	public void setNbDiagnosticEnergieTraites(Integer nbDiagnosticEnergieTraites) {
		this.nbDiagnosticEnergieTraites = nbDiagnosticEnergieTraites;
	}

	public Integer getNbDiagnosticSecuriteTraites() {
		return nbDiagnosticSecuriteTraites;
	}

	public void setNbDiagnosticSecuriteTraites(Integer nbDiagnosticSecuriteTraites) {
		this.nbDiagnosticSecuriteTraites = nbDiagnosticSecuriteTraites;
	}

	public Integer getNbDiagnosticHygieneTraites() {
		return nbDiagnosticHygieneTraites;
	}

	public void setNbDiagnosticHygieneTraites(Integer nbDiagnosticHygieneTraites) {
		this.nbDiagnosticHygieneTraites = nbDiagnosticHygieneTraites;
	}

	public IBusinessPublic getProxyBusinessPublic() {
		return proxyBusinessPublic;
	}

	public void setProxyBusinessPublic(IBusinessPublic proxyBusinessPublic) {
		this.proxyBusinessPublic = proxyBusinessPublic;
	}

	public Integer getNbInterventionTerminees() {
		return nbInterventionTerminees;
	}

	public void setNbInterventionTerminees(Integer nbInterventionTerminees) {
		this.nbInterventionTerminees = nbInterventionTerminees;
	}

	public Integer getNbInterventionEnCours() {
		return nbInterventionEnCours;
	}

	public void setNbInterventionEnCours(Integer nbInterventionEnCours) {
		this.nbInterventionEnCours = nbInterventionEnCours;
	}

	public Integer getNbInterventionPlanifiees() {
		return nbInterventionPlanifiees;
	}

	public void setNbInterventionPlanifiees(Integer nbInterventionPlanifiees) {
		this.nbInterventionPlanifiees = nbInterventionPlanifiees;
	}

	public Integer getNbInterventionDiagnostiquees() {
		return nbInterventionDiagnostiquees;
	}

	public void setNbInterventionDiagnostiquees(Integer nbInterventionDiagnostiquees) {
		this.nbInterventionDiagnostiquees = nbInterventionDiagnostiquees;
	}

	@PostConstruct
	public void init(){

		/*Graph1 ************************/

		nbInterventionTerminees = proxyBusinessPublic.nbInterventionsTerminees();
		nbInterventionEnCours = proxyBusinessPublic.nbInterventionsEnCours();
		nbInterventionPlanifiees = proxyBusinessPublic.nbInterventionsPlanifiess();
		nbInterventionDiagnostiquees = proxyBusinessPublic.nbInterventionsDiagnostiquees();


		/*Graph3 ************************/

		nbDiagnosticAccessibilitéTotal = proxyBusinessPublic.nbDiagnosticAccessibilitéTotal();
		nbDiagnosticEnergieTotal = proxyBusinessPublic.nbDiagnosticEnergieTotal();
		nbDiagnosticSecuriteTotal = proxyBusinessPublic.nbDiagnosticSecuriteTotal();
		nbDiagnosticHygieneTotal = proxyBusinessPublic.nbDiagnosticHygieneTotal();

		nbDiagnosticAccessibilitéTraites= proxyBusinessPublic.nbDiagnosticAccessibilitéTraites();
		nbDiagnosticEnergieTraites= proxyBusinessPublic.nbDiagnosticEnergieTraites();
		nbDiagnosticSecuriteTraites= proxyBusinessPublic.nbDiagnosticSecuriteTraites();
		nbDiagnosticHygieneTraites= proxyBusinessPublic.nbDiagnosticHygieneTraites();

		/*Graph2 ************************/

		nbERpAuxNormes = proxyBusinessPublic.nbERpAuxNormes();
		nbErpEnCoursDeNormalité = proxyBusinessPublic.nbErpEnCoursDeNormalité();


		/*Bandeau Recherche **************/

		listeTypeErp = proxyBusinessPublic.listerTypeErp();

		listeTypeDiagnostic = proxyBusinessPublic.listerTypeDiagnostic();

		listeErp= proxyBusinessErp.recupereToutErp();

	}

	public void retourListeTypeErp(){

		listeTypeErp = proxyBusinessPublic.listerTypeErp();

	}

	public void retourListeTypeDiag(){

		listeTypeDiagnostic = proxyBusinessPublic.listerTypeDiagnostic();
	}


	public List<Erp> recup2(){


		listeErp = new ArrayList<>();
		listeErp = proxyBusinessErp.recupereToutErp();

		List<Erp> listeTempByTypeErp = new ArrayList<>();
		List<Erp> listeTempByTypeDiag = new ArrayList<>();

		if(idTypeErpSelect != null && idTypeErpSelect != 0){
			for (Erp e : listeErp){
				if(e.getTypeErp().getIdTypeErp() == idTypeErpSelect){
					listeTempByTypeErp.add(e);
				}
			}
			
		}

		if(idTypeDiagSelect != null && idTypeDiagSelect != 0){

			listeTempByTypeDiag= proxyBusinessPublic.recupererErpParTypeDiagnostic(idTypeDiagSelect);
			
		}


		if((idTypeErpSelect == null || idTypeErpSelect == 0) && (idTypeDiagSelect == null || idTypeDiagSelect == 0)){
			listeErp = proxyBusinessErp.recupereToutErp();
			
		}
		if((idTypeErpSelect != null && idTypeErpSelect != 0) && (idTypeDiagSelect != null && idTypeDiagSelect != 0)){

			List<Erp> liste = new ArrayList<>();

			for (Erp e: listeTempByTypeErp){
				for(Erp e2 : listeTempByTypeDiag){

					if (e.getTypeErp().getIdTypeErp() == e2.getTypeErp().getIdTypeErp()){

						if(!liste.contains(e)){
							liste.add(e);
						}
					}
				}
			}
			listeErp = liste;
			
		}
		if((idTypeErpSelect == null || idTypeErpSelect == 0) && (idTypeDiagSelect != null && idTypeDiagSelect != 0)){
			listeErp = listeTempByTypeDiag;
		
		}
		if((idTypeErpSelect != null && idTypeErpSelect != 0) && ( idTypeDiagSelect == null || idTypeDiagSelect == 0)){
			listeErp = listeTempByTypeErp;
		
		}

		return listeErp;
	}

	public String afficherDansMap() {
		System.out.println("coucou");
		Erp erp = proxyBusinessErp.recupererErpParId(idErpRecherche);
		adressErp = erp.getNumVoieErp().toString() +" "+ erp.getNomVoieErp() +" "+ erp.getLocalisation().getVille();
		System.out.println(adressErp);
		return adressErp;
	}

}