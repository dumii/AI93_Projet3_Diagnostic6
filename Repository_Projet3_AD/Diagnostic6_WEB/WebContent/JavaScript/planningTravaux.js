/**
 * Fonction 1 : masquer/afficher les enfants (et/ou leurs enfants) d'un parent
 * Note : la classe ClasseBidon sert uniquement à faire fonctionner le javascript pour éviter les exceptions nulles 
 */

function afficherCacher(maClasse)
{
	var elements = document.getElementById("monTableau").rows ;
	/*
	 * Détermination de l'index de la première ligne du tableau contenant le nom de ma classe
	 * Le boolean monBoolean sert à sortir de la boucle lorsque celui est true (ligne trouvée)
	 */
	var monBoolean = new Boolean(false) ;
	var indexFirstRow = 0;	
	while (monBoolean == false)
	{
		if (elements[indexFirstRow].className.indexOf(maClasse) > -1)
		{
			monBoolean = true;
		}
		else
		{
			indexFirstRow ++ ;
		}
	}

	/*
	 * Le boolean firstRowAffichee sert à déterminer si la première ligne contenant le nom de ma classe est affichée (true) ou non (false)
	 */
	var firstRowAffichee = new Boolean(true);	
	if (elements[indexFirstRow].style.display == "none")
		{
		firstRowAffichee = false;
		}
	
	/*
	 * Si la première ligne est affichée (true) alors on masque toutes les lignes contenant la classe, sinon (false) on les affiche
	 */
	for(var i=0; i<elements.length; i++)
	{
		if (elements[i].className.indexOf(maClasse) > -1)
		{
			if (firstRowAffichee == false)
			{
				elements[i].style.display = "";
			}
			else
			{
				elements[i].style.display = "none";
			}
		}
	}
}

/**
 * Fonction 2 : pour rendre des inputs modifiables (disabled = false) ou non modifiables (disabled = true)
 */

function disabledEnabled(monID)
{
	if (document.getElementById(monID).disabled == true)
		{
			document.getElementById(monID).disabled = false;
		}
	else
		{
			document.getElementById(monID).disabled = true;
		}
}

/**
 * Fonction 3 : dessiner et positionner les rectangles représentant les interventions
 */

function dessinerRectangle(dateDebut, dateFin, idIntervention)
{	
	var debutIntervention = new Date(dateDebut);
	var finIntervention = new Date(dateFin);
	
	//La récupération des dates restera à définir le moment venu
	var debutTableau = new Date (2015,03,01) ;
	var finTableau = new Date (2015,07,31) ;
	var dureeTableau = ((finTableau - debutTableau)/(24*3600*1000));

	//durée de l’intervention en jours		
	var dureeIntervention = parseInt(
			(finIntervention - debutIntervention)/(24*3600*1000));
	
	var element = document.getElementById(idIntervention);
	
	if ((finIntervention <= debutTableau)  || (finTableau <= debutIntervention))
	{
		document.getElementById(idIntervention).style.marginLeft = "0%" ;
		document.getElementById(idIntervention).style.width = "0%" ;
	}
	else
	{
		if (debutIntervention <= debutTableau)
		{
			element.style.marginLeft = "0%" ;
		}
		else
		{			
			//L’écart en jours
			var ecart = parseInt(
					(debutIntervention - debutTableau)/(24*3600*1000));
			element.style.marginLeft = (ecart * 100 / dureeTableau)+"%";
		}



		if (finTableau <= finIntervention)
		{
			var marge = (100-document.getElementById(idIntervention).style.marginLeft) ;
			document.getElementById(idIntervention).style.width = marge+"%" ;
		}
		else
		{
			document.getElementById(idIntervention).style.width = (dureeIntervention*100 /dureeTableau)+"%" ;
		}
	}
}
