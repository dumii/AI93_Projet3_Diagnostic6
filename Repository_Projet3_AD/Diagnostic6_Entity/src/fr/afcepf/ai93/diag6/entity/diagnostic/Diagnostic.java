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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import fr.afcepf.ai93.diag6.entity.autres.Expert;
import fr.afcepf.ai93.diag6.entity.autres.Favoris;
import fr.afcepf.ai93.diag6.entity.erp.Erp;

@Entity
@Table(name="diagnostic")
public class Diagnostic implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="No_Diagnostic")
	private Integer idDiagnostic;
	
	@Column(name="nom_diagnostiqueur")
	private String nomDiagnostiqueur;
	
	@Column(name="date_saisie_diagnostic")
	private Date dateSaisieDiagnostic;
	
	@Column(name="date_realisation_diagnostic")
	private Date dateRealisationDiagnostic;
	
	@Column(name="intitule_diagnostic")
	private String intituleDiagnostic;
	
	@Column(name="traite")
	private Integer traite;
	
	@ManyToOne
	@JoinColumn(name="no_erp")
	private Erp erp;
	
	@ManyToOne
	@JoinColumn(name="no_expert")
	private Expert expert;
	
	@ManyToOne
	@JoinColumn(name="id_type_diagnostic")
	private TypeDiagnostic typeDiagnostic;
	
	
	@OneToMany(mappedBy="diagnostic")
	private List<Favoris> listeFavorisDiagnostic;
	
	@OneToMany(mappedBy="diagnostic")
	private List<Anomalie> listeAnomaliesDiagnostic;
	
	@OneToMany(mappedBy="diagnostic")
	private List<HistoriqueDiagnostic> listeHistoriqueDiagnostic;

	
	
	public Integer getIdDiagnostic() {
		return idDiagnostic;
	}

	public void setIdDiagnostic(Integer idDiagnostic) {
		this.idDiagnostic = idDiagnostic;
	}

	public String getNomDiagnostiqueur() {
		return nomDiagnostiqueur;
	}

	public void setNomDiagnostiqueur(String nomDiagnostiqueur) {
		this.nomDiagnostiqueur = nomDiagnostiqueur;
	}

	public Date getDateSaisieDiagnostic() {
		return dateSaisieDiagnostic;
	}

	public void setDateSaisieDiagnostic(Date dateSaisieDiagnostic) {
		this.dateSaisieDiagnostic = dateSaisieDiagnostic;
	}

	public Date getDateRealisationDiagnostic() {
		return dateRealisationDiagnostic;
	}

	public void setDateRealisationDiagnostic(Date dateRealisationDiagnostic) {
		this.dateRealisationDiagnostic = dateRealisationDiagnostic;
	}

	public String getIntituleDiagnostic() {
		return intituleDiagnostic;
	}

	public void setIntituleDiagnostic(String intituleDiagnostic) {
		this.intituleDiagnostic = intituleDiagnostic;
	}

	public Integer getTraite() {
		return traite;
	}

	public void setTraite(Integer traite) {
		this.traite = traite;
	}

	public Erp getErp() {
		return erp;
	}

	public void setErp(Erp erp) {
		this.erp = erp;
	}

	public Expert getExpert() {
		return expert;
	}

	public void setExpert(Expert expert) {
		this.expert = expert;
	}

	public TypeDiagnostic getTypeDiagnostic() {
		return typeDiagnostic;
	}

	public void setTypeDiagnostic(TypeDiagnostic typeDiagnostic) {
		this.typeDiagnostic = typeDiagnostic;
	}

	public List<Favoris> getListeFavorisDiagnostic() {
		return listeFavorisDiagnostic;
	}

	public void setListeFavorisDiagnostic(List<Favoris> listeFavorisDiagnostic) {
		this.listeFavorisDiagnostic = listeFavorisDiagnostic;
	}

	public List<Anomalie> getListeAnomaliesDiagnostic() {
		return listeAnomaliesDiagnostic;
	}

	public void setListeAnomaliesDiagnostic(List<Anomalie> listeAnomaliesDiagnostic) {
		this.listeAnomaliesDiagnostic = listeAnomaliesDiagnostic;
	}

	public List<HistoriqueDiagnostic> getListeHistoriqueDiagnostic() {
		return listeHistoriqueDiagnostic;
	}

	public void setListeHistoriqueDiagnostic(
			List<HistoriqueDiagnostic> listeHistoriqueDiagnostic) {
		this.listeHistoriqueDiagnostic = listeHistoriqueDiagnostic;
	}

	public Diagnostic() {
		super();
	}

	public Diagnostic(Integer idDiagnostic, String nomDiagnostiqueur,
			Date dateSaisieDiagnostic, Date dateRealisationDiagnostic,
			String intituleDiagnostic, Integer traite, Erp erp, Expert expert,
			TypeDiagnostic typeDiagnostic,
			List<Favoris> listeFavorisDiagnostic,
			List<Anomalie> listeAnomaliesDiagnostic,
			List<HistoriqueDiagnostic> listeHistoriqueDiagnostic) {
		super();
		this.idDiagnostic = idDiagnostic;
		this.nomDiagnostiqueur = nomDiagnostiqueur;
		this.dateSaisieDiagnostic = dateSaisieDiagnostic;
		this.dateRealisationDiagnostic = dateRealisationDiagnostic;
		this.intituleDiagnostic = intituleDiagnostic;
		this.traite = traite;
		this.erp = erp;
		this.expert = expert;
		this.typeDiagnostic = typeDiagnostic;
		this.listeFavorisDiagnostic = listeFavorisDiagnostic;
		this.listeAnomaliesDiagnostic = listeAnomaliesDiagnostic;
		this.listeHistoriqueDiagnostic = listeHistoriqueDiagnostic;
	}
	
	
}
