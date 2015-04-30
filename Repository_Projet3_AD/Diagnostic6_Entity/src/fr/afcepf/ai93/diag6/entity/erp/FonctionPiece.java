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
@Table(name="fonction_piece")
public class FonctionPiece implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_FONCTION_PIECE")
	private Integer idFonctionPiece;
	
	@Column(name="LIBELLE_FONCTION_PIECE")
	private String libelleFonctionPiece;
	
	@OneToMany(mappedBy="fonctionPiece")
	private List<Piece> listePiecesFonctionPiece;
	
	
	public Integer getIdFonctionPiece() {
		return idFonctionPiece;
	}


	public void setIdFonctionPiece(Integer idFonctionPiece) {
		this.idFonctionPiece = idFonctionPiece;
	}


	public String getLibelleFonctionPiece() {
		return libelleFonctionPiece;
	}


	public void setLibelleFonctionPiece(String libelleFonctionPiece) {
		this.libelleFonctionPiece = libelleFonctionPiece;
	}


	public List<Piece> getListePiece() {
		return listePiecesFonctionPiece;
	}


	public void setListePiece(List<Piece> listePiece) {
		this.listePiecesFonctionPiece = listePiece;
	}


	public FonctionPiece() {
		super();
	}


	public FonctionPiece(Integer idFonctionPiece, String libelleFonctionPiece,
			List<Piece> listePiecesFonctionPiece) {
		super();
		this.idFonctionPiece = idFonctionPiece;
		this.libelleFonctionPiece = libelleFonctionPiece;
		this.listePiecesFonctionPiece = listePiecesFonctionPiece;
	}
	
	
}
