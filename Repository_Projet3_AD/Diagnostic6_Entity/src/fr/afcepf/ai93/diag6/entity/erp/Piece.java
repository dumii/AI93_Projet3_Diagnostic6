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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.jboss.resteasy.spi.touri.MappedBy;

import fr.afcepf.ai93.diag6.entity.diagnostic.Anomalie;

@Entity
@Table(name="piece")
public class Piece implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_PIECE")
	private Integer idPiece;
	
	@ManyToOne
	@JoinColumn(name="ID_ETAGE")
	private Etage etage;
	
	@ManyToOne
	@JoinColumn(name="ID_FONCTION_PIECE")
	private FonctionPiece fonctionPiece;
	
	@Column(name="DENOMINATION_PIECE")
	private String denominationPiece;
	
	@OneToMany(mappedBy="piece")
	private List<Anomalie> listeAnomaliesPiece;

	
	
	public Integer getIdPiece() {
		return idPiece;
	}

	public void setIdPiece(Integer idPiece) {
		this.idPiece = idPiece;
	}

	public Etage getEtage() {
		return etage;
	}

	public void setEtage(Etage etage) {
		this.etage = etage;
	}

	public FonctionPiece getFonctionPiece() {
		return fonctionPiece;
	}

	public void setFonctionPiece(FonctionPiece fonctionPiece) {
		this.fonctionPiece = fonctionPiece;
	}

	public String getDenominationPiece() {
		return denominationPiece;
	}

	public void setDenominationPiece(String denominationPiece) {
		this.denominationPiece = denominationPiece;
	}

	
	public List<Anomalie> getListeAnomaliesPiece() {
		return listeAnomaliesPiece;
	}

	public void setListeAnomaliesPiece(List<Anomalie> listeAnomaliesPiece) {
		this.listeAnomaliesPiece = listeAnomaliesPiece;
	}

	public Piece() {
		super();
	}

	public Piece(Integer idPiece, Etage etage, FonctionPiece fonctionPiece,
			String denominationPiece, List<Anomalie> listeAnomaliesPiece) {
		super();
		this.idPiece = idPiece;
		this.etage = etage;
		this.fonctionPiece = fonctionPiece;
		this.denominationPiece = denominationPiece;
		this.listeAnomaliesPiece = listeAnomaliesPiece;
	}

	
	
	
	
}
