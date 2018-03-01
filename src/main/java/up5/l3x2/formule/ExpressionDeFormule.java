package up5.l3x2.formule;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;
import java.util.StringTokenizer;

import spc.up5.parchemal.shuntingyard.Fonction;
import spc.up5.parchemal.shuntingyard.Formule;
import spc.up5.parchemal.shuntingyard.Node;
import spc.up5.parchemal.shuntingyard.Operator;
import spc.up5.parchemal.shuntingyard.ShuntingYard;
import up5.l3x2.exception.FormuleExceptions;


/**
 * 
 * @author MK.Achmerouanine
 * @see spc.up5.parchemal.shuntingyard
 * @see typesDeFormule 
 * {@docRoot}
 * 
 * 
 */
public class ExpressionDeFormule{

	
	private final String fIX ="x";
	Formule f;
	private 
	int typeProv;				// Type de la formule
	
	private enum 
	typesDeFormule{
		pondereePossible, maxPossible,	
		ponderee,			//3 La formule est pondérée
		max,				//4 La formule est Max de 2 élements
		nonSupporte			//5 La formule comporte des erreurs
		
	};
	
	private enum delimEnum{ 
		c,n,max //1 Coef //2 Notes //3 Max
	};
	String[] lpost;
	
	private String expression;				// La formule en String
	
	
	private ArbreComplet 
	arbre,
	arbreMod;
	
