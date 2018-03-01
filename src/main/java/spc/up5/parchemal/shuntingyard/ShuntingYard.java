package spc.up5.parchemal.shuntingyard;
// principe et commentaires trouvés sur https://en.wikipedia.org/wiki/Shunting-yard_algorithm
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import up5.l3x2.exception.FormuleExceptions;


public class ShuntingYard {
	/**
	 * 
	 * 
	 * @see java.util.Stack
	 * {@literal }
	 * {@docRoot }
	 * 
	 */
	
	private List<String> infixeList;

	private int currentIndex;
	private List<String> postfixeList;
	private  Stack <String> stack ;

	public ShuntingYard(String expression){
		this(new Formule(expression).getList());
	}
	/**
	 * 
	 * @param infixeList
	 */
	public ShuntingYard(List<String> infixeList){
		this.infixeList=infixeList; // 

		this.currentIndex=0;
		postfixeList=new ArrayList<String>();
		stack = new Stack<String>();

		while(this.currentIndex<this.infixeList.size()){
			String item = getNext();
			if (isLeaf(item))
				addTokenToOutput(item);
			else if (isFunction(item))
				addTokenToStack(item);
			else if (isArgumentSeparator(item))
				popStackToOuputUntilLeftParenthesis();
			else if (isOperator(item))
				popLowerPriorityOperator(Operator.getOperator(item));
			else if (isLeftParenthesis(item))
				addTokenToStack(item);
			else if (isRightParenthesis(item))
				popStackToOuputUntilLeftParenthesisAndPop();
			else throw new RuntimeException("Wrong expression ; unknown item : "+item);


		}
		popEntireStackToOutput();
	}



	private String getNext(){
		return this.infixeList.get(this.currentIndex++);
	}

	public List<String> getPostfixeList(){

		return postfixeList;
	}

	private boolean isRightParenthesis(String item) {
		return item.equals(")");
	}

	private boolean isLeftParenthesis(String item) {
		return item.equals("(");
	}

	private boolean isOperator(String item) {
		return Operator.isOperator(item);
	}
	private int getOperatorPriority(String item) {
		return Operator.getOperator(item).getPriority();
	}

	private boolean isArgumentSeparator(String item) {
		return item.equals(",");
	}

	private boolean isFunction(String item) {
		return Fonction.isFonction(item);
	}

	private boolean isLeaf(String item) {
		return ! (isRightParenthesis(item)||isLeftParenthesis(item)||isOperator(item)||isArgumentSeparator(item)||isFunction(item));
	}

	//Add token to output
	public void addTokenToOutput(String item){
		if (isLeftParenthesis(item))
			throw new RuntimeException("Wrong expression : misplaced left parenthesis");
		this.postfixeList.add(item);
		//System.out.println("Output"+this.postfixeList+"  stack="+stack);
	}

	//Push token to stack
	public void addTokenToStack(String item){
		this.stack.push(item);
		 //System.out.println("Output"+this.postfixeList+"  stack="+stack);
	}
	//Pop stack to output until "(" and pop stack for the "("
	public void popStackToOuputUntilLeftParenthesisAndPop(){
		popStackToOuputUntilLeftParenthesis();
		stack.pop();
		while(!stack.isEmpty()&&isFunction(stack.peek()))
			addTokenToOutput(stack.pop());
	}

	//Until the token at the top of the stack is a left parenthesis, pop operators off the stack onto the output queue
	public void popStackToOuputUntilLeftParenthesis(){
		if (stack.isEmpty())throw new RuntimeException("Pile vide");
		String item;
		do { item= stack.peek();
		if (!isLeftParenthesis(item))
			this.addTokenToOutput(stack.pop());
		}
		while (!isLeftParenthesis(item));
	}


