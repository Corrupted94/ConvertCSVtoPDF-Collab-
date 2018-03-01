package up5.l3x2.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ConcurrentLinkedQueue;

import spc.up5.parchemal.shuntingyard.Fonction;
import spc.up5.parchemal.shuntingyard.Node;
import spc.up5.parchemal.shuntingyard.Operator;
import spc.up5.parchemal.shuntingyard.ShuntingYard;

/**
 * 
 * @author MK.Achmerouanine
 * @see spc.up5.parchemal.shuntingyard
 * 
 */
public class ArbreDeFormule extends Arbre{

	private String type;
	private Node arbre;
	private Node arbreOrganisee;
	private List<String> postFixeList;
	private List<String> feuilles;
	private List<String> lNotes;
	private List<String> lCoef;
	
	/**
	 * 
	 * @param sy Objet ShuntingYard
	 */
	private ArbreDeFormule(ShuntingYard sy) {
		// TODO Auto-generated constructor stub
		super(sy.getNode().toString());
		this.arbre = sy.getNode();
		this.postFixeList = sy.getPostfixeList();
		this.feuilles = new ArrayList<>();
		this.lNotes = new ArrayList<>();
		this.lCoef = new ArrayList<>();
		if(!this.postFixeList.isEmpty()){
		for (String string : this.postFixeList) {
			if(this.getPostFixeList().contains(string) && !Operator.isOperator(string) && string.length()>1) {
				
				if(string.startsWith("n") && !this.lNotes.contains(string)) this.lNotes.add(string); //$NON-NLS-1$
				if(string.startsWith("c") && !this.lCoef.contains(string)) this.lCoef.add(string); //$NON-NLS-1$
				this.feuilles.add(string);
			}
			if(this.arbre.getCh().equals(Operator.getOperator("/").getName()) && this.arbre.hasFils()) this.type = "division";
			if(Fonction.isFonction(this.feuilles.get(this.feuilles.size()-1))) this.type = this.feuilles.get(this.feuilles.size()-1); 
		}
		}
	}
	/**
	 * Création de l'arbre directement à partir de l'expression sous format String
	 * 
	 * @param expression Format String 
	 * @see spc.up5.parchemal.shuntingyard
	 */
	public ArbreDeFormule(String expression){
		this(new ShuntingYard(expression));
	}
	private boolean isNote(){
		return true;
	}
	private boolean leftIsOperation() {
		return Operator.isOperator(this.arbre.getLeft().getCh());
	}
	private boolean rightIsOperation(){
		return Operator.isOperator(this.arbre.getRight().getCh());
	}
	private void checkCoef(){
		// TODO Auto-generated method stub
		if(lNotes.size() == lCoef.size()){
			for (String note : lNotes) {
				
			}
		}else if (true) {
			
		}

	}
	public boolean isCorrectExpression(){
		if(isFormulePonderee() || isFormuleWithFunction()) return true;
		return false;
	}
	/**
	 * 
	 * @return vrai si la formule 
	 */
	private boolean isFormuleWithFunction() {
		if(Fonction.isFonction(this.arbre.getCh())) return true;
		for (Node node : this.lParcoursPrefixe) {
			if(Fonction.isFonction(node.getCh())) return true;
		}
		return false;
	}
	public boolean isFormulePonderee() {
		if(Operator.isOperator(this.arbre.getCh()) && this.arbre.hasFils() && !this.isFormuleWithFunction()){
			
			return true;
		}
		if(this.arbre.hasFils() && Fonction.isFonction(this.arbre.getCh())) return false;
		return false;
	}
	public String getType() {
		if(this.arbre.getCh().equals(Operator.getOperator("/").getName()) && this.arbre.hasFils()) return "Formule pondérée possible.";
		
		return this.type;
	}
	public Node getArbre() {
		return this.arbre;
	}
	public List<String> getFeuilles() {
		return feuilles;
	}
	public List<String> getPostFixeList() {
		return this.postFixeList;
	}

	public String toString() {
		return super.toString();
	}
	
	@Override
	public List<String> parcoursPrefixe() {
		// TODO Auto-generated method stub
		String parcoursPref = this.parcoursPrefixe(this.arbre);
		for (int i = 0; i < parcoursPref.length(); i++) {
			String operation = String.valueOf(parcoursPref.charAt(i));
			this.postFixeList.add(operation);
		}
		return this.postFixeList;
		
		
	}
	@Override
	public void parcoursInfixe() {
		// TODO Auto-generated method stub
		this.parcoursInfixe(this.arbre);
	}
	@Override
	public void parcoursSuffixe() {
		// TODO Auto-generated method stub
		this.parcoursSuffixe(this.arbre);
	}
	@Override
	public int taille() {
		// TODO Auto-generated method stub
		return this.taille(this.arbre);
	}
	@Override
	public void parcoursPrefixeI() {
		// TODO Auto-generated method stub
		Stack<Node> pile = this.parcoursPrefixeI(this.arbre);
		for (Node node : pile) {
			System.out.println(node.toString());
		}
	}
	@Override
	public void parcoursLargeurI() {
		// TODO Auto-generated method stub
		Queue<Node> file = this.parcoursLargeurI(this.arbre);
		for (Node node : file) {
			System.out.println(node.toString());
		}
		
	}
	public List<String> getlCoef() {
		return this.lCoef;
	}
	public List<String> getlNotes() {
		return this.lNotes;
	}
	
	public void organiserArbre(Arbre a){
		
		Queue<Node> file = new ConcurrentLinkedQueue<Node>();
		this.parcoursLargeurI();
		file.add((Node) a);
		for (Node node : file) {
			
			System.err.println(node.toString());
			System.err.println();
		}
		
		
		
		
		
	}
	

}
