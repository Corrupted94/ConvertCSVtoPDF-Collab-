package spc.up5.parchemal.shuntingyard;
import java.util.HashMap;
import java.util.Map;


public class Fonction {
   private String name;
   private int arite;
   
   private static Map<String,Fonction> map=new HashMap<String,Fonction>();
   
   static {
	   new Fonction("min",-1);
	   new Fonction("max",-1);// -1 : unknown
	   new Fonction("max_2",2);
	   new Fonction("max_3",3);
	   new Fonction("max_4",4);
	   new Fonction("max_5",5);
	   new Fonction("max_6",6);
	   new Fonction("max_7",7);
	   new Fonction("max_8",8);
	     }
   
   public Fonction(String name, int arite) {
	super();
	this.name = name;
	this.arite = arite;
	Fonction.map.put(name,this);
}

public String getName() {
	return name;
}

public int getArite() {
	return arite;
}
   
public static boolean isFonction(String name){
	return map.containsKey(name);
}
public static Fonction getFonction(String name){
	Fonction function = map.get(name);
	/*if (function==null)
		throw new ShuntingYardException("Unknown function : "+name);*/
	return function;
}
   
}
