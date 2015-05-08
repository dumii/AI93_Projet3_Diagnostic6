package fr.afcepf.ai93.diag6.api.data.diagnostic;

import java.util.List;

import fr.afcepf.ai93.diag6.entity.autres.Utilisateur;
import fr.afcepf.ai93.diag6.entity.diagnostic.Diagnostic;
import fr.afcepf.ai93.diag6.entity.diagnostic.HistoriqueDiagnostic;
import fr.afcepf.ai93.diag6.entity.travaux.Intervention;

public interface IDaoHistoriqueDiagnostic {
	
	public List<HistoriqueDiagnostic> recupereToutHistoriqueDiagnostic();
	
	public void historiser(Diagnostic diagnosticInitial, Diagnostic diagnostic, Utilisateur user);
}
