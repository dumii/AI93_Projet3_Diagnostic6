package fr.afcepf.ai93.diag6.controler.gestionnaires;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import fr.afcepf.ai93.diag6.api.business.autres.IBusinessArtisan;
import fr.afcepf.ai93.diag6.api.business.diagnostic.IBusinessAnomalie;
import fr.afcepf.ai93.diag6.api.business.diagnostic.IBusinessDiagnostic;
import fr.afcepf.ai93.diag6.api.business.travaux.IBusinessIntervention;
import fr.afcepf.ai93.diag6.entity.autres.Artisan;
import fr.afcepf.ai93.diag6.entity.autres.Utilisateur;
import fr.afcepf.ai93.diag6.entity.diagnostic.Anomalie;
import fr.afcepf.ai93.diag6.entity.diagnostic.Diagnostic;
import fr.afcepf.ai93.diag6.entity.erp.Erp;
import fr.afcepf.ai93.diag6.entity.travaux.EtatAvancementTravaux;
import fr.afcepf.ai93.diag6.entity.travaux.Intervention;
import fr.afcepf.ai93.diag6.entity.travaux.TypeIntervention;

@ManagedBean(name="mbPlanningTravaux")
@SessionScoped
public class PlanningTravauxManagedBean {

	@EJB
	private IBusinessIntervention proxyIntervention;
	@EJB
	private IBusinessDiagnostic proxyDiagnostic;
	@EJB
	private IBusinessAnomalie proxyAnomalie;	
	@EJB
	private IBusinessArtisan proxyArtisan;
	
	//Utilisateur connecté
	private Utilisateur user;
	
	//Informations relatives à l'ERP
	private Erp monERP;
	private List<Diagnostic> listeDiagnosticERP;
	private List<Anomalie> listeAnomalieAvecInterventionERP;
	private List<Anomalie> listeAnomalieSansInterventionERP;
	private List<Intervention> interventionsERP;
	
	//Informations générales
	private List<TypeIntervention> listeTousTypes;
	
	private SimpleDateFormat formater;
	private SimpleDateFormat shortFormater;
	
	private Date dateDuJour;
	private Date date1;
	private Date date2;
	private Date date3;
	private String moisAnnee;
	
	
	//Initialisation de la liste d'interventions au chargement de la page
	@PostConstruct
	public void init() throws ParseException
	{
		//Initialisation de l'ERP à modifier par la suite
		monERP = new Erp();
		monERP.setIdErp(1);
		
		déterminerDates();	
		
		chargerListeAnomalieEtIntervention();

	}

	public void chargerListeAnomalieEtIntervention() {

		monERP.setListeDiagnosticErp(proxyDiagnostic.);
		
	}

	public void déterminerDates() throws ParseException {

		Calendar c = Calendar.getInstance();
		int mois = c.get(Calendar.MONTH)+1;
		int annee = c.get(Calendar.YEAR);
		moisAnnee = getMonth(mois)+" "+annee;

		c.setTime(new Date());

		if (c.DATE >= 15)
		{
			c.set(Calendar.DAY_OF_MONTH, 15);
			date1 = c.getTime();

			c.set(Calendar.DAY_OF_MONTH, 7);
			date2 = c.getTime();

			c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
			date3 = c.getTime();
		}
		else
		{
			c.set(Calendar.DAY_OF_MONTH, 1);
			date1 = c.getTime();

			c.set(Calendar.DAY_OF_MONTH, 7);
			date2 = c.getTime();

			c.set(Calendar.DAY_OF_MONTH, 15);
			date3 = c.getTime();
		}
	}

	public String getMonth (int month)
	{
		String monthString;

		switch (month) {
		case 1:  monthString = "Janvier";      break;
		case 2:  monthString = "Février";      break;
		case 3:  monthString = "Mars";         break;
		case 4:  monthString = "Avril";         break;
		case 5:  monthString = "Mai";           break;
		case 6:  monthString = "Juin";          break;
		case 7:  monthString = "Juillet";          break;
		case 8:  monthString = "Août";        break;
		case 9:  monthString = "Septembre";     break;
		case 10: monthString = "Octobre";       break;
		case 11: monthString = "Novembre";      break;
		case 12: monthString = "Décembre";      break;
		default: monthString = "Invalid month"; break;
		}

		return monthString;
	}

	public String localisationAnomalie(Anomalie a){
		if(a.getPiece() != null)
		{
			return "Etage " + a.getPiece().getEtage().getNumeroEtage() + " - " + a.getPiece().getEtage().getNomEtage()+ "<br />" 
					+ "Pièce " + a.getPiece().getNumeroPiece()+ " - " + a.getPiece().getFonctionPiece().getLibelleFonctionPiece()+ " - " + a.getPiece().getDenominationPiece(); 
		}
		else
		{
			if(a.getVoirie() !=null)
			{
				return a.getVoirie().getTypeVoirie().getLibelleTypeVoirie()+ "<br />" +
						a.getVoirie().getDesignationVoirie()+ " - " + a.getVoirie().getIntituleVoirie(); 
			}
			else
			{
				if(a.getAcces() != null)
				{
					//return "Bâtiment "+a.getAcces().getBatiment().getNumBatiment()+ "<br />" +
					//"Accès " + a.getAcces().getTypeAcces().getLibelleTypeAcces(); 
					return "Accès " + a.getAcces().getTypeAcces().getLibelleTypeAcces(); 
				}
				else
				{
					if(a.getEscalier() != null){
						//return "Bâtiment "+a.getAcces().getBatiment().getNumBatiment()+ "<br />" +
						//"Escalier "+a.getEscalier().getDenominationEscalier();
						return "Escalier "+a.getEscalier().getDenominationEscalier();
					}
					else
					{
						if(a.getAscenceur() !=null)
						{
							//return "Bâtiment "+a.getAcces().getBatiment().getNumBatiment()+ "<br />" +
							//"Ascenceur "+a.getAscenceur().getDenominationAscenceur(); 
							return "Ascenceur "+a.getAscenceur().getDenominationAscenceur(); 
						}
						else {
							return "Localisation non définie"; 
						}
					}
				}
			}
		}
	}
}
