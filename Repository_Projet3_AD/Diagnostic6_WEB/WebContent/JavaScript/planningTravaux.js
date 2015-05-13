/**
 * 
 */
function afficherCacherTest(variable)
{
	var elements = document.getElementsByClassName(variable);
	
	for(var i=0; i<elements.length; i++)
	{
		elements[i].style.display = "none";
	}
	
}


function afficherCacher(maClasse)
{
	var elements = document.getElementById("monTableau").rows ;
	var monBoolean = new Boolean(false) ;
	var indexFirstRow = 0;
	
	while (monBoolean == false)
	{
		if (elements[indexFirstRow].className == maClasse)
		{
			monBoolean = true;
		}
		else
		{
			indexFirstRow ++ ;
		}
	}

	for(var i=indexFirstRow; i<elements.length; i++)
	{
		if (elements[i].className == maClasse)
		{
			if (elements[indexFirstRow].style.display == "none")
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