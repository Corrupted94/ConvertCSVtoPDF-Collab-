package up5.l3x2.app;


import up5.l3x2.exception.FormuleExceptions;
import up5.l3x2.formule.ExpressionDeFormule;

public class FormMain {


		public static void main(String[] args) {
			String formule = "(n1*c1+n2*c2+n3*c3+n4*c4+n5*c5+n6*c6+n7*c7)/(c1+c2+c3+c4+c5)";
			String formule0 = ExpressionDeFormule.getFormulePonderee(5);
			String formule1 = "max(((n1*c1+n2*c2+n3*c3+n4*c4+n5*c5+n6*c6+n7*c7+n8*c8+n9*c9+n10*c10+n11*c11)/(c1+c2+c3+c4+c5+c6+c7+c8+c9+c10+c11)),((n12*c12+n13*c13)/(c12+c13)),n2*c1)";
			String formule2 = "(n1*c1+n2*c2+n3*c3+n4*c4+n5*c5+n6*c6+n7*c7)/(c1+c2+c3+c4+c5+c6+c7)";
			String formule3 = "max("+formule0+","+formule2+","+formule+")";
			
			try {
				ExpressionDeFormule edf = new ExpressionDeFormule(formule1);
				System.out.println(edf);
				System.out.println("_________________________________________");
				ExpressionDeFormule edf2 = new ExpressionDeFormule(formule2);
				System.out.println(edf2);
				System.out.println("_________________________________________");
				ExpressionDeFormule edf3 = new ExpressionDeFormule("(n23)");
				System.out.println(edf3);
				System.out.println("_________________________________________");
				ExpressionDeFormule edf4 = new ExpressionDeFormule(formule3);
				System.out.println(edf4);
			} catch (FormuleExceptions e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}



