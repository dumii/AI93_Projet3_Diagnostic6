package fr.afcepf.ai93.diag6.entity.autres;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import fr.afcepf.ai93.diag6.entity.diagnostic.Diagnostic;
import fr.afcepf.ai93.diag6.entity.erp.Erp;
import fr.afcepf.ai93.diag6.entity.travaux.Intervention;

@Entity
@Table(name="notification")
public class Notifications implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_notification")
	private Integer idNotification;
	
	@Column(name="checkee")
	private Integer checkee;
	
	@Column(name="date_creation")
	private Date dateCreation;
	
	@ManyToOne
	@JoinColumn(name="id_profil")
	private ProfilUtilisateur profilUtilisateur;
	
	@ManyToOne
	@JoinColumn(name="id_erp")
	private Erp erp;
	
	@ManyToOne
	@JoinColumn(name="id_diagnostic")
	private Diagnostic diagnostic;
	
	@ManyToOne
	@JoinColumn(name="id_intervention")
	private Intervention intervention;

	public Integer getIdNotification() {
		return idNotification;
	}

	public void setIdNotification(Integer idNotification) {
		this.idNotification = idNotification;
	}

	public Integer getCheckee() {
		return checkee;
	}

	public void setCheckee(Integer checkee) {
		this.checkee = checkee;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public ProfilUtilisateur getProfilUtilisateur() {
		return profilUtilisateur;
	}

	public void setProfilUtilisateur(ProfilUtilisateur profilUtilisateur) {
		this.profilUtilisateur = profilUtilisateur;
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

	public Notifications() {
		super();
	}

	public Notifications(Integer idNotification, Integer checkee,
			Date dateCreation, ProfilUtilisateur profilUtilisateur, Erp erp,
			Diagnostic diagnostic, Intervention intervention) {
		super();
		this.idNotification = idNotification;
		this.checkee = checkee;
		this.dateCreation = dateCreation;
		this.profilUtilisateur = profilUtilisateur;
		this.erp = erp;
		this.diagnostic = diagnostic;
		this.intervention = intervention;
	}
	
}
