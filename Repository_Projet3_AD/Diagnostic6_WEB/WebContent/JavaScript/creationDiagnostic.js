/**
 * Modification du nom de diagnostic lorsqu'un type de diagnostic est sélectionné
 */

function typeDiagTitre(monSelect){
	for(i = 0; i < monSelect.length; i++)
	{
		if(monSelect[i].selected)
		{
			document.getElementById("spanTypeSelectionne").innerHTML = monSelect[i].innerText;
		}
	}
};