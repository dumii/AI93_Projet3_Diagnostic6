package fr.afcepf.ai93.diag6.api.business.erp;

import java.util.List;

import fr.afcepf.ai93.diag6.entity.erp.Acces;
import fr.afcepf.ai93.diag6.entity.erp.Ascenceur;
import fr.afcepf.ai93.diag6.entity.erp.Batiment;
import fr.afcepf.ai93.diag6.entity.erp.CategorieErp;
import fr.afcepf.ai93.diag6.entity.erp.Erp;
import fr.afcepf.ai93.diag6.entity.erp.Escalier;
import fr.afcepf.ai93.diag6.entity.erp.Etage;
import fr.afcepf.ai93.diag6.entity.erp.Piece;
import fr.afcepf.ai93.diag6.entity.erp.TypeErp;
import fr.afcepf.ai93.diag6.entity.erp.Voirie;

public interface IBusinessErp {

	//ERP
	
	public List<Erp> recupereToutErp();
	
	public List<Erp> rechercheErpParNom(String nomERP);
	
	public Erp recupererErpParId(int idErp);

	
	//Elements de l'ERP
	
	public List<Batiment> recupererBatParErp(int idErp);

	public List<Etage> recupererEtagesParBat(int idBatiment);

	public List<Piece> recupererPiecesParEtage(int idEtage);

	public List<Acces> recupererAccesParBat(int idBatiment);

	public List<Escalier> recupererEscaliersParBat(int idBatiment);

	public List<Ascenceur> recupererAscenceursParBat(int idBatiment);

	public List<Voirie> recupererVoirieParErp(int idErp);

	
	//Type d'ERP
	
	public List<TypeErp> recupererListeTypeERP();
	
	public List<TypeErp> recupererToutTypeErp();
	
	public List<CategorieErp> recupererToutCategorieErp();

	public List<Erp> recupereErpParNom(String stringCherche);
	
	
	//Structure de l'ERP
	public Acces recupereAccesParID(int idAcces);
	
	public Escalier recupereEscalierParID(int idEscalier);
	
	public Ascenceur recupereAscenceurParID(int idAscenceur);
	
	public Piece recuperePieceParID(int idPiece);
	
	public Voirie recupereVoirieParID(int idVoirie);

	public List<Erp> rechercheErpParInterventionEnCours();

}
