package fr.afcepf.ai93.diag6.data.diagnostic;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.afcepf.ai93.diag6.api.data.diagnostic.IDaoAnomalie;
import fr.afcepf.ai93.diag6.entity.autres.Utilisateur;
import fr.afcepf.ai93.diag6.entity.diagnostic.Anomalie;

@Stateless
@Remote(IDaoAnomalie.class)

public class DaoAnomalieImpl implements IDaoAnomalie{
	
	@PersistenceContext(unitName="Malak_Diag_Data")
	private EntityManager em; 

	@Override
	public List<Anomalie> recupereToutAnomalie() {
		Query query = em.createQuery("SELECT a FROM Anomalie a");
		Query query = em.createQuery("SELECT e from Anomalie e");
		List<Anomalie> liste = query.getResultList();
		return liste;
	}

	@Override
	public void ajouterAnomalie(Anomalie anomalie) {
		em.persist(anomalie);		
	}
	
	@Override
	public boolean modifierAnomalie(Anomalie anomalie, Utilisateur user) {
		// Merges changes to the database
		em.merge(anomalie);
		return true;
	}

	@Override
	public void modifierAnomalie(Anomalie anomalie) {
		em.merge(anomalie);
	public String supprimerAnomalie(Anomalie anomalie) {
		em.remove(anomalie);
		return "L'anomalie a été supprimée";
	}

	@Override
	public Anomalie recupereAnomalie(int idAnomalie) {
		Query query = em.createQuery("SELECT a FROM Anomalie a WHERE a.idAnomalie = :pid");
		Query query = em.createQuery("SELECT e from Anomalie e WHERE e.idAnomalie = :pid");
		query.setParameter("pid", idAnomalie);
		Anomalie anomalie = (Anomalie) query.getSingleResult();
		return anomalie;
	}

	@Override
	public List<Anomalie> rechercheAnomaliesErp(String nomERP) {
		return null; 
	}

	@Override
	public void historiserAnomalie(Anomalie anomalie) {

	}

	@Override
	public boolean supprimerAnomalie(Anomalie anomalie) {
		em.merge(anomalie);
		return true;
	}


	@Override
	public List<Anomalie> recupereAnomalieParDiagnostic(int idDiagnostic) {
		Query requete = em.createQuery("select a from Anomalie a where diagnostic.idDiagnostic = :pif"); 
		requete.setParameter("pif", idDiagnostic); 
		List<Anomalie> listeAnomParDiag = requete.getResultList(); 
		return listeAnomParDiag; 
	}
}
