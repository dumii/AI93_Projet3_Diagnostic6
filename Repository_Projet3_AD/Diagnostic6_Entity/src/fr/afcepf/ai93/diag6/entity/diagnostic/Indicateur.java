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
import javax.persistence.Table;

@Entity
@Table(name="indicateur")
public class Indicateur implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_indicateur")
	private Integer idIndicateur;
	
	@Column(name="libelle_indicateur")
	private String libelleIndicateur;
	
	@Column(name="valeur_indicateur")
	private Integer valeurIndicateur;
	
	@OneToMany(mappedBy="indicateur")
	private List<Anomalie> listeAnomaliesIndicateur;
	
	@ManyToOne
	@JoinColumn(name="id_type_diagnostic")
	private TypeDiagnostic typeDiagnostic;

	public Integer getIdIndicateur() {
		return idIndicateur;
	}

	public void setIdIndicateur(Integer idIndicateur) {
		this.idIndicateur = idIndicateur;
	}

	public String getLibelleIndicateur() {
		return libelleIndicateur;
	}

	public void setLibelleIndicateur(String libelleIndicateur) {
		this.libelleIndicateur = libelleIndicateur;
	}

	public Integer getValeurIndicateur() {
		return valeurIndicateur;
	}

	public void setValeurIndicateur(Integer valeurIndicateur) {
		this.valeurIndicateur = valeurIndicateur;
	}

	public List<Anomalie> getListeAnomaliesIndicateur() {
		return listeAnomaliesIndicateur;
	}

	public void setListeAnomaliesIndicateur(List<Anomalie> listeAnomaliesIndicateur) {
		this.listeAnomaliesIndicateur = listeAnomaliesIndicateur;
	}

	public TypeDiagnostic getTypeDiagnostic() {
		return typeDiagnostic;
	}

	public void setTypeDiagnostic(TypeDiagnostic typeDiagnostic) {
		this.typeDiagnostic = typeDiagnostic;
	}

	public Indicateur() {
		super();
	}

	public Indicateur(Integer idIndicateur, String libelleIndicateur,
			Integer valeurIndicateur, List<Anomalie> listeAnomaliesIndicateur,
			TypeDiagnostic typeDiagnostic) {
		super();
		this.idIndicateur = idIndicateur;
		this.libelleIndicateur = libelleIndicateur;
		this.valeurIndicateur = valeurIndicateur;
		this.listeAnomaliesIndicateur = listeAnomaliesIndicateur;
		this.typeDiagnostic = typeDiagnostic;
	}
	
	
}
