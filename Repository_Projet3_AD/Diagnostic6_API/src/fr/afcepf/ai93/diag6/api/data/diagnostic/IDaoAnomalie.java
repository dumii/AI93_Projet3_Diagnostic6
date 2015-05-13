package fr.afcepf.ai93.diag6.api.data.diagnostic;

import java.util.List;

import fr.afcepf.ai93.diag6.entity.autres.Utilisateur;
import fr.afcepf.ai93.diag6.entity.diagnostic.Anomalie;

public interface IDaoAnomalie {
	
	public List<Anomalie> recupereToutAnomalie();

    public void ajouterAnomalie(Anomalie anomalie);

    public boolean modifierAnomalie(Anomalie anomalie, Utilisateur user);

    public String supprimerAnomalie(Anomalie anomalie);

    public Anomalie recupereAnomalie(int idAnomalie);

    public List<Anomalie> rechercheAnomaliesErp(String nomERP);

	public List<Anomalie> recupereAnomalieParDiagnostic(int idDiagnostic);
}
