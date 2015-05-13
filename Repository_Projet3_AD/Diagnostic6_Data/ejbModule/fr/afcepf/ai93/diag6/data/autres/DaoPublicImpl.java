package fr.afcepf.ai93.diag6.data.autres;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;






import fr.afcepf.ai93.diag6.api.data.publics.IDaoPublic;
import fr.afcepf.ai93.diag6.entity.diagnostic.Diagnostic;
import fr.afcepf.ai93.diag6.entity.diagnostic.TypeDiagnostic;
import fr.afcepf.ai93.diag6.entity.erp.Erp;
import fr.afcepf.ai93.diag6.entity.erp.TypeErp;
import fr.afcepf.ai93.diag6.entity.travaux.Intervention;

@Stateless
@Remote(IDaoPublic.class)
public class DaoPublicImpl implements IDaoPublic {

	@PersistenceContext(unitName="Malak_Diag_Data")
	EntityManager em;
	
	
	@Override
	public List<Erp> afficherErpAuxNormes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Erp> moyenneErpAuxNormes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Erp> calculGraphiquePatrimoine() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Erp> calculErpParTypeDiagnostic(int idTypeDiagnostic) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Erp> calculErpParTraite(int traite) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void localisationErpGoogleMap() {
		// TODO Auto-generated method stub

	}

	
	
	@Override
	public Integer nbInterventionsTerminees() {
		
		Integer nbInterventionsTerminees = 0;
		
		
		Query query = em.createQuery("Select e from Intervention e where e.etatAvancementTravaux = 1");
		List<Intervention> liste = query.getResultList();
		
		for(Intervention i : liste){
			nbInterventionsTerminees++;
			System.out.println(i.getIdIntervention());
		}
		
		return nbInterventionsTerminees;
	}
	

	@Override
	public Integer nbInterventionsEnCours() {
		
		Integer nbInterventionsEnCours = 0;
		
		Query query = em.createQuery("Select e from Intervention e where e.etatAvancementTravaux = 2");
		List<Intervention> liste = query.getResultList();
		
		for(Intervention i : liste){
			nbInterventionsEnCours++;
			System.out.println(i.getIdIntervention());
		}
		
		return nbInterventionsEnCours;
	}
	

	@Override
	public Integer nbInterventionsPlanifiess() {
		
		Integer nbInterventionsPlanifiees = 0;
		
		Query query = em.createQuery("Select e from Intervention e where e.etatAvancementTravaux = 3");
		List<Intervention> liste = query.getResultList();
		
		for(Intervention i : liste){
			nbInterventionsPlanifiees++;
			System.out.println(i.getIdIntervention());
		}
		
		return nbInterventionsPlanifiees;
	}
	

	@Override
	public Integer nbInterventionsDiagnostiquees() {
		
		Integer nbInterventionsDiagnostiquees = 0;
		
		Query query = em.createQuery("Select e from Intervention e where e.etatAvancementTravaux = 4");
		List<Intervention> liste = query.getResultList();
		
		for(Intervention i : liste){
			nbInterventionsDiagnostiquees++;
			System.out.println(i.getIdIntervention());
		}
		
		return nbInterventionsDiagnostiquees;
	}

	@Override
	public Integer nbDiagnosticAccessibilitéTotal() {
		
		Integer nbDiagnosticAccessibilitéTotal = 0;
		
		Query query = em.createQuery("Select e from Diagnostic e where e.typeDiagnostic = 1");
		List<Diagnostic> liste = query.getResultList();
		
		for(Diagnostic i : liste){
			nbDiagnosticAccessibilitéTotal++;
		}
		
		return nbDiagnosticAccessibilitéTotal;
	}

	@Override
	public Integer nbDiagnosticEnergieTotal() {
		
		Integer nbDiagnosticEnergieTotal = 0;
		
		Query query = em.createQuery("Select e from Diagnostic e where e.typeDiagnostic = 2");
		List<Diagnostic> liste = query.getResultList();
		
		for(Diagnostic i : liste){
			nbDiagnosticEnergieTotal++;
		}
		
		return nbDiagnosticEnergieTotal;
	}

	@Override
	public Integer nbDiagnosticSecuriteTotal() {
		
		Integer nbDiagnosticSecuriteTotal = 0;
		
		Query query = em.createQuery("Select e from Diagnostic e where e.typeDiagnostic = 3");
		List<Diagnostic> liste = query.getResultList();
		
		for(Diagnostic i : liste){
			nbDiagnosticSecuriteTotal++;
		}
		
		return nbDiagnosticSecuriteTotal;
	}

