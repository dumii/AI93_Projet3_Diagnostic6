package fr.afcepf.ai93.diag6.api.data.diagnostic;

import java.util.List;

import fr.afcepf.ai93.diag6.entity.diagnostic.TypeDiagnostic;

public interface IDaoTypeDiagnostic {
	
	public List<TypeDiagnostic> recupereTypeDiagnostic();
}
