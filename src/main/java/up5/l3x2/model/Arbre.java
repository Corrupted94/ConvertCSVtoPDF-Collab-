package up5.l3x2.model;

import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ConcurrentLinkedQueue;

import spc.up5.parchemal.shuntingyard.Node;

/**
 * 
 * @author MK.Achmerouanine
 * Cette classe renferme toutes les opérations qu'on réalise avec les arbres binaires
 * 
 * @see MaquettesApo.Sources.spc.up5.parchemal.shuntingyard.Node;
 * @see MaquettesApo.Sources.spc.up5.parchemal.shuntingyard.ShuntingYard;
 */
public abstract class Arbre extends Node{
	Stack<Node> lParcoursPrefixe;
	StringBuilder parcoursPrefixe = new StringBuilder();
	
	public Arbre(String etiq) {
		super(etiq);
		this.lParcoursPrefixe = new Stack<Node>();
		
	}
	 
 
    /**
     * 
     * @return taille La taille de l'arbre
     */
	public abstract int taille();
	protected  int taille(Node n){
		if (!n.hasFils()) return 0;
		return 1 + this.taille(n.getLeft()) + this.taille(n.getRight()); 
	}
	
	public abstract List<String> parcoursPrefixe();
	protected String parcoursPrefixe(Node n){
		
		 
		if (!n.hasFils()) {
			return  this.parcoursPrefixe.append("_").toString();
		}
		this.parcoursPrefixe.append(n.getCh());
	  System.out.print(n.getCh()); //$NON-NLS-1$
	  parcoursPrefixe(n.getLeft());
	  	this.parcoursPrefixe.append("_");
	  System.out.print("_");
	  parcoursPrefixe(n.getRight());
	  return this.parcoursPrefixe.toString();
	}
	
	public abstract void parcoursInfixe();
	protected void parcoursInfixe(Node n){
	  if (!n.hasFils()) return;
	  parcoursInfixe(n.getLeft());
	  System.out.print(n.getCh() + " "); //$NON-NLS-1$
	  parcoursInfixe(n.getRight());
	  
	}
	
	public abstract void parcoursSuffixe();
	protected void parcoursSuffixe(Node n) {
	  if (!n.hasFils())return;
	  parcoursSuffixe(n.getLeft());
	  parcoursSuffixe(n.getRight());
	  System.out.print(n.getCh() + " "); //$NON-NLS-1$
	}
	
	public abstract void parcoursPrefixeI();
	protected Stack<Node> parcoursPrefixeI(Node n){
		 if (!n.hasFils()) {
				if (!n.getRight().hasFils()) System.out.print(n.getLeft().getCh() + " ");
				if (!n.getLeft().hasFils()) System.out.print(n.getRight().getCh() + " ");
				
				return new Stack<Node>();
			}
		Stack<Node> pile = new Stack<Node>();
		Node nTemp;
		pile.push(n);
		while (!pile.isEmpty()){
			nTemp = pile.pop();
			System.out.print(nTemp.getCh() + " ");
			if (nTemp.getLeft().hasFils()) 
				pile.push(nTemp.getLeft());
			if (nTemp.getRight().hasFils())
				pile.push(nTemp.getRight());
			
		}
		return pile;
	}
	public abstract void parcoursLargeurI();
	/**
	 * Affiche l'arbre selon un parcours en largeur
	 * @param n
	 */
	protected Queue<Node> parcoursLargeurI(Node n) 
	{
		if (!n.hasFils()) {
			System.out.print(n.getCh() + " ");
			return new ConcurrentLinkedQueue<>();
		}
	  Queue<Node> file = new ConcurrentLinkedQueue<Node>();
	  file.add(n);
	  
	  Node nTemp;
	  while (!file.isEmpty()) 
	  {
	    nTemp = file.poll();
	   
	    System.out.print(nTemp.getCh() + "_");
	    if(nTemp.getRight().hasFils() && nTemp.getLeft().hasFils()) 
	    if (nTemp.getRight().hasFils())
	      file.offer(nTemp.getRight());
	    if (nTemp.getLeft().hasFils()) 
	      file.offer(nTemp.getLeft());
	  
	  }
	  
	return file;
	
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	/**
     * @param a un arbre
     * @return un boolean indiquant si a est un arbre binaire de recherche
     */
    public static boolean estABR(Arbre a) {
	/* Need a correction
	 * if (!a.hasFils())
	    return true;
	if (a.getLeft().hasFils() && (a.getLeft().getCh(). > a.getCh() ) )
	    return false;
	if ((a.getRight().hasFils()) && (a.getCh() > a.getRight().getCh()))
	    return false;
	return (estABR(a.getLeft()) && estABR(a.getRight()));
	*/
    	return false;
    }
	   
}
