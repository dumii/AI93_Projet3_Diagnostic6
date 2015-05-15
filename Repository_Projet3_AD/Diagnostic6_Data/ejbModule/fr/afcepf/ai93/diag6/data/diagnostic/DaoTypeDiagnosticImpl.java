package fr.afcepf.ai93.diag6.data.diagnostic;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.afcepf.ai93.diag6.api.data.diagnostic.IDaoTypeDiagnostic;
import fr.afcepf.ai93.diag6.entity.diagnostic.Anomalie;
import fr.afcepf.ai93.diag6.entity.diagnostic.TypeDiagnostic;
import fr.afcepf.ai93.diag6.entity.erp.Erp;

@Stateless
@Remote(IDaoTypeDiagnostic.class)
public class DaoTypeDiagnosticImpl implements IDaoTypeDiagnostic {

	@PersistenceContext(unitName="Malak_Diag_Data")
	private EntityManager em;

	@Override
	public List<TypeDiagnostic> recupereTypeDiagnostic() {
		Query query = em.createQuery("SELECT e from TypeDiagnostic e");
		List<TypeDiagnostic> liste = query.getResultList();
		return liste;
	}

	@Override
	public List<TypeDiagnostic> recupereTypeDiagnosticParErp(Erp erp) {
		//Recherche des différents types de diagnostic sur un ERP et pour lesquels le diagnostic a le statut "non traité" (traite = 0)
		Query requete = em.createQuery("SELECT a.typeDiagnostic FROM Diagnostic a WHERE a.erp.idErp = :pid AND a.traite = :ptraite");
		requete.setParameter("pid", erp.getIdErp());
		requete.setParameter("ptraite", 0);
		List<TypeDiagnostic> liste = requete.getResultList();
		return liste;
	}

	@Override
	public TypeDiagnostic recupereTypeDiagnosticParID(int idTypeDiagnostic) {
		Query requete = em.createQuery("SELECT a FROM TypeDiagnostic a WHERE a.idTypeDiagnostic = :pid");
		requete.setParameter("pid", idTypeDiagnostic);
		TypeDiagnostic type = (TypeDiagnostic) requete.getSingleResult();
		return type;
	}
}
