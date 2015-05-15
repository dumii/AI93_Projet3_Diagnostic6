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
@Table(name="ETAGE")
public class Etage implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_etage")
	private Integer idEtage;
	
	@Column(name="num_etage")
	private Integer numeroEtage;
	
	@Column(name="nom_etage")
	private String nomEtage;
	
	@ManyToOne
	@JoinColumn(name="id_batiment")
	private Batiment batiment;
	
	@OneToMany(mappedBy="etage")
	private List<Piece> listePiecesEtage;

	public Integer getIdEtage() {
		return idEtage;
	}

	public void setIdEtage(Integer idEtage) {
		this.idEtage = idEtage;
	}

	public Integer getNumeroEtage() {
		return numeroEtage;
	}

	public void setNumeroEtage(Integer numeroEtage) {
		this.numeroEtage = numeroEtage;
	}

	public String getNomEtage() {
		return nomEtage;
	}

	public void setNomEtage(String nomEtage) {
		this.nomEtage = nomEtage;
	}

	public Batiment getBatiment() {
		return batiment;
	}

	public void setBatiment(Batiment batiment) {
		this.batiment = batiment;
	}

	public List<Piece> getListePieces() {
		return listePiecesEtage;
	}

	public void setListePieces(List<Piece> listePieces) {
		this.listePiecesEtage = listePieces;
	}

	public Etage() {
		super();
	}

	public Etage(Integer idEtage, Integer numeroEtage, String nomEtage,
			Batiment batiment, List<Piece> listePiecesEtage) {
		super();
		this.idEtage = idEtage;
		this.numeroEtage = numeroEtage;
		this.nomEtage = nomEtage;
		this.batiment = batiment;
		this.listePiecesEtage = listePiecesEtage;
	}
}
