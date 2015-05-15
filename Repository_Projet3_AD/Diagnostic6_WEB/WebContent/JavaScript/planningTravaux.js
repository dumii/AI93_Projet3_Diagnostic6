/**
 * Fonction 1 : masquer/afficher les enfants (et/ou leurs enfants) d'un parent
 * Note : la classe ClasseBidon sert uniquement à faire fonctionner le javascript pour éviter les exceptions nulles 
 */

function afficherCacher(monID)
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

	if (document.getElementById("boutonValider - "+monID).style.display == "none")
		{
			document.getElementById("boutonModifier - "+monID).value = "Annuler";
			document.getElementById("boutonValider - "+monID).style.display = "";
		}
	else
		{
			document.getElementById("boutonModifier - "+monID).value = "Modifier";
			document.getElementById("boutonValider - "+monID).style.display = "none";
		}
}

/**
 * Fonction 3 : dessiner et positionner les rectangles représentant les interventions
 */

function dessinerRectangle(element)
{	
	//Récupération des données liées à l'intervention (durées des interventions en jours)
	var idIntervention = element.id;

	var dateDebut = document.getElementById("DateDebut - "+idIntervention).value.split("/");
	var debutIntervention = new Date(parseInt(dateDebut[2], 10),
			parseInt(dateDebut[1], 10) - 1,
			parseInt(dateDebut[0], 10));
	
	var dateFin = document.getElementById("DateFin - "+idIntervention).value.split("/");
	var finIntervention = new Date(parseInt(dateFin[2], 10),
			parseInt(dateFin[1], 10) - 1,
			parseInt(dateFin[0], 10));
	
	//var finIntervention = new Date(document.getElementById("DateFin - "+idIntervention).value);	
	
	var dureeIntervention = parseInt((finIntervention - debutIntervention)/(24*3600*1000));

	//La récupération des dates restera à définir le moment venu
	var date1 = document.getElementById("date1").innerHTML.split("/");
	var debutTableau = new Date(parseInt(date1[2], 10),
								parseInt(date1[1], 10) - 1,
								parseInt(date1[0], 10));
	
	var date3 = document.getElementById("date3").innerHTML.split("/");	
	var finTableau =  new Date(parseInt(date3[2], 10),
							   parseInt(date3[1], 10) - 1,
							   parseInt(date3[0], 10));	
	
	var dureeTableau = ((finTableau - debutTableau)/(24*3600*1000));

	//Déclarations des variables margeGauche et Taille :
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
			//L’écart en jours
			var ecart = parseInt(
					(debutIntervention - debutTableau)/(24*3600*1000));
			margeGauche = (ecart * 100 / dureeTableau);
		}


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
		element.style.marginLeft = margeGauche*10 + "%" ;
		element.style.width = taille*10 + "%";
	}
}

/**
 * Fonction 4 : afficher ou masquer les icônes d'état d'avancement pour chaque interventions
 */

function afficherMasquerEtatAvancement(idIntervention, idEtat)
{
	
	switch (idEtat) {
	
	case "1":
		document.getElementById("iconeEtat - "+idIntervention).src = "images/iconeValider.26.26.png";
		break;
	case "3":
		document.getElementById("iconeEtat - "+idIntervention).src = "images/iconeEnCours.26.26.png";
		break;		
	case "4":
		document.getElementById("iconeEtat - "+idIntervention).src = "images/iconeEnAttente.26.26.png";
		break;	
	case "2":
		document.getElementById("iconeEtat - "+idIntervention).src = "images/iconeSuspendue2.26.26.png";		
		break;
	}
}

/**
 * Fonction 5 : avancer le diagramme de Gantt de 15 jours
 */

