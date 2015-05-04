package fr.afcepf.ai93.diag6.entity.erp;

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

import fr.afcepf.ai93.diag6.entity.diagnostic.Anomalie;


@Entity
@Table(name="voirie")
public class Voirie implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_voirie")
	private Integer idVoirie;
	
	@Column(name="designation_voirie")
	private String designationVoirie;
	

	@Column(name="emplacement_voirie")
	private String intituleVoirie;

	
	@ManyToOne
	@JoinColumn(name="no_erp")
	private Erp erp;
	
	@ManyToOne
	@JoinColumn(name="id_type_voirie")
	private TypeVoirie typeVoirie;
	
	@OneToMany(mappedBy="voirie")
	private List<Anomalie> listeAnomaliesVoirie;

	
	public Integer getIdVoirie() {
		return idVoirie;
	}

	public void setIdVoirie(Integer idVoirie) {
		this.idVoirie = idVoirie;
	}

	public String getDesignationVoirie() {
		return designationVoirie;
	}

	public void setDesignationVoirie(String designationVoirie) {
		this.designationVoirie = designationVoirie;
	}

	public String getIntituleVoirie() {
		return intituleVoirie;
	}

	public void setIntituleVoirie(String intituleVoirie) {
		this.intituleVoirie = intituleVoirie;
	}

	public Erp getErp() {
		return erp;
	}

	public void setErp(Erp erp) {
		this.erp = erp;
	}

	public TypeVoirie getTypeVoirie() {
		return typeVoirie;
	}

	public void setTypeVoirie(TypeVoirie typeVoirie) {
		this.typeVoirie = typeVoirie;
	}

	public List<Anomalie> getListeAnomaliesVoirie() {
		return listeAnomaliesVoirie;
	}

	public void setListeAnomaliesVoirie(List<Anomalie> listeAnomaliesVoirie) {
		this.listeAnomaliesVoirie = listeAnomaliesVoirie;
	}

	public Voirie() {
		super();
	}

	public Voirie(Integer idVoirie, String designationVoirie, Erp erp, TypeVoirie typeVoirie,
			List<Anomalie> listeAnomaliesVoirie) {
		super();
		this.idVoirie = idVoirie;
		this.designationVoirie = designationVoirie;
		this.erp = erp;
		this.typeVoirie = typeVoirie;
		this.listeAnomaliesVoirie = listeAnomaliesVoirie;
	}
	
	
	
	
}
