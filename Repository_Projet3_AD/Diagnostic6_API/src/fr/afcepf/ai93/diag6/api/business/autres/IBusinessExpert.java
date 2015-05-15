package fr.afcepf.ai93.diag6.api.business.autres;

import java.util.List;

import fr.afcepf.ai93.diag6.entity.autres.Expert;

public interface IBusinessExpert {
	
	public List<Expert> recupereToutExpert();
	
	public Expert recupererExpertParId(int idExpert);

    public String ajouterExpert(Expert expert);

    public String supprimerExpert(Expert expert);
}
