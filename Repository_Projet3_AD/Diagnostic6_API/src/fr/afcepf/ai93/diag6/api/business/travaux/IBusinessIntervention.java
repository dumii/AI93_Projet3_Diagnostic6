package fr.afcepf.ai93.diag6.api.business.travaux;

import java.util.Date;
import java.util.List;

import fr.afcepf.ai93.diag6.entity.travaux.HistoriqueIntervention;
import fr.afcepf.ai93.diag6.entity.travaux.Intervention;
import fr.afcepf.ai93.diag6.entity.travaux.TypeIntervention;

public interface IBusinessIntervention {

	public List<HistoriqueIntervention> recupereToutHistoriqueIntervention();
	
	public List<Intervention> recupereToutesIntervention();

    public void ajouterIntervention(Intervention intervention);

    public boolean modifierIntervention(Intervention intervention);

    public Intervention recupereIntervention(int idIntervention);

}
