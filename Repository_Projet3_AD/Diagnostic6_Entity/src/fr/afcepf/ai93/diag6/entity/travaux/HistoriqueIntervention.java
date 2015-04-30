package fr.afcepf.ai93.diag6.entity.travaux;

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
@Table(name="historique_intervention")
public class HistoriqueIntervention implements Serializable {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_historique_intervention")
	private Integer idHistoriqueIntervention;
	
	@Column(name="date_modification")	
	private Date dateModification;
	
	@Column(name="type_de_modification")
	private String typeModification;
	
	@Column(name="ancienne_donnee")
	private String ancienneDonnee;
	
	@Column(name="nouvelle_donnee")
	private String nouvelleDonnee;
	
	@ManyToOne
	@JoinColumn(name="id_intervention")
	private Intervention intervention;
	
	@ManyToOne
	@JoinColumn(name="id_utilisateur")
	private Utilisateur utilisateur;
	
	public Integer getIdHistoriqueIntervention() {
		return idHistoriqueIntervention;
	}

	public void setIdHistoriqueIntervention(Integer idHistoriqueIntervention) {
		this.idHistoriqueIntervention = idHistoriqueIntervention;
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

	public Intervention getIntervention() {
		return intervention;
	}

	public void setIntervention(Intervention intervention) {
		this.intervention = intervention;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public HistoriqueIntervention() {
		super();
	}

	public HistoriqueIntervention(Integer idHistoriqueIntervention,
			Date dateModification, String typeModification,
			String ancienneDonnee, String nouvelleDonnee,
			Intervention intervention, Utilisateur utilisateur) {
		super();
		this.idHistoriqueIntervention = idHistoriqueIntervention;
		this.dateModification = dateModification;
		this.typeModification = typeModification;
		this.ancienneDonnee = ancienneDonnee;
		this.nouvelleDonnee = nouvelleDonnee;
		this.intervention = intervention;
		this.utilisateur = utilisateur;
	}
	
	
	
}
