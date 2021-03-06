package fr.afcepf.ai93.diag6.api.business.diagnostic;

import java.util.List;

import fr.afcepf.ai93.diag6.entity.autres.Utilisateur;
import fr.afcepf.ai93.diag6.entity.diagnostic.Anomalie;
import fr.afcepf.ai93.diag6.entity.diagnostic.HistoriqueAnomalie;
import fr.afcepf.ai93.diag6.entity.diagnostic.Indicateur;

public interface IBusinessAnomalie {

	//Anomalies
	public List<Anomalie> recupereToutAnomalie();

    public String ajouterAnomalie(Anomalie anomalie);

    public String modifierAnomalie(Anomalie anomalie, Utilisateur user);

    public String supprimerAnomalie(Anomalie anomalie, Utilisateur user);
    
    public Anomalie recupereAnomalie(int idAnomalie);

    public List<Anomalie> rechercheAnomaliesErp(String nomERP);

	public List<Anomalie> recupereAnomalieParDiagnostic(int idDiagnostic);

    //Historique d'anomalie
    public List<HistoriqueAnomalie> recupereToutHistoriqueAnomalie();

    //Indicateur d'anomalie
    public List<Indicateur> recupereIndicateur();

	public List<HistoriqueAnomalie> recupereHistoriqueAnomalieParDiag(int idDiagEnCours);

    
    public Indicateur recupereIndicateurParID(int idIndicateur);
  
}
