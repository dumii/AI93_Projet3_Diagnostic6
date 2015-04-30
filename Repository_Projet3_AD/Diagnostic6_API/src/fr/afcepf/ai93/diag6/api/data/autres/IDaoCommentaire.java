package fr.afcepf.ai93.diag6.api.data.autres;

import fr.afcepf.ai93.diag6.entity.autres.Commentaire;

public interface IDaoCommentaire {

	public void ajouterCommentaire(Commentaire commentaire);
	
	public void supprimerCommentaire(Commentaire commentaire);
}
