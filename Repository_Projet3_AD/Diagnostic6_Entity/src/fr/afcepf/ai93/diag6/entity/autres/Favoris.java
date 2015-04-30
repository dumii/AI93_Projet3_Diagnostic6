package fr.afcepf.ai93.diag6.entity.autres;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import fr.afcepf.ai93.diag6.entity.diagnostic.Diagnostic;
import fr.afcepf.ai93.diag6.entity.erp.Erp;
import fr.afcepf.ai93.diag6.entity.travaux.Intervention;

@Entity
@Table(name="favoris")
public class Favoris implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_favoris")
	private Integer idFavoris;
	
	@ManyToOne
	@JoinColumn(name="id_utilisateur")
	private Utilisateur utilisateur;
	
	@ManyToOne
	@JoinColumn(name="no_ERP")
	private Erp erp;
	
	@ManyToOne
	@JoinColumn(name="no_diagnostic")
	private Diagnostic diagnostic;
	
	@ManyToOne
	@JoinColumn(name="id_intervention")
	private Intervention intervention;

	
	public Integer getIdFavoris() {
		return idFavoris;
	}

	public void setIdFavoris(Integer idFavoris) {
		this.idFavoris = idFavoris;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Erp getErp() {
		return erp;
	}

	public void setErp(Erp erp) {
		this.erp = erp;
	}

	public Diagnostic getDiagnostic() {
		return diagnostic;
	}

	public void setDiagnostic(Diagnostic diagnostic) {
		this.diagnostic = diagnostic;
	}

	public Intervention getIntervention() {
		return intervention;
	}

	public void setIntervention(Intervention intervention) {
		this.intervention = intervention;
	}

	public Favoris() {
		super();
	}

	public Favoris(Integer idFavoris, Utilisateur utilisateur, Erp erp,
			Diagnostic diagnostic, Intervention intervention) {
		super();
		this.idFavoris = idFavoris;
		this.utilisateur = utilisateur;
		this.erp = erp;
		this.diagnostic = diagnostic;
		this.intervention = intervention;
	}

	
}
