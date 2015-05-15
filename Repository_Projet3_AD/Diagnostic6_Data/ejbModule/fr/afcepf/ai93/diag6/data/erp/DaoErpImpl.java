package fr.afcepf.ai93.diag6.data.erp;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.afcepf.ai93.diag6.api.data.erp.IDaoErp;
import fr.afcepf.ai93.diag6.entity.diagnostic.Diagnostic;
import fr.afcepf.ai93.diag6.entity.erp.Acces;
import fr.afcepf.ai93.diag6.entity.erp.Ascenceur;
import fr.afcepf.ai93.diag6.entity.erp.Batiment;
import fr.afcepf.ai93.diag6.entity.erp.Erp;
import fr.afcepf.ai93.diag6.entity.erp.Escalier;
import fr.afcepf.ai93.diag6.entity.erp.Etage;
import fr.afcepf.ai93.diag6.entity.erp.Piece;
import fr.afcepf.ai93.diag6.entity.erp.TypeErp;
import fr.afcepf.ai93.diag6.entity.erp.Voirie;
import fr.afcepf.ai93.diag6.entity.travaux.Intervention;

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

	@Override
	public List<Voirie> recupererVoirieParErp(int idErp) {
		Query requete = em.createQuery("select voir from Voirie voir where erp.idErp = :pid"); 
		requete.setParameter("pid", idErp);
		List<Voirie> listeVoiries = requete.getResultList(); 
		return listeVoiries;
	}
	
	public Erp recupererErpParId(int idErp) {
		Query query = em.createQuery("SELECT e from Erp e WHERE e.idErp = :pid");
		query.setParameter("pid", idErp);
		Erp erp = (Erp) query.getSingleResult();
		return erp;
	}

	@Override
	public List<Erp> recupereErpParNom(String stringCherche) {
		Query query = em.createQuery("SELECT e FROM Erp e WHERE e.nomErp like :pid");
		query.setParameter("pid","%"+stringCherche+"%");
		List<Erp> liste = query.getResultList();
		return liste;
	}

	public Acces recupereAccesParID(int idAcces) {
		Query query = em.createQuery("SELECT e from Acces e WHERE e.idAcces = :pid");
		query.setParameter("pid", idAcces);
		Acces acces = (Acces) query.getSingleResult();
		return acces;
	}

	@Override
	public Escalier recupereEscalierParID(int idEscalier) {
		Query query = em.createQuery("SELECT e from Escalier e WHERE e.idEscalier = :pid");
		query.setParameter("pid", idEscalier);
		Escalier escalier = (Escalier) query.getSingleResult();
		return escalier;
	}

	@Override
	public Ascenceur recupereAscenceurParID(int idAscenceur) {
		Query query = em.createQuery("SELECT e from Ascenceur e WHERE e.idAscenceur = :pid");
		query.setParameter("pid", idAscenceur);
		Ascenceur ascenceur = (Ascenceur) query.getSingleResult();
		return ascenceur;
	}

	@Override
	public Piece recuperePieceParID(int idPiece) {
		Query query = em.createQuery("SELECT e from Piece e WHERE e.idPiece = :pid");
		query.setParameter("pid", idPiece);
		Piece piece = (Piece) query.getSingleResult();
		return piece;
	}

	@Override
	public Voirie recupereVoirieParID(int idVoirie) {
		Query query = em.createQuery("SELECT e from Voirie e WHERE e.idVoirie = :pid");
		query.setParameter("pid", idVoirie);
		Voirie voirie = (Voirie) query.getSingleResult();
		return voirie;
	}

	@Override
	public List<Erp> rechercheErpParNom(String nomERP) {
		Query requete = em.createQuery("SELECT e FROM Erp e WHERE e.nomErp LIKE :pid"); 
		requete.setParameter("pid", "%" + nomERP + "%");
		List<Erp> liste = requete.getResultList(); 
		return liste;
	}
}
