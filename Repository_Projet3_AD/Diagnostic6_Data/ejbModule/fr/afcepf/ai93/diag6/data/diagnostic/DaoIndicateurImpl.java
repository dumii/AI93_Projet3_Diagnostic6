package fr.afcepf.ai93.diag6.data.diagnostic;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.afcepf.ai93.diag6.api.data.diagnostic.IDaoDiagnostic;
import fr.afcepf.ai93.diag6.api.data.diagnostic.IDaoIndicateur;
import fr.afcepf.ai93.diag6.entity.diagnostic.Anomalie;
import fr.afcepf.ai93.diag6.entity.diagnostic.Diagnostic;
import fr.afcepf.ai93.diag6.entity.diagnostic.Indicateur;
import fr.afcepf.ai93.diag6.entity.erp.Erp;
import fr.afcepf.ai93.diag6.entity.travaux.Intervention;

@Stateless
@Remote(IDaoIndicateur.class)
public class DaoIndicateurImpl implements IDaoIndicateur {
	@PersistenceContext(unitName="Malak_Diag_Data")
	private EntityManager em; 

	@Override
	public List<Indicateur> recupereIndicateur() {
		Query requete = em.createQuery("SELECT d FROM Indicateur d"); 
		List<Indicateur> liste = requete.getResultList(); 
		return liste;
	}

	@Override
	public List<Indicateur> recupereIndicateurParTypeDiagnostic(int idTypeDiagnostic) {
		Query query = em.createQuery("SELECT d.listeIndicateursTypeDiagnostic FROM TypeDiagnostic d WHERE d.idTypeDiagnostic = :pid");
		query.setParameter("pid", idTypeDiagnostic);
		List<Indicateur> liste = query.getResultList(); 
		return liste;
	}

	@Override
	public Indicateur recupereIndicateurParID(int idIndicateur) {
		Query query = em.createQuery("SELECT d FROM Indicateur d WHERE d.idIndicateur = :pid");
		query.setParameter("pid", idIndicateur);
		Indicateur indicateur = (Indicateur)query.getSingleResult(); 
		return indicateur;
	}

}
