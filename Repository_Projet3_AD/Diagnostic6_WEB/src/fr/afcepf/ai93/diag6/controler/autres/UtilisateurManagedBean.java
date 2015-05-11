package fr.afcepf.ai93.diag6.controler.autres;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import fr.afcepf.ai93.diag6.api.business.autres.IBusinessUtilisateur;


@ManagedBean(name="mbUtilisateur")
@SessionScoped
public class UtilisateurManagedBean implements Serializable {
	
	@EJB
	private IBusinessUtilisateur proxyBusinessUtilisateur;
	
	

	
	
}
