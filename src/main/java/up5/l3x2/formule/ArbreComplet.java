/**
 * 
 */
package up5.l3x2.formule;

import java.util.ArrayList;
import java.util.List;

import spc.up5.parchemal.shuntingyard.Fonction;
import spc.up5.parchemal.shuntingyard.Formule;
import spc.up5.parchemal.shuntingyard.Node;
import spc.up5.parchemal.shuntingyard.ShuntingYard;


/**
 * 
 * @author MK.Achmerouanine
 * 
 *
 */
public class ArbreComplet extends ShuntingYard {
	Formule f ;
	String[] lPost;
	String typeConnu;
	private Node arbre;
	private List<Node> arbresFils =new ArrayList<>();
	private List<String> 
	
	lFeuilles = new ArrayList<>(),
	parcoursPrefixe = new ArrayList<>(),
	parcoursInfixe = new ArrayList<>(),
	parcoursSuffixe = new ArrayList<>();
	
	
	/**
	 * @author MK.Achmerouanine
	 * Créer l'arbre à partir d'une chaine de caractère
	 * @param expression
	 */
	public ArbreComplet(String expression) {
			this(new Formule(expression));
	}

	/**
	 * @param infixeList
	 */
	public ArbreComplet(Formule f) {
		super(f.getList());
		this.f = f;
		this.arbre = super.getNode();
		this.lPost = new String[super.getPostfixeList().size()];int i=0;
		for (String string : super.getPostfixeList()) {
			 lPost[i] = string ;
			 i++;
		}
		Fonction fonc;
		if (!f.getList().isEmpty())
		if(Fonction.isFonction(f.getList().get(0))) {
			fonc = Fonction.getFonction(f.getList().get(0));
			typeConnu = fonc.getName();
			for (i=0;i<fonc.getArite();i++) {
				this.arbresFils.add((Node) super.getNode().getFils().get(i));
			}
		}
	}
	
	public String[] getlPost() {
		return lPost;
	}

	public Node getArbre() {
		return arbre;
	}

	@Override
	public List<String> getPostfixeList() {
		// TODO Auto-generated method stub
		return super.getPostfixeList();
	}
	@Override
	public Node getNode() {
		// TODO Auto-generated method stub
		return super.getNode();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.arbre.toString();
	}

	public List<String> getPPList(){
		this.parcoursPrefixe(arbre);
		return parcoursPrefixe;
	}
 	
    /**
     * 
     * @return taille La taille de l'arbre
     */
	
	public  int taille(Node n){
		if (!n.hasFils()) return 0;
		return 1 + taille(n.getLeft()) + taille(n.getRight()); 
	}
 	public List<String> getPIList(){
		this.parcoursInfixe(arbre);
		return parcoursInfixe;
	}
 	public List<String> getlFeuilles() {
 		this.getFeuilles(arbre);
		return lFeuilles;
	}
	private void getFeuilles(Node n){
		if(!n.hasFils()) lFeuilles.add(n.getCh());
		else{
			getFeuilles(n.getLeft());
			getFeuilles(n.getRight());
		}
		
	}
	public List<String> getPSList() {
		this.parcoursSuffixe(arbre);
		return parcoursSuffixe;
	}
	private void parcoursPrefixe(Node n){
		if(!n.hasFils()) return;	
			parcoursPrefixe.add(n.getCh());
			parcoursPrefixe(n.getLeft());
			parcoursPrefixe(n.getRight());
		
	}
	private void parcoursInfixe(Node n){
		  
		  if(n.getLeft().hasFils() ) parcoursInfixe(n.getLeft());
		  parcoursInfixe.add((n.getCh()));
		  if(n.getRight().hasFils()) parcoursInfixe(n.getRight());
		  if (!n.hasFils()) parcoursInfixe.add((n.getCh()));
	}
	public List<Node> getArbresFils() {
		return arbresFils;
	}
	private void parcoursSuffixe(Node n) {
	  if (!n.hasFils())return;
	  parcoursSuffixe(n.getLeft());
	  parcoursSuffixe(n.getRight());
	  parcoursSuffixe.add(n.getCh());
	}
	public Formule getF() {
		return f;
	}
	
}