	@Override
	public Integer nbDiagnosticHygieneTotal() {
		
		Integer nbDiagnosticHygieneTotal = 0;
		
		Query query = em.createQuery("Select e from Diagnostic e where e.typeDiagnostic = 4");
		List<Diagnostic> liste = query.getResultList();
		
		for(Diagnostic i : liste){
			nbDiagnosticHygieneTotal++;
		}
		
		return nbDiagnosticHygieneTotal;
	}

	@Override
	public Integer nbDiagnosticAccessibilitéTraites() {

		Integer nbDiagnosticAccessibilitéTraites = 0;
		
		Query query = em.createQuery("Select e from Diagnostic e where e.typeDiagnostic = 1 and e.traite = 1");
		List<Diagnostic> liste = query.getResultList();
		
		for(Diagnostic i : liste){
			nbDiagnosticAccessibilitéTraites++;
		}
		
		return nbDiagnosticAccessibilitéTraites;
	}

	@Override
	public Integer nbDiagnosticEnergieTraites() {

		Integer nbDiagnosticEnergieTraites = 0;
		
		Query query = em.createQuery("Select e from Diagnostic e where e.typeDiagnostic = 2 and e.traite = 1");
		List<Diagnostic> liste = query.getResultList();
		
		for(Diagnostic i : liste){
			nbDiagnosticEnergieTraites++;
		}
		
		return nbDiagnosticEnergieTraites;
	}

	@Override
	public Integer nbDiagnosticSecuriteTraites() {

		Integer nbDiagnosticSecuriteTraites = 0;
		
		Query query = em.createQuery("Select e from Diagnostic e where e.typeDiagnostic = 3 and e.traite = 1");
		List<Diagnostic> liste = query.getResultList();
		
		for(Diagnostic i : liste){
			nbDiagnosticSecuriteTraites++;
		}
		
		return nbDiagnosticSecuriteTraites;
	}

	@Override
	public Integer nbDiagnosticHygieneTraites() {

		Integer nbDiagnosticHygieneTraites = 0;
		
		Query query = em.createQuery("Select e from Diagnostic e where e.typeDiagnostic = 4 and e.traite = 1");
		List<Diagnostic> liste = query.getResultList();
		
		for(Diagnostic i : liste){
			nbDiagnosticHygieneTraites++;
		}
		
		return nbDiagnosticHygieneTraites;
	}

	@Override
	public Integer nbERpAuxNormes() {

		Integer nbERpAuxNormes = 0;
		
		Query query = em.createQuery("SELECT distinct e.erp FROM Diagnostic e where e.traite =1");
		List<Erp> liste = query.getResultList();
		
		for(Erp i : liste){
			nbERpAuxNormes++;
		}
		
		return nbERpAuxNormes;
	}

	@Override
	public Integer nbErpEnCoursDeNormalité() {

		Integer nbErpEnCoursDeNormalité = 0;
		
		Query query = em.createQuery("SELECT distinct e.erp FROM Diagnostic e where e.traite =0");
		List<Erp> liste = query.getResultList();
		
		for(Erp i : liste){
			nbErpEnCoursDeNormalité++;
		}
		
		return nbErpEnCoursDeNormalité;
	}

	@Override
	public List<TypeErp> listerTypeErp() {
		
		Query query = em.createQuery("SELECT e FROM TypeErp e where e.idTypeErp in (select t.typeErp from Erp t)");
		List<TypeErp> liste = query.getResultList();
		return liste;
	}

	@Override
	public List<TypeDiagnostic> listerTypeDiagnostic() {
		
		Query query = em.createQuery("SELECT e FROM TypeDiagnostic e");
		List<TypeDiagnostic> liste = query.getResultList();
		return liste;
	}

	@Override
	public List<Erp> recupererErpParTypeDiagnostic(Integer idTypeDiagSelect) {
		
		Query query = em.createQuery("SELECT e FROM Erp e inner join fetch  e.listeDiagnosticErp l where l.typeDiagnostic.idTypeDiagnostic = :pid ");
									
		query.setParameter("pid", idTypeDiagSelect);
		List<Erp> liste = query.getResultList();
		for(Erp e : liste){
			System.out.println(e.getNomErp());
		}
		return liste;
	}

	@Override
	public List<Erp> recupererErpParTravauxEnCours(boolean booleenTravaux) {
		
		Query query = em.createQuery("SELECT e FROM TypeDiagnostic e");
		List<Erp> liste = query.getResultList();
		return liste;
	}

}
