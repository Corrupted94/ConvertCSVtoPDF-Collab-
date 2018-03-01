package up5.l3x2.io;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class OpenCSV {


	/**
	 * Methode qui permet de lire un fichier csv
	 * @param nom du fichier Ã  lire
	 * @return une liste de String[]
	 * @throws IOException
	 */
	public static List<String[]> lireCSV (String filename) throws IOException
	{
		
		CSVReader reader = new CSVReader(new FileReader(filename), ';');
		
	    List <String []> lignes = reader.readAll();
	    reader.close();
	    
	    return lignes;
	}
	
	/**
	 * Methode qui retourne un tableau equivalent à la premiere ligne du fichier filename
	 * @param filename nom+path du fichier
	 * @return premiereLigne Un tableau de String qui contient les informations de la premiere ligne du fichier filename
	 * @throws IOException
	 */
	public static String[] lire1erligneCSV (String filename) throws IOException
	{
		CSVReader reader = new CSVReader(new FileReader(filename), ';');
		String [] premiereLigne = reader.readNext();
		reader.close();
		return premiereLigne;
	
	}
	
	/**
	 * Methode qui permet d'ecrire une Liste de String[] dans un fichier csv
	 * @param list Liste de <String[]> 
	 * @throws IOException
	 */
	public static void ecrireCSV (List <String []> list, String filename) throws IOException
	{
		CSVWriter writer = new CSVWriter (new FileWriter ("exportCSV_" + filename +".csv"), ';');
		
		writer.writeAll(list);
		
		writer.close();
		
	}
}
