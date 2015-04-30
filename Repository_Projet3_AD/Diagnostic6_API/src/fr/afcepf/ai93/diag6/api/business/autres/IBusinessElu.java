package fr.afcepf.ai93.diag6.api.business.autres;

import fr.afcepf.ai93.diag6.entity.autres.Commentaire;
import fr.afcepf.ai93.diag6.entity.autres.Publication;

public interface IBusinessElu {

	 	public void ajouterPublication(Publication publication);

	    public void modifierPublication(Publication publication);

	    public void supprimerCommentaire(Commentaire commentaire);
	    
	    public void ajouterCommentaire(Commentaire commentaire);
		
}
