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

@Entity
@Table(name="Batiment")
public class Batiment implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_batiment")
	private Integer idBatiment;
	
	@Column(name="intitule_batiment")
	private String intituleBatiment;
	
	@Column(name="num_batiment")
	private Integer numBatiment;
	
	@OneToMany(mappedBy="batiment")
	private List<Acces> listeAccesBatiment;
	
	@OneToMany(mappedBy="batiment")
	private List<Escalier> listeEscaliersBatiment;
	
	@OneToMany(mappedBy="batiment")
	private List<Ascenceur> listeAscenceursBatiment;
	
	@OneToMany(mappedBy="batiment")
	private List<Etage> listeEtagesBatiment;
	
	@ManyToOne
	@JoinColumn(name="no_erp")
	private Erp erp;

	
	public Integer getIdBatiment() {
		return idBatiment;
	}

	public void setIdBatiment(Integer idBatiment) {
		this.idBatiment = idBatiment;
	}

	public String getIntituleBatiment() {
		return intituleBatiment;
	}

	public void setIntituleBatiment(String intituleBatiment) {
		this.intituleBatiment = intituleBatiment;
	}

	public Integer getNumBatiment() {
		return numBatiment;
	}

	public void setNumBatiment(Integer numBatiment) {
		this.numBatiment = numBatiment;
	}

	public List<Acces> getListeAccesBatiment() {
		return listeAccesBatiment;
	}

	public void setListeAccesBatiment(List<Acces> listeAccesBatiment) {
		this.listeAccesBatiment = listeAccesBatiment;
	}

	public List<Escalier> getListeEscaliersBatiment() {
		return listeEscaliersBatiment;
	}

	public void setListeEscaliersBatiment(List<Escalier> listeEscaliersBatiment) {
		this.listeEscaliersBatiment = listeEscaliersBatiment;
	}

	public List<Ascenceur> getListeAscenceursBatiment() {
		return listeAscenceursBatiment;
	}

	public void setListeAscenceursBatiment(List<Ascenceur> listeAscenceursBatiment) {
		this.listeAscenceursBatiment = listeAscenceursBatiment;
	}

	public List<Etage> getListeEtagesBatiment() {
		return listeEtagesBatiment;
	}

	public void setListeEtagesBatiment(List<Etage> listeEtagesBatiment) {
		this.listeEtagesBatiment = listeEtagesBatiment;
	}

	public Erp getErp() {
		return erp;
	}

	public void setErp(Erp erp) {
		this.erp = erp;
	}

	public Batiment() {
		super();
	}

	public Batiment(Integer idBatiment, String intituleBatiment,
			Integer numBatiment, List<Acces> listeAccesBatiment,
			List<Escalier> listeEscaliersBatiment,
			List<Ascenceur> listeAscenceursBatiment,
			List<Etage> listeEtagesBatiment, Erp erp) {
		super();
		this.idBatiment = idBatiment;
		this.intituleBatiment = intituleBatiment;
		this.numBatiment = numBatiment;
		this.listeAccesBatiment = listeAccesBatiment;
		this.listeEscaliersBatiment = listeEscaliersBatiment;
		this.listeAscenceursBatiment = listeAscenceursBatiment;
		this.listeEtagesBatiment = listeEtagesBatiment;
		this.erp = erp;
	}
	
	
	
}
