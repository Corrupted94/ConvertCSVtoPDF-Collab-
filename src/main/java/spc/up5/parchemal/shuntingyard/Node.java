package spc.up5.parchemal.shuntingyard;
import java.util.ArrayList;
import java.util.List;


public class Node {
    private String etiq;
    private List<Node> fils = new ArrayList<Node>();
	public Node(String etiq) {
		super();
		int index = etiq.indexOf('_');
		if (index!=-1)
			etiq=etiq.substring(0, index);
		this.etiq = etiq;
	}     
    
    public void add(Node node){
    	fils.add(node);
    }
	public void addFirst(Node node) {
		fils.add(0,node);
		
	}
    public Node getLeft( ){
    	return getNode(0);
    }
    public Node getRight( ){
    	return getNode(1);
    }
    
    public String getCh(){
    	return etiq;
    }

    
    public Node getNode(int index){
    	try {return fils.get(index);}
    	catch (java.lang.ArrayIndexOutOfBoundsException exp){
    		throw new ShuntingYardException(exp); 		
    	}
    }
    public List<Node> getFils() {
		return fils;
	}
    public void setNode(String etiq, List<Node> fils)
    {
    	this.etiq=etiq;
    	this.fils=fils;
    }
    
    public boolean hasFils()
    {
    	boolean retour=true;
    	if (fils.isEmpty())
    		retour=false;
    	return retour;
    }
    public String toString(){
    	StringBuffer sb = new StringBuffer();
    	sb.append(etiq);
    	if (fils.size()>0){
    		sb.append("{");
    		for (int i=0;i<fils.size();i++){
    			if (i>0)sb.append(",");
    			sb.append(fils.get(i));
    			}
    		sb.append("}");
    		
    	}
    	return sb.toString();
    }


    
}
