package fr.afcepf.ai93.diag6.entity.autres;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="publication")
public class Publication implements Serializable{

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_publication")
	private Integer idPublication;
	
	@Column(name="libelle_publication")
	private String libellePublication;
	
	@Column(name="photo")
	private String photo;

	public Integer getIdPublication() {
		return idPublication;
	}

	public void setIdPublication(Integer idPublication) {
		this.idPublication = idPublication;
	}

	public String getLibellePublication() {
		return libellePublication;
	}

	public void setLibellePublication(String libellePublication) {
		this.libellePublication = libellePublication;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Publication() {
		super();
	}

	public Publication(Integer idPublication, String libellePublication,
			String photo) {
		super();
		this.idPublication = idPublication;
		this.libellePublication = libellePublication;
		this.photo = photo;
	}
	
	
}
