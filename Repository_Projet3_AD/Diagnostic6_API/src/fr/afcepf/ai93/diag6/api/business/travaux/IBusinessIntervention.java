package fr.afcepf.ai93.diag6.api.business.travaux;

import java.util.Date;
import java.util.List;

import fr.afcepf.ai93.diag6.entity.autres.Utilisateur;
import fr.afcepf.ai93.diag6.entity.travaux.EtatAvancementTravaux;
import fr.afcepf.ai93.diag6.entity.travaux.HistoriqueIntervention;
import fr.afcepf.ai93.diag6.entity.travaux.Intervention;
import fr.afcepf.ai93.diag6.entity.travaux.TypeIntervention;

public interface IBusinessIntervention {

	//Intervention	
	public List<HistoriqueIntervention> recupereToutHistoriqueIntervention();
	
	public List<Intervention> recupereToutesIntervention();

    public String ajouterIntervention(Intervention intervention);

    public String modifierIntervention(Intervention intervention, Utilisateur user);

    public Intervention recupereIntervention(int idIntervention);
    
    //Type d'intervention
    public List<TypeIntervention> recupererTousTypesIntervention();
    
    //Etat d'avancement des travaux	
	public List<EtatAvancementTravaux> recupererTousEtats();

}
