package fr.afcepf.ai93.diag6.api.business.diagnostic;

import java.util.List;

import fr.afcepf.ai93.diag6.entity.autres.Utilisateur;
import fr.afcepf.ai93.diag6.entity.diagnostic.Diagnostic;
import fr.afcepf.ai93.diag6.entity.diagnostic.HistoriqueDiagnostic;
import fr.afcepf.ai93.diag6.entity.diagnostic.Indicateur;
import fr.afcepf.ai93.diag6.entity.diagnostic.TypeDiagnostic;
import fr.afcepf.ai93.diag6.entity.erp.Erp;

public interface IBusinessDiagnostic {
	
	 	public List<Diagnostic> recupereToutDiagnostic();
	 	
		public List<Diagnostic> recupereToutDiagnosticIntervEnCours();
		
		public List<Diagnostic> recupereToutDiagnosticEnAttente();

		public List<Diagnostic> recupereToutDiagnosticArchives();
		

	    public String ajouterDiagnostic(Diagnostic diagnostic);

	    public String modifierDiagnostic(Diagnostic diagnostic, Utilisateur user);

	    public void notifierDiagnostic();

	    public void historiserDiagnostic(Diagnostic diagnostic);

	    public List<HistoriqueDiagnostic> recupereToutHistoriqueDiagnostic();

	    public List<TypeDiagnostic> recupereTypeDiagnostic();


	    public Diagnostic recupereDiagnostic(int idDiagnostic);

	    public List<Diagnostic> rechercheDiagnostics(String nomDiagnostic);

	    public List<Diagnostic> rechercheDiagnosticsErp(String nomERP);
	    
	    public List<Diagnostic> recupereDiagnosticNonTraitesParErp(Erp erp);

		public List<Indicateur> recupererIndicateursParDiag(Diagnostic diagEnCours);
		
		public List<Indicateur> recupereIndicateurParTypeDiagnostic(int idTypeDiagnostic);

		public List<Diagnostic> recupereToutDiagnosticParErp(Erp e);
		
		
		public boolean interventionEnCoursSurERP();
		
		public boolean interventionEnAttenteSurERP();
		
		public boolean interventionArchivesSurERP();

		public boolean recupererDiagSansInterv(Integer idDiag);

}
