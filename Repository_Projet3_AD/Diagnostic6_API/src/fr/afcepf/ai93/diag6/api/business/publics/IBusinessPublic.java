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
    
    
    
    
    public Integer nbInterventionsTerminees();
	public Integer nbInterventionsEnCours();
	public Integer nbInterventionsPlanifiess();
	public Integer nbInterventionsDiagnostiquees();
}
