package fr.afcepf.ai93.diag6.data.diagnostic;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.afcepf.ai93.diag6.api.data.diagnostic.IDaoDiagnostic;
import fr.afcepf.ai93.diag6.entity.autres.Utilisateur;
import fr.afcepf.ai93.diag6.entity.diagnostic.Anomalie;
import fr.afcepf.ai93.diag6.entity.diagnostic.Diagnostic;
import fr.afcepf.ai93.diag6.entity.erp.Erp;
import fr.afcepf.ai93.diag6.entity.travaux.Intervention;

@Stateless
@Remote(IDaoDiagnostic.class)
public class DaoDiagnosticImpl implements IDaoDiagnostic {
	
	@PersistenceContext(unitName="Malak_Diag_Data")
	private EntityManager em; 

	@Override
	public List<Diagnostic> recupereToutDiagnostic() {
		Query requete = em.createQuery("SELECT d FROM Diagnostic d"); 
		List<Diagnostic> listeToutDiag = requete.getResultList(); 
		return listeToutDiag;
	}

	@Override
	public boolean recupereSiIntervEnCoursParDiag(int idDiag) {
		Query requete = em.createQuery("SELECT a from Anomalie a inner join fetch a.intervention where a.diagnostic.idDiagnostic = :id");
		requete.setParameter("id", idDiag);
		List<Anomalie> listeAnomaliesAvecIntervention = requete.getResultList();
		for (Anomalie a : listeAnomaliesAvecIntervention)

			if (listeAnomaliesAvecIntervention.size() > 0)
			{
				return true;
			}
		return false;
	}

	@Override
	public void ajouterDiagnostic(Diagnostic diagnostic) {
		em.persist(diagnostic);
	}

	@Override
	public void modifierDiagnostic(Diagnostic diagnostic, Utilisateur user) {
		em.merge(diagnostic);
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
	public Diagnostic recupereDiagnostic(int idDiagnostic) {
		Query query = em.createQuery("SELECT d FROM Diagnostic d WHERE d.idDiagnostic = :pid");
		query.setParameter("pid", idDiagnostic);
		Diagnostic diagnostic = (Diagnostic) query.getSingleResult();
		return diagnostic;
	}

	@Override
	public List<Diagnostic> rechercheDiagnostics(String nomDiagnostic) {
		Query query = em.createQuery("SELECT d FROM Diagnostic d WHERE d.intituleDiagnostic like :pid");
		query.setParameter("pid","%"+nomDiagnostic+"%");
		List<Diagnostic> liste = query.getResultList();
		return liste;
	}

	@Override
	public List<Diagnostic> rechercheDiagnosticsErp(String nomERP) {
		//TO DO GENERATED
		return null;
	}


	public List<Diagnostic> recupereDiagnosticNonTraitesParErp(Erp erp) {

		Query requete = em.createQuery("SELECT a.listeDiagnosticErp FROM Erp a WHERE a.idErp = :pid");
		
		requete.setParameter("pid", erp.getIdErp());
		List<Diagnostic> liste = requete.getResultList();

		List<Diagnostic> listeARetourner = new ArrayList<>();

		for (Diagnostic diag : liste)
		{
			//On ne récupère que les diagnostics non traités
			if (diag.getTraite() == 0)
			{
				listeARetourner.add(diag);
			}
		}
		return listeARetourner;
	}

	@Override
	public List<Diagnostic> recupereToutDiagnosticParErp(Erp e) {
		Query requete = em.createQuery("SELECT a.listeDiagnosticErp from Erp a WHERE a.idErp = :pid");
		requete.setParameter("pid", e.getIdErp());
		List<Diagnostic> liste = requete.getResultList();
		return liste;
	}
}
