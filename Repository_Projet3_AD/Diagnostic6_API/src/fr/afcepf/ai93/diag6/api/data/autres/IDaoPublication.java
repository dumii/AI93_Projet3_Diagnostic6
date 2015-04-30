package fr.afcepf.ai93.diag6.api.data.autres;

import fr.afcepf.ai93.diag6.entity.autres.Publication;

public interface IDaoPublication {

	public void ajouterPublication(Publication publication);

    public void modifierPublication(Publication publication);
}
