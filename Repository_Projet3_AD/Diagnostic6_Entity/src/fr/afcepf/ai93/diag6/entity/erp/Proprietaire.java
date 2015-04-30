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
import javax.persistence.Table;

import fr.afcepf.ai93.diag6.entity.autres.Localisation;

@Entity
@Table(name="proprietaire")
public class Proprietaire implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_proprietaire")
	private Integer idProprietaire;
	
	@Column(name="prenom_proprietaire")
	private String prenomProprietaire;
	
	@Column(name="nom_proprietaire")
	private String nomProprietaire;
	
	@Column(name="raison_sociale_proprietaire")
	private String raisonSocialeProprietaire;
	
	@Column(name="nom_voie_proprietaire")
	private String nomVoieProprietaire;
	
	@Column(name="num_voie_proprietaire")
	private Integer numVoieProprietaire;
	
	@Column(name="num_tel_proprietaire")
	private Integer telProprietaire;
	
	@Column(name="mail_proprietaire")
	private String mailProprietaire;
	
	@ManyToOne
	@JoinColumn(name="id_localisation")
	private Localisation localisation;
	
	@OneToMany(mappedBy="proprietaire")
	private List<Erp> listeErpProprietaire;

	
	public Integer getIdProprietaire() {
		return idProprietaire;
	}

	public void setIdProprietaire(Integer idProprietaire) {
		this.idProprietaire = idProprietaire;
	}

	public String getPrenomProprietaire() {
		return prenomProprietaire;
	}

	public void setPrenomProprietaire(String prenomProprietaire) {
		this.prenomProprietaire = prenomProprietaire;
	}

	public String getNomProprietaire() {
		return nomProprietaire;
	}

	public void setNomProprietaire(String nomProprietaire) {
		this.nomProprietaire = nomProprietaire;
	}

	public String getRaisonSocialeProprietaire() {
		return raisonSocialeProprietaire;
	}

	public void setRaisonSocialeProprietaire(String raisonSocialeProprietaire) {
		this.raisonSocialeProprietaire = raisonSocialeProprietaire;
	}

	public String getNomVoieProprietaire() {
		return nomVoieProprietaire;
	}

	public void setNomVoieProprietaire(String nomVoieProprietaire) {
		this.nomVoieProprietaire = nomVoieProprietaire;
	}

	public Integer getNumVoieProprietaire() {
		return numVoieProprietaire;
	}

	public void setNumVoieProprietaire(Integer numVoieProprietaire) {
		this.numVoieProprietaire = numVoieProprietaire;
	}

	public Integer getTelProprietaire() {
		return telProprietaire;
	}

	public void setTelProprietaire(Integer telProprietaire) {
		this.telProprietaire = telProprietaire;
	}

	public String getMailProprietaire() {
		return mailProprietaire;
	}

	public void setMailProprietaire(String mailProprietaire) {
		this.mailProprietaire = mailProprietaire;
	}

	public Localisation getLocalisation() {
		return localisation;
	}

	public void setLocalisation(Localisation localisation) {
		this.localisation = localisation;
	}

	public List<Erp> getListeErpProprietaire() {
		return listeErpProprietaire;
	}

	public void setListeErpProprietaire(List<Erp> listeErpProprietaire) {
		this.listeErpProprietaire = listeErpProprietaire;
	}

	public Proprietaire() {
		super();
	}

	public Proprietaire(Integer idProprietaire, String prenomProprietaire,
			String nomProprietaire, String raisonSocialeProprietaire,
			String nomVoieProprietaire, Integer numVoieProprietaire,
			Integer telProprietaire, String mailProprietaire,
			Localisation localisation, List<Erp> listeErpProprietaire) {
		super();
		this.idProprietaire = idProprietaire;
		this.prenomProprietaire = prenomProprietaire;
		this.nomProprietaire = nomProprietaire;
		this.raisonSocialeProprietaire = raisonSocialeProprietaire;
		this.nomVoieProprietaire = nomVoieProprietaire;
		this.numVoieProprietaire = numVoieProprietaire;
		this.telProprietaire = telProprietaire;
		this.mailProprietaire = mailProprietaire;
		this.localisation = localisation;
		this.listeErpProprietaire = listeErpProprietaire;
	}
	
	
	
}
