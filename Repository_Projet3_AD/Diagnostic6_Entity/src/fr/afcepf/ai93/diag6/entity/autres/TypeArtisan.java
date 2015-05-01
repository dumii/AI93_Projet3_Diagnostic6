package fr.afcepf.ai93.diag6.entity.autres;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
	private List<Artisan> listeArtisan;
	
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
		return listeArtisan;
	}

	public void setListeTypeArtisan(List<Artisan> listeArtisan) {
		this.listeArtisan = listeArtisan;
	}

	public TypeArtisan() {
		super();
	}

	public TypeArtisan(Integer idTypeArtisan, String libelleTypeArtisan) {
		super();
		this.idTypeArtisan = idTypeArtisan;
		this.libelleTypeArtisan = libelleTypeArtisan;
	}
	
	
	
}
