package up5.l3x2.io.settings;

import up5.l3x2.exception.FormuleExceptions;
import up5.l3x2.formule.ExpressionDeFormule;
import up5.l3x2.model.ELP;
import up5.l3x2.model.VET;

public class Afficher {

	
	private static int indice=0;

	/**
	 * Methode qui permet d'afficher récursivement les ELP fils contenus dans un VET ou ELP passé en argument. Elle appelle la récursvité sur les ELP fils
	 * @param element Element de type générique T qui peut soit être un VET soit un ELP
	 * @author SU
	 */
	public static <T> void affichageRecursifDesELP (T element)
	{
		int indiceTMP = indice;
		StringBuffer espacement = new StringBuffer ();
		String defautEspacement = "          ";
		indice++;

		//Concatenation des espacements selon l'arborescence
		for (int k=1; k <indice; k++)
		{
			espacement.append("  ");
		}
		
		/*
		 * Le traitement entre les Objets VET et ELP est différent
		 */
		
		
		//Si l'element passé en argument est un VET
		if (element.getClass().equals((new VET (null)).getClass()))
		{	
			VET elementVET = (VET) element;

			//Parcous de la liste des LSE
			for (int i =0; i <elementVET.getListeLSE().size(); i++)
			{
				defautEspacement = defautEspacement.substring(elementVET.getListeLSE().get(i).getCodeLSE().length());
				System.out.println(elementVET.getListeLSE().get(i).getCodeLSE() + defautEspacement+espacement + elementVET.getListeLSE().get(i).getNom() + "\t\t\t"+elementVET.getListeLSE().get(i).getFormuleCoeff().getFormule());
				
				/*
				 * Parcours des listes d'ELP contenue dans la Liste des LSE
				 * Appel de la recursivité sur les éléments ELP fils
				 */
				for (int j = 0; j<elementVET.getListeLSE().get(i).getListeELP().size(); j++)
					affichageRecursifDesELP (elementVET.getListeLSE().get(i).getListeELP().get(j));
			}
		}
		
		//Si l'element passé en argument est un ELP
		else if (element.getClass().equals((new ELP (null)).getClass()))
		{
			ELP elementELP = (ELP)element;
		
			defautEspacement = defautEspacement.substring(elementELP.getCodeELP().length());
			System.out.println("\t"+elementELP.getCodeELP() +defautEspacement /*+espacement */+ elementELP.getNom()+ "\t"+ "\t"+ elementELP.getFormuleCoeff().getCoeff()+ " " +elementELP.getType() + " " + elementELP.getEcts());
			/*
			 * Premiere boucle : parcours de la liste des LSE
			 * Deuxieme boucle : parcours de la liste des ELP  (FILS)
			 */
			for (int i = 0; i<elementELP.getListeEPR().size(); i++)
			{
				//Affiche list CC
				System.out.println("                -"+elementELP.getListeEPR().get(i).getCodeEPR() + "\t" + elementELP.getListeEPR().get(i).getCod_tep()+ "\t"+ elementELP.getListeEPR().get(i).getFormuleCoeff().getFormule());
				
			}
			for (int i =0; i <elementELP.getListeLSE().size(); i++)
			{
				//Appel de la recursivité sur les éléments ELP fils
				System.out.println(elementELP.getListeLSE().get(i).getNom());
				for (int j = 0; j<elementELP.getListeLSE().get(i).getListeELP().size(); j++)
					affichageRecursifDesELP (elementELP.getListeLSE().get(i).getListeELP().get(j));
			}
		}
		indice = indiceTMP++;
	}
	
}

