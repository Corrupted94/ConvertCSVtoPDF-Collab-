package up5.l3x2.model;

import java.util.ArrayList;

public class ELP {
	private float nbrHrTD;
	private float nbrHrCM;
	private float nbrHrTP;
	private float ects;
	private float codeCNU;
	private float dureeS1;
	private float dureeS2;
	private String nom;
	private String codeELP;
	private String type;
	private String message;
	private ArrayList<LSE> listeLSE;
	private ArrayList<Epreuve> listeEPR;
	private FormuleCoeff formuleCoeff; 
	private String partCC;
	private String typeExamS1;
	private String typeExamS2;
	private String stage;
	
//on récupère formulecoeffs1 ou formulecoeffs2	
	
	public ELP(String codeELP) {
		// TODO Auto-generated constructor stub
		this.codeELP = codeELP;
		this.listeLSE = new ArrayList<LSE> (); 
		this.listeEPR = new ArrayList<Epreuve> ();
		this.formuleCoeff = new FormuleCoeff ();
		this.nom = "";
		this.type = "";
		this.typeExamS1= "";
		this.typeExamS2 ="";
		this.message = "";
		this.partCC = "";
		this.stage = "";
	}

	/**
	 * GET : Code de ELP
	 * @return Code de l'ELP
	 */
	public String getCodeELP() {
		return codeELP;
	}
	/**
	 * Methode toString
	 * return String
	 */
	public String toString ()
	{
		return nom + "\t" + codeELP + "\t" +type + "\t" + nbrHrTD + "\t" + nbrHrCM + "\t" + nbrHrTP + "\t" + ects + "\t" + codeCNU + "\t" + dureeS1 + "\t" + dureeS2; 
	}

	/**
	 * GET : Nombre d'heures de TD
	 * @return Le nombre d'heures de TD pour ce ELP, ou -1 s'il n'y a pas de valeur
	 */
	public float getNbrHrTD() {
		if (nbrHrTD == 0) return -1;
		else return nbrHrTD;
	}
	
	/**
	 * SET : Nombre d'heures de TD
	 * @param nbrHrTD Nombre d'heure de TD
	 */
	public void setNbrHrTD(float nbrHrTD) {
		this.nbrHrTD = nbrHrTD;
	}
	
	/**
	 * GET : Nombre d'heures de CM
	 * @return Nombre d'heures de CM ou -1 s'il n'y a pas de valeur
	 */
	public float getNbrHrCM() {
		if (nbrHrCM == 0) return -1;
		else return nbrHrCM;
	}
	/**
	 * SET : Nombre d'heures de CM
	 * @param nbrHrCM Nombre d'heures de CM
	 */
	public void setNbrHrCM(float nbrHrCM) {
		this.nbrHrCM = nbrHrCM;
	}
	/**
	 * GET : Nombre d'heures de TP
	 * @return Nombre d'heures de TP ou -1 s'il n'y a pas de valeur
	 */
	public float getNbrHrTP() {
		if(nbrHrTP == 0) return -1;
		else return nbrHrTP;
	}
	
	/**
	 * SET : Nombre d'heures de TP
	 * @param nbrHrTP Nombre d'heures de TP
	 */
	public void setNbrHrTP(float nbrHrTP) {
		this.nbrHrTP = nbrHrTP;
	}
	/**
	 * GET : Nombre d'ECTS
	 * @return Le nombre d'ects ou -1 s'il n'y a pas de valeur
	 */
	public float getEcts() {
		if (ects == 0) return -1;
		else return ects;
	}
	/**
	 * SET : Nombre d'ECTS
	 * @param ects Nombre d'ECTS
	 */
	public void setEcts(float ects) {
		this.ects = ects;
	}
	
