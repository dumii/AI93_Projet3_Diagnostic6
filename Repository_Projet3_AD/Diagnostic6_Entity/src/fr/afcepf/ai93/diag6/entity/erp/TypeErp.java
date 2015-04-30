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
@Table(name="type_erp")
public class TypeErp implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_type_erp")
	private Integer idTypeErp;
	
	@Column(name="denomination_type_erp")
	private String denominationTypeErp;
	
	@Column(name="type_erp")
	private String typeErp;
	
	@Column(name="type_erp_court")
	private String typeErpCourt;
	
	@OneToMany(mappedBy="typeErp")
	private List<Erp> listeErpTypeErp;

	
	public Integer getIdTypeErp() {
		return idTypeErp;
	}

	public void setIdTypeErp(Integer idTypeErp) {
		this.idTypeErp = idTypeErp;
	}

	public String getDenominationTypeErp() {
		return denominationTypeErp;
	}

	public void setDenominationTypeErp(String denominationTypeErp) {
		this.denominationTypeErp = denominationTypeErp;
	}

	public String getTypeErp() {
		return typeErp;
	}

	public void setTypeErp(String typeErp) {
		this.typeErp = typeErp;
	}

	public String getTypeErpCourt() {
		return typeErpCourt;
	}

	public void setTypeErpCourt(String typeErpCourt) {
		this.typeErpCourt = typeErpCourt;
	}

	public List<Erp> getListeErpTypeErp() {
		return listeErpTypeErp;
	}

	public void setListeErpTypeErp(List<Erp> listeErpTypeErp) {
		this.listeErpTypeErp = listeErpTypeErp;
	}

	public TypeErp() {
		super();
	}

	public TypeErp(Integer idTypeErp, String denominationTypeErp,
			String typeErp, String typeErpCourt, List<Erp> listeErpTypeErp) {
		super();
		this.idTypeErp = idTypeErp;
		this.denominationTypeErp = denominationTypeErp;
		this.typeErp = typeErp;
		this.typeErpCourt = typeErpCourt;
		this.listeErpTypeErp = listeErpTypeErp;
	}
	
	
	
	
}
