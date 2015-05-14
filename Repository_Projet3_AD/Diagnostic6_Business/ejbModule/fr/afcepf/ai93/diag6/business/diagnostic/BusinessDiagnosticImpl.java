package fr.afcepf.ai93.diag6.business.diagnostic;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import fr.afcepf.ai93.diag6.api.business.diagnostic.IBusinessDiagnostic;
import fr.afcepf.ai93.diag6.api.data.diagnostic.IDaoDiagnostic;
import fr.afcepf.ai93.diag6.api.data.diagnostic.IDaoHistoriqueDiagnostic;
import fr.afcepf.ai93.diag6.api.data.diagnostic.IDaoIndicateur;
import fr.afcepf.ai93.diag6.api.data.diagnostic.IDaoTypeDiagnostic;
import fr.afcepf.ai93.diag6.entity.autres.Utilisateur;
import fr.afcepf.ai93.diag6.entity.diagnostic.Anomalie;
import fr.afcepf.ai93.diag6.entity.diagnostic.Diagnostic;
import fr.afcepf.ai93.diag6.entity.diagnostic.HistoriqueDiagnostic;
import fr.afcepf.ai93.diag6.entity.diagnostic.Indicateur;
import fr.afcepf.ai93.diag6.entity.diagnostic.TypeDiagnostic;
import fr.afcepf.ai93.diag6.entity.erp.Erp;

@Stateless
@Remote(IBusinessDiagnostic.class)
public class BusinessDiagnosticImpl implements IBusinessDiagnostic {
	@EJB
	private IDaoDiagnostic proxyDiagnostic; 
	@EJB
	private IDaoTypeDiagnostic proxyTypeDiagnostic; 
	@EJB 
	private IDaoIndicateur proxyIndicateur; 
	@EJB
	private IDaoHistoriqueDiagnostic proxyHistoDiag; 
	
	private List<Diagnostic> listeDiag; 
	private List<Diagnostic> listeDiagIntervEnCours = new ArrayList<Diagnostic>(); 
	private List<Diagnostic> listeDiagEnAttente = new ArrayList<Diagnostic>(); 
	private List<Diagnostic> listeDiagArchives = new ArrayList<Diagnostic>();
	
	private boolean ERPinterventionEnCours = false;
	private boolean ERPinterventionEnAttente = false;
	private boolean ERPinterventionArchives = false;
		
	@Override
	public List<Diagnostic> recupereToutDiagnostic() {
	
		List<Diagnostic> liste = proxyDiagnostic.recupereToutDiagnostic(); 
		
		for (Diagnostic d : liste)
		{
			if(d.getTraite()!= 0)
			{
				listeDiagArchives.add(d); 
			}
			else
			{
				if(proxyDiagnostic.recupereSiIntervEnCoursParDiag(d.getIdDiagnostic())) 
				{
					listeDiagIntervEnCours.add(d); 
				}
				else 
				{
					listeDiagEnAttente.add(d);	
				}
			}
		}
		return liste;
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
		List<Diagnostic> liste =  proxyDiagnostic.recupereDiagnosticNonTraitesParErp(diagnostic.getErp());
		for (Diagnostic diag2 : liste) {
			if(diag2.getTraite() == 0 && diag2.getTypeDiagnostic().getIdTypeDiagnostic() == typeDiag)
			{
				ajoutAutorise = false;
			}	
		} 
		if (ajoutAutorise == true) {	
			proxyDiagnostic.ajouterDiagnostic(diagnostic);
			return "Intervention enregistrée avec succès";
				
		}else
		{
			return "Un diagnostic de ce type est déjà actif sur cet Erp, un seul diagnostic de chaque type peut-être attribué à un Erp.";
		}
	}

