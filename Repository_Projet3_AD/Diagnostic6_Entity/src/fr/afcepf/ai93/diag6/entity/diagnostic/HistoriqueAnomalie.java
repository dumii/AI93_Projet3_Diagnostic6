package fr.afcepf.ai93.diag6.entity.diagnostic;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import fr.afcepf.ai93.diag6.entity.autres.Utilisateur;

@Entity
@Table(name="historique_anomalie")
public class HistoriqueAnomalie implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_historique_anomalie")
	private Integer idHistoriqueAnomalie;
	
	@Column(name="date_modification")
	private Date dateModification;
	
	@Column(name="type_de_modification")
	private String typeModification;
	
	@Column(name="ancienne_donnee")
	private String ancienneDonnee;
	
	@Column(name="nouvelle_donnee")
	private String nouvelleDonnee;
	
	@ManyToOne
	@JoinColumn(name="id_anomalie")
	private Anomalie anomalie;
	
	@ManyToOne
	@JoinColumn(name="id_utilisateur")
	private Utilisateur utilisateur;

	public Integer getIdHistoriqueAnomalie() {
		return idHistoriqueAnomalie;
	}

	public void setIdHistoriqueAnomalie(Integer idHistoriqueAnomalie) {
		this.idHistoriqueAnomalie = idHistoriqueAnomalie;
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

	public Anomalie getAnomalie() {
		return anomalie;
	}

	public void setAnomalie(Anomalie anomalie) {
		this.anomalie = anomalie;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public HistoriqueAnomalie() {
		super();
	}

	public HistoriqueAnomalie(Integer idHistoriqueAnomalie,
			Date dateModification, String typeModification,
			String ancienneDonnee, String nouvelleDonnee, Anomalie anomalie,
			Utilisateur utilisateur) {
		super();
		this.idHistoriqueAnomalie = idHistoriqueAnomalie;
		this.dateModification = dateModification;
		this.typeModification = typeModification;
		this.ancienneDonnee = ancienneDonnee;
		this.nouvelleDonnee = nouvelleDonnee;
		this.anomalie = anomalie;
		this.utilisateur = utilisateur;
	}
	
}