	private List<String>
	lOperation = new ArrayList<String>();
	private Set<String>
	lNotes = new HashSet<>(),
	lCoef = new HashSet<>();
	
	
	/**
	 * Création de l'arbre directement à partir de l'expression sous format String 
	 * 
	 * @param expression La formule tel importée du fichier csv 
	 * @throws FormuleExceptions 
	 * @see ArbreComplet
	 * @see spc.up5.parchemal.ShuntingYard
	 */
	public ExpressionDeFormule(String expression) throws FormuleExceptions{
		this.f = new Formule(expression);
		this.expression = expression;
		
		this.arbre = new ArbreComplet(this.f);
		//this.lpost = arbre.getlPost();
		
		
		LinkedList<String> ll = new LinkedList<>(arbre.getPostfixeList());
		
		if(Fonction.isFonction(ll.getLast())){
			this.lOperation = this.getOperations(this.f);
			//System.out.println(lOperation);
			for (String string : this.arbre.getlFeuilles()) {
				if( !Operator.isOperator(string) && string.length()>1) {		
					if(string.startsWith(delimEnum.n.name()) ) this.lNotes.add(string); 
					if(string.startsWith(delimEnum.c.name()) ) this.lCoef.add(string);						
				}
			}
			try{
				ShuntingYard.verifier(expression, arbre.toString(), arbre.getlPost());
			}catch(FormuleExceptions f){
				f.printStackTrace();
			}
		}else{
			//this.typeProv = this.verifForm(expression);
			this.lOperation.add(this.expression);
			if(this.typeProv == 0) this.arbreMod = new ArbreComplet(expression.replace("*", this.fIX));
			for (String string : this.arbre.getlFeuilles()) {
				if( !Operator.isOperator(string) && string.length()>1) {		
					if(string.startsWith(delimEnum.n.name()) ) this.lNotes.add(string); 
					if(string.startsWith(delimEnum.c.name()) ) this.lCoef.add(string);						
				}
			}
			try{
				ShuntingYard.verifier(expression, arbre.toString(), arbre.getlPost());
			}catch(FormuleExceptions f){
				f.printStackTrace();
			}
			//System.out.println(arbreMod.getPostfixeList());
		}
		

		
	}
	/**
	 * @author MK.Achmerouanine
	 * @see spc.up5.parchemal.Formule 
	 * @param formule un objet de la classe Formule
	 * 
	 * @return La liste des sous formules.<Strong> Important ! </strong> <h1>Le premier element de cette liste est la formule entrée en paramètre</h1>
	 */
	private List<String> getOperations(Formule formule) {
		// TODO Auto-generated method stub
		List<String> 
		lFormule = formule.getList(),
		lFormuleFinal = new ArrayList<String>();
		lFormuleFinal.add(this.expression);
		LinkedList<String> lForm = new LinkedList<String>();
		
		for (String string : lFormule) {
			lForm.add(string);
		}
		lForm.removeFirst();
		lForm.removeFirst();
		lForm.removeLast();
		StringBuffer formula = new StringBuffer();
		//System.out.println(lForm.toString());
		for (String string : lForm) {
			if(string != " ") formula.append(string);
		}
		StringTokenizer st = new StringTokenizer(formula.toString(), ",");
		//System.out.println(st.countTokens());
		while (st.hasMoreElements()) {
			String form = (String) st.nextToken();
			Formule fb = new Formule(form);
			ArbreComplet acb = new ArbreComplet(fb);
			try {
				ShuntingYard.verifier(form, acb.toString(), acb.lPost);
			} catch (FormuleExceptions e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			lFormuleFinal.add(form);
			//System.out.println(form);
		}
			//System.out.println(lFormuleFinal.toString());
			return lFormuleFinal;
	}
	/**
	 * 
	 * @param expression l'expression tel importée de la formule.
	 * @author MK.Achmerouanine
	 * @return Vrai si la vérification est faites
	 * @throws FormuleExceptions 
	 * @throws ShuntingYardException 
	 */
	public int verifForm(String expression) throws FormuleExceptions {	
			Formule f = new Formule(expression);
			//System.out.println(f.getList());
			if(expression.contains("/") || f.getList().size() ==1) return typesDeFormule.pondereePossible.ordinal();
			if(Fonction.isFonction(f.getList().get(0))) return typesDeFormule.maxPossible.ordinal();
			ShuntingYard.verifier(expression, this.arbre.toString(), this.lpost);
			throw new FormuleExceptions("La formule est "+typesDeFormule.nonSupporte.name()+" !");				
	}
	
	/**
	 * <blockquote>Cette méthode renvoie le type de la formule. Si le type n'est pas reconnu</blockquote> 
	 * @author MK.Achmerouanine
	 * @return le type de la formule
	 * 
	 */
	public String checkTypeFormule() throws FormuleExceptions{
		
		if(isFormulePonderee() != -1) return typesDeFormule.ponderee.name();
		if(isFormuleWithFunction()) return typesDeFormule.max.name();
		return typesDeFormule.nonSupporte.name();
	}

	/**
	 * Vérifier si la formule est de type: Max(element1,element2) 
	 * @author MK.Achmerouanine
	 * @return Vrai si la formule est de type max(<T>element1,<T>element2) 
	 * @exception  FormuleExceptions 
	 * @throws {@link FormuleExceptions}
	 * 
	 */
	public boolean isFormuleMax() throws FormuleExceptions{	
		if(Fonction.getFonction(this.f.getList().get(0))!=null && Fonction.getFonction(this.f.getList().get(0)).getArite() == 2 && isFormuleWithFunction()) return true;
		return false;
	}
	private boolean isFormuleWithFunction()  {
		Formule f = new Formule(this.expression);
		boolean retour = false;
		if(Fonction.isFonction(f.getList().get(0))) retour = true;	
		return retour;
	}
	/**
	 * @author MK.Achmerouanine
	 * @return i <strong>i = -1</strong> si la formule n'est pas pondérée. i represente le nombre d'elements de la formule pondérée
	 * 			
	 * 
	 */
	public int isFormulePonderee(){
		if(!isFormuleWithFunction() && this.getType() == 0 && arbreMod.getNode().hasFils() ){
			if(arbreMod.getlFeuilles().isEmpty()) return 0;
			int tailleG, tailleD = 0;
			tailleG= this.arbre.taille(this.arbreMod.getNode().getLeft());
			tailleD= this.arbre.taille(this.arbre.getNode().getRight());		
			if(tailleG == tailleD) return 1+tailleG; //Verification faite hasfils()
			
		}
		return -1;
	}
	public int isFormulePonderee(Node n){
		if(n.getCh() == "/");
		return -1;
	}
	public int getType() {
		return this.typeProv;
	}
	public ArbreComplet getArbre() {
		return this.arbre;
	}
	public ArbreComplet getArbreMod() {
		return this.arbreMod;
	}
	public String toString(ExpressionDeFormule edf){
		StringBuilder sb = new StringBuilder();
		sb.append("|_____Element n°"+this.lOperation.indexOf(edf.getExpression())+" :"+edf.getExpression()+"\n");
		try {
			sb.append("\t\tType :" +edf.checkTypeFormule()+"\n");
			if(edf.checkTypeFormule() == "ponderee"){
				sb.append("\t\tPrécision : ponderee de "+edf.isFormulePonderee()+" elements.\n"
						+ "_________________________________________________________________\n");
			}
			if(edf.checkTypeFormule() == "max"){
				
				int nbOper= edf.lOperation.size()-1;
				sb.append("\t\tEn plus : max de "+nbOper+" elements.\n"
						+ "\t\t_________________________________________\n");
				
				for (int i=1;i<edf.lOperation.size();i++) {
					sb.append("\t"+edf.toString());
				}
			}
		} catch (FormuleExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		sb.append("Formule :"+expression+"\n");
		try {
			sb.append("Type :"+this.checkTypeFormule()+"\n");
			if(this.checkTypeFormule() == "ponderee"){
				sb.append("Précision : ponderee de "+isFormulePonderee()+" elements.\n");
			}
			if(this.checkTypeFormule() == "max"){
				
				int nbOper= this.lOperation.size()-1;
				sb.append("En plus : max de "+nbOper+" elements.\n");
				for (int i=1;i<this.lOperation.size();i++) {
					ExpressionDeFormule edf = new ExpressionDeFormule(this.lOperation.get(i));
					sb.append(this.toString(edf));
				}
			}
		} catch (FormuleExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sb.append("La liste des notes extraites de cette formule ("+lNotes.size()+" notes distincts) :"+this.lNotes+"\n");
		sb.append("La liste des coeficients extraits de cette formule ("+this.lCoef.size()+" coeficients distincts) :"+this.lCoef+"\n");
		return sb.toString();
	}
	public String getExpression() {
		return expression;
	}
	public int taille() {
		return this.taille();
	}
	/**
	 * Retourne et affiche à la console la liste des coefficiens importés de la formule
	 * @return La liste des coefficiens importés de la formule
	 */
	public Set<String> getlCoef() {
		return this.lCoef;
	}
	public Set<String> getlNotes() {
		return this.lNotes;
	}
	/**
	 * @author MK.Achmerouanine
	 * @param nbElements
	 * @return une formule pondérée de nbElements.
	 */
	public static String getFormulePonderee(int nbElements){
		//Opérations:
		StringJoiner 
		sbSommeOper = new StringJoiner("+"),
		sbSommeCoef = new StringJoiner("+");
		
		for (int i = 1; i < nbElements+1; i++) {
			StringJoiner
			sbMul = new StringJoiner("*");
			String		
			n = new String("n"+i),
			c = new String("c"+i);
			sbMul.add(n);sbMul.add(c+"");			
			sbSommeOper.add(sbMul.toString());			
			sbSommeCoef.add(c);			
		}
		//Dénominateur:
		StringBuffer 
		formule = new StringBuffer();
		formule.append("(").append(sbSommeOper).append(")").append("/").append("(").append(sbSommeCoef).append(")");
		
		return formule.toString();
		
	}


	

}
