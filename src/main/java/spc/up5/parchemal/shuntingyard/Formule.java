package spc.up5.parchemal.shuntingyard;
import java.util.ArrayList;
import java.util.List;


public class Formule {
	private List<String> items = new ArrayList<String>();
	
    public Formule(String text){
    	int index=0;
    	while (index<text.length()){
    		if (!Character.isLetterOrDigit(text.charAt(index)))
    			items.add(""+text.charAt(index++));
    		else {
    			int sep=index+1;
    			while(sep<text.length()&&Character.isLetterOrDigit(text.charAt(sep)))
    				sep++;
    			items.add(""+text.substring(index,sep));
    			index=sep;
    		}
    				
    	}
    	for (int i=0;i<items.size();i++)
    		if (Fonction.isFonction(items.get(i))){
    			String item=items.get(i);
    		
    			int arite=getAriteFonction(i);
    			items.set(i,item+"_"+arite);
    		}
    }
    
    public List<String> getList(){
    	return items;
    }
    
    public int getAriteFonction(int index){
    	int cptPar=0;
    	int cptVir=0;
    	for (int i=0;i<items.size();i++){
       		if (items.get(i).equals("(")) cptPar++;
       		if (items.get(i).equals(")")) cptPar--;
       		if (cptPar==1&&items.get(i).equals(",")) cptVir++;
    	}
    	return cptVir+1;
       	    		
    	}
    		
    }
