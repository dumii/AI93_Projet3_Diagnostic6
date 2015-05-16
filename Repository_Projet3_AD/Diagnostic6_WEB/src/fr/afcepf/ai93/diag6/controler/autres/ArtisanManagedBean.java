package fr.afcepf.ai93.diag6.controler.autres;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import fr.afcepf.ai93.diag6.api.business.autres.IBusinessArtisan;
import fr.afcepf.ai93.diag6.api.business.travaux.IBusinessIntervention;
import fr.afcepf.ai93.diag6.entity.autres.Artisan;
import fr.afcepf.ai93.diag6.entity.autres.Localisation;
import fr.afcepf.ai93.diag6.entity.autres.TypeArtisan;
import fr.afcepf.ai93.diag6.entity.travaux.TypeIntervention;

@ManagedBean(name="mbArtisan")
@SessionScoped
public class ArtisanManagedBean implements Serializable {
	
	@EJB
	private IBusinessArtisan proxyBusinessArtisan;
	@EJB
	private IBusinessIntervention proxyBusinessIntervention;
	
	private Artisan artisan = new Artisan();
	private List<Artisan> listArtisan;
	private List<TypeIntervention> listeTypes;
	private List<Localisation> listeLocalisation;
	private Localisation localisation = new Localisation();
	
	private int idTypeArtiSelect;
	
	@PostConstruct
	public void init(){
		
		listeTypes = proxyBusinessIntervention.recupererTousTypesIntervention();
		listArtisan = proxyBusinessArtisan.recupererToutArtisan();
		localisation.getListeArtisansLocalisation();
	}
	
	public void enregistrerArtisan(){
		artisan.setLocalisation(localisation);
		proxyBusinessArtisan.ajouterArtisan(artisan);		
		init();
	}
	
	public String supprimerArtisan(Artisan arti){
		proxyBusinessArtisan.supprimerArtisan(arti);
		init();
		return "suppression réalisée";
	}
	

	
//////////////////////////////GETTER / SETTER ///////////////////////////////

	public IBusinessArtisan getProxyBusinessArtisan() {
		return proxyBusinessArtisan;
	}
	public void setProxyBusinessArtisan(IBusinessArtisan proxyBusinessArtisan) {
		this.proxyBusinessArtisan = proxyBusinessArtisan;
	}
	public IBusinessIntervention getProxyBusinessIntervention() {
		return proxyBusinessIntervention;
	}
	public void setProxyBusinessIntervention(
			IBusinessIntervention proxyBusinessIntervention) {
		this.proxyBusinessIntervention = proxyBusinessIntervention;
	}
	public Artisan getArtisan() {
		return artisan;
	}
	public void setArtisan(Artisan artisan) {
		this.artisan = artisan;
	}
	public List<Artisan> getListArtisan() {
		return listArtisan;
	}
	public void setListArtisan(List<Artisan> listArtisan) {
		this.listArtisan = listArtisan;
	}
	public List<TypeIntervention> getListeTypes() {
		return listeTypes;
	}
	public void setListeTypes(List<TypeIntervention> listeTypes) {
		this.listeTypes = listeTypes;
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

	public int getIdTypeArtiSelect() {
		return idTypeArtiSelect;
	}

	public void setIdTypeArtiSelect(int idTypeArtiSelect) {
		this.idTypeArtiSelect = idTypeArtiSelect;
	}
	
//	public void recupArtisan()
//	{
//		System.out.println("1111111111111111111111 je suis entré dans ma méthode 1111111111111111111111111");
//		
//		listTypeArtisan = proxyBusinessArtisan.recupererTypeArtisan();
//		
//		listArtisan = proxyBusinessArtisan.recupererToutArtisan();
//		System.out.println("2222222222222222222222 je suis sorti de ma méthode 222222222222222222222222");
//	}
//
//	public String avoirListeArtisans()
//	{
//		type.setIdTypeIntervention(idType);
//		System.out.println("ManagedBean, idType : " + idType);
//		listArtisan = proxyBusinessArtisan.recupererArtisansParTypeIntervention(type);
//		return "";
//	}
	


	
}
