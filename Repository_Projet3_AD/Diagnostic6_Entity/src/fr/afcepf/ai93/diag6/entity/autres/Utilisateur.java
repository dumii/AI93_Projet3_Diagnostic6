package fr.afcepf.ai93.diag6.entity.autres;

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

import fr.afcepf.ai93.diag6.entity.diagnostic.HistoriqueAnomalie;
import fr.afcepf.ai93.diag6.entity.diagnostic.HistoriqueDiagnostic;
import fr.afcepf.ai93.diag6.entity.erp.HistoriqueErp;
import fr.afcepf.ai93.diag6.entity.travaux.HistoriqueIntervention;


@Entity 
@Table(name="utilisateur")
public class Utilisateur implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_utilisateur")
	private Integer idUtilisateur;
	
	@Column(name="login")
	private String loginUtilisateur;
	
	@Column(name="nom")
	private String nomUtilisateur;
	
	@Column(name="prenom")
	private String prenomUtilisateur;
	
	@Column(name="email")
	private String emailUtilisateur;
	
	@Column(name="telephone")
	private int telUtilisateur;
	
	@Column(name="mot_de_passe")
	private String motDePasseUtilisateur;
	
	@Column(name="date_enregistrement")
	private Date dateEnregistrement;

	
	@ManyToOne
	@JoinColumn(name="id_profil")
	private ProfilUtilisateur profilUtilisateur;
	
	@OneToMany(mappedBy="utilisateur")
	private List<HistoriqueErp> listeHistoriqueErpUtilisateur;
	
	@OneToMany(mappedBy="utilisateur")
	private List<HistoriqueAnomalie> listeHistoriqueAnomalieUtilisateur;
	
	@OneToMany(mappedBy="utilisateur")
	private List<HistoriqueIntervention> listeHistoriqueInterventioUtilisateur;
	
	@OneToMany(mappedBy="utilisateur")
	private List<HistoriqueDiagnostic>listeHistoriqueDiagnosticUtilisateur;
	
	@OneToMany(mappedBy="utilisateur")
	private List<Favoris> listeFavorisUtilisateur;

	
	public Integer getIdUtilisateur() {
		return idUtilisateur;
	}


	public void setIdUtilisateur(Integer idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}


	public String getLoginUtilisateur() {
		return loginUtilisateur;
	}


	public void setLoginUtilisateur(String loginUtilisateur) {
		this.loginUtilisateur = loginUtilisateur;
	}


	public String getNomUtilisateur() {
		return nomUtilisateur;
	}


	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}


	public String getPrenomUtilisateur() {
		return prenomUtilisateur;
	}


	public void setPrenomUtilisateur(String prenomUtilisateur) {
		this.prenomUtilisateur = prenomUtilisateur;
	}





	public Integer getTelUtilisateur() {
		return telUtilisateur;
	}


	public void setTelUtilisateur(Integer telUtilisateur) {
		this.telUtilisateur = telUtilisateur;
	}


	public String getMotDePasseUtilisateur() {
		return motDePasseUtilisateur;
	}


	public void setMotDePasseUtilisateur(String motDePasseUtilisateur) {
		this.motDePasseUtilisateur = motDePasseUtilisateur;
	}


	public Date getDateEnregistrement() {
		return dateEnregistrement;
	}


	public void setDateEnregistrement(Date dateEnregistrement) {
		this.dateEnregistrement = dateEnregistrement;
	}


	public ProfilUtilisateur getProfilUtilisateur() {
		return profilUtilisateur;
	}


	public void setProfilUtilisateur(ProfilUtilisateur profilUtilisateur) {
		this.profilUtilisateur = profilUtilisateur;
	}


	public List<HistoriqueErp> getListeHistoriqueErpUtilisateur() {
		return listeHistoriqueErpUtilisateur;
	}


	public void setListeHistoriqueErpUtilisateur(
			List<HistoriqueErp> listeHistoriqueErpUtilisateur) {
		this.listeHistoriqueErpUtilisateur = listeHistoriqueErpUtilisateur;
	}


	public List<HistoriqueAnomalie> getListeHistoriqueAnomalieUtilisateur() {
		return listeHistoriqueAnomalieUtilisateur;
	}


	public void setListeHistoriqueAnomalieUtilisateur(
			List<HistoriqueAnomalie> listeHistoriqueAnomalieUtilisateur) {
		this.listeHistoriqueAnomalieUtilisateur = listeHistoriqueAnomalieUtilisateur;
	}


	public List<HistoriqueIntervention> getListeHistoriqueInterventioUtilisateur() {
		return listeHistoriqueInterventioUtilisateur;
	}


	public void setListeHistoriqueInterventioUtilisateur(
			List<HistoriqueIntervention> listeHistoriqueInterventioUtilisateur) {
		this.listeHistoriqueInterventioUtilisateur = listeHistoriqueInterventioUtilisateur;
	}


	public List<HistoriqueDiagnostic> getListeHistoriqueDiagnosticUtilisateur() {
		return listeHistoriqueDiagnosticUtilisateur;
	}


	public void setListeHistoriqueDiagnosticUtilisateur(
			List<HistoriqueDiagnostic> listeHistoriqueDiagnosticUtilisateur) {
		this.listeHistoriqueDiagnosticUtilisateur = listeHistoriqueDiagnosticUtilisateur;
	}


	public List<Favoris> getListeFavorisUtilisateur() {
		return listeFavorisUtilisateur;
	}


	public void setListeFavorisUtilisateur(List<Favoris> listeFavorisUtilisateur) {
		this.listeFavorisUtilisateur = listeFavorisUtilisateur;
	}


	public Utilisateur() {
		super();
	}


	public Utilisateur(Integer idUtilisateur, String loginUtilisateur,
			String nomUtilisateur, String prenomUtilisateur,
			String emailUtilisateur, Integer telUtilisateur,
			String motDePasseUtilisateur, Date dateEnregistrement,
			ProfilUtilisateur profilUtilisateur,
			List<HistoriqueErp> listeHistoriqueErpUtilisateur,
			List<HistoriqueAnomalie> listeHistoriqueAnomalieUtilisateur,
			List<HistoriqueIntervention> listeHistoriqueInterventioUtilisateur,
			List<HistoriqueDiagnostic> listeHistoriqueDiagnosticUtilisateur,
			List<Favoris> listeFavorisUtilisateur) {
		super();
		this.idUtilisateur = idUtilisateur;
		this.loginUtilisateur = loginUtilisateur;
		this.nomUtilisateur = nomUtilisateur;
		this.prenomUtilisateur = prenomUtilisateur;
		this.emailUtilisateur = emailUtilisateur;
		this.telUtilisateur = telUtilisateur;
		this.motDePasseUtilisateur = motDePasseUtilisateur;
		this.dateEnregistrement = dateEnregistrement;
		this.profilUtilisateur = profilUtilisateur;
		this.listeHistoriqueErpUtilisateur = listeHistoriqueErpUtilisateur;
		this.listeHistoriqueAnomalieUtilisateur = listeHistoriqueAnomalieUtilisateur;
		this.listeHistoriqueInterventioUtilisateur = listeHistoriqueInterventioUtilisateur;
		this.listeHistoriqueDiagnosticUtilisateur = listeHistoriqueDiagnosticUtilisateur;
		this.listeFavorisUtilisateur = listeFavorisUtilisateur;
	}


	public String getEmailUtilisateur() {
		return emailUtilisateur;
	}


	public void setEmailUtilisateur(String emailUtilisateur) {
		this.emailUtilisateur = emailUtilisateur;
	}
	
	
	
	
}
