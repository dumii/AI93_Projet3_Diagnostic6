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

@Entity
@Table(name="type_intervention")
public class TypeIntervention implements Serializable {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_type_intervention")
	private Integer idTypeIntervention;
	
	@Column(name="type_intervention")
	private String typeIntervention;
	
	@OneToMany(mappedBy="typeIntervention")
	private List<Intervention> listeInterventionTypeIntervention;

	
	public Integer getIdTypeIntervention() {
		return idTypeIntervention;
	}

	public void setIdTypeIntervention(Integer idTypeIntervention) {
		this.idTypeIntervention = idTypeIntervention;
	}

	public String getTypeIntervention() {
		return typeIntervention;
	}

	public void setTypeIntervention(String typeIntervention) {
		this.typeIntervention = typeIntervention;
	}

	public List<Intervention> getListeInterventionTypeIntervention() {
		return listeInterventionTypeIntervention;
	}

	public void setListeInterventionTypeIntervention(
			List<Intervention> listeInterventionTypeIntervention) {
		this.listeInterventionTypeIntervention = listeInterventionTypeIntervention;
	}

	public TypeIntervention() {
		super();
	}

	public TypeIntervention(Integer idTypeIntervention,
			String typeIntervention,
			List<Intervention> listeInterventionTypeIntervention) {
		super();
		this.idTypeIntervention = idTypeIntervention;
		this.typeIntervention = typeIntervention;
		this.listeInterventionTypeIntervention = listeInterventionTypeIntervention;
	}
	
	
}
