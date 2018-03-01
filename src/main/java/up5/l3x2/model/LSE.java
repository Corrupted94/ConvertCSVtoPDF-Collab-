package up5.l3x2.model;

import java.util.ArrayList;

public class LSE {
	private String type;
	private String nom;
	private int nbchoix;
	private String codeLSE;
	private String codeELPPere;
	private ArrayList <ELP> listeELP;
	private FormuleCoeff formuleCoeff; 
	
	public LSE(String codeLSE) {
		// TODO Auto-generated constructor stub
		
		this.codeLSE = codeLSE;
		this.listeELP = new ArrayList <ELP> ();
		this.formuleCoeff = new FormuleCoeff();
		this.type = "";
		this.nom = "";
		this.codeELPPere = "";
		
	}
	/**
	 * Methode toString
	 */
	public String toString ()
	{
		return codeLSE + "\t" + type + "\t" + nbchoix + "\t";
	}
	/**
	 * GET : Type de la LSE
	 * @return Type de la LSE
	 */
	public String getType() {
		return type;
	}
	/**
	 * SET : Type de la LSE
	 * @param type Type de la LSE (Facultative / Obligatoire / Obligatoire à choix)
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * GET : Nombre de choix entre les ELP de la Liste
	 * @return Nombre de choix
	 */
	public int getNbchoix() {
		return nbchoix;
	}
	/**
	 * SET : Nombre de choix entre les ELP de la Liste
	 * @param nbchoix Nombre de choix
	 */
	public void setNbchoix(int nbchoix) {
		this.nbchoix = nbchoix;
	}
	/**
	 * GET : code LSE
	 * @return Le code de la LSE
	 */
	public String getCodeLSE() {
		return codeLSE;
	}
	/**
	 * SET : codeLSE
	 * @param codeLSE Code de la LSE
	 */
	public void setCodeLSE(String codeLSE) {
		this.codeLSE = codeLSE;
	}
	
	/**
	 * GET : Liste de ELP contenus dans la LSE
	 * @return La liste d'element ELP contenus dans la LSE
	 */
	public ArrayList<ELP> getListeELP() {
		return listeELP;
	}
	
	/**
	 * SET : le code de l'ELP pere à la LSE
	 * @param codeELPPere Code de l'ELP Pere à la LSE
	 */
	public void setCodeELPPere (String codeELPPere)
	{
		this.codeELPPere = codeELPPere;
	}	
	/**
	 * GET : Nom de la LSE
	 * @return Nom de la LSE
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * SET : Nom de la LSE
	 * @param nom Nom de la LSE
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/**
	 * GET : code de l'ELP pere de cette LSE
	 * @return Code de l'ELP Pere
	 */
	public String getCodeELPPere() {
		return codeELPPere;
	}
	
	/**
	 * GET : Objet FormuleCoeff
	 * @return Objet de type FormuleCoeff
	 */
	public FormuleCoeff getFormuleCoeff() {
		return formuleCoeff;
	}


}
