package fr.afcepf.ai93.diag6.controler.navigation;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class testBean {

	//Propri�t� qui va r�cup�rer l'id pass� en requ�te
	@ManagedProperty(value="#{param.pageId}")
	private int pageId;
}
