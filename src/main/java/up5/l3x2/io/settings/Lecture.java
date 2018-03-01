package up5.l3x2.io.settings;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Lecture {
	
	private String vet_1er_niveau;
	private String arborescence;
	private String epreuve;
	private String elp_heure;
	private String formule;
	private String nomRepertoire;
	
	private File repertoire;
	
	
	public Lecture (String nomRepertoire)
	{
		this.nomRepertoire = nomRepertoire;
		this.repertoire = new File (nomRepertoire);
		this.arborescence = "";
		this.elp_heure = "";
		this.vet_1er_niveau = "";
		this.epreuve = "";
		this.formule ="";
	}
	
	public String getNomRepertoire() {
		return nomRepertoire;
	}		


	/**
	 * Methode qui permet la saisie du repertoire en entrée
	 */
	public static String choisirRepertoire ()
	{
		String st;
		System.out.println("Choisir le répertoire à  importer");
		Scanner sc = new Scanner (System.in);
		
		st = sc.nextLine();
		sc.close();
		return st;

	}
	/**
	 * Fonction qui permet de lister les fichiers d'un repertoire
	 * @author SU
	 * @return fichiers ArrayList des nom de fichiers
	 */
	public ArrayList <String> listerFichiers ()
	{
		ArrayList <String> fichiers = new ArrayList <String>();
		for (int i =0; i <repertoire.list().length; i++)
		{
			fichiers.add(repertoire.list()[i]);
		}
		
		return fichiers;	
	}
	
	/**
	 * Methode qui renvoie true si le repertoire passé en argument est conforme, false sinon
	 * @author SU
	 * @return boolean
	 */
	public boolean repertoireConforme ()
	{
		
			if (arborescence != null && vet_1er_niveau != null && formule != null && epreuve != null && elp_heure !=null) return true;
			else return false;
	}
	
	/**
	 * Methode qui permet de chercher les noms des fichiers du repertoire et de les stocker dans les atrributs correspondants
	 */
	public void setNomDesFichiers ()
	{
		ArrayList <String> fichiers = listerFichiers ();
		
		for (int i = 0; i<fichiers.size(); i++)
		{
			if (fichiers.get(i).toLowerCase().contains("vet")) vet_1er_niveau = fichiers.get(i);
			else if (fichiers.get(i).toLowerCase().contains("arborescence")) arborescence = fichiers.get(i);
			else if (fichiers.get(i).toLowerCase().contains("formule")) formule = fichiers.get(i);
			else if (fichiers.get(i).toLowerCase().contains("heure")) elp_heure = fichiers.get(i);
			else if (fichiers.get(i).toLowerCase().contains("epreuve")) epreuve = fichiers.get(i);
		}
	}
	/**
	 * GET : le nom du fichier qui correspond au fichier VET
	 * @return vet_1er_niveau Nom du fichier VET
	 */
	public String getVet_1er_niveau() {
		return vet_1er_niveau;
	}
	
	/**
	 * GET : le nom du fichier qui correspond au fichier arborescence
	 * @return arborescence Nom du fichier arborescence
	 */
	public String getArborescence() {
		return arborescence;
	}
	/**
	 * GET : le nom du fichier qui correspond au fichier Epreuve
	 * @return epreuve Nom du fichier arborescence
	 */
	public String getEpreuve() {
		return epreuve;
	}
	/**
	 * GET : le nom du fichier qui correspond au fichier ELP_Heure
	 * @return elp_heure Nom du fichier elp_heure
	 */
	public String getElp_heure() {
		return elp_heure;
	}
	/**
	 * GET : le nom du fichier qui correspond au fichier formule
	 * @return formule Nom du fichier formule
	 */
	public String getFormule() {
		return formule;
	}	
	
	/**
	 * Retourne un String contenant tous les noms de fichiers
	 * return listeNomFichiers
	 */
	public String toString ()
	{
		return "Repertoire : " +nomRepertoire + "\n" 
				+ "Fichier Vet_1er_niveau : " + vet_1er_niveau + "\n" 
				+ "Fichier Arborescence : " +arborescence + "\n" 
				+ "Fichier Epreuve : " +epreuve  + "\n"
				+ "Fichier Elp_heure : " +elp_heure + "\n"
				+ "Fichier Formule : " +formule + "\n"; 
	} 
}
