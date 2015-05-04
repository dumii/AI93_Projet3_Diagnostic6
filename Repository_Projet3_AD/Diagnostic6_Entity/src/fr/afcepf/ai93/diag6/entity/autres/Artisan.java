package fr.afcepf.ai93.diag6.entity.autres;

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

import fr.afcepf.ai93.diag6.entity.travaux.Intervention;

@Entity
@Table(name="artisan")
public class Artisan implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_ARTISAN")
	private Integer idArtisan;
	
	@Column(name="nom_artisan")
	private String nomArtisan;
	
	@Column(name="prenom_artisan")
	private String prenomArtisan;
	
	@Column(name="num_voie_artisan")
	private Integer numVoieArtisan;
	
	@Column(name="nom_voie_artisan")
	private String nomVoieArtisan;
	
	@Column(name="tel_artisan")
	private Integer telArtisan;
	
	@Column(name="mail_artisan")
	private String mailArtisan;
	
	@OneToMany(mappedBy="artisan")
	private List<TypeArtisan> listeTypeInterventions;
	
	@ManyToOne
	@JoinColumn(name="id_localisation")
	private Localisation localisation;
	
	@OneToMany(mappedBy="artisan")
	private List<Intervention> listeInterventions;

	
	public Integer getIdArtisan() {
		return idArtisan;
	}

	public void setIdArtisan(Integer idArtisan) {
		this.idArtisan = idArtisan;
	}

	public String getNomArtisan() {
		return nomArtisan;
	}

	public void setNomArtisan(String nomArtisan) {
		this.nomArtisan = nomArtisan;
	}

	public String getPrenomArtisan() {
		return prenomArtisan;
	}

	public void setPrenomArtisan(String prenomArtisan) {
		this.prenomArtisan = prenomArtisan;
	}

	public Integer getNumVoieArtisan() {
		return numVoieArtisan;
	}

	public void setNumVoieArtisan(Integer numVoieArtisan) {
		this.numVoieArtisan = numVoieArtisan;
	}

	public String getNomVoieArtisan() {
		return nomVoieArtisan;
	}

	public void setNomVoieArtisan(String nomVoieArtisan) {
		this.nomVoieArtisan = nomVoieArtisan;
	}

	public Integer getTelArtisan() {
		return telArtisan;
	}

	public void setTelArtisan(Integer telArtisan) {
		this.telArtisan = telArtisan;
	}

	public String getMailArtisan() {
		return mailArtisan;
	}

	public void setMailArtisan(String mailArtisan) {
		this.mailArtisan = mailArtisan;
	}

	public Localisation getLocalisation() {
		return localisation;
	}

	public void setLocalisation(Localisation localisation) {
		this.localisation = localisation;
	}

	public List<Intervention> getListeInterventions() {
		return listeInterventions;
	}

	public void setListeInterventions(List<Intervention> listeInterventions) {
		this.listeInterventions = listeInterventions;
	}

	public Artisan() {
		super();
	}

	public Artisan(Integer idArtisan, String nomArtisan, String prenomArtisan,
			Integer numVoieArtisan, String nomVoieArtisan, Integer telArtisan,
			String mailArtisan, TypeArtisan typeArtisan,
			Localisation localisation) {
		super();
		this.idArtisan = idArtisan;
		this.nomArtisan = nomArtisan;
		this.prenomArtisan = prenomArtisan;
		this.numVoieArtisan = numVoieArtisan;
		this.nomVoieArtisan = nomVoieArtisan;
		this.telArtisan = telArtisan;
		this.mailArtisan = mailArtisan;
		this.localisation = localisation;
	}

	public List<TypeArtisan> getListeTypeInterventions() {
		return listeTypeInterventions;
	}

	public void setListeTypeInterventions(List<TypeArtisan> listeTypeInterventions) {
		this.listeTypeInterventions = listeTypeInterventions;
	}	
}
