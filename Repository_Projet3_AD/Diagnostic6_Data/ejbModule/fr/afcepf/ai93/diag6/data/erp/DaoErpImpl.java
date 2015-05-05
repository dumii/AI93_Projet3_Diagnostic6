package fr.afcepf.ai93.diag6.data.erp;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.afcepf.ai93.diag6.api.data.erp.IDaoErp;
import fr.afcepf.ai93.diag6.entity.erp.Acces;
import fr.afcepf.ai93.diag6.entity.erp.Ascenceur;
import fr.afcepf.ai93.diag6.entity.erp.Batiment;
import fr.afcepf.ai93.diag6.entity.erp.Erp;
import fr.afcepf.ai93.diag6.entity.erp.Escalier;
import fr.afcepf.ai93.diag6.entity.erp.Etage;
import fr.afcepf.ai93.diag6.entity.erp.Piece;

@Stateless
@Remote(IDaoErp.class)
public class DaoErpImpl implements IDaoErp {
	@PersistenceContext(unitName="Malak_Diag_Data")
	private EntityManager em; 
	
	@Override
	public List<Erp> recupereToutErp() {
		Query requete = em.createQuery("SELECT e FROM Erp e"); 
		List<Erp> listeToutErp = requete.getResultList(); 
		return listeToutErp;
	}

	@Override
	public List<Batiment> recupererBatParErp(int idErp) {
		Query requete = em.createQuery("select bat from Batiment bat where erp.idErp = :pid"); 
		requete.setParameter("pid", idErp);
		List<Batiment> listeBatiments = requete.getResultList(); 
		return listeBatiments;
	}

	@Override
	public List<Etage> recupererEtagesParBat(int idBatiment) {
		Query requete = em.createQuery("select etg from Etage etg where batiment.idBatiment = :pid"); 
		requete.setParameter("pid", idBatiment);
		List<Etage> listeEtages = requete.getResultList(); 
		return listeEtages;
	}
	

	@Override
	public List<Piece> recupererPiecesParEtage(int idEtage) {
		Query requete = em.createQuery("select p from Piece p where etage.idEtage = :pid"); 
		requete.setParameter("pid", idEtage);
		List<Piece> listePieces = requete.getResultList(); 
		return listePieces;
	}

	@Override
	public List<Acces> recupererAccesParBat(int idBatiment) {
		Query requete = em.createQuery("select acc from Acces acc where batiment.idBatiment = :pid"); 
		requete.setParameter("pid", idBatiment);
		List<Acces> listeAcces = requete.getResultList(); 
		return listeAcces;
	}


	@Override
	public List<Ascenceur> recupererAscenceursParBat(int idBatiment) {
		Query requete = em.createQuery("select asce from Ascenceur asce where batiment.idBatiment = :pid"); 
		requete.setParameter("pid", idBatiment);
		List<Ascenceur> listeAscenceur = requete.getResultList(); 
		return listeAscenceur;
	}

	@Override
	public List<Escalier> recupererEscaliersParBat(int idBatiment) {
		Query requete = em.createQuery("select esc from Escalier esc where batiment.idBatiment = :pid"); 
		requete.setParameter("pid", idBatiment);
		List<Escalier> listeEscalier = requete.getResultList(); 
		return listeEscalier;
	}


}
