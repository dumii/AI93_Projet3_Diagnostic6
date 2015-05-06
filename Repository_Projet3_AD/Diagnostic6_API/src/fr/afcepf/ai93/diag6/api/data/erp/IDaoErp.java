package fr.afcepf.ai93.diag6.api.data.erp;

import java.util.List;

import fr.afcepf.ai93.diag6.entity.erp.Erp;

public interface IDaoErp {
	
	public List<Erp> recupereToutErp(); 
	
	public Erp recupererErpParId(int idErp);
	
}
