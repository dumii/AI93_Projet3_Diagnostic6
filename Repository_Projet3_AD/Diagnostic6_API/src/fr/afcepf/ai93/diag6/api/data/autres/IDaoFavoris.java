package fr.afcepf.ai93.diag6.api.data.autres;

import java.util.List;

import fr.afcepf.ai93.diag6.entity.autres.Favoris;

public interface IDaoFavoris {

	public List<Favoris> recupereErpFavoris();

    public List<Favoris> recupereDiagFavoris();

    public List<Favoris> recupereInterFavoris();
    
    public void ajouterFavoris(Favoris favoris);

    public boolean supprimerFavoris(Favoris favoris);
    
    public List<Favoris> recupereToutFavoris();
}
