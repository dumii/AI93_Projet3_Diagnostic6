package fr.afcepf.ai93.diag6.api.data.diagnostic;

import java.util.List;

import fr.afcepf.ai93.diag6.entity.diagnostic.Anomalie;

public interface IDaoAnomalie {
	
	public List<Anomalie> recupereToutAnomalie();

    public void ajouterAnomalie(Anomalie anomalie);

    public void modifierAnomalie(Anomalie anomalie);

    public void historiserAnomalie(Anomalie anomalie);

    public boolean supprimerAnomalie(Anomalie anomalie);

    public Anomalie recupereAnomalie(int idAnomalie);

    public List<Anomalie> rechercheAnomalies(String nomAnomalie);

    public List<Anomalie> rechercheAnomaliesErp(String nomERP);
}
