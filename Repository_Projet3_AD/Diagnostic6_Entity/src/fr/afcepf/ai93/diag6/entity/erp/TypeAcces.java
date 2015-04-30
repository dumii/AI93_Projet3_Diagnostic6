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
@Table(name="type_acces")
public class TypeAcces implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_type_acces")
	private Integer idTypeAcces;
	
	@Column(name="Libelle_type_acces")
	private String libelleTypeAcces;
	
	@OneToMany(mappedBy="typeAcces")
	private List<Acces> listeAccesTypeAcces;

	
	public Integer getIdTypeAcces() {
		return idTypeAcces;
	}

	public void setIdTypeAcces(Integer idTypeAcces) {
		this.idTypeAcces = idTypeAcces;
	}

	public String getLibelleTypeAcces() {
		return libelleTypeAcces;
	}

	public void setLibelleTypeAcces(String libelleTypeAcces) {
		this.libelleTypeAcces = libelleTypeAcces;
	}

	public List<Acces> getListeAccesTypeAcces() {
		return listeAccesTypeAcces;
	}

	public void setListeAccesTypeAcces(List<Acces> listeAccesTypeAcces) {
		this.listeAccesTypeAcces = listeAccesTypeAcces;
	}

	public TypeAcces() {
		super();
	}

	public TypeAcces(Integer idTypeAcces, String libelleTypeAcces,
			List<Acces> listeAccesTypeAcces) {
		super();
		this.idTypeAcces = idTypeAcces;
		this.libelleTypeAcces = libelleTypeAcces;
		this.listeAccesTypeAcces = listeAccesTypeAcces;
	}
	
	
	
}
