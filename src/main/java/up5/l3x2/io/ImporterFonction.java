package up5.l3x2.io;


import java.io.IOException;

import up5.l3x2.exception.FormuleExceptions;
import up5.l3x2.exception.ImportException;
import up5.l3x2.io.settings.Calculcccoef;
import up5.l3x2.io.settings.Import;
import up5.l3x2.io.settings.Lecture;
import up5.l3x2.io.settings.Trie;
import up5.l3x2.io.settings.VerifImport;

public class ImporterFonction {

	
	/**
	 * Methode qui permet de réaliser l'import du repertoire dont le nom est passé en paramètre
	 * @author SU
	 * @return un objet Import
	 * @throws IOException
	 * @throws ImportException
	 */
	public static Import importer (String repertoire) throws IOException, ImportException
	{
		
		System.out.println("*********DEBUT IMPORT**********");
		
		Lecture lecture;
		if (repertoire.equals(""))
		{
			repertoire = Lecture.choisirRepertoire();
		}
		
		lecture = new Lecture (repertoire);
		
		lecture.setNomDesFichiers();
		
		if (!lecture.repertoireConforme()) 
		{
			throw new ImportException ("Repertoire non conforme");		
		}
		
		//Appel de la methode toString de Lecture
		System.out.println(lecture);
		
		Import imp = new Import (lecture);
		
		// Appel de la methode pour importer les VETs (lecture du fichier vet_1er niveau)
		imp.importerVET();
		
		// Appel pour chacun des ELP contenus dans la LSE des VETs, la methode importerArborescence
		for(int i =0; i <imp.getVets().size(); i++)
		{
			for (int j = 0; j<imp.getVets().get(i).getListeLSE().size(); j++)
			{
				for (int k = 0; k < imp.getVets().get(i).getListeLSE().get(j).getListeELP().size(); k++)
				{
					imp.importerArborescence(imp.getVets().get(i).getListeLSE().get(j).getListeELP().get(k));
					imp.importerHeure_Epreuve(imp.getVets().get(i).getListeLSE().get(j).getListeELP().get(k));
				}
			}
		}
		
		for(int i =0; i <imp.getVets().size(); i++)
		{
			for (int j = 0; j<imp.getVets().get(i).getListeLSE().size(); j++)
			{
				imp.importFormule(imp.getVets().get(i).getListeLSE().get(j), imp.getVets().get(i).getListeLSE().get(j).getCodeLSE());
		
			}
		}
		
		Trie.trierArborescence(imp);
		VerifImport.verifierArborescence(imp);
		
		
		
		return imp;
	}
}
