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

@Entity
@Table(name="expert")
public class Expert implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="no_expert")
	private Integer idExpert;
	
	@Column(name="nom_expert")
	private String nomExpert;
	
	@Column(name="prenom_expert")
	private String prenomExpert;
	
	@Column(name="nom_voie_expert")
	private String nomVoieExpert;
	
	@Column(name="num_voie_expert")
	private Integer numVoieExpert;
	
	@Column(name="tel_expert")
	private Integer telExpert;
	
	@Column(name="mail_expert")
	private String mailExpert;
	
	@ManyToOne
	@JoinColumn(name="id_localisation")
	private Localisation localisation;

	public Integer getIdExpert() {
		return idExpert;
	}

	public void setIdExpert(Integer idExpert) {
		this.idExpert = idExpert;
	}

	public String getNomExpert() {
		return nomExpert;
	}

	public void setNomExpert(String nomExpert) {
		this.nomExpert = nomExpert;
	}

	public String getPrenomExpert() {
		return prenomExpert;
	}

	public void setPrenomExpert(String prenomExpert) {
		this.prenomExpert = prenomExpert;
	}

	public String getNomVoieExpert() {
		return nomVoieExpert;
	}

	public void setNomVoieExpert(String nomVoieExpert) {
		this.nomVoieExpert = nomVoieExpert;
	}

	public Integer getNumVoieExpert() {
		return numVoieExpert;
	}

	public void setNumVoieExpert(Integer numVoieExpert) {
		this.numVoieExpert = numVoieExpert;
	}

	public Integer getTelExpert() {
		return telExpert;
	}

	public void setTelExpert(Integer telExpert) {
		this.telExpert = telExpert;
	}

	public String getMailExpert() {
		return mailExpert;
	}

	public void setMailExpert(String mailExpert) {
		this.mailExpert = mailExpert;
	}

	public Localisation getLocalisation() {
		return localisation;
	}

	public void setLocalisation(Localisation localisation) {
		this.localisation = localisation;
	}

	public Expert() {
		super();
	}

	public Expert(Integer idExpert, String nomExpert, String prenomExpert,
			String nomVoieExpert, Integer numVoieExpert, Integer telExpert,
			String mailExpert, Localisation localisation) {
		super();
		this.idExpert = idExpert;
		this.nomExpert = nomExpert;
		this.prenomExpert = prenomExpert;
		this.nomVoieExpert = nomVoieExpert;
		this.numVoieExpert = numVoieExpert;
		this.telExpert = telExpert;
		this.mailExpert = mailExpert;
		this.localisation = localisation;
	}
	
	
}
