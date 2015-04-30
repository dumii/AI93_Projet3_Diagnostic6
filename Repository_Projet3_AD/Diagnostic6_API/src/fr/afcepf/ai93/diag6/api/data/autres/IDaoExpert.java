package fr.afcepf.ai93.diag6.api.data.autres;

import java.util.List;

import fr.afcepf.ai93.diag6.entity.autres.Expert;

public interface IDaoExpert {

	public List<Expert> recupereToutExpert();

    public void ajouterExpert(Expert expert );

    public boolean supprimerExpert(Expert expert);
}
