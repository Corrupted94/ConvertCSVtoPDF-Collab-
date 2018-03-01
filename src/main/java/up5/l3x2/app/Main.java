package up5.l3x2.app;


import java.io.IOException;
import java.util.ArrayList;

import up5.l3x2.exception.FormuleExceptions;
import up5.l3x2.exception.ImportException;
import up5.l3x2.io.ImporterFonction;
import up5.l3x2.io.settings.Afficher;
import up5.l3x2.io.settings.Calculcccoef;
import up5.l3x2.io.settings.ExportCSV;
import up5.l3x2.io.settings.Import;
import up5.l3x2.pdf.*;


public class Main {
	
	
	public static void main (String [] args) throws IOException, ImportException
	{
		/* la fonction importer(String) renvoie un objet Import qu'on le stock dans "imp"
		   on utilisera 'imp' pour la réalisation des fonctionalités "EXPORT" et "COMPARER"
		*/
		
		System.setProperty( "file.encoding", "ISO-8859-1" );
		Import imp = ImporterFonction.importer("");

		try {
			Calculcccoef.parcourarbo(imp);
		} catch (FormuleExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	

		try {
			Calculcccoef.parcourarbo(imp);
		} catch (FormuleExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ArrayList<String> vet=new ArrayList<String>();
	


		String file= "exportPdf_" + imp.getLec().getNomRepertoire() +".pdf";

	}
}
 