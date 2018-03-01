 package up5.l3x2.model;

public class Epreuve {
	
	private String codeEPR;
	private String cod_tep;
	private FormuleCoeff formuleCoeff;
	private int session;
	
	public Epreuve(String codeEPR) {
		
		this.codeEPR = codeEPR;
		this.formuleCoeff = new FormuleCoeff();
	}

	/**
	 * GET : code de l'EPREUVE
	 * @return Code de l'Epreuve
	 */
	public String getCodeEPR() {
		return codeEPR;
	}

	/**
	 * SET : code de l'EPREUVE
	 * @param codeEPR Code de l'Epreuve (String)
	 */
	public void setCodeEPR(String codeEPR) {
		this.codeEPR = codeEPR;
	}

	/**
	 * GET : Type de l'Epreuve (CC, CT ou TR)
 	 * @return cod_tep
	 */
	public String getCod_tep() {
		return cod_tep;
	}

	/**
	 * SET : Type Epreuve
	 * @param cod_tep Type de l'Epreuve (String)
	 */
	public void setCod_tep(String cod_tep) {
		this.cod_tep = cod_tep;
	}


	/**
	 * GET : Session
	 * @return Numero de Session
	 */
	public int getSession() {
		return session;
	}

	/**
	 * SET : Session
	 * @param session Numero de session
	 */
	public void setSession(int session) {
		this.session = session;
	}

	/**
	 * GET : Objet FormuleCoeff
	 * @return Objet FormuleCoeff de l'Epreuve
	 */
	public FormuleCoeff getFormuleCoeff() {
		return formuleCoeff;
	}

}
