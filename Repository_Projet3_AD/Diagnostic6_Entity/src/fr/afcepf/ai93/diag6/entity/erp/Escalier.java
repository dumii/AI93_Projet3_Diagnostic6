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
@Table(name="Escaliers")
public class Escalier implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_escalier")
	private Integer idEscalier;
	
	@Column(name="Denomination_escalier")
	private String denominationEscalier;
	
	@ManyToOne
	@JoinColumn(name="id_batiment")
	private Batiment batiment;
	
	@OneToMany(mappedBy="escalier")
	private List<Anomalie> listeAnomaliesEscalier;

	
	public Integer getIdEscalier() {
		return idEscalier;
	}

	public void setIdEscalier(Integer idEscalier) {
		this.idEscalier = idEscalier;
	}

	public String getDenominationEscalier() {
		return denominationEscalier;
	}

	public void setDenominationEscalier(String denominationEscalier) {
		this.denominationEscalier = denominationEscalier;
	}

	public Batiment getBatiment() {
		return batiment;
	}

	public void setBatiment(Batiment batiment) {
		this.batiment = batiment;
	}

	public List<Anomalie> getListeAnomaliesEscalier() {
		return listeAnomaliesEscalier;
	}

	public void setListeAnomaliesEscalier(List<Anomalie> listeAnomaliesEscalier) {
		this.listeAnomaliesEscalier = listeAnomaliesEscalier;
	}

	public Escalier() {
		super();
	}

	public Escalier(Integer idEscalier, String denominationEscalier,
			Batiment batiment, List<Anomalie> listeAnomaliesEscalier) {
		super();
		this.idEscalier = idEscalier;
		this.denominationEscalier = denominationEscalier;
		this.batiment = batiment;
		this.listeAnomaliesEscalier = listeAnomaliesEscalier;
	}
	
}
