package fr.afcepf.ai93.diag6.entity.erp;

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
@Table(name="categorie_erp")
public class CategorieErp implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_categorie_erp")
	private Integer idCategorieErp;
	
	@Column(name="denomination_categorie_erp")
	private String denominationCategorieErp;
	
	@OneToMany(mappedBy="categorieErp")
	private List<Erp> listeErpCategorieErp;

	
	public Integer getIdCategorieErp() {
		return idCategorieErp;
	}

	public void setIdCategorieErp(Integer idCategorieErp) {
		this.idCategorieErp = idCategorieErp;
	}

	public String getDenominationCategorieErp() {
		return denominationCategorieErp;
	}

	public void setDenominationCategorieErp(String denominationCategorieErp) {
		this.denominationCategorieErp = denominationCategorieErp;
	}

	public List<Erp> getListeErpCategorieErp() {
		return listeErpCategorieErp;
	}

	public void setListeErpCategorieErp(List<Erp> listeErpCategorieErp) {
		this.listeErpCategorieErp = listeErpCategorieErp;
	}

	public CategorieErp() {
		super();
	}

	public CategorieErp(Integer idCategorieErp,
			String denominationCategorieErp, List<Erp> listeErpCategorieErp) {
		super();
		this.idCategorieErp = idCategorieErp;
		this.denominationCategorieErp = denominationCategorieErp;
		this.listeErpCategorieErp = listeErpCategorieErp;
	}
	
	
	
}
