package fr.afcepf.ai93.diag6.entity.travaux;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder.In;

@Entity
@Table(name="etat_avancement_travaux")
public class EtatAvancementTravaux implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_etat_avancement")
	private Integer idEtatAvancement;
	
	@Column(name="intitule_avancement")
	private String intituleEtatAvancement;
	
	@OneToMany(mappedBy="etatAvancementTravaux")
	private List<Intervention> listeInterventionsEtatAvancement;

	public Integer getIdEtatAvancement() {
		return idEtatAvancement;
	}

	public void setIdEtatAvancement(Integer idEtatAvancement) {
		this.idEtatAvancement = idEtatAvancement;
	}

	public String getIntituleEtatAvancement() {
		return intituleEtatAvancement;
	}

	public void setIntituleEtatAvancement(String intituleEtatAvancement) {
		this.intituleEtatAvancement = intituleEtatAvancement;
	}

	public List<Intervention> getListeInterventionsEtatAvancement() {
		return listeInterventionsEtatAvancement;
	}

	public void setListeInterventionsEtatAvancement(
			List<Intervention> listeInterventionsEtatAvancement) {
		this.listeInterventionsEtatAvancement = listeInterventionsEtatAvancement;
	}

	public EtatAvancementTravaux() {
		super();
	}

	public EtatAvancementTravaux(Integer idEtatAvancement,
			String intituleEtatAvancement,
			List<Intervention> listeInterventionsEtatAvancement) {
		super();
		this.idEtatAvancement = idEtatAvancement;
		this.intituleEtatAvancement = intituleEtatAvancement;
		this.listeInterventionsEtatAvancement = listeInterventionsEtatAvancement;
	}
	
	
	
}
