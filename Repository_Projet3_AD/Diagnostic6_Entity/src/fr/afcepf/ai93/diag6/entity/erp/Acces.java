package fr.afcepf.ai93.diag6.entity.erp;

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

import fr.afcepf.ai93.diag6.entity.diagnostic.Anomalie;

@Entity
@Table(name="Acces")
public class Acces implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_acces")
	private Integer idAcces;
	
	@ManyToOne
	@JoinColumn(name="id_type_acces")
	private TypeAcces typeAcces;
	
	@OneToMany(mappedBy="acces")
	private List<Anomalie> listeAnomaliesAcces;

	@ManyToOne
	@JoinColumn(name="id_batiment")
	private Batiment batiment;
	
	public Integer getIdAcces() {
		return idAcces;
	}

	public void setIdAcces(Integer idAcces) {
		this.idAcces = idAcces;
	}

	public TypeAcces getTypeAcces() {
		return typeAcces;
	}

	public void setTypeAcces(TypeAcces typeAcces) {
		this.typeAcces = typeAcces;
	}

	public List<Anomalie> getListeAnomaliesAcces() {
		return listeAnomaliesAcces;
	}

	public void setListeAnomaliesAcces(List<Anomalie> listeAnomaliesAcces) {
		this.listeAnomaliesAcces = listeAnomaliesAcces;
	}

	public Acces() {
		super();
	}


	public Acces(Integer idAcces, TypeAcces typeAcces,
			List<Anomalie> listeAnomaliesAcces, Batiment batiment) {
		super();
		this.idAcces = idAcces;
		this.typeAcces = typeAcces;
		this.listeAnomaliesAcces = listeAnomaliesAcces;
		this.batiment = batiment;
	}

	public Batiment getBatiment() {
		return batiment;
	}

	public void setBatiment(Batiment batiment) {
		this.batiment = batiment;
	}

}
