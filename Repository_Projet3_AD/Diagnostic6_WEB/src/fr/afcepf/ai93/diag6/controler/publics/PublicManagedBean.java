package fr.afcepf.ai93.diag6.controler.publics;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import fr.afcepf.ai93.diag6.api.business.publics.IBusinessPublic;
import fr.afcepf.ai93.diag6.entity.diagnostic.TypeDiagnostic;
import fr.afcepf.ai93.diag6.entity.erp.TypeErp;

@ManagedBean(name="mbPublic")
@SessionScoped
public class PublicManagedBean{

	@EJB
	private IBusinessPublic proxyBusinessPublic;
	
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
	
	private int idErpSelect;
	
	private int idDiagSelect;
	
	public int getIdErpSelect() {
		return idErpSelect;
	}

	public void setIdErpSelect(int idErpSelect) {
		this.idErpSelect = idErpSelect;
	}

	public int getIdDiagSelect() {
		return idDiagSelect;
	}

	public void setIdDiagSelect(int idDiagSelect) {
		this.idDiagSelect = idDiagSelect;
	}

	public int getIdSelect() {
		return idErpSelect;
	}

	public void setIdSelect(int idSelect) {
		this.idErpSelect = idSelect;
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
	}
	
}
