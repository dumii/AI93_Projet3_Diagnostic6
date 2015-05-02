package fr.afcepf.ai93.diag6.api.business.diagnostic;

import java.util.List;

import fr.afcepf.ai93.diag6.entity.diagnostic.Diagnostic;
import fr.afcepf.ai93.diag6.entity.diagnostic.HistoriqueDiagnostic;
import fr.afcepf.ai93.diag6.entity.diagnostic.TypeDiagnostic;

public interface IBusinessDiagnostic {
	
	 	public List<Diagnostic> recupereToutDiagnostic();
	 	
		public List<Diagnostic> recupereToutDiagnosticIntervEnCours();
		
		public List<Diagnostic> recupereToutDiagnosticEnAttente();

		public List<Diagnostic> recupereToutDiagnosticArchives();
		

	    public void ajouterDiagnostic(Diagnostic diagnostic);

	    public void modifierDiagnostic(Diagnostic diagnostic);

	    public void notifierDiagnostic();

	    public void historiserDiagnostic(Diagnostic diagnostic);

	    public List<HistoriqueDiagnostic> recupereToutHistoriqueDiagnostic();

	    public List<TypeDiagnostic> recupereTypeDiagnostic();

	    public Diagnostic recupereDiagnostic(int idDiagnostic);

	    public List<Diagnostic> rechercheDiagnostics(String nomDiagnostic);

	    public List<Diagnostic> rechercheDiagnosticsErp(String nomERP);




}