	@Override
	public String modifierDiagnostic(Diagnostic diagnostic, Utilisateur user) {
		Diagnostic diagInitial = proxyDiagnostic.recupereDiagnostic(diagnostic.getIdDiagnostic());
		proxyDiagnostic.modifierDiagnostic(diagnostic, user);
		proxyHistoDiag.historiser(diagInitial, diagnostic, user);
		return "Ok modif";
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
		return proxyTypeDiagnostic.recupereTypeDiagnostic();
	}

	@Override
	public Diagnostic recupereDiagnostic(int idDiagnostic) {
		return proxyDiagnostic.recupereDiagnostic(idDiagnostic);
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
	

	@Override
	public List<Indicateur> recupererIndicateursParDiag(Diagnostic diagEnCours) {
		List<Indicateur> listeIndicateurs = proxyIndicateur.recupereIndicateur();
		List<Indicateur> listeIndicateursParDiag = new ArrayList<Indicateur>(); 
		for(Indicateur i : listeIndicateurs){
			if(i.getTypeDiagnostic().getIdTypeDiagnostic() == diagEnCours.getTypeDiagnostic().getIdTypeDiagnostic()){
				listeIndicateursParDiag.add(i); 
			}
		}
		return listeIndicateursParDiag;
	}

	@Override
	public List<Diagnostic> recupereDiagnosticNonTraitesParErp(Erp erp) {
		return proxyDiagnostic.recupereDiagnosticNonTraitesParErp(erp);
	}

	@Override
	public List<Diagnostic> recupereToutDiagnosticParErp(Erp erp) {
		
		List<Diagnostic> liste = proxyDiagnostic.recupereToutDiagnosticParErp(erp); 
		
		for (Diagnostic d : liste)
		{
			if(d.getTraite()!= 0)
			{
				ERPinterventionArchives = true;
			}
			else
			{
				if(proxyDiagnostic.recupereSiIntervEnCoursParDiag(d.getIdDiagnostic())) 
				{
					ERPinterventionEnCours = true;
				}
				else 
				{
					ERPinterventionEnAttente = true;
				}
			}
		}		
		return liste;
	}

	public boolean interventionEnCoursSurERP()
	{
		return ERPinterventionEnCours;
	}
	
	public boolean interventionEnAttenteSurERP()
	{
		return ERPinterventionEnAttente;
	}
	
	public boolean interventionArchivesSurERP()
	{
		return ERPinterventionArchives;
	}


	@Override
	public boolean recupererDiagSansInterv(Integer idDiag) {
		//si la methode du Dao retourne false, c'est qu'il n'y a aucune intervention dessous
		return proxyDiagnostic.recupereSiIntervEnCoursParDiag(idDiag); 
	}

	@Override
	public List<Indicateur> recupereIndicateurParTypeDiagnostic(
			int idTypeDiagnostic) {
		return proxyIndicateur.recupereIndicateurParTypeDiagnostic(idTypeDiagnostic);
	}

	@Override
	public int getMaxId() {
		return proxyDiagnostic.getMaxId();
	}

	@Override
	public List<TypeDiagnostic> recupereTypeDiagnosticDospoParERP(Erp erp) {
		List<TypeDiagnostic> listeTousTypes = proxyTypeDiagnostic.recupereTypeDiagnostic();
		List<TypeDiagnostic> listeTypeERP = proxyTypeDiagnostic.recupereTypeDiagnosticParErp(erp);
		List<TypeDiagnostic> listeFinale = new ArrayList<TypeDiagnostic>();

		for (TypeDiagnostic type : listeTousTypes)
		{
			boolean ajout = true;
			for (TypeDiagnostic type2 : listeTypeERP)
			{
				if (type.getIdTypeDiagnostic() == type2.getIdTypeDiagnostic())
				{
					ajout = false;
				}
			}
			if (ajout)
			{
				listeFinale.add(type);
			}
		}

		return listeFinale;
	}

	@Override
	public TypeDiagnostic recupereTypeDiagnosticParID(int idTypeDiagnostic) {
		return proxyTypeDiagnostic.recupereTypeDiagnosticParID(idTypeDiagnostic);
	}
}
