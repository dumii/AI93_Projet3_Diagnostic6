package fr.afcepf.ai93.diag6.api.data.diagnostic;

import java.util.List;

import fr.afcepf.ai93.diag6.entity.autres.Utilisateur;
import fr.afcepf.ai93.diag6.entity.diagnostic.Diagnostic;
import fr.afcepf.ai93.diag6.entity.erp.Erp;

public interface IDaoDiagnostic {

	public List<Diagnostic> recupereToutDiagnostic();
	
	public boolean recupereSiIntervEnCoursParDiag(int idDiag);

    public void ajouterDiagnostic(Diagnostic diagnostic);

    public void modifierDiagnostic(Diagnostic diagnostic, Utilisateur user);

    public void notifierDiagnostic();

    public void historiserDiagnostic(Diagnostic diagnostic);

    public Diagnostic recupereDiagnostic(int idDiagnostic);

    public List<Diagnostic> rechercheDiagnostics(String nomDiagnostic);

    public List<Diagnostic> rechercheDiagnosticsErp(String nomERP);
    
    public List<Diagnostic> recupereDiagnosticNonTraitesParErp(Erp erp);
    
	public List<Diagnostic> recupereToutDiagnosticParErp(Erp e);
	
	public int getMaxId();
    
}
