package fr.afcepf.ai93.diag6.entity.autres;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="conmmetaire")
public class Commentaire implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_commentaire")
	private Integer idCommentaire;
	
	@Column(name="libelle_commentaire")
	private String libelleCommentaire;
	
	@Column(name="nom_commentateur")
	private String nomCommentateur;
	
	@Column(name="date_commentaire")
	private Date dateCommentaire;

	public Integer getIdCommentaire() {
		return idCommentaire;
	}

	public void setIdCommentaire(Integer idCommentaire) {
		this.idCommentaire = idCommentaire;
	}

	public String getLibelleCommentaire() {
		return libelleCommentaire;
	}

	public void setLibelleCommentaire(String libelleCommentaire) {
		this.libelleCommentaire = libelleCommentaire;
	}

	public String getNomCommentateur() {
		return nomCommentateur;
	}

	public void setNomCommentateur(String nomCommentateur) {
		this.nomCommentateur = nomCommentateur;
	}

	public Date getDateCommentaire() {
		return dateCommentaire;
	}

	public void setDateCommentaire(Date dateCommentaire) {
		this.dateCommentaire = dateCommentaire;
	}

	public Commentaire() {
		super();
	}

	public Commentaire(Integer idCommentaire, String libelleCommentaire,
			String nomCommentateur, Date dateCommentaire) {
		super();
		this.idCommentaire = idCommentaire;
		this.libelleCommentaire = libelleCommentaire;
		this.nomCommentateur = nomCommentateur;
		this.dateCommentaire = dateCommentaire;
	} 
	
	

}
