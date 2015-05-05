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
@Table(name="Ascenceurs")
public class Ascenceur implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_ascenceur")
	private Integer idAscenceur;
	
	@Column(name="denomination_ascenceur")
	private String denominationAscenceur;
	
	@OneToMany(mappedBy="ascenceur")
	private List<Anomalie> listeAnomaliesAscenceur;
	
	@ManyToOne
	@JoinColumn(name="id_batiment")
	private Batiment batiment;

	public Integer getIdAscenceur() {
		return idAscenceur;
	}

	public void setIdAscenceur(Integer idAscenceur) {
		this.idAscenceur = idAscenceur;
	}

	public String getDenominationAscenceur() {
		return denominationAscenceur;
	}

	public void setDenominationAscenceur(String denominationAscenceur) {
		this.denominationAscenceur = denominationAscenceur;
	}

	public List<Anomalie> getListeAnomaliesAscenceur() {
		return listeAnomaliesAscenceur;
	}

	public void setListeAnomaliesAscenceur(List<Anomalie> listeAnomaliesAscenceur) {
		this.listeAnomaliesAscenceur = listeAnomaliesAscenceur;
	}

	public Batiment getBatiment() {
		return batiment;
	}

	public void setBatiment(Batiment batiment) {
		this.batiment = batiment;
	}

	public Ascenceur(){
		super();
	}
	public Ascenceur(Integer idAscenceur, String denominationAscenceur,
			List<Anomalie> listeAnomaliesAscenceur, Batiment batiment) {
		super();
		this.idAscenceur = idAscenceur;
		this.denominationAscenceur = denominationAscenceur;
		this.listeAnomaliesAscenceur = listeAnomaliesAscenceur;
		this.batiment = batiment;
	}
	
	
}
