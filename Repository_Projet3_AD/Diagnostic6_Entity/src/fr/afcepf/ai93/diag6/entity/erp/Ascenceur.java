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
@Table(name="Ascenceurs")
public class Ascenceur implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_ascenceur")
	private Integer idAscenceur;
	
	@Column(name="denomination_ascenceur")
	private String denominationAscenceur;
	
	@OneToMany(mappedBy="ascenceur")
	private List<Anomalie> listeAnomaliesAscenceur;
	
	@ManyToOne
	@JoinColumn(name="id_batiment")
	private Batiment batiment;
	
	
}
