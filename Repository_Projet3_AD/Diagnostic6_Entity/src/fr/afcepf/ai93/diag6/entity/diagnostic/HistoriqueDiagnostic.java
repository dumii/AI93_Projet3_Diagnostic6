package fr.afcepf.ai93.diag6.entity.diagnostic;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
@Table(name="historique_diagnostic")
public class HistoriqueDiagnostic implements Serializable {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_historique_diagnostic")
	private Integer idHistoriqueDiagnostic;
	
	@Column(name="date_modification")
	private Date dateModification;
	
	@Column(name="type_de_modification")
	private String typeModification;
	
	@Column(name="ancienne_donnee")
	private String ancienneDonnee;
	
	@Column(name="nouvelle_donnee")
	private String nouvelleDonnee;
	
	@ManyToOne
	@JoinColumn(name="no_diagnostic")
	private Diagnostic diagnostic;
	
	@ManyToOne
	@JoinColumn(name="id_utilisateur")
	private Utilisateur utilisateur;

	public Integer getIdHistoriqueDiagnostic() {
		return idHistoriqueDiagnostic;
	}

	public void setIdHistoriqueDiagnostic(Integer idHistoriqueDiagnostic) {
		this.idHistoriqueDiagnostic = idHistoriqueDiagnostic;
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

	public Diagnostic getDiagnostic() {
		return diagnostic;
	}

	public void setDiagnostic(Diagnostic diagnostic) {
		this.diagnostic = diagnostic;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public HistoriqueDiagnostic() {
		super();
	}

	public HistoriqueDiagnostic(Integer idHistoriqueDiagnostic,
			Date dateModification, String typeModification,
			String ancienneDonnee, String nouvelleDonnee,
			Diagnostic diagnostic, Utilisateur utilisateur) {
		super();
		this.idHistoriqueDiagnostic = idHistoriqueDiagnostic;
		this.dateModification = dateModification;
		this.typeModification = typeModification;
		this.ancienneDonnee = ancienneDonnee;
		this.nouvelleDonnee = nouvelleDonnee;
		this.diagnostic = diagnostic;
		this.utilisateur = utilisateur;
	}
	
}
