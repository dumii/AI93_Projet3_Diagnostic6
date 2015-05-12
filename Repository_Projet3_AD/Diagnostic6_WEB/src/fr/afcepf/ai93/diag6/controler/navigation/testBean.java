package fr.afcepf.ai93.diag6.controler.navigation;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class testBean {

	//Propriété qui va récupérer l'id passé en requête
	@ManagedProperty(value="#{param.pageId}")
	private int pageId;
}
