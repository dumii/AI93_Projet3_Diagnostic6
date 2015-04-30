package fr.afcepf.ai93.diag6.entity.erp;

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
@Table(name="type_voirie")
public class TypeVoirie implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_type_voirie")
	private Integer idTypeVoirie;
	
	@Column(name="libelle_type_voirie")
	private String libelleTypeVoirie;
	
	@OneToMany(mappedBy="typeVoirie")
	private List<Voirie> listeVoiriesTypeVoirie;
	
	public Integer getIdTypeVoirie() {
		return idTypeVoirie;
	}

	public void setIdTypeVoirie(Integer idTypeVoirie) {
		this.idTypeVoirie = idTypeVoirie;
	}

	public String getLibelleTypeVoirie() {
		return libelleTypeVoirie;
	}

	public void setLibelleTypeVoirie(String libelleTypeVoirie) {
		this.libelleTypeVoirie = libelleTypeVoirie;
	}

	public List<Voirie> getListeVoiriesTypeVoirie() {
		return listeVoiriesTypeVoirie;
	}

	public void setListeVoiriesTypeVoirie(List<Voirie> listeVoiriesTypeVoirie) {
		this.listeVoiriesTypeVoirie = listeVoiriesTypeVoirie;
	}

	public TypeVoirie() {
		super();
	}

	public TypeVoirie(Integer idTypeVoirie, String libelleTypeVoirie,
			List<Voirie> listeVoiriesTypeVoirie) {
		super();
		this.idTypeVoirie = idTypeVoirie;
		this.libelleTypeVoirie = libelleTypeVoirie;
		this.listeVoiriesTypeVoirie = listeVoiriesTypeVoirie;
	}
	
	
	
}
