package fr.afcepf.ai93.diag6.business.diagnostic;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import fr.afcepf.ai93.diag6.api.business.diagnostic.IBusinessAnomalie;
import fr.afcepf.ai93.diag6.api.data.diagnostic.IDaoAnomalie;
import fr.afcepf.ai93.diag6.api.data.diagnostic.IDaoDiagnostic;
import fr.afcepf.ai93.diag6.api.data.diagnostic.IDaoHistoriqueAnomalie;
import fr.afcepf.ai93.diag6.api.data.diagnostic.IDaoIndicateur;
import fr.afcepf.ai93.diag6.entity.autres.Utilisateur;
import fr.afcepf.ai93.diag6.entity.diagnostic.Anomalie;
import fr.afcepf.ai93.diag6.entity.diagnostic.Diagnostic;
import fr.afcepf.ai93.diag6.entity.diagnostic.HistoriqueAnomalie;
import fr.afcepf.ai93.diag6.entity.diagnostic.Indicateur;
import fr.afcepf.ai93.diag6.entity.travaux.Intervention;

@Stateless
@Remote(IBusinessAnomalie.class)

public class BusinessAnomalieImpl implements IBusinessAnomalie {
	
	@EJB
	private IDaoAnomalie proxyAnomalie;
	@EJB
	private IDaoHistoriqueAnomalie proxyHistorique;
	@EJB
	private IDaoDiagnostic proxyDiagnostic;
	@EJB
	private IDaoIndicateur proxyIndicateur;

	@Override
	public String ajouterAnomalie(Anomalie anomalie) {
		//Récupération du diagnostic sur lequel l'utilisateur souhaite ajouter l'anomalie
		Diagnostic diagnostic = proxyDiagnostic.recupereDiagnostic(anomalie.getDiagnostic().getIdDiagnostic());
		
		//Ajout autorisé uniquement si le statut du diagnostic n'est pas traité
		//0 : non traité
		//1 : traité
		if (diagnostic.getTraite() != 1)
		{
			proxyAnomalie.ajouterAnomalie(anomalie);
			return "Anomalie ajoutée avec succès";
		}
		else
		{
			return "L'ajout n'est pas autorisé sur un diagnostic traité";
		}		
	}

	@Override
	public String modifierAnomalie(Anomalie anomalie, Utilisateur user) {

		Anomalie anomalieInitiale = proxyAnomalie.recupereAnomalie(anomalie.getIdAnomalie());		
		
		//l'indicateur d'une anomalie ne peut que être amélioré
		//Autrement dit, la valeur de l'indicateur ne peut que augmenter
		int valeurIndicateurInitiale = anomalieInitiale.getIndicateur().getValeurIndicateur();
		int valeurIndicateurNouvelle = anomalie.getIndicateur().getValeurIndicateur();
		
		if (valeurIndicateurInitiale <= valeurIndicateurNouvelle)
		{
			proxyAnomalie.modifierAnomalie(anomalie, user);
			proxyHistorique.historiser(anomalieInitiale, anomalie, user);
			return "Modification enregistrée avec succès";
		}
		else
		{		
			return "Modification illégale de l'indicateur de l'anomalie";
		}
	}

	@Override
	public String supprimerAnomalie(Anomalie anomalie, Utilisateur user) {
		Anomalie anomalieInitiale = proxyAnomalie.recupereAnomalie(anomalie.getIdAnomalie());		
		proxyAnomalie.supprimerAnomalie(anomalie);
		//proxyHistorique.historiserSuppression(anomalieInitiale, anomalie, user);
		return "Suppression réalisée"; 
	}

	@Override
	public List<HistoriqueAnomalie> recupereToutHistoriqueAnomalie() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Indicateur> recupereIndicateur() {
		return proxyIndicateur.recupereIndicateur();
	}

	@Override
	public Anomalie recupereAnomalie(int idAnomalie) {
		return proxyAnomalie.recupereAnomalie(idAnomalie);
	}

	@Override
	public List<Anomalie> rechercheAnomaliesErp(String nomERP) {
		return proxyAnomalie.rechercheAnomaliesErp(nomERP);
	}


	@Override
	public List<Anomalie> recupereAnomalieParDiagnostic(int idDiagnostic) {
		return proxyAnomalie.recupereAnomalieParDiagnostic(idDiagnostic);
	}

	@Override
	public List<Anomalie> recupereToutAnomalie() {
		return proxyAnomalie.recupereToutAnomalie();
	}

	public IDaoAnomalie getProxyAnomalie() {
		return proxyAnomalie;
	}

	public void setProxyAnomalie(IDaoAnomalie proxyAnomalie) {
		this.proxyAnomalie = proxyAnomalie;
	}

	public IDaoHistoriqueAnomalie getProxyHistorique() {
		return proxyHistorique;
	}

	public void setProxyHistorique(IDaoHistoriqueAnomalie proxyHistorique) {
		this.proxyHistorique = proxyHistorique;
	}

	public IDaoDiagnostic getProxyDiagnostic() {
		return proxyDiagnostic;
	}

	public void setProxyDiagnostic(IDaoDiagnostic proxyDiagnostic) {
		this.proxyDiagnostic = proxyDiagnostic;
	}

	public IDaoIndicateur getProxyIndicateur() {
		return proxyIndicateur;
	}

	public void setProxyIndicateur(IDaoIndicateur proxyIndicateur) {
		this.proxyIndicateur = proxyIndicateur;
	}

	@Override
	public Indicateur recupereIndicateurParID(int idIndicateur) {
		return proxyIndicateur.recupereIndicateurParID(idIndicateur);
	}
}
