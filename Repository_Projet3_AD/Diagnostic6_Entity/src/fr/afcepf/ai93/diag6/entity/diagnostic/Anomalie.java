package fr.afcepf.ai93.diag6.entity.diagnostic;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder.In;

import fr.afcepf.ai93.diag6.entity.erp.Acces;
import fr.afcepf.ai93.diag6.entity.erp.Ascenceur;
import fr.afcepf.ai93.diag6.entity.erp.Escalier;
import fr.afcepf.ai93.diag6.entity.erp.Piece;
import fr.afcepf.ai93.diag6.entity.erp.Voirie;
import fr.afcepf.ai93.diag6.entity.travaux.Intervention;


@Entity
@Table(name="anomalie")
public class Anomalie implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_anomalie")
	private Integer idAnomalie;
	
	@Column(name="description_anomalie")
	private String descriptionAnomalie;
	
	@Column(name="preconisation_anomalie")
	private String preconisationAnomalie;
	
	@Column(name="cout_estime_anomalie")
	private Double coutEstimeAnomalie;
	
	@ManyToOne
	@JoinColumn(name="id_indicateur")
	private Indicateur indicateur;
	
	@ManyToOne
	@JoinColumn(name="id_ascenceur")
	private Ascenceur ascenceur;
	
	@ManyToOne
	@JoinColumn(name="id_voirie")
	private Voirie voirie;
	
	@ManyToOne
	@JoinColumn(name="id_acces")
	private Acces acces;
	
	@ManyToOne
	@JoinColumn(name="id_escalier")
	private Escalier escalier;
	
	@ManyToOne
	@JoinColumn(name="id_piece")
	private Piece piece;
	
	@ManyToOne
	@JoinColumn(name="no_diagnostic")
	private Diagnostic diagnostic;
	
	@OneToMany(mappedBy="anomalie")
	private List<HistoriqueAnomalie> listeHistoriqueAnomalie;
	
	@OneToOne(mappedBy="anomalie")
	private Intervention listeInterventions;
	

	public Integer getIdAnomalie() {
		return idAnomalie;
	}

	public void setIdAnomalie(Integer idAnomalie) {
		this.idAnomalie = idAnomalie;
	}

	public String getDescriptionAnomalie() {
		return descriptionAnomalie;
	}

	public void setDescriptionAnomalie(String descriptionAnomalie) {
		this.descriptionAnomalie = descriptionAnomalie;
	}

	public String getPreconisationAnomalie() {
		return preconisationAnomalie;
	}

	public void setPreconisationAnomalie(String preconisationAnomalie) {
		this.preconisationAnomalie = preconisationAnomalie;
	}

	public Double getCoutEstimeAnomalie() {
		return coutEstimeAnomalie;
	}

	public void setCoutEstimeAnomalie(Double coutEstimeAnomalie) {
		this.coutEstimeAnomalie = coutEstimeAnomalie;
	}

	public Ascenceur getAscenceur() {
		return ascenceur;
	}

	public void setAscenceur(Ascenceur ascenceur) {
		this.ascenceur = ascenceur;
	}

	public Voirie getVoirie() {
		return voirie;
	}

	public void setVoirie(Voirie voirie) {
		this.voirie = voirie;
	}

	public Acces getAcces() {
		return acces;
	}

	public void setAcces(Acces acces) {
		this.acces = acces;
	}

	public Escalier getEscalier() {
		return escalier;
	}

	public void setEscalier(Escalier escalier) {
		this.escalier = escalier;
	}

	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	public Diagnostic getDiagnostic() {
		return diagnostic;
	}

	public void setDiagnostic(Diagnostic diagnostic) {
		this.diagnostic = diagnostic;
	}

	public List<HistoriqueAnomalie> getListeHistoriqueAnomalie() {
		return listeHistoriqueAnomalie;
	}

	public void setListeHistoriqueAnomalie(
			List<HistoriqueAnomalie> listeHistoriqueAnomalie) {
		this.listeHistoriqueAnomalie = listeHistoriqueAnomalie;
	}

	public Anomalie() {
		super();
	}

	public Anomalie(Integer idAnomalie, String descriptionAnomalie,
			String preconisationAnomalie, Double coutEstimeAnomalie,
			Ascenceur ascenceur, Voirie voirie, Acces acces, Escalier escalier,
			Piece piece, Diagnostic diagnostic,
			List<HistoriqueAnomalie> listeHistoriqueAnomalie) {
		super();
		this.idAnomalie = idAnomalie;
		this.descriptionAnomalie = descriptionAnomalie;
		this.preconisationAnomalie = preconisationAnomalie;
		this.coutEstimeAnomalie = coutEstimeAnomalie;
		this.ascenceur = ascenceur;
		this.voirie = voirie;
		this.acces = acces;
		this.escalier = escalier;
		this.piece = piece;
		this.diagnostic = diagnostic;
		this.listeHistoriqueAnomalie = listeHistoriqueAnomalie;
	}
	
	
}
