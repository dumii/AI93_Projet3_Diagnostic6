package fr.afcepf.ai93.diag6.api.data.autres;

import java.util.List;

import fr.afcepf.ai93.diag6.entity.autres.ProfilUtilisateur;

public interface IDaoProfilUtilisateur {

	public List<ProfilUtilisateur> recupereToutProfil();

    public boolean modifierNomProfil(ProfilUtilisateur nomProfil);
	
}
