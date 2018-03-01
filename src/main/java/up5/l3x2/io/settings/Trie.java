package up5.l3x2.io.settings;

import up5.l3x2.model.ELP;
import up5.l3x2.model.LSE;

public class Trie {

	/**
	 * Cette methode permet de trier par ordre alphabetique la listeELP si element est un LSE, ou de trier la listeLSE si element est un ELP.
	 * Elle appelle la recursivité sur les elements fils.
	 * @param element Objet de type générique T, qui peut soit être un LSE ou un ELP
	 * @author SU
	 */
	public static <T> void trierListeElementT (T element)
	{
		if (element.getClass().equals(new LSE (null).getClass()))
		{
			LSE elementLSE = (LSE) element;
			//Algo de trie
			
			
			for (int i = 0; i < elementLSE.getListeELP().size()-1; i++)
			{
				
				int min = elementLSE.getListeELP().size() - (i+1);
				
				if (i!=min)
				{
					ELP tmpELP = elementLSE.getListeELP().get(i);
					elementLSE.getListeELP().set(i, elementLSE.getListeELP().get(min));
					elementLSE.getListeELP().set(min, tmpELP);
				}
				
			}
						
			for (int i = 0; i <elementLSE.getListeELP().size(); i++)
			{
				trierListeElementT (elementLSE.getListeELP().get(i));
			}
		}
		
		else if (element.getClass().equals(new ELP (null).getClass()))
		{
			ELP elementELP = (ELP) element;
			
			//Algo de trie
			for (int i = 0; i < elementELP.getListeLSE().size()-1; i++)
			{
				int min = elementELP.getListeLSE().size() - (i+1);
				/*for (int j = i+1; j< elementELP.getListeLSE().size(); j++)
				{
					if (elementELP.getListeLSE().get(i).getCodeLSE().compareTo(elementELP.getListeLSE().get(j).getCodeLSE())>0)
					{
						//System.out.println(elementELP.getListeLSE().get(i).getCodeLSE() + ""  +elementELP.getListeLSE().get(j).getCodeLSE());
						min = j;
					}
				}*/
				if (min != i)
				{
					LSE tmpLSE = elementELP.getListeLSE().get(i);
					elementELP.getListeLSE().set(i, elementELP.getListeLSE().get(min));
					elementELP.getListeLSE().set(min, tmpLSE);
				}
				
			}
			
			
			
			for (int i = 0; i < elementELP.getListeLSE().size(); i++)
			{
				trierListeElementT (elementELP.getListeLSE().get(i));
			}
		}
	}
	/**
	 * Cette methode permet de trier selon le type de la liste.
	 * Elle permet d'obtenir une liste dont les U.E. apparaissent toujours avant les Blocs et que les listes Obligatoires avant les listes Facultatives
	 * Elle appelle la recursivité sur les elements fils.
	 * @param Objet de type générique T, qui peut soit être un LSE ou un ELP
	 * @author SU
	 */
	public static <T> void ordonnerListeElementT (T element)
	{
		if (element.getClass().equals(new LSE (null).getClass()))
		{
			LSE elementLSE = (LSE) element;
			//Algo de trie
			
			
			for (int i = 0; i < elementLSE.getListeELP().size()-1; i++)
			{
				int min = i;
				for (int j = i+1; j< elementLSE.getListeELP().size(); j++)
				{
					if (elementLSE.getListeELP().get(i).getType().equals("Bloc") && elementLSE.getListeELP().get(j).getType().equals("U.E."))
					{
						min = j;
					}
				}
				if (i != min)
				{
					ELP tmpELP = elementLSE.getListeELP().get(i);
					elementLSE.getListeELP().set(i, elementLSE.getListeELP().get(min));
					elementLSE.getListeELP().set(min, tmpELP);
				}
				
			}
			
			for (int i = 0; i < elementLSE.getListeELP().size()-1; i++)
			{
				int min = i;
				for (int j = i+1; j< elementLSE.getListeELP().size(); j++)
				{
					if (!elementLSE.getListeELP().get(i).getListeLSE().isEmpty() && !elementLSE.getListeELP().get(j).getListeLSE().isEmpty())
					{
						if (elementLSE.getListeELP().get(i).getListeLSE().get(0).getType().equals("Facultative") && (elementLSE.getListeELP().get(j).getListeLSE().get(0).getType().equals("Obligatoire") || elementLSE.getListeELP().get(j).getListeLSE().get(0).getType().equals("Obligatoire à choix")))
						{
							min = j;
						}
					}
					
				}
				if (i != min)
				{
					ELP tmpELP = elementLSE.getListeELP().get(i);
					elementLSE.getListeELP().set(i, elementLSE.getListeELP().get(min));
					elementLSE.getListeELP().set(min, tmpELP);
				}
				
			}
						
			for (int i = 0; i <elementLSE.getListeELP().size(); i++)
			{
				ordonnerListeElementT (elementLSE.getListeELP().get(i));
			}
		}
		
		else if (element.getClass().equals(new ELP (null).getClass()))
		{
			ELP elementELP = (ELP) element;
	
			//Algo de trie
			for (int i = 0; i < elementELP.getListeLSE().size()-1; i++)
			{
				int min = i;
				for (int j = i+1; j< elementELP.getListeLSE().size(); j++)
				{
					//System.out.println(elementELP.getListeLSE().get(i).getCodeLSE()+ "  "+ elementELP.getListeLSE().get(i).getType() + "   " + elementELP.getListeLSE().get(j).getCodeLSE());
					if (elementELP.getListeLSE().get(i).getType().equals("Facultative") && (elementELP.getListeLSE().get(j).getType().equals("Obligatoire") || elementELP.getListeLSE().get(j).getType().equals("Obligatoire à choix")))
					{
						min = j;
					}
				}
				if (min != i)
				{
					LSE tmpLSE = elementELP.getListeLSE().get(i);
					elementELP.getListeLSE().set(i, elementELP.getListeLSE().get(min));
					elementELP.getListeLSE().set(min, tmpLSE);
				}
				
			}
			
			
			
			for (int i = 0; i < elementELP.getListeLSE().size(); i++)
			{
				ordonnerListeElementT (elementELP.getListeLSE().get(i));
			}
		}
	}
	/**
	 * Methode qui permet de trier, en appelant la methode trierElementT(), l'arraylist de vets.
	 * @param imp Objet Import 
	 * @author SU
	 * 
	 */
	public static void trierArborescence (Import imp)
	{
		
		for (int i = 0; i< imp.getVets().size(); i++)
		{
			for (int j=0; j< imp.getVets().get(i).getListeLSE().size(); j++)
			{
			
					trierListeElementT (imp.getVets().get(i).getListeLSE().get(j));
					ordonnerListeElementT (imp.getVets().get(i).getListeLSE().get(j));
				
			}
		}
	}
}
