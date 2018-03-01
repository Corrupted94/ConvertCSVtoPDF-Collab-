package spc.up5.parchemal.shuntingyard;
import java.util.HashMap;
import java.util.Map;


public class Operator {
   private String name;
   private int priority;
   
   private static Map<String,Operator> map=new HashMap<String,Operator>();
   
   static {
	   new Operator("+",1);
	   new Operator("-",1);
	   new Operator("*",2);
	   new Operator("/",2);
   }
   
   public Operator(String name, int priority) {
	super();
	this.name = name;
	this.priority = priority;
	Operator.map.put(name,this);
}

public String getName() {
	return name;
}

public int getPriority() {
	return priority;
}
   
public static boolean isOperator(String name){
	return map.containsKey(name);
}
public static Operator getOperator(String name){
	Operator operator = map.get(name);
	if (operator==null)
		throw new RuntimeException("Unknown operator");
	return operator;
}
   
}
