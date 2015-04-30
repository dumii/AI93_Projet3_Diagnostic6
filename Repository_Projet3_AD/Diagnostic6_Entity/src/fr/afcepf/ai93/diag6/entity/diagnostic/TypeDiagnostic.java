package fr.afcepf.ai93.diag6.entity.diagnostic;

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
@Table(name="type_diagnostic")
public class TypeDiagnostic implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_type_diagnostic")
	private Integer idTypeDiagnostic;
	
	@Column(name="nom_type")
	private String nomType;
	
	@OneToMany(mappedBy="typeDiagnostic")
	private List<Diagnostic> listeDiagnosticsTypeDiagnostic;
	
	@OneToMany(mappedBy="typeDiagnostic")
	private List<Indicateur> listeIndicateursTypeDiagnostic;

	public Integer getIdTypeDiagnostic() {
		return idTypeDiagnostic;
	}

	public void setIdTypeDiagnostic(Integer idTypeDiagnostic) {
		this.idTypeDiagnostic = idTypeDiagnostic;
	}

	public String getNomType() {
		return nomType;
	}

	public void setNomType(String nomType) {
		this.nomType = nomType;
	}

	public List<Diagnostic> getListeDiagnosticsTypeDiagnostic() {
		return listeDiagnosticsTypeDiagnostic;
	}

	public void setListeDiagnosticsTypeDiagnostic(
			List<Diagnostic> listeDiagnosticsTypeDiagnostic) {
		this.listeDiagnosticsTypeDiagnostic = listeDiagnosticsTypeDiagnostic;
	}

	public List<Indicateur> getListeIndicateursTypeDiagnostic() {
		return listeIndicateursTypeDiagnostic;
	}

	public void setListeIndicateursTypeDiagnostic(
			List<Indicateur> listeIndicateursTypeDiagnostic) {
		this.listeIndicateursTypeDiagnostic = listeIndicateursTypeDiagnostic;
	}

	public TypeDiagnostic() {
		super();
	}

	public TypeDiagnostic(Integer idTypeDiagnostic, String nomType,
			List<Diagnostic> listeDiagnosticsTypeDiagnostic,
			List<Indicateur> listeIndicateursTypeDiagnostic) {
		super();
		this.idTypeDiagnostic = idTypeDiagnostic;
		this.nomType = nomType;
		this.listeDiagnosticsTypeDiagnostic = listeDiagnosticsTypeDiagnostic;
		this.listeIndicateursTypeDiagnostic = listeIndicateursTypeDiagnostic;
	}
	
	
	
}
