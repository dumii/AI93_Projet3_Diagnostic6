/**
 * 
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

function nomERPTitre(monSelect){
	for(i = 0; i < monSelect.length; i++)
	{
		if(monSelect[i].selected)
		{

			document.getElementById("spanNomErp").innerHTML = monSelect[i].innerText;
		}
	}
};