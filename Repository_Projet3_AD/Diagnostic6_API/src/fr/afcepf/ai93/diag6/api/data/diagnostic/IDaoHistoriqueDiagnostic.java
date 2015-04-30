package fr.afcepf.ai93.diag6.api.data.diagnostic;

import java.util.List;

import fr.afcepf.ai93.diag6.entity.diagnostic.HistoriqueDiagnostic;

public interface IDaoHistoriqueDiagnostic {
	
	public List<HistoriqueDiagnostic> recupereToutHistoriqueDiagnostic();
}
