package fr.afcepf.ai93.diag6.data.diagnostic;

import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.afcepf.ai93.diag6.api.data.diagnostic.IDaoDiagnostic;
import fr.afcepf.ai93.diag6.entity.diagnostic.Anomalie;
import fr.afcepf.ai93.diag6.entity.diagnostic.Diagnostic;
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
	Query requete = em.createQuery("SELECT a from Anomalie a inner join fetch a.listeInterventions where a.diagnostic.idDiagnostic = :id");
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
	public void modifierDiagnostic(Diagnostic diagnostic) {
		em.merge(diagnostic);
	}

	@Override
	public void notifierDiagnostic() {
		// la notification se fait automatiquement. une methode est-elle utile?
	}

	@Override
	public void historiserDiagnostic(Diagnostic diagnostic) {
		// l'historisation est automatique, la methode n'est pas utile
	}

	@Override
	public Diagnostic recupereDiagnostic(int idDiagnostic) {
		Query query = em.createQuery("SELECT d FROM Diagnostic d WHERE d.id = :pid");
		query.setParameter("pid", idDiagnostic);
		Diagnostic diagnostic = (Diagnostic) query.getSingleResult();
		return diagnostic;
	}

	@Override
	public List<Diagnostic> rechercheDiagnostics(String nomDiagnostic) {
		Query query = em.createQuery("SELECT d FROM Diagnostic d WHERE d.intitule = :pid");
		List<Diagnostic> liste = query.getResultList();
		return liste;
	}

	@Override
	public List<Diagnostic> rechercheDiagnosticsErp(String nomERP) {
		
		return null;
	}
	
	
}
