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

@Entity
@Table(name="profil_utilisateur")
public class ProfilUtilisateur implements Serializable {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_profil")
	private Integer idProfil;
	
	@Column(name="nom_profil")
	private String nomProfil;
	
	
	@OneToMany(mappedBy="profilUtilisateur")
	private List<Utilisateur> listeUtilisateurProfilUtilisateur;

	
	public Integer getIdProfil() {
		return idProfil;
	}

	public void setIdProfil(Integer idProfil) {
		this.idProfil = idProfil;
	}

	public String getNomProfil() {
		return nomProfil;
	}

	public void setNomProfil(String nomProfil) {
		this.nomProfil = nomProfil;
	}

	public List<Utilisateur> getListeUtilisateurProfilUtilisateur() {
		return listeUtilisateurProfilUtilisateur;
	}

	public void setListeUtilisateurProfilUtilisateur(
			List<Utilisateur> listeUtilisateurProfilUtilisateur) {
		this.listeUtilisateurProfilUtilisateur = listeUtilisateurProfilUtilisateur;
	}

	public ProfilUtilisateur() {
		super();
	}

	public ProfilUtilisateur(Integer idProfil, String nomProfil,
			List<Utilisateur> listeUtilisateurProfilUtilisateur) {
		super();
		this.idProfil = idProfil;
		this.nomProfil = nomProfil;
		this.listeUtilisateurProfilUtilisateur = listeUtilisateurProfilUtilisateur;
	}
	
	
	
	
}
