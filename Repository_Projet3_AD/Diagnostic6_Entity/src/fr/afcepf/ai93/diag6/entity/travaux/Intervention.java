package fr.afcepf.ai93.diag6.entity.travaux;

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

import fr.afcepf.ai93.diag6.entity.autres.Artisan;
import fr.afcepf.ai93.diag6.entity.autres.Favoris;
import fr.afcepf.ai93.diag6.entity.diagnostic.Anomalie;

@Entity
@Table(name="intervention")
public class Intervention implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_intervention")
	private Integer idIntervention;
	
	@Column(name="date_debut_intervention")
	private Date dateDebutIntervention;
	
	@Column(name="date_fin_intervention")
	private Date dateFinIntervention;
	
	@Column(name="cout_intervention")
	private double coutIntervention;
	
	@OneToMany(mappedBy="intervention")
	private List<HistoriqueIntervention> listeHistoriqueIntervention;
	
	@OneToMany(mappedBy="intervention")
	private List<Favoris> listeFavorisIntervention;
	
	@ManyToOne
	@JoinColumn(name="id_anomalie")
	private Anomalie anomalie;
	
	@ManyToOne
	@JoinColumn(name="id_etat_avancement")
	private EtatAvancementTravaux etatAvancementTravaux;
	
	@ManyToOne
	@JoinColumn(name="id_type_intervention")
	private TypeIntervention typeIntervention;
	
	@ManyToOne
	@JoinColumn(name="id_artisan")
	private Artisan artisan;

	
	public Integer getIdIntervention() {
		return idIntervention;
	}


	public void setIdIntervention(Integer idIntervention) {
		this.idIntervention = idIntervention;
	}


	public Date getDateDebutIntervention() {
		return dateDebutIntervention;
	}


	public void setDateDebutIntervention(Date dateDebutIntervention) {
		this.dateDebutIntervention = dateDebutIntervention;
	}


	public Date getDateFinIntervention() {
		return dateFinIntervention;
	}


	public void setDateFinIntervention(Date dateFinIntervention) {
		this.dateFinIntervention = dateFinIntervention;
	}


	public double getCoutIntervention() {
		return coutIntervention;
	}


	public void setCoutIntervention(double coutIntervention) {
		this.coutIntervention = coutIntervention;
	}


	public List<HistoriqueIntervention> getListeHistoriqueIntervention() {
		return listeHistoriqueIntervention;
	}


	public void setListeHistoriqueIntervention(
			List<HistoriqueIntervention> listeHistoriqueIntervention) {
		this.listeHistoriqueIntervention = listeHistoriqueIntervention;
	}


	public List<Favoris> getListeFavorisIntervention() {
		return listeFavorisIntervention;
	}


	public void setListeFavorisIntervention(List<Favoris> listeFavorisIntervention) {
		this.listeFavorisIntervention = listeFavorisIntervention;
	}


	public Anomalie getAnomalie() {
		return anomalie;
	}


	public void setAnomalie(Anomalie anomalie) {
		this.anomalie = anomalie;
	}


	public EtatAvancementTravaux getEtatAvancementTravaux() {
		return etatAvancementTravaux;
	}


	public void setEtatAvancementTravaux(EtatAvancementTravaux etatAvancementTravaux) {
		this.etatAvancementTravaux = etatAvancementTravaux;
	}


	public TypeIntervention getTypeIntervention() {
		return typeIntervention;
	}


	public void setTypeIntervention(TypeIntervention typeIntervention) {
		this.typeIntervention = typeIntervention;
	}


	public Artisan getArtisan() {
		return artisan;
	}


	public void setArtisan(Artisan artisan) {
		this.artisan = artisan;
	}


	public Intervention() {
	}


	public Intervention(Integer idIntervention, Date dateDebutIntervention,
			Date dateFinIntervention, double coutIntervention,
			Anomalie anomalie,
			EtatAvancementTravaux etatAvancementTravaux,
			TypeIntervention typeIntervention, Artisan artisan) {
		this.idIntervention = idIntervention;
		this.dateDebutIntervention = dateDebutIntervention;
		this.dateFinIntervention = dateFinIntervention;
		this.coutIntervention = coutIntervention;
		//this.listeHistoriqueIntervention = listeHistoriqueIntervention;
		//this.listeFavorisIntervention = listeFavorisIntervention;
		this.anomalie = anomalie;
		this.etatAvancementTravaux = etatAvancementTravaux;
		this.typeIntervention = typeIntervention;
		this.artisan = artisan;
	}
	
	
}