	/**
	 * GET : Code CNU
	 * @return Le code CNU ou -1 s'il n'y a pas de valeur
	 */
	public float getCodeCNU() {
		if (codeCNU == 0) return -1;
		else return codeCNU;
	}
	/**
	 * SET : Code CNU
	 * @param codeCNU Code CNU
	 */
	public void setCodeCNU(float codeCNU) {
		this.codeCNU = codeCNU;
	}
	
	/**
	 * GET : Durée exam session 1
	 * @return Durée de l'examen finale (session 1) ou -1 s'il n'y a pas de valeur
	 */
	public float getDureeS1() {
		if (dureeS1 == 0) return -1;
		else return dureeS1;
	}
	/**
	 * SET : Durée exam session 1
	 * @param Durée de l'examen finale (session 1)
	 */
	public void setDureeS1(float dureeS1) {
		this.dureeS1 = dureeS1;
	}
	/**
	 * GET : Durée exam session 2
	 * @return Durée de l'examen finale (session 2) ou -1 s'il n'y a pas de valeur
	 */
	public float getDureeS2() {
		if (dureeS2 == 0) return -1;
		else return dureeS2;
	}
	
	/**
	 * SET : Durée exam session 2
	 * @param dureeS2 Durée de l'examen finale (session 2)
	 */
	public void setDureeS2(float dureeS2) {
		this.dureeS2 = dureeS2;
	}
	
	/**
	 * GET : Nom de l'ELP
	 * @return Nom de l'ELP
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * SET : Nom de l'ELP
	 * @param nom Nom de l'ELP (String)
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	/**
	 * GET : Type de l'ELP
	 * @return Type de l'ELP
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * SET : Type de l'ELP
	 * @param type Type de l'ELP (String)
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * GET : Liste de LSE contenue dans l'ELP
	 * @return ArrayList de LSE
	 */
	public ArrayList<LSE> getListeLSE() {
		return listeLSE;
	}
	
	/**
	 * GET : Liste d' Epreuves contenue dans l'ELP
	 * @return ArrayList de Epreuve
	 */
	public ArrayList<Epreuve> getListeEPR() {
		return listeEPR;
	}

	/**
	 * GET : Messages d'erreurs
	 * @return Une chaine de caractère contenant les messages d'erreurs
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * Methode qui permet de concatener le nouveau message passé en paramètre au message déjà existant.
	 * @param newMessage
	 * @return String
	 */
	public String addMessage(String newMessage)
	{
		message = message.concat(" ");
		message = message.concat(newMessage);
		
		return message;
	}

	/**
	 * GET : Objet FormuleCoeff
	 * @return Objet FormuleCoeff (Object qui contient le couple Formule / Coefficient)
	 */
	public FormuleCoeff getFormuleCoeff() {
		return formuleCoeff;
	}

	/**
	 * GET : Part du CC
	 * @return Part du controle continu dans la note finale de l'ELP
	 */
	public String getPartCC() {
		return partCC;
	}

	/**
	 * SET : Part du CC
	 * @param partCC String qui contient le pourcentage du CC dans la note finale de l'ELP
	 */
	public void setPartCC(String partCC) {
		this.partCC = partCC;
	}

	/**
	 * GET : Type de l'examen final Session 1
	 * @return type de l'examen final Session 1 (String)
	 */
	public String getTypeExamS1() {
		return typeExamS1;
	}

	/**
	 * SET : Type de l'examen finale Session 1
	 * @param typeExamS1 Type de l'examen finale Session 1 (String)
	 */
	public void setTypeExamS1(String typeExamS1) {
		this.typeExamS1 = typeExamS1;
	}

	/**
	 * GET : Type de l'examen final Session 2
	 * @return type de l'examen final Session 2 (String)
	 */
	public String getTypeExamS2() {
		return typeExamS2;
	}

	/**
	 * SET : Type de l'examen finale Session 2
	 * @param typeExamS1 Type de l'examen finale Session 2 (String)
	 */
	public void setTypeExamS2(String typeExamS2) {
		this.typeExamS2 = typeExamS2;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}
}
