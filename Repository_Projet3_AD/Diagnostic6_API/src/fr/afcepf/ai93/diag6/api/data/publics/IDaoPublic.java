package fr.afcepf.ai93.diag6.api.data.publics;

import java.util.List;

import fr.afcepf.ai93.diag6.entity.erp.Erp;

public interface IDaoPublic {

	 public List<Erp> afficherErpAuxNormes();

	 public List<Erp> moyenneErpAuxNormes();

	 public List<Erp> calculGraphiquePatrimoine();

	 public List<Erp> calculErpParTypeDiagnostic(int idTypeDiagnostic);

	 public List<Erp> calculErpParTraite(int traite);
	 
	 public void localisationErpGoogleMap();
	 
	 
	 
	 
	 public Integer nbInterventionsTerminees();
	 public Integer nbInterventionsEnCours();
	 public Integer nbInterventionsPlanifiess();
	 public Integer nbInterventionsDiagnostiquees();
	 
	 
	 
	 
}
