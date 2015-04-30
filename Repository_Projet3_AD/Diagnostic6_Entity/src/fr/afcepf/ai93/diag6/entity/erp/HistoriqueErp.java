package fr.afcepf.ai93.diag6.entity.erp;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import fr.afcepf.ai93.diag6.entity.autres.Utilisateur;

@Entity
@Table(name="historique_erp")

public class HistoriqueErp implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_historique_erp")
	private Integer idHistoriqueErp;
	
	@Column(name="date_modification")
	private Date dateModification;
	
	@Column(name="type_de_modification")
	private String typeModification;
	
	@Column(name="ancienne_donnee")
	private String ancienneDonnee;
	
	@Column(name="nouvelle_donnee")
	private String nouvelleDonnee;
	
	@ManyToOne
	@JoinColumn(name="no_erp")
	private Erp erp;
	
	@ManyToOne
	@JoinColumn(name="id_utilisateur")
	private Utilisateur utilisateur;

	
	public Integer getIdHistoriqueErp() {
		return idHistoriqueErp;
	}

	public void setIdHistoriqueErp(Integer idHistoriqueErp) {
		this.idHistoriqueErp = idHistoriqueErp;
	}

	public Date getDateModification() {
		return dateModification;
	}

	public void setDateModification(Date dateModification) {
		this.dateModification = dateModification;
	}

	public String getTypeModification() {
		return typeModification;
	}

	public void setTypeModification(String typeModification) {
		this.typeModification = typeModification;
	}

	public String getAncienneDonnee() {
		return ancienneDonnee;
	}

	public void setAncienneDonnee(String ancienneDonnee) {
		this.ancienneDonnee = ancienneDonnee;
	}

	public String getNouvelleDonnee() {
		return nouvelleDonnee;
	}

	public void setNouvelleDonnee(String nouvelleDonnee) {
		this.nouvelleDonnee = nouvelleDonnee;
	}

	public Erp getErp() {
		return erp;
	}

	public void setErp(Erp erp) {
		this.erp = erp;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public HistoriqueErp() {
		super();
	}

	public HistoriqueErp(Integer idHistoriqueErp, Date dateModification,
			String typeModification, String ancienneDonnee,
			String nouvelleDonnee, Erp erp, Utilisateur utilisateur) {
		super();
		this.idHistoriqueErp = idHistoriqueErp;
		this.dateModification = dateModification;
		this.typeModification = typeModification;
		this.ancienneDonnee = ancienneDonnee;
		this.nouvelleDonnee = nouvelleDonnee;
		this.erp = erp;
		this.utilisateur = utilisateur;
	}
	
	
	
}
