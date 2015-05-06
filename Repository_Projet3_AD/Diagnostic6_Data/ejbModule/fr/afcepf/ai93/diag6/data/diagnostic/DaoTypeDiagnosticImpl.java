package fr.afcepf.ai93.diag6.data.diagnostic;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.afcepf.ai93.diag6.api.data.diagnostic.IDaoTypeDiagnostic;
import fr.afcepf.ai93.diag6.entity.diagnostic.TypeDiagnostic;

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
}
