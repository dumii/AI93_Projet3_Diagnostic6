package fr.afcepf.ai93.diag6.entity.autres;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import fr.afcepf.ai93.diag6.entity.travaux.TypeIntervention;

@Entity
@Table(name="type_artisan")
public class TypeArtisan implements Serializable{

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_type_artisan")
	private Integer idTypeArtisan;
	
	@ManyToOne
	@JoinColumn(name="id_artisan")
	private Artisan artisan;
	
	@ManyToOne
	@JoinColumn(name="id_type_intervention")
	private TypeIntervention typeIntervention;
	
	public Integer getIdTypeArtisan() {
		return idTypeArtisan;
	}

	public void setIdTypeArtisan(Integer idTypeArtisan) {
		this.idTypeArtisan = idTypeArtisan;
	}

	public TypeArtisan() {
	}

	public TypeArtisan(Integer idTypeArtisan, Artisan artisan, TypeIntervention type) {
		super();
		this.idTypeArtisan = idTypeArtisan;
		this.artisan = artisan;
		this.typeIntervention = type;
	}

	public Artisan getArtisan() {
		return artisan;
	}

	public void setArtisan(Artisan artisan) {
		this.artisan = artisan;
	}

	public TypeIntervention getTypeIntervention() {
		return typeIntervention;
	}

	public void setTypeIntervention(TypeIntervention typeIntervention) {
		this.typeIntervention = typeIntervention;
	}
	
	
	
}
