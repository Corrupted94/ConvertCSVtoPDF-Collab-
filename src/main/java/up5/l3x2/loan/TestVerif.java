package up5.l3x2.loan;

import java.util.List;

import spc.up5.parchemal.shuntingyard.Formule;
import spc.up5.parchemal.shuntingyard.Node;
import spc.up5.parchemal.shuntingyard.Operator;
import spc.up5.parchemal.shuntingyard.ShuntingYard;


public class TestVerif {

	public static void main(String[] args) {
		String chaine;
		String formule0 = "((n1*c1)+(n2*c2)+(n3*c3)+(n4*c4)+(n5*c5)+(n6*c6)+(n7*c7))/(c1+c2+c3+c4+c5+c6+c7)"; //bonne
		String formule1 = "max((n1*c1+n2*c2)/(c1+c2),n2)"; //max bon
		String formule2 = "(n1*c1+n2*c2+n3*c8+n4*c4+n5*c5+n6*c6+n7*c7)/(c1+c2+c3+c4+c5+c6+c7)";//mauvaise 
		String formule3= "(n1*c1+n2*c2+n3*c3)/(c1+c2)";//mauvaise
		String formule4= "(n1*c1+n2*c2)/(c2+c1)";
		boolean max = false;
		boolean moyenne = true;
		int taille=0;
		List<String> list1 = new Formule(formule4).getList();// On change la formule ici pour les test
		ShuntingYard sy1 = new ShuntingYard(list1);
		chaine = sy1.toString(); //Permet de pouvoir travailler sur la string
		System.out.println(chaine);
		max=maxOuNon(chaine); // Appel a la méthode pour determiner si on a un Max ou Non , Vrai pour oui Faux pour non
		System.out.println("Est ce un max "+max);
		taille=nbNotes(chaine); // Retourne le nombre de notes
		System.out.println("Le nombre de notes "+taille);
		moyenne = verifFormule(chaine); //Verifie si la formule est une moyenne pondérée
		System.out.println("Est ce une moyenne ponderee "+moyenne);
	}

	public static boolean maxOuNon(String s){
		if(s.startsWith("max"))return true;
		else return false;
	}
	
	public static int nbNotes(String s){
		int cpt=0;
		for(int i=0;i<s.length();i++){
			if (s.charAt(i)=='*') cpt++;
		}
		return cpt;
	}

	public static boolean verifFormule(String s){
		boolean max;
		max=maxOuNon(s);
		boolean verif=true;
		int compteurAccOuverte=0, compteurAccFerme=0,compteurPlus=0,compteurPlus2=0,compteurFois=0;
		char premier; 
		if(!max){ //Dans le cas d'un max il n'y a pas le "/" en premier
			premier=s.charAt(0);
			if (premier!='/')return false;
		}
		for(int i=0;i<s.length();i++){
			if (s.charAt(i)=='{') compteurAccOuverte++;
			if (s.charAt(i)=='}') compteurAccFerme++;
			if (s.charAt(i)=='+') compteurPlus++;
			if (s.charAt(i)=='*') compteurFois++;
		}
		if (compteurAccOuverte-compteurAccFerme !=0) verif=false;
		if ((compteurFois-1)-(compteurPlus/2)!=0) verif=false; //Il y a un * de plus de part l'algo et on compte les + 2 fois
		// Les 2 test précédent permettent de vérifier le nombre d'élément

		//Ce test la permet de vérifier le numérateur
		int j=0;
		compteurPlus2=compteurPlus/2; //On met CompteurPlus2 au nombre de "+" réel de la formule
		compteurPlus=0;compteurFois=0; //On réinitialise les variables avant de retravailler avec
		boolean present;
		present = s.contains("c") && s.contains("n"); //Permet de vérifier qu'il y a au moins un c et un n dans la formule
		if (present==false) verif=false;
		char numero;
		while(j<s.length()&&compteurPlus<compteurPlus2+1){ //Tant qu'on est pas allez au bout de la string ET que l'on ne dépasse pas le nombre de + réel de la formule
			
			if (s.charAt(j)=='+') compteurPlus++;
			if (s.charAt(j)=='*') compteurFois++;
			if (s.charAt(j)=='n')
			{//On fait directement les traitement souhaités lorsqu'on rencontre un "n"
				j++;
				numero= s.charAt(j);
				if(Character.getNumericValue(numero)!=compteurFois)verif=false;
				j=j+2;
				if(s.charAt(j)!='c') verif=false;
				j++;
				numero= s.charAt(j);
				if(Character.getNumericValue(numero)!=compteurFois) verif=false;	
			}	
			if (s.charAt(j)=='c')
			{
				//On fait directement les traitement souhaités lorsqu'on rencontre un "c"
				j++;
				numero= s.charAt(j);
				if(Character.getNumericValue(numero)!=compteurFois) verif=false;
				j=j+2;
				if(s.charAt(j)!='n') verif=false;
				j++;
				numero= s.charAt(j);
				if(Character.getNumericValue(numero)!=compteurFois) verif=false;
			}	
			j++;
		}
		//Test du dénominateur
		int indicePlus=0;
		for(int i=0;i<s.length();i++){
			if (s.charAt(i)=='+') {
				compteurPlus++;
				indicePlus=i; //On recupère l'indice du dernier + de la formule
			}				  //Cela nous permet de faire la string suivante	
		}
		String denominateur=s.substring(indicePlus+1, s.length());
		int compteurC=0;
		for(int i=0;i<denominateur.length();i++){
			if (denominateur.charAt(i)=='c'){
				i++;
				compteurC++; //On compte le nombre de c pour qu'il corresponde bien à l'indice
				numero= denominateur.charAt(i);
				if(Character.getNumericValue(numero)!=compteurC)verif=false; //On vérifie l'indice
			}
		}
		return verif;
	}
}

