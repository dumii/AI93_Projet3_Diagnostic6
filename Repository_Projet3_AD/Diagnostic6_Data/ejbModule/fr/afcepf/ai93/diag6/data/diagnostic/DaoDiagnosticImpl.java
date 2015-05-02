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
	// TODO Auto-generated method stub
	
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


}