	/*  If the token is an operator, o1, then:
        while there is an operator token, o2, at the top of the operator stack, and either
            o1 is left-associative and its precedence is less than or equal to that of o2, or
            o1 is right associative, and has precedence less than that of o2,
        then pop o2 off the operator stack, onto the output queue;
        To do :push o1 onto the operator stack.*/
	public void popLowerPriorityOperator(Operator operator){
		int priority=operator.getPriority();
		while (!stack.isEmpty()&&isOperator(stack.peek()) &&  getOperatorPriority(stack.peek())>=priority)
			addTokenToOutput(stack.pop());
		this.addTokenToStack(operator.getName());
	}



	//Pop entire stack to output
	public void  popEntireStackToOutput(){
		while (!stack.isEmpty()) this.addTokenToOutput(stack.pop());
	}


	public Node getNode(){
		Node node=null;
		Stack<Node> nodes=new Stack<Node>();
		for (String item:this.postfixeList){
			node = new Node(item);
			if (this.isOperator(item)||this.isFunction(item)){
				int arite=2;
				if (this.isFunction(item)) arite=Fonction.getFonction(item).getArite();
				for (int i=0;i<arite;i++)
					node.addFirst(nodes.pop());


			}

			nodes.push(node);
		}
		return node;



	}

	public static void verifier(String input,String treeOutput,String ... postfixeOutput) throws FormuleExceptions{
		List<String> list = new Formule(input).getList();
		//System.out.println("List="+list);
		ShuntingYard sy = new ShuntingYard(list);

		List<String> post = sy.getPostfixeList();
		//System.out.println("Post="+post.toString());
		if (!post.equals(Arrays.asList(postfixeOutput))) throw new FormuleExceptions("ERROR POSTFIXE");
			//System.out.println("ERROR POSTFIXE");

		String tree=sy.getNode().toString();
		//System.out.println("Tree (sy.getNode())="+tree);
		if (!tree.equals(treeOutput)) throw new FormuleExceptions("ERROR TREE :"+tree);
			//System.out.println("ERROR TREE :"+tree);
	}

	public static void main(String [] args) throws FormuleExceptions{
		String formule1 = "max(((n1*c1+n2*c2+n3*c3+n4*c4+n5*c5+n6*c6+n7*c7+n8*c8+n9*c9+n10*c10+n11*c11)/(c1+c2+c3+c4+c5+c6+c7+c8+c9+c10+c11)), ((n12*c12+n13*c13)/(c12+c13)))";
		String formule2 = "(n1*c1+n2*c2+n3*c3+n4*c4+n5*c5+n6*c6+n7*c7)/(c1+c2+c3+c4+c5+c6+c7)";
		String formule3 = "max("+formule2+",(n12*c12+n13*c13)/(c12+c13))";
		List<String> list1 = new Formule(formule1).getList();
		List<String> list2 = new Formule(formule2).getList();
		
		ShuntingYard sy1 = new ShuntingYard(list1);
		ShuntingYard sy2 = new ShuntingYard(list2);
		
		System.out.println(sy1.getPostfixeList());
		System.err.println(sy1.getNode());
		System.out.println(sy2.getPostfixeList());
		System.err.println(sy2.getNode());
		
		List<String> list3 = new Formule(formule3).getList();
		ShuntingYard sy3 = new ShuntingYard(list3);
		System.out.println(sy3.getPostfixeList());
		System.err.println(sy3.getNode());
		verifier(formule2,sy2.getNode().toString(),sy2.getPostfixeList().toString());
		/*verifier("3+5","+{3,5}","3","5","+");
		verifier("(n1*c1+n2*c2)/(c1+c2)","/{+{*{n1,c1},*{n2,c2}},+{c1,c2}}","n1","c1","*","n2","c2","*","+","c1","c2","+","/");
		verifier("3+5*8","+{3,*{5,8}}","3","5","8","*","+");
		verifier("(3+5)*8","*{+{3,5},8}","3","5","+","8","*");
		verifier("max(5,7)","max{5,7}","5","7","max_2");
		verifier("max(5,7,9)","max{5,7,9}","5","7","9","max_3");
		verifier("max(5,7)+25","+{max{5,7},25}","5","7","max_2","25","+");
		verifier("4+max(5,7)+25","+{+{4,max{5,7}},25}","4","5","7","max_2","+","25","+");
*/
	}
}
