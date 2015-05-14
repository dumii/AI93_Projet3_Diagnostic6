package fr.afcepf.ai93.diag6.controler.autres;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import fr.afcepf.ai93.diag6.api.business.autres.IBusinessExpert;
import fr.afcepf.ai93.diag6.entity.autres.Expert;
import fr.afcepf.ai93.diag6.entity.autres.Localisation;

@ManagedBean(name="mbExpert")
@SessionScoped
public class ExpertManagedBean implements Serializable {

	@EJB
	private IBusinessExpert proxyBusinessExpert;
	
	private Expert expert = new Expert();
	private List<Expert> listeExpert;
	private List<Localisation> listeLocalisation;
	private Localisation localisation = new Localisation();
	
	@PostConstruct
	public void init(){
		listeExpert = proxyBusinessExpert.recupereToutExpert();
		localisation.getListeExpertsLocalisation();
	}
	
	public void enregistrerExpert(){
		System.out.println("111111111111111111111 je rentre dans l'ajout ArtisanManagedBean 11111111111111111111111");
		proxyBusinessExpert.ajouterExpert(expert);
	}
	
	
//////////////////////////////GETTER / SETTER ///////////////////////////////

	public IBusinessExpert getProxyBusinessExpert() {
		return proxyBusinessExpert;
	}
	public void setProxyBusinessExpert(IBusinessExpert proxyBusinessExpert) {
		this.proxyBusinessExpert = proxyBusinessExpert;
	}
	public Expert getExpert() {
		return expert;
	}
	public void setExpert(Expert expert) {
		this.expert = expert;
	}
	public List<Expert> getListeExpert() {
		return listeExpert;
	}
	public void setListeExpert(List<Expert> listeExpert) {
		this.listeExpert = listeExpert;
	}
	public List<Localisation> getListeLocalisation() {
		return listeLocalisation;
	}
	public void setListeLocalisation(List<Localisation> listeLocalisation) {
		this.listeLocalisation = listeLocalisation;
	}
	public Localisation getLocalisation() {
		return localisation;
	}
	public void setLocalisation(Localisation localisation) {
		this.localisation = localisation;
	}
	
}
