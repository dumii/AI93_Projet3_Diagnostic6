package fr.afcepf.ai93.diag6.api.business.travaux;

import java.util.Date;
import java.util.List;

import fr.afcepf.ai93.diag6.entity.autres.Utilisateur;
import fr.afcepf.ai93.diag6.entity.diagnostic.Anomalie;
import fr.afcepf.ai93.diag6.entity.travaux.EtatAvancementTravaux;
import fr.afcepf.ai93.diag6.entity.travaux.HistoriqueIntervention;
import fr.afcepf.ai93.diag6.entity.travaux.Intervention;
import fr.afcepf.ai93.diag6.entity.travaux.TypeIntervention;

public interface IBusinessIntervention {

	//Intervention	
	public List<HistoriqueIntervention> recupereToutHistoriqueIntervention();
	
	public List<Intervention> recupereToutesIntervention();
	
	public List<Intervention> recupereInterventionParType(TypeIntervention type);

    public String ajouterIntervention(Intervention intervention, Anomalie anomalie);

    public String modifierIntervention(Intervention intervention, Utilisateur user);

    public Intervention recupereIntervention(int idIntervention);
    
    public List<Intervention> rechercherInterventionSurAnomalie(int idAnomalie);
    
    //Type d'intervention
    public List<TypeIntervention> recupererTousTypesIntervention();
    
    public TypeIntervention recupererTypeParIntervention(Intervention intervention);
    
    public TypeIntervention recupererTypeParID(int idType);
    
    //Etat d'avancement des travaux	
	public List<EtatAvancementTravaux> recupererTousEtats();
	
	public List<EtatAvancementTravaux> recupererEtatDisponibles(Intervention intervention);
	
	public EtatAvancementTravaux recupererEtatParIntervention (Intervention intervention);

	int nombreInterventionDiag(int idDiag);

	public boolean voirSiInterventionEnCoursParErp(int idErp);
}
