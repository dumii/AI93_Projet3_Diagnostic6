package fr.afcepf.ai93.diag6.api.data.erp;

import java.util.List;

import fr.afcepf.ai93.diag6.entity.erp.Acces;
import fr.afcepf.ai93.diag6.entity.erp.Ascenceur;
import fr.afcepf.ai93.diag6.entity.erp.Batiment;
import fr.afcepf.ai93.diag6.entity.erp.Erp;
import fr.afcepf.ai93.diag6.entity.erp.Escalier;
import fr.afcepf.ai93.diag6.entity.erp.Etage;
import fr.afcepf.ai93.diag6.entity.erp.Piece;

public interface IDaoErp {
	public List<Erp> recupereToutErp();


	public List<Batiment> recupererBatParErp(int idErp);

	public List<Etage> recupererEtagesParBat(int idBatiment);


	public List<Piece> recupererPiecesParEtage(int idEtage);


	public List<Acces> recupererAccesParBat(int idBatiment);


	public List<Escalier> recupererEscaliersParBat(int idBatiment);


	public List<Ascenceur> recupererAscenceursParBat(int idBatiment);
	
}
