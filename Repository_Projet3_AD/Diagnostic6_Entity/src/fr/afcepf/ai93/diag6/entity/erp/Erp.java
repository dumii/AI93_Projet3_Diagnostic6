package fr.afcepf.ai93.diag6.entity.erp;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import fr.afcepf.ai93.diag6.entity.autres.Favoris;
import fr.afcepf.ai93.diag6.entity.autres.Localisation;
import fr.afcepf.ai93.diag6.entity.diagnostic.Diagnostic;

@Entity
@Table(name="erp")
public class Erp implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="no_erp")
	private Integer idErp;
	
	@Column(name="nom_erp")
	private String nomErp;
	
	@Column(name="date_enregistrement_erp")
	private Date dateEnregistrement;
	
	@Column(name="date_de_suppression_erp")
	private Date dateSuppression;
	
	@Column(name="nom_voie_erp")
	private String nomVoieErp;
	
	@Column(name="num_voie_erp")
	private Integer numVoieErp;
	
	@Column(name="complement_adresse_erp")
	private String complementAdresseErp;
	
	@Column(name="date_acquistion")
	private Date dateAcquisition;
	
	@Column(name="date_separation")
	private Date dateSeparation;
	
	@OneToMany(mappedBy="erp")
	private List<Diagnostic> listeDiagnosticErp;
	
	@ManyToOne
	@JoinColumn(name="id_proprietaire")
	private Proprietaire proprietaire;
	
	@ManyToOne
	@JoinColumn(name="id_localisation")
	private Localisation localisation;
	
	@OneToMany(mappedBy="erp")
	private List<Batiment> listeBatimentsErp;
	
	@OneToMany(mappedBy="erp")
	private List<Voirie> listeVoiriesErp;
	
	@OneToMany(mappedBy="erp")
	private List<HistoriqueErp> listeHistoriqueErp;
	
	@ManyToOne
	@JoinColumn(name="id_type_erp")
	private TypeErp typeErp;
	
	@ManyToOne
	@JoinColumn(name="id_categorie_erp")
	private CategorieErp categorieErp;
	
	@OneToMany(mappedBy="erp")
	private List<Favoris> favoris;
	
	
	public Integer getIdErp() {
		return idErp;
	}


	public void setIdErp(Integer idErp) {
		this.idErp = idErp;
	}


	public String getNomErp() {
		return nomErp;
	}


	public void setNomErp(String nomErp) {
		this.nomErp = nomErp;
	}


	public Date getDateEnregistrement() {
		return dateEnregistrement;
	}


	public void setDateEnregistrement(Date dateEnregistrement) {
		this.dateEnregistrement = dateEnregistrement;
	}


	public Date getDateSuppression() {
		return dateSuppression;
	}


	public void setDateSuppression(Date dateSuppression) {
		this.dateSuppression = dateSuppression;
	}


	public String getNomVoieErp() {
		return nomVoieErp;
	}


	public void setNomVoieErp(String nomVoieErp) {
		this.nomVoieErp = nomVoieErp;
	}


	public Integer getNumVoieErp() {
		return numVoieErp;
	}


	public void setNumVoieErp(Integer numVoieErp) {
		this.numVoieErp = numVoieErp;
	}


	public String getComplementAdresseErp() {
		return complementAdresseErp;
	}


	public void setComplementAdresseErp(String complementAdresseErp) {
		this.complementAdresseErp = complementAdresseErp;
	}


	public Date getDateAcquisition() {
		return dateAcquisition;
	}


	public void setDateAcquisition(Date dateAcquisition) {
		this.dateAcquisition = dateAcquisition;
	}


	public Date getDateSeparation() {
		return dateSeparation;
	}


	public void setDateSeparation(Date dateSeparation) {
		this.dateSeparation = dateSeparation;
	}


	public List<Diagnostic> getListeDiagnosticErp() {
		return listeDiagnosticErp;
	}


	public void setListeDiagnosticErp(List<Diagnostic> listeDiagnosticErp) {
		this.listeDiagnosticErp = listeDiagnosticErp;
	}


	public Proprietaire getProprietaire() {
		return proprietaire;
	}


	public void setProprietaire(Proprietaire proprietaire) {
		this.proprietaire = proprietaire;
	}


	public Localisation getLocalisation() {
		return localisation;
	}


	public void setLocalisation(Localisation localisation) {
		this.localisation = localisation;
	}


	public List<Batiment> getListeBatimentsErp() {
		return listeBatimentsErp;
	}


	public void setListeBatimentsErp(List<Batiment> listeBatimentsErp) {
		this.listeBatimentsErp = listeBatimentsErp;
	}


	public List<Voirie> getListeVoiriesErp() {
		return listeVoiriesErp;
	}


	public void setListeVoiriesErp(List<Voirie> listeVoiriesErp) {
		this.listeVoiriesErp = listeVoiriesErp;
	}


	public List<HistoriqueErp> getListeHistoriqueErp() {
		return listeHistoriqueErp;
	}


	public void setListeHistoriqueErp(List<HistoriqueErp> listeHistoriqueErp) {
		this.listeHistoriqueErp = listeHistoriqueErp;
	}


	public TypeErp getTypeErp() {
		return typeErp;
	}


	public void setTypeErp(TypeErp typeErp) {
		this.typeErp = typeErp;
	}


	public CategorieErp getCategorieErp() {
		return categorieErp;
	}


	public void setCategorieErp(CategorieErp categorieErp) {
		this.categorieErp = categorieErp;
	}


	public List<Favoris> getFavoris() {
		return favoris;
	}


	public void setFavoris(List<Favoris> favoris) {
		this.favoris = favoris;
	}


	public Erp() {
		super();
	}


	public Erp(Integer idErp, String nomErp, Date dateEnregistrement,
			Date dateSuppression, String nomVoieErp, Integer numVoieErp,
			String complementAdresseErp, Date dateAcquisition,
			Date dateSeparation, List<Diagnostic> listeDiagnosticErp,
			Proprietaire proprietaire, Localisation localisation,
			List<Batiment> listeBatimentsErp, List<Voirie> listeVoiriesErp,
			List<HistoriqueErp> listeHistoriqueErp, TypeErp typeErp,
			CategorieErp categorieErp, List<Favoris> favoris) {
		super();
		this.idErp = idErp;
		this.nomErp = nomErp;
		this.dateEnregistrement = dateEnregistrement;
		this.dateSuppression = dateSuppression;
		this.nomVoieErp = nomVoieErp;
		this.numVoieErp = numVoieErp;
		this.complementAdresseErp = complementAdresseErp;
		this.dateAcquisition = dateAcquisition;
		this.dateSeparation = dateSeparation;
		this.listeDiagnosticErp = listeDiagnosticErp;
		this.proprietaire = proprietaire;
		this.localisation = localisation;
		this.listeBatimentsErp = listeBatimentsErp;
		this.listeVoiriesErp = listeVoiriesErp;
		this.listeHistoriqueErp = listeHistoriqueErp;
		this.typeErp = typeErp;
		this.categorieErp = categorieErp;
		this.favoris = favoris;
	}
	
	
	
}
