package fr.afcepf.ai93.diag6.entity.autres;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import fr.afcepf.ai93.diag6.entity.erp.Erp;
import fr.afcepf.ai93.diag6.entity.erp.Proprietaire;


@Entity
@Table(name="localisation")
public class Localisation implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_localisation")
	private Integer idLocalisation;
	
	@Column(name="code_postal")
	private Integer codePostal;
	
	@Column(name="ville")
	private String ville;
	
	@OneToMany(mappedBy="localisation")
	private List<Artisan> listeArtisansLocalisation;
	
	@OneToMany(mappedBy="localisation")
	private List<Expert> listeExpertsLocalisation;
	
	@OneToMany(mappedBy="localisation")
	private List<Erp> listeErpLocalisation;
	
	@OneToMany(mappedBy="localisation")
	private List<Proprietaire> listeProprietairesLocalisation;

	
	public Integer getIdLocalisation() {
		return idLocalisation;
	}

	public void setIdLocalisation(Integer idLocalisation) {
		this.idLocalisation = idLocalisation;
	}

	public Integer getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(Integer codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public List<Artisan> getListeArtisansLocalisation() {
		return listeArtisansLocalisation;
	}

	public void setListeArtisansLocalisation(List<Artisan> listeArtisansLocalisation) {
		this.listeArtisansLocalisation = listeArtisansLocalisation;
	}

	public List<Expert> getListeExpertsLocalisation() {
		return listeExpertsLocalisation;
	}

	public void setListeExpertsLocalisation(List<Expert> listeExpertsLocalisation) {
		this.listeExpertsLocalisation = listeExpertsLocalisation;
	}

	public List<Erp> getListeErpLocalisation() {
		return listeErpLocalisation;
	}

	public void setListeErpLocalisation(List<Erp> listeErpLocalisation) {
		this.listeErpLocalisation = listeErpLocalisation;
	}

	public List<Proprietaire> getListeProprietairesLocalisation() {
		return listeProprietairesLocalisation;
	}

	public void setListeProprietairesLocalisation(
			List<Proprietaire> listeProprietairesLocalisation) {
		this.listeProprietairesLocalisation = listeProprietairesLocalisation;
	}

	public Localisation() {
		super();
	}

	public Localisation(Integer idLocalisation, Integer codePostal,
			String ville, List<Artisan> listeArtisansLocalisation,
			List<Expert> listeExpertsLocalisation,
			List<Erp> listeErpLocalisation,
			List<Proprietaire> listeProprietairesLocalisation) {
		super();
		this.idLocalisation = idLocalisation;
		this.codePostal = codePostal;
		this.ville = ville;
		this.listeArtisansLocalisation = listeArtisansLocalisation;
		this.listeExpertsLocalisation = listeExpertsLocalisation;
		this.listeErpLocalisation = listeErpLocalisation;
		this.listeProprietairesLocalisation = listeProprietairesLocalisation;
	}
	
	
	
	
}
