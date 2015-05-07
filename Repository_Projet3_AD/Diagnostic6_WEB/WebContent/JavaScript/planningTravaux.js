/**
 * Fonction 1 : masquer/afficher les enfants (et/ou leurs enfants) d'un parent
 * Note : la classe ClasseBidon sert uniquement � faire fonctionner le javascript pour �viter les exceptions nulles 
 */

function afficherCacher(monID)
{
	var elements = document.getElementById("monTableau").rows ;
	/*
	 * D�termination de l'index de la premi�re ligne du tableau contenant le nom de ma classe
	 * Le boolean monBoolean sert � sortir de la boucle lorsque celui est true (ligne trouv�e)
	 */
	var monBoolean = new Boolean(false) ;
	var indexFirstRow = 0;	
	while (monBoolean == false)
	{
		if (elements[indexFirstRow].id.indexOf(monID) > -1)
		{
			monBoolean = true;
		}
		else
		{
			indexFirstRow ++ ;
		}
	}

	/*
	 * Le boolean firstRowAffichee sert � d�terminer si la premi�re ligne contenant le nom de ma classe est affich�e (true) ou non (false)
	 */
	var firstRowAffichee = new Boolean(true);	
	if (elements[indexFirstRow].style.display == "none")
		{
		firstRowAffichee = false;
		}
	
	/*
	 * Si la premi�re ligne est affich�e (true) alors on masque toutes les lignes contenant la classe, sinon (false) on les affiche
	 */
	for(var i=0; i<elements.length; i++)
	{
		if (elements[i].id.indexOf(monID) > -1)
		{
			if (firstRowAffichee == false)
			{
				if (elements[i].className != "ligneOrange")
					{
					elements[i].style.display = "";
					}
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

	if (document.getElementById("datepickerDebut - "+monID).disabled == true)
		{
			document.getElementById("datepickerDebut - "+monID).disabled = false;
			document.getElementById("datepickerFin - "+monID).disabled = false;
			document.getElementById("cout - "+monID).disabled = false;
			document.getElementById("cout - "+monID).style.color = "black";
			document.getElementById("boutonModifier - "+monID).value = "Annuler";
			document.getElementById("boutonValider - "+monID).style.display = "";
		}
	else
		{
			document.getElementById("datepickerDebut - "+monID).disabled = true;
			document.getElementById("datepickerFin - "+monID).disabled = true;
			document.getElementById("cout - "+monID).disabled = true;
			document.getElementById("cout - "+monID).style.color = "#CDC0B9";
			document.getElementById("boutonModifier - "+monID).value = "Modifier";
			document.getElementById("boutonValider - "+monID).style.display = "none";
		}
}

/**
 * Fonction 3 : dessiner et positionner les rectangles repr�sentant les interventions
 */

function dessinerRectangle(element)
{	
	//R�cup�ration des donn�es li�es � l'intervention (dur�es des interventions en jours)
	var idIntervention = element.id;
	var dateDebut = document.getElementById("datepickerDebut - "+idIntervention).value.split("/");
	var debutIntervention = new Date(parseInt(dateDebut[2], 10),
	                  				 parseInt(dateDebut[1], 10) - 1,
	                  				 parseInt(dateDebut[0], 10));
	
	var dateFin = document.getElementById("datepickerFin - "+idIntervention).value.split("/");
	var finIntervention = new Date(parseInt(dateFin[2], 10),
				 				   parseInt(dateFin[1], 10) - 1,
				 				   parseInt(dateFin[0], 10));		
	
	var dureeIntervention = parseInt(
			(finIntervention - debutIntervention)/(24*3600*1000));
	
	//La r�cup�ration des dates restera � d�finir le moment venu
	var debutTableau = new Date (2015,04,15) ;
	var finTableau = new Date (2015,04,31) ;
	var dureeTableau = ((finTableau - debutTableau)/(24*3600*1000));

	//D�clarations des variables margeGauche et Taille :
	var margeGauche = 0;
	var taille = 0;
	
	if ((finIntervention <= debutTableau)  || (finTableau <= debutIntervention))
	{
		element.style.display = "none";
	}
	else
	{
		element.style.display = "";
		
		if (debutIntervention > debutTableau)
		{		
			//L��cart en jours
			var ecart = parseInt(
					(debutIntervention - debutTableau)/(24*3600*1000));
			margeGauche = (ecart * 100 / dureeTableau);
		}
		console.log("ID : "+idIntervention+" ecart : " + ecart);



		if (finTableau <= finIntervention)
		{
			taille = 100-margeGauche;
		}
		else
		{
			if (debutIntervention < debutTableau)
			{
				var joursACacher = debutTableau - debutIntervention;
				taille = ((dureeIntervention-joursACacher)*100 / dureeTableau) ;
			}
			else
			{
				taille = (dureeIntervention*100 / dureeTableau) ;
			}
		}
		
		//On applique la marge gauche et la taille au rectangle
		element.style.marginLeft = margeGauche + "%" ;
		element.style.width = taille + "%";
	}
}