function avancerQuinzeJours()
{	
	//Récupération des dates en millisecondes
	var date1 = document.getElementById("date1").innerHTML.split("/");
	var debutTableau = new Date(parseInt(date1[2], 10),
								parseInt(date1[1], 10) - 1,
								parseInt(date1[0], 10));
	
	var date2 = document.getElementById("date2").innerHTML.split("/");
	var milieuTableau = new Date(parseInt(date2[2], 10),
								parseInt(date2[1], 10) - 1,
								parseInt(date2[0], 10));
	
	var date3 = document.getElementById("date3").innerHTML.split("/");	
	var finTableau =  new Date(parseInt(date3[2], 10),
							   parseInt(date3[1], 10) - 1,
							   parseInt(date3[0], 10));	
	
	//Calcul des nouvelles dates en ajoutant 15 jours (en millisecondes)
	var newDate1 = new Date(debutTableau);
	var newDate2 = new Date(milieuTableau);
	var newDate3 = new Date(finTableau);
	
	var lastDay = parseInt(date3[0], 10);
	
	if (lastDay == 15)
	{
		newDate1.setDate(15);
		newDate2.setDate(22);
		newDate3 = new Date(newDate3.getFullYear(), newDate3.getMonth() + 1, 0, 23, 59, 59);
	}
	else
	{
		newDate1.setDate(1);
		newDate1.setMonth(finTableau.getMonth()+1);
		newDate2.setDate(7);
		newDate2.setMonth(finTableau.getMonth()+1);
		newDate3.setDate(15);
		newDate3.setMonth(finTableau.getMonth()+1);
	}
	document.getElementById("date1").innerHTML = dateToString(newDate1);
	document.getElementById("date2").innerHTML = dateToString(newDate2);
	document.getElementById("date3").innerHTML = dateToString(newDate3);
	monthToString(newDate1);
}

function reculerQuinzeJours()
{	
	//Récupération des dates en millisecondes
	var date1 = document.getElementById("date1").innerHTML.split("/");
	var debutTableau = new Date(parseInt(date1[2], 10),
								parseInt(date1[1], 10) - 1,
								parseInt(date1[0], 10));
	
	var date2 = document.getElementById("date2").innerHTML.split("/");
	var milieuTableau = new Date(parseInt(date2[2], 10),
								parseInt(date2[1], 10) - 1,
								parseInt(date2[0], 10));
	
	var date3 = document.getElementById("date3").innerHTML.split("/");	
	var finTableau =  new Date(parseInt(date3[2], 10),
							   parseInt(date3[1], 10) - 1,
							   parseInt(date3[0], 10));	
	
	//Calcul des nouvelles dates en enlevant 15 jours (en millisecondes)
	var newDate1 = new Date(debutTableau);
	var newDate2 = new Date(milieuTableau);
	var newDate3 = new Date(finTableau);
	
	var firstDay = parseInt(date1[0], 10);
	
	if (firstDay == 1)
	{
		newDate1.setDate(15);
		newDate1.setMonth(finTableau.getMonth()-1);
		newDate2.setDate(22);
		newDate2.setMonth(finTableau.getMonth()-1);
		newDate3 = new Date(newDate3.getFullYear(), finTableau.getMonth(), 0, 23, 59, 59);
	}
	else
	{
		newDate1.setDate(1);
		newDate2.setDate(7);
		newDate3.setDate(15);
	}
	document.getElementById("date1").innerHTML = dateToString(newDate1);
	document.getElementById("date2").innerHTML = dateToString(newDate2);
	document.getElementById("date3").innerHTML = dateToString(newDate3);
	monthToString(newDate1);
}

/**
 * Fonction 6 : convertir une date Javascript au format jj/mm/yyyy
 */

function dateToString(date) {
	   
	var yyyy = date.getFullYear();
	var mm = date.getMonth()+1; // getMonth() is zero-based
	var dd  = date.getDate();
	
	if (mm < 10)
	{
		if (dd < 10)
		{
			return String("0" + dd + "/0" + mm + "/" + yyyy);
		}
		else
		{
			return String(dd + "/0" + mm + "/" + yyyy);
		}
	}
	else
	{
		if (dd < 10)
		{
			return String("0" + dd + "/" + mm + "/" + yyyy);
		}
		else
		{
			return String(dd + "/" + mm + "/" + yyyy);
		}
	}
}

/**
 * Fonction 7 : modifier l'entête du tableau (ex: Mars 2015)
 */

function monthToString(date) {
	   
	var yyyy = date.getFullYear();
	var mm = date.getMonth()+1;
	
	var monthString;
	
	switch (mm) {
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
	document.getElementById("moisAffiche").innerHTML = monthString + " " + yyyy;
}
