package up5.l3x2.model;

public class FormuleCoeff {

	private float coeff;
	private String formule;
	
	public FormuleCoeff ()
	{
		formule = "";
	}

	/**
	 * GET : Coefficient
	 * @return Coefficient ou -1 s'il n'y a pas de valeur
	 */
	public float getCoeff() {
		if (coeff ==0) return -1;
		else return coeff;
	}

	/**
	 * SET: Coefficient
	 * @param coeff Coefficient
	 */
	public void setCoeff(float coeff) {
		this.coeff = coeff;
	}

	/**
	 * GET : Formule 
	 * @return Formule ou -1 s'il n'y a pas de valeur
	 */
	public String getFormule() {
		if (formule.equals(null)) return "-1";
		else return formule;
	}

	/**
	 * SET : Formule
	 * @param formule Formule (String)
	 */
	public void setFormule(String f) {
		this.formule = supprimerEspacement(f);
	}
	
	
	public String supprimerEspacement (String f)
	{
		StringBuffer sb = new StringBuffer ("");
		if (f!=null)
		{
			for (int i = 0; i <f.length(); i++)
			{
				if (f.charAt(i) != ' ') sb.append(f.charAt(i));
			}
		}
		return sb.toString();
	}
}
