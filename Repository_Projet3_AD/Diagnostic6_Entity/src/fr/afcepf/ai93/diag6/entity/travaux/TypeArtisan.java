package fr.afcepf.ai93.diag6.entity.travaux;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import fr.afcepf.ai93.diag6.entity.autres.Artisan;

@Entity
@Table(name="type_artisan")
public class TypeArtisan implements Serializable{

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_type_artisan")
	private Integer idTypeArtisan;
	
	@Column(name="libelle_type_artisan")
	private String libelleTypeArtisan;
	
	@OneToMany(mappedBy="typeArtisan")
	private List<Artisan> listeTypeArtisan;
	
	public Integer getIdTypeArtisan() {
		return idTypeArtisan;
	}

	public void setIdTypeArtisan(Integer idTypeArtisan) {
		this.idTypeArtisan = idTypeArtisan;
	}

	public String getLibelleTypeArtisan() {
		return libelleTypeArtisan;
	}

	public void setLibelleTypeArtisan(String libelleTypeArtisan) {
		this.libelleTypeArtisan = libelleTypeArtisan;
	}

	public List<Artisan> getListeTypeArtisan() {
		return listeTypeArtisan;
	}

	public void setListeTypeArtisan(List<Artisan> listeTypeArtisan) {
		this.listeTypeArtisan = listeTypeArtisan;
	}

	public TypeArtisan() {
		super();
	}

	public TypeArtisan(Integer idTypeArtisan, String libelleTypeArtisan,
			List<Artisan> listeTypeArtisan) {
		super();
		this.idTypeArtisan = idTypeArtisan;
		this.libelleTypeArtisan = libelleTypeArtisan;
		this.listeTypeArtisan = listeTypeArtisan;
	}
	
	
	
}
