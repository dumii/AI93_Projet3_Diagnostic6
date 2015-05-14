/**
 * Modification du nom de diagnostic lorsqu'un type de diagnostic est sélectionné et affichage du panel 2
 */

function typeDiagTitre(monSelect)
{
	for(i = 0; i < monSelect.length; i++)
	{
		if(monSelect[i].selected)
		{
			document.getElementById("spanTypeSelectionne").innerHTML = monSelect[i].innerText;
		}
	}
	
	document.getElementById("ajoutAnomalies").style.display = "inline-block";
}

/**
 * Affichage du panels 1
 * 
 */
function afficherPanel1()
{
	var element = document.getElementById("informations");
	element.style.display = "inline-block";
}
