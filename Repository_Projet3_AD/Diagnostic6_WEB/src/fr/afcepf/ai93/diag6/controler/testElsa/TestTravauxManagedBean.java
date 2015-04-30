package fr.afcepf.ai93.diag6.controler.testElsa;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import fr.afcepf.ai93.diag6.api.business.travaux.IBusinessIntervention;
import fr.afcepf.ai93.diag6.entity.travaux.Intervention;

@ManagedBean(name="mbTestTravaux")
@SessionScoped
public class TestTravauxManagedBean {

	@EJB
	private IBusinessIntervention proxyBusiness;
	private List<Intervention> travaux;
	private Intervention intervention;
	private int idIntervention;
	
	@PostConstruct
	public void init()
	{
		travaux = proxyBusiness.recupereToutesIntervention();
	}
	
	public String rechercher()
	{
		intervention = proxyBusiness.recupereIntervention(idIntervention);
		System.out.println("Hello"+intervention.getCoutIntervention());
		return "";
	}
	
	public IBusinessIntervention getProxyBusiness() {
		return proxyBusiness;
	}
	public void setProxyBusiness(IBusinessIntervention proxyBusiness) {
		this.proxyBusiness = proxyBusiness;
	}
	public List<Intervention> getTravaux() {
		return travaux;
	}
	public void setTravaux(List<Intervention> travaux) {
		this.travaux = travaux;
	}
	public Intervention getIntervention() {
		return intervention;
	}
	public void setIntervention(Intervention intervention) {
		this.intervention = intervention;
	}
	public int getIdIntervention() {
		return idIntervention;
	}
	public void setIdIntervention(int idIntervention) {
		this.idIntervention = idIntervention;
	}
}
