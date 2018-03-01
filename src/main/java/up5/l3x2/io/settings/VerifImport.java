package up5.l3x2.io.settings;


import up5.l3x2.model.ELP;
import up5.l3x2.model.LSE;

public class VerifImport {

	public static void verifierArborescence(Import imp)
	{
		for (int i = 0; i <imp.getVets().size(); i++)
		{
			for (int j = 0; j <imp.getVets().get(i).getListeLSE().size(); j++)
			{
				verifierElement(imp.getVets().get(i).getListeLSE().get(j));
				for (int k = 0; k < imp.getVets().get(i).getListeLSE().get(j).getListeELP().size(); k++)
				{
					coefficientGestion (imp.getVets().get(i).getListeLSE().get(j).getListeELP().get(k));
				}
			}
			
		}
		
	}
	
	public static void verifierElement (LSE elementLSE)
	{
		boolean testVider;
		
		for (int i = 0; i<elementLSE.getListeELP().size(); i++)
		{
			if (elementLSE.getListeELP().get(i).getType().equals("Bloc"))
			{
				for (int k = 0; k < elementLSE.getListeELP().get(i).getListeLSE().size(); k++)
				{
					testVider = true;
					
					LSE newElementLSE = elementLSE.getListeELP().get(i).getListeLSE().get(k);
					for (int l = 0; l <newElementLSE.getListeELP().size(); l++)
					{
						if (newElementLSE.getListeELP().get(l).getType().equals("Enseignt") || newElementLSE.getListeELP().get(l).getType().equals("Semestre"))
						{
							newElementLSE.getListeELP().remove(l);
						}
						else testVider = false;
					}
					if (testVider) elementLSE.getListeELP().get(i).getListeLSE().remove(k);
				}
				if (elementLSE.getListeELP().get(i).getListeLSE().isEmpty()) elementLSE.getListeELP().remove(i);
			}

		}
		
		for (int i = 0; i<elementLSE.getListeELP().size(); i++)
		{
			for (int k = 0; k < elementLSE.getListeELP().get(i).getListeLSE().size(); k++)
			{
				verifierElement (elementLSE.getListeELP().get(i).getListeLSE().get(k));
			}
		}

	}
	
	public static void coefficientGestion (ELP elp)
	{
		if (elp.getType().equals("Bloc") && elp.getFormuleCoeff().getCoeff() > 0)
		{
			for (int i = 0; i <elp.getListeLSE().size(); i++)
			{
				for (int j = 0; j <elp.getListeLSE().get(i).getListeELP().size(); j++)
				{
					if (elp.getListeLSE().get(i).getListeELP().get(j).getFormuleCoeff().getCoeff() == -1 && elp.getListeLSE().get(i).getNbchoix() > 0)
					{
						float nb = elp.getFormuleCoeff().getCoeff() / elp.getListeLSE().get(i).getNbchoix();
						elp.getListeLSE().get(i).getListeELP().get(j).getFormuleCoeff().setCoeff(nb);;
					}
				}
			}
			elp.getFormuleCoeff().setCoeff(-1);
		}
		for (int i = 0; i <elp.getListeLSE().size(); i++)
		{
			for (int j = 0; j <elp.getListeLSE().get(i).getListeELP().size(); j++)
			{
				coefficientGestion (elp.getListeLSE().get(i).getListeELP().get(j));
			}
		}
	}
}
