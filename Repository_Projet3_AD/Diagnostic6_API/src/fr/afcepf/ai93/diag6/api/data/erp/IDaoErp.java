package fr.afcepf.ai93.diag6.api.data.erp;

import java.util.List;

import fr.afcepf.ai93.diag6.entity.erp.Batiment;
import fr.afcepf.ai93.diag6.entity.erp.Erp;
import fr.afcepf.ai93.diag6.entity.erp.Etage;

public interface IDaoErp {
	public List<Erp> recupereToutErp();


	public List<Batiment> recupererBatParErp(int idErp);


	public List<Etage> recupererEtagesParBat(int idBatiment); 
	
}
