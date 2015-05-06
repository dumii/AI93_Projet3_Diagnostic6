package fr.afcepf.ai93.diag6.data.diagnostic;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.afcepf.ai93.diag6.api.data.diagnostic.IDaoHistoriqueAnomalie;
import fr.afcepf.ai93.diag6.entity.autres.Utilisateur;
import fr.afcepf.ai93.diag6.entity.diagnostic.Anomalie;
import fr.afcepf.ai93.diag6.entity.diagnostic.HistoriqueAnomalie;

@Stateless
@Remote(IDaoHistoriqueAnomalie.class)
public class DaoHistoriqueAnomalieImpl implements IDaoHistoriqueAnomalie {

	
	private final static String MODIF_INDICATEUR = "indicateur";
	private final static String MODIF_DESCRIPTION = "descriptionAnomalie";
	private final static String MODIF_PRECONISATION = "preconisationAnomalie";
	private final static String MODIF_COUT_ESTIME = "coutEstimeAnomalie";
	
	@PersistenceContext(unitName="Malak_Diag_Data")
	private EntityManager em;

	@Override
	public List<HistoriqueAnomalie> recupereToutHistoriqueAnomalie() {
		Query query = em.createQuery("SELECT h from HistoriqueAnomalie h");
		List<HistoriqueAnomalie> liste = query.getResultList();
		return liste;
	}

	@Override
	public void historiser(Anomalie anomalieInitiale, Anomalie anomalie,
			Utilisateur user) {
		HistoriqueAnomalie historique = new HistoriqueAnomalie();
		historique.setAnomalie(anomalie);
		historique.setUtilisateur(user);
		historique.setDateModification(new Date());
		
		int valeurIndicateurInitiale = anomalieInitiale.getIndicateur().getValeurIndicateur();
		int valeurIndicateurNouvelle = anomalie.getIndicateur().getValeurIndicateur();
		
		if (valeurIndicateurInitiale != valeurIndicateurNouvelle)
		{
			historique.setTypeModification(MODIF_INDICATEUR);
			historique.setAncienneDonnee(""+valeurIndicateurInitiale);
			historique.setNouvelleDonnee(""+valeurIndicateurNouvelle);
			em.persist(historique);
		}
		
		String descriptionInitiale = anomalieInitiale.getDescriptionAnomalie();
		String descriptionNouvelle = anomalie.getDescriptionAnomalie();
		
		if (!descriptionInitiale.equals(descriptionNouvelle))
		{
			historique.setTypeModification(MODIF_DESCRIPTION);
			historique.setAncienneDonnee(descriptionInitiale);
			historique.setNouvelleDonnee(descriptionNouvelle);
			em.persist(historique);
		}
		
		String preconisationInitiale = anomalieInitiale.getPreconisationAnomalie();
		String preconisationNouvelle = anomalie.getPreconisationAnomalie();
		
		if (!preconisationInitiale.equals(preconisationNouvelle))
		{
			historique.setTypeModification(MODIF_PRECONISATION);
			historique.setAncienneDonnee(preconisationInitiale);
			historique.setNouvelleDonnee(preconisationNouvelle);
			em.persist(historique);
		}
		
		double coutInitialEstime = anomalieInitiale.getCoutEstimeAnomalie();
		double coutNouveauEstime = anomalie.getCoutEstimeAnomalie();
		
		if (coutInitialEstime != coutNouveauEstime)
		{
			historique.setTypeModification(MODIF_COUT_ESTIME);
			historique.setAncienneDonnee(""+coutInitialEstime);
			historique.setNouvelleDonnee(""+coutNouveauEstime);
			em.persist(historique);
		}
	}
}
