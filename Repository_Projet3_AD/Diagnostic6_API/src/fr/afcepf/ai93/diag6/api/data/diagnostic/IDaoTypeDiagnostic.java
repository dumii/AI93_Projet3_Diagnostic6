package fr.afcepf.ai93.diag6.api.data.diagnostic;

import java.util.List;

import fr.afcepf.ai93.diag6.entity.diagnostic.TypeDiagnostic;
import fr.afcepf.ai93.diag6.entity.erp.Erp;

public interface IDaoTypeDiagnostic {
	
	public List<TypeDiagnostic> recupereTypeDiagnostic();
	
	public List<TypeDiagnostic> recupereTypeDiagnosticParErp(Erp erp);
}
