package fr.afcepf.ai93.diag6.controler.gestionnaires;

import fr.afcepf.ai93.diag6.entity.erp.Erp;

public class Chantier {
	private int idChantier; 
	private String nomChantier;
	private Erp erpChantier;
	
	public int getIdChantier() {
		return idChantier;
	}
	public void setIdChantier(int idChantier) {
		this.idChantier = idChantier;
	}
	public String getNomChantier() {
		return nomChantier;
	}
	public void setNomChantier(String nomChantier) {
		this.nomChantier = nomChantier;
	}
	public Erp getErpChantier() {
		return erpChantier;
	}
	public void setErpChantier(Erp erpChantier) {
		this.erpChantier = erpChantier;
	}
	public Chantier() {
		
	} 
	public Chantier(int idChantier, String nomChantier, Erp erpChantier) {
		super();
		this.idChantier = idChantier;
		this.nomChantier = nomChantier;
		this.erpChantier = erpChantier;
	}
}
