package fr.afcepf.ai93.diag6.data.diagnostic;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.afcepf.ai93.diag6.api.data.diagnostic.IDaoHistoriqueAnomalie;
import fr.afcepf.ai93.diag6.api.data.diagnostic.IDaoHistoriqueDiagnostic;
import fr.afcepf.ai93.diag6.entity.autres.Utilisateur;
import fr.afcepf.ai93.diag6.entity.diagnostic.Anomalie;
import fr.afcepf.ai93.diag6.entity.diagnostic.Diagnostic;
import fr.afcepf.ai93.diag6.entity.diagnostic.HistoriqueAnomalie;
import fr.afcepf.ai93.diag6.entity.diagnostic.HistoriqueDiagnostic;

@Stateless
@Remote(IDaoHistoriqueDiagnostic.class)
public class DaoHistoriqueDiagnosticImpl implements IDaoHistoriqueDiagnostic {


	private final static String MODIF_TRAITE = "Traité";
	private final static String MODIF_EXPERT = "Expert";
	private final static String MODIF_DATE_REALISATION = "Date réalisation";

	@PersistenceContext(unitName="Malak_Diag_Data")
	private EntityManager em;


	@Override
	public List<HistoriqueDiagnostic> recupereToutHistoriqueDiagnostic() {
		// TODO Auto-generated method stub
		return null;
	}
	
	//comparaison successive de chacun des attributs du nouveau et de l'ancien diagnostic.
	// s'il s'avèe que des valeurs différent, on les "historisent" dans la table associée
	@Override
	public void historiser(Diagnostic diagnosticInitial, Diagnostic diagnostic,
			Utilisateur user) {

		HistoriqueDiagnostic historique = new HistoriqueDiagnostic();
		historique.setDiagnostic(diagnostic);
		historique.setUtilisateur(user);
		historique.setDateModification(new Date());

		int valeurTraiteInitiale = diagnosticInitial.getTraite();
		int valeurTraiteNouvelle = diagnostic.getTraite();

		if (valeurTraiteInitiale != valeurTraiteNouvelle)
		{
			historique.setTypeModification(MODIF_TRAITE);
			historique.setAncienneDonnee(""+valeurTraiteInitiale);
			historique.setNouvelleDonnee(""+valeurTraiteNouvelle);
			em.persist(historique);
		}

		int expertInitiale = diagnosticInitial.getExpert().getIdExpert();
		int expertNouvelle = diagnostic.getExpert().getIdExpert();

		if (expertInitiale != expertNouvelle)
		{
			historique.setTypeModification(MODIF_EXPERT);
			historique.setAncienneDonnee(""+expertInitiale);
			historique.setNouvelleDonnee(""+expertNouvelle);
			em.persist(historique);
		}

		Date dateInitiale = diagnosticInitial.getDateRealisationDiagnostic();
		Date dateNouvelle = diagnostic.getDateRealisationDiagnostic();

		if (!dateInitiale.equals(dateNouvelle))
		{
			historique.setTypeModification(MODIF_DATE_REALISATION);
			historique.setAncienneDonnee(""+dateInitiale);
			historique.setNouvelleDonnee(""+dateNouvelle);
			em.persist(historique);
		}
	}
}

