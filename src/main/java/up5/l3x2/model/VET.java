package up5.l3x2.model;

import java.util.ArrayList;

public class VET {

	private String type;
	private String codeVET;
	private float ects;
	private ArrayList<LSE> listeLSE;
	

	public VET(String codeVET	) {
		// TODO Auto-generated constructor stub
		this.codeVET = codeVET;
		this.listeLSE = new ArrayList <LSE>();
		this.type = "";
		this.ects = -1;
	}
	/**
	 * Methode toString
	 */
	public String toString ()
	{
		return codeVET+ "\t" + type + "\t" ;
	}
	
	/**
	 * GET : Type de VET
	 * @return Le type de la VET
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * SET : Type de VET
	 * @param type Le type de la VET
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * GET : code de la VET
	 * @return Le code de la VET
	 */
	public String getCodeVET() {
		return codeVET;
	}
	
	/**
	 * SET : code de la vet
	 * @param codeVET
	 */
	public void setCodeVET(String codeVET) {
		this.codeVET = codeVET;
	}
	
	/**
	 * GET : Liste de LSE contenus dans la VET
	 * @return La Liste de LSE de la VET
	 */
	public ArrayList<LSE> getListeLSE() {
		return listeLSE;
	}

	/**
	 * GET : Nombre d'ECTS de la VET
	 * @return Le nombre d'ects de la VET
	 */
	public float getEcts() {
		return ects;
	}
	
	/**
	 * SET : Nombre d'ects de la VET
	 * @param ects Nombre d'ects de la VET
	 */
	public void setEcts(float ects) {
		this.ects = ects;
	}
	
}
