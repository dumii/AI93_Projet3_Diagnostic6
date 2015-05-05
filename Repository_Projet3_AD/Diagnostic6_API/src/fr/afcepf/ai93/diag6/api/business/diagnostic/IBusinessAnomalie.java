package fr.afcepf.ai93.diag6.api.business.diagnostic;

import java.util.List;

import fr.afcepf.ai93.diag6.entity.diagnostic.Anomalie;
import fr.afcepf.ai93.diag6.entity.diagnostic.HistoriqueAnomalie;
import fr.afcepf.ai93.diag6.entity.diagnostic.Indicateur;

public interface IBusinessAnomalie {

	
	public List<Anomalie> recupereToutAnomalie();

    public String ajouterAnomalie(Anomalie anomalie);

    public void modifierAnomalie(Anomalie anomalie);

    public void historiserAnomalie(Anomalie anomalie);

    public boolean supprimerAnomalie(Anomalie anomalie);

    public List<HistoriqueAnomalie> recupereToutHistoriqueAnomalie();

    public List<Indicateur> recupereIndicateur();

    public Anomalie recupereAnomalie(int idAnomalie);

    public List<Anomalie> rechercheAnomalies(String nomAnomalie);

    public List<Anomalie> rechercheAnomaliesErp(String nomERP);

	public List<Anomalie> recupereAnomalieParDiagnostic(int idDiagnostic);
    
}
