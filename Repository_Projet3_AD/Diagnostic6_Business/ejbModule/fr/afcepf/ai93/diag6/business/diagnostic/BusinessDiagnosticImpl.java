package fr.afcepf.ai93.diag6.business.diagnostic;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import fr.afcepf.ai93.diag6.api.business.diagnostic.IBusinessDiagnostic;
import fr.afcepf.ai93.diag6.api.data.diagnostic.IDaoDiagnostic;
import fr.afcepf.ai93.diag6.entity.diagnostic.Anomalie;
import fr.afcepf.ai93.diag6.entity.diagnostic.Diagnostic;
import fr.afcepf.ai93.diag6.entity.diagnostic.HistoriqueDiagnostic;
import fr.afcepf.ai93.diag6.entity.diagnostic.TypeDiagnostic;

@Stateless
@Remote(IBusinessDiagnostic.class)
public class BusinessDiagnosticImpl implements IBusinessDiagnostic {
	@EJB
	private IDaoDiagnostic proxyDiagnostic; 
	
	private List<Diagnostic> listeDiag; 
	List<Diagnostic> listeDiagIntervEnCours = new ArrayList<Diagnostic>(); 
	private List<Diagnostic> listeDiagEnAttente = new ArrayList<Diagnostic>(); 
	List<Diagnostic> listeDiagArchives = new ArrayList<Diagnostic>(); 
		
	@Override
	public List<Diagnostic> recupereToutDiagnostic() {
	
		listeDiag = proxyDiagnostic.recupereToutDiagnostic(); 
		for (Diagnostic d : listeDiag)
		{
			if(d.getTraite()!=0) 
				listeDiagArchives.add(d); 
			else
				if(proxyDiagnostic.recupereSiIntervEnCoursParDiag(d.getIdDiagnostic())) 
						listeDiagIntervEnCours.add(d); 
					else 
						listeDiagEnAttente.add(d);				
		}
		return listeDiag;
	}
	
	@Override
	public List<Diagnostic> recupereToutDiagnosticIntervEnCours() {
		return listeDiagIntervEnCours;		
	}
	
	@Override
	public List<Diagnostic> recupereToutDiagnosticEnAttente() {
		return listeDiagEnAttente;
	}

	@Override
	public List<Diagnostic> recupereToutDiagnosticArchives() {
		return listeDiagArchives;
	}

	@Override
	public String ajouterDiagnostic(Diagnostic diagnostic) {
		
		//en premier lieu on vérifie si le diagnostic lié à cet Erp n'est pas déjà traité
		boolean ajoutAutorise = true;
		
		int typeDiag = diagnostic.getTypeDiagnostic().getIdTypeDiagnostic();
		
		//on affiche les Erp avec leurs diags avec leurs Type de diag
		List<Diagnostic> liste =  proxyDiagnostic.recupereDiagnosticParErp(diagnostic.getErp());
		for (Diagnostic diag2 : liste) {
			if(diag2.getTraite() == 0 && diag2.getTypeDiagnostic().getIdTypeDiagnostic() == typeDiag)
			{
				ajoutAutorise = false;
				System.out.println(diag2.getErp().getIdErp() + "/" + diag2.getIdDiagnostic() + "/" + diag2.getTypeDiagnostic().getNomType());
				System.out.println(ajoutAutorise);
				System.out.println(diag2.getTraite() + diag2.getTypeDiagnostic().getIdTypeDiagnostic());
			}	
		} 
		if (ajoutAutorise == true) {
			System.out.println("on est dans le business");
			System.out.println("2222222222222222222222222222222222222");	
			proxyDiagnostic.ajouterDiagnostic(diagnostic);
			System.out.println(ajoutAutorise);
			return "Intervention enregristrée avec succès";
				
		}else
		{
			return "Un diagnostic de ce type est déjà actif sur cet Erp, un seul diagnostic de chaque type peut-être attribué à un Erp.";
		}
	}

	@Override
	public void modifierDiagnostic(Diagnostic diagnostic) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifierDiagnostic() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void historiserDiagnostic(Diagnostic diagnostic) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<HistoriqueDiagnostic> recupereToutHistoriqueDiagnostic() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TypeDiagnostic> recupereTypeDiagnostic() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Diagnostic recupereDiagnostic(int idDiagnostic) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Diagnostic> rechercheDiagnostics(String nomDiagnostic) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Diagnostic> rechercheDiagnosticsErp(String nomERP) {
		// TODO Auto-generated method stub
		return null;
	}

	public IDaoDiagnostic getProxyDiagnostic() {
		return proxyDiagnostic;
	}

	public void setProxyDiagnostic(IDaoDiagnostic proxyDiagnostic) {
		this.proxyDiagnostic = proxyDiagnostic;
	}

}
