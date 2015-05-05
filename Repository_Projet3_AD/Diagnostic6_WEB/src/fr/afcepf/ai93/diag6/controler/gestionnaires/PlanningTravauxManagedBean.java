package fr.afcepf.ai93.diag6.controler.gestionnaires;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import fr.afcepf.ai93.diag6.api.business.travaux.IBusinessIntervention;
import fr.afcepf.ai93.diag6.entity.erp.Erp;
import fr.afcepf.ai93.diag6.entity.travaux.TypeIntervention;

@ManagedBean(name="mbPlanningTravaux")
@SessionScoped
public class PlanningTravauxManagedBean {

	@EJB
	private IBusinessIntervention proxyBusiness;
	
	//La liste des types d'interventions 
	private List<TypeIntervention> listeTypesInterventions;
	
	//L'ERP concerné par le chantier
	private Erp erp;
	
	/*
	 * Ce dont on a besoin :
	 * ***la liste des anomalies sans interventions
	 * ***la liste des anomalies avec interventions par type d'intervention (on part du type d'intervention pour obtenir la liste d'anomalie
	 *    sur l'erp concerné)
	 */
	
}
