package up5.l3x2.io.settings;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import up5.l3x2.io.OpenCSV;
import up5.l3x2.model.ELP;
import up5.l3x2.model.VET;

public class ExportCSV {

	private static int indice = 0;
	
	/**
	 * Fonction qui permet de construire la List <String []> qu'on utilisera pour exporter (creer un fichier CSV).
	 * @author SU
	 * @param element Element de type générique T qui peut soit etre un ELP ou soit un VET
	 * @param list
	 */
	public static <T> void getStringListCSV (T element, List <String []> list)
	{
		int indiceTMP = indice;

		indice++;
		StringBuffer ligne = new StringBuffer (); 

		
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
				ligne.append(elementVET.getListeLSE().get(i).getCodeLSE()+"#");
				for (int k = 1; k<indice; k++)
				{
					ligne.append("#");
				}
				ligne.append(elementVET.getListeLSE().get(i).getNom()+"#");
				for (int k = indice; k<7; k++ )
				{
					ligne.append("#");
				}
				ligne.append(""+ suppressionVirguleInutile(elementVET.getEcts()));
				
				list.add(ligne.toString().split("#"));
				/*
				 * Parcours des listes d'ELP contenue dans la Liste des LSE
				 * Appel de la recursivité sur les éléments ELP fils
				 */
				for (int j = 0; j<elementVET.getListeLSE().get(i).getListeELP().size(); j++)
					getStringListCSV (elementVET.getListeLSE().get(i).getListeELP().get(j), list);
			}
		}
		
		//Si l'element passé en argument est un ELP
		else if (element.getClass().equals((new ELP (null)).getClass()))
		{
			ELP elementELP = (ELP)element;
			

			ligne.append(elementELP.getCodeELP()+"#");
			for (int k = 1; k<indice; k++)
			{
				ligne.append("#");
			}
			if (!elementELP.getListeLSE().isEmpty())
			{
				if (elementELP.getListeLSE().get(0).getType().equals("Facultative")) 
				{
					if (!elementELP.getNom().contains(elementELP.getType()))ligne.append(elementELP.getType()+ " " +elementELP.getNom()+ "(" + elementELP.getListeLSE().get(0).getType() + ")"+"#");
					else ligne.append(elementELP.getNom()+ "(" + elementELP.getListeLSE().get(0).getType() + ")"+"#");
				}
				
				else if (elementELP.getListeLSE().get(0).getType().contains("choix")) 
				{
					if (!elementELP.getNom().contains(elementELP.getType())) ligne.append(elementELP.getType()+ " " +elementELP.getNom()+ "(" +  elementELP.getListeLSE().get(0).getNbchoix() + " à choix)"+"#");
					else ligne.append(elementELP.getNom()+ "(" +  elementELP.getListeLSE().get(0).getNbchoix() + " à choix)"+"#");
				}
			
				else 
				{
					if (!elementELP.getNom().contains(elementELP.getType())) ligne.append(elementELP.getType()+ " " +elementELP.getNom()+"#");
					else ligne.append(elementELP.getNom()+"#");
				}
			}
			else 
			{
				if (!elementELP.getNom().contains(elementELP.getType())) ligne.append(elementELP.getType()+ " " +elementELP.getNom()+"#");
				else ligne.append(elementELP.getNom()+"#");
			}
			for (int k = indice; k<7; k++ )
			{
				ligne.append("#");
			}
			ligne.append(""+suppressionVirguleInutile(elementELP.getEcts())+"#"); 
			ligne.append(""+suppressionVirguleInutile(elementELP.getFormuleCoeff().getCoeff())+"#");
			ligne.append(""+suppressionVirguleInutile(elementELP.getNbrHrCM())+"#");
			ligne.append(""+suppressionVirguleInutile(elementELP.getNbrHrTD())+"#");
			ligne.append(""+suppressionVirguleInutile(elementELP.getNbrHrTP())+"#");
			ligne.append(""+suppressionVirguleInutile(elementELP.getCodeCNU())+"#");
			ligne.append(elementELP.getPartCC()+"#");
			ligne.append(""+suppressionVirguleInutile(elementELP.getDureeS1())+"#");
			
			String st = elementELP.getTypeExamS1();
			if (st.equals("ECR")) ligne.append("epreuve ecrite#");
			else ligne.append("#");
			
			ligne.append(""+suppressionVirguleInutile(elementELP.getDureeS2())+"#");
			st = elementELP.getTypeExamS2();
			if (st.equals("ECR")) ligne.append("epreuve ecrite#");
			else if (st.equals("ORA")) ligne.append("orale#");
			else ligne.append("#");
			
			ligne.append(elementELP.getMessage());
			
			
			
			
			list.add(ligne.toString().split("#"));
			/*
			 * Premiere boucle : parcours de la liste des LSE
			 * Deuxieme boucle : parcours de la liste des ELP  (FILS)
			 */
			for (int i =0; i <elementELP.getListeLSE().size(); i++)
			{
				
				if (elementELP.getListeLSE().size()>1)
				{
					StringBuffer newLigne = new StringBuffer();
					
					newLigne.append("#");
					for (int k = 1; k<indice; k++)
					{
						newLigne.append("#");
					}
					if (elementELP.getListeLSE().get(i).getType().equals("Facultative")) 
					{
						 if (!elementELP.getListeLSE().get(i).getListeELP().isEmpty()) newLigne.append(elementELP.getListeLSE().get(i).getListeELP().get(0).getType()+ "(" + elementELP.getListeLSE().get(i).getType() + ")"+"#");
					}
					
					else if (elementELP.getListeLSE().get(i).getType().contains("choix")) 
					{
						if (!elementELP.getListeLSE().get(i).getListeELP().isEmpty()) newLigne.append(elementELP.getListeLSE().get(i).getListeELP().get(0).getType()+ "(" + elementELP.getListeLSE().get(i).getNbchoix() + " à choix)"+"#");
						
					}
				
					else 
					{
						if (!elementELP.getListeLSE().get(i).getListeELP().isEmpty())newLigne.append(elementELP.getListeLSE().get(i).getListeELP().get(0).getType()+"#");
						
					}
					
					for (int k = indice; k<7; k++ )
					{
						newLigne.append("#");
					}
					
					list.add(newLigne.toString().split("#"));
				}
				//Appel de la recursivité sur les éléments ELP fils
				for (int j = 0; j<elementELP.getListeLSE().get(i).getListeELP().size(); j++)
					getStringListCSV (elementELP.getListeLSE().get(i).getListeELP().get(j),list);
			}
		}
		indice = indiceTMP++;
	}
	
	/**
	 * Fonction qui prend en parametre un objet Import.
	 * Cette fonction permet de creer un fichier CSV (realiser l'export) depuis les vets contenus dans l'Objet Import
	 * @param imp Objet Import
	 * @throws IOException
	 */
	public static void exporterCSV (Import imp) throws IOException
	{
		List <String []>list = new ArrayList <String []> ();
		
		//Initialisation de l'entête
		String st = "Code Apogee#Nom#######ECTS#Coeff#Nombre Heure CM#Nombre Heure TD#Nombre Heure TP#CodeCNU#Part du Controle Continu#Duree Exam S1#Epreuve Terminale S1#DureeExamS S2#Epreuve S2#Info";
		String vide = "#";
		list.add(st.split("#"));
		
		//Appel de la fonction pour remplir la liste
		for (int i = 0; i <imp.getVets().size(); i++)
		{
			getStringListCSV (imp.getVets().get(i), list);
			list.add(vide.split("#"));
			list.add(vide.split("#"));
		}
		//Appel de la fonction pour ecrire les données.
		
		OpenCSV.ecrireCSV(list, imp.getLec().getNomRepertoire()); 
	}
	
	/**
	 * Fonction qui permet de supprimer les décimales inutiles
	 * @param nb
	 * @return
	 */
	public static String suppressionVirguleInutile(float nb)
	{
		//Si un attribut renvoie -1 dans notre programme, ca equivaut à dire qu'il n'y a pas de valeur pour cet attribut
		if (nb  != -1)
		{
			String st = Float.toString(nb);
	        int position = st.lastIndexOf(".");
	        
	        if (position>0)
	        {
	             for(int i=position;i<st.length();i++)
	             {
	            	 if(st.charAt(i) == '0')
	            	 {
	            		 st=st.substring(0, position);
	                 }
	             }
	        }    
	       return st;

		}
		else return "";
	}
		
        
        

	}
