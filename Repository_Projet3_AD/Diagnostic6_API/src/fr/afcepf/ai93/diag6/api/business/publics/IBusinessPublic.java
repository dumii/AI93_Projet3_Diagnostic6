package fr.afcepf.ai93.diag6.api.business.publics;

import java.util.List;

import fr.afcepf.ai93.diag6.entity.diagnostic.Diagnostic;
import fr.afcepf.ai93.diag6.entity.diagnostic.TypeDiagnostic;
import fr.afcepf.ai93.diag6.entity.erp.Erp;

public interface IBusinessPublic {
	
	public List<Erp> afficherErpAuxNormes();

    public List<Erp> moyenneErpAuxNormes();

    public List<Erp> calculGraphiquePatrimoine();

    public List<Erp> calculErpParTypeDiagnostic(TypeDiagnostic idTypeDiagnostic);

    public List<Erp> calculErpParTraite(Diagnostic traite);

    public void localisationErpGoogleMap();
    
    
    
    
    /* Methodes statistique graph1   */
	/* ***************************** */
    
    public Integer nbInterventionsTerminees();
	public Integer nbInterventionsEnCours();
	public Integer nbInterventionsPlanifiess();
	public Integer nbInterventionsDiagnostiquees();
	
	
	/* Methodes statistique graph2 */
	 /* *****************************/
	
	
	public Integer nbDiagnosticAccessibilitéTotal();
	public Integer nbDiagnosticEnergieTotal();
	public Integer nbDiagnosticSecuriteTotal();
	public Integer nbDiagnosticHygieneTotal();
	 
	public Integer nbDiagnosticAccessibilitéTraites();
	public Integer nbDiagnosticEnergieTraites();
	public Integer nbDiagnosticSecuriteTraites();
	public Integer nbDiagnosticHygieneTraites();
}
