package up5.l3x2.io.settings;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import up5.l3x2.exception.ImportException;
import up5.l3x2.io.OpenCSV;
import up5.l3x2.model.ELP;
import up5.l3x2.model.Epreuve;
import up5.l3x2.model.LSE;
import up5.l3x2.model.VET;

public class Import {

	private ArrayList <VET> vets;
	private String fichierEnCoursDeLecture;
	private IndiceColonne ic;
	private Lecture lec; 
	List <String[]> contenusFichierVET;
	List <String[]> contenusFichierArborescence;
	List <String[]> contenusFichierELPHeure;
	List <String[]> contenusFichierEpreuve;
	List <String[]> contenusFichierFormule;
	
	
	public Import (Lecture lec) throws IOException
	{
		this.vets = new ArrayList <VET> ();
		this.ic = new IndiceColonne (lec);
		this.lec = lec;
		//ic.afficher();
		
		this.contenusFichierVET = OpenCSV.lireCSV(lec.getNomRepertoire() + "/" + lec.getVet_1er_niveau());
		this.contenusFichierArborescence = OpenCSV.lireCSV(lec.getNomRepertoire() + "/" + lec.getArborescence());
		this.contenusFichierELPHeure = OpenCSV.lireCSV(lec.getNomRepertoire() + "/" + lec.getElp_heure());
		this.contenusFichierEpreuve = OpenCSV.lireCSV(lec.getNomRepertoire() + "/" + lec.getEpreuve());
		this.contenusFichierFormule = OpenCSV.lireCSV(lec.getNomRepertoire() + "/" + lec.getFormule());
		
	}
	
	/**
	 * @author SU 
	 * 
	 * Methode qui permet de lire le fichier VET_1er niveau et d'extraire les VETs contenus dans le fichier et les stocker dans l'ArrayList
	 * 
	 * @throws IOException
	 * @throws ImportException
	 */
	public void importerVET () throws IOException, ImportException
	{
		
		// fichiersCoursDeLecture note le fichier dans laquelle on est entrain de lire. Certaines methodes auront un comportement different selon le fichier qu'on est entrain de lire
		fichierEnCoursDeLecture = "vet";
		
		Iterator <String[]> it = contenusFichierVET.iterator();
		it.next();
		while (it.hasNext())
		{
			String [] ligne = it.next();

			ajoutVETDansListeVET (vets, ligne);
		}
	}
	
	/**
	 * Methode qui permet de verifier un VET existe déjÃ  ou pas dans l'ArrayList <VET> et d'ajouter si il n'existe pas. 
	 * Si elle n'existe pas : ajout
	 * Sinon : Appel de la methode qui permet d'ajouter une LSE dans la liste des LSE d'un VET
	 * @author SU
	 * @param liste Arraylist de VETS
	 * @param ligne Array de String
	 * @throws ImportException
	 */
	public void ajoutVETDansListeVET (ArrayList <VET> liste, String []ligne) throws ImportException
	{
		boolean testExistence = false;
		int indice = 0;
		VET tmpVET = new VET (ligne [ic.getCode_vet()]);
		
		//Verifier l'existence
		for (int i=0; i<liste.size(); i++)
		{
				if (liste.get(i).getCodeVET().equals(ligne[ic.getCode_vet()]))
				{
					indice = i ;
					testExistence = true;
					break;
				}
		}

		//Ajout si VET n'existe pas dans la liste
		if (!testExistence)
		{
			LSE tmpLSE = new LSE (ligne[ic.getCod_lse_vet()]);
			tmpLSE.setNom(ligne[ic.getLic_lse_vet()]);
			tmpLSE.setCodeELPPere(tmpVET.getCodeVET());
			tmpLSE.setType(ligne[ic.getTyp_lse_vet()]);
			
			if (!ligne[ic.getNbr_choix_vet()].equals("")) tmpLSE.setNbchoix(Integer.parseInt(ligne[ic.getNbr_choix_vet()]));
			
			tmpVET.getListeLSE().add(tmpLSE);
			
			String ects = ligne [ic.getNbr_crd_vet()];
			if (!ects.equals("")) 
			{
				ects = ects.replace(',', '.');
				tmpVET.setEcts(Float.parseFloat(ects));
			}
			
			liste.add(tmpVET);
			
			ajoutELPDansLSE (liste.get(liste.size()-1).getListeLSE().get(0), ligne);
			
		}
		//Si il existe on appel la méthode qui verifie si la LSE existe ou pas dans sa liste des LSE
		else ajouterLSEDansVET (vets.get(indice), ligne);
	}
	
	/**
	 * Methode qui permet de verifier si une LSE existe deja ou pas dans la liste des LSE d'un VET passé en argument.
	 * Si elle n'existe pas : ajout
	 * Sinon : Appel de la methode qui permet d'ajouter un ELP dans la LSE
	 * @author SU
	 * @param vet Objet VET
	 * @param ligne Array de String
	 * @throws ImportException
	 */
	public void ajouterLSEDansVET (VET vet, String []ligne) throws ImportException
	{
		boolean testExistence = false; 
		int indice = 0;
		LSE tmpLSE = new LSE (ligne[ic.getCod_lse_vet()]);
	
		//Verifie l'existence
		for(int i = 0; i <vet.getListeLSE().size(); i++)
		{
			if (vet.getListeLSE().get(i).getCodeLSE().equals(ligne[ic.getCod_lse_vet()]))
			{
				
				testExistence = true; 
				indice = i;
				break;
			}
		}
		//Ajout de la LSE si la liste ne la contienne pas
		if (!testExistence)
		{
			
			tmpLSE.setNom(ligne[ic.getLic_lse_vet()]);
			tmpLSE.setCodeELPPere(vet.getCodeVET());
			tmpLSE.setType(ligne[ic.getTyp_lse_vet()]);
			if (!ligne[ic.getNbr_choix_vet()].equals("")) tmpLSE.setNbchoix(Integer.parseInt(ligne[ic.getNbr_choix_vet()]));
			
			ELP tmpELP = new ELP (ligne [ic.getCod_elp_vet()]);
			
			tmpELP.setNom(ligne[ic.getLic_elp_vet()]);
			
			String ects = ligne [ic.getNbr_crd_elp_vet()];
			if (!ects.equals("")) 
			{
				ects = ects.replace(',', '.');
				tmpELP.setEcts(Float.parseFloat(ects));
			}
			
			tmpELP.setType(ligne[ic.getLic_nel_vet()]);
			
			tmpLSE.getListeELP().add(tmpELP);
			vet.getListeLSE().add(tmpLSE);
		}
		//Si elle existe, on appelle la methode qui verifie si elle contienne l'ELP
		else 
		{
			ajoutELPDansLSE (vet.getListeLSE().get(indice), ligne);
		}
	}
	/**
	 * @author SU
	 * 
	 * Methode qui permet de vérifier si un ELP existe déjÃ  ou pas dans une LSE passée en argument
	 * @param lse
	 * @param ligne
	 * @throws ImportException
	 */
	public void ajoutELPDansLSE (LSE lse, String []ligne) throws ImportException
	{
		boolean testExistence = false;
		int indiceELPFils = 0; 
		
		//La colonne oÃ¹ les données se trouvent diffÃ¨re d'un fichier Ã  l'autre
		if (fichierEnCoursDeLecture == "vet") 
		{
			indiceELPFils = ic.getCod_elp_vet();
		}
		else if (fichierEnCoursDeLecture == "arborescence") 
		{
			indiceELPFils = ic.getCod_elp_fils_arb();
		}
		else throw new ImportException("Appel de la methode AjoutELPDansLSE() alors que le fichier en cours de lecture n'est pas vet ou arborescence");

		//Verifie l'existence
		for (int i = 0; i < lse.getListeELP().size(); i++)
		{
			if (lse.getListeELP().get(i).getCodeELP().equals(ligne[indiceELPFils]))
			{
				testExistence = true;
				break;
			}
		}
		

		//Ajout si la liste ne contient pas l'ELP
		if (!testExistence && fichierEnCoursDeLecture == "vet")
		{
			ELP tmpELP = new ELP (ligne [indiceELPFils]);
			tmpELP.setNom(ligne[ic.getLic_elp_vet()]);
			
			String ects = ligne[ic.getNbr_crd_elp_vet()];
			if (!ects.equals("")) 
				{
					ects = ects.replace(',', '.');
					tmpELP.setEcts(Float.parseFloat(ects));
				}
			
			tmpELP.setType(ligne[ic.getLic_nel_vet()]);
			lse.getListeELP().add(tmpELP);
		}
		else if(!testExistence && fichierEnCoursDeLecture == "arborescence")
		{
			ELP tmpELP = new ELP (ligne [indiceELPFils]);
			tmpELP.setNom(ligne[ic.getLic_elp_fils_arb()]);

			String ects = ligne[ic.getCrd_elp_fils_arb()];
			if (!ects.equals("")) 
				{
					ects = ects.replace(',', '.');
					tmpELP.setEcts(Float.parseFloat(ects));
				}
			
			tmpELP.setType(ligne[ic.getLic_nel_fils_arb()]);
			lse.getListeELP().add(tmpELP);
		}
		
	}
	
	/**
	 * Methode qui permet de vérifier si une LSE existe déjà  ou pas dans la liste des LSE d'un ELP. L'ajoute si elle n'existe pas.
	 * @author SU
	 * @param elp Objet ELP
	 * @param ligne Array de String
	 * @throws ImportException
	 */
	public void ajoutLSEDansELP (ELP elp, String []ligne) throws ImportException
	{
		//Cette methode ne peut Ãªtre appelée que lorsqu'on lit le fichier "arborescence"
		if (fichierEnCoursDeLecture == "arborescence")
		{
			boolean testExistence = false;
			int indice = 0;
			LSE tmpLSE = new LSE (ligne [ic.getCode_lse_arb()]);
		
			//Verifier l'existence
			for (int i = 0; i < elp.getListeLSE().size(); i++)
			{
				if (elp.getListeLSE().get(i).getCodeLSE().equals(ligne[ic.getCode_lse_arb()]))
				{
					testExistence = true;
					indice = i;
					break;
				}
			}

			//Si elle n'existe pas dans la liste des LSE de l'ELP, on l'ajoute
			if (!testExistence)
			{
				tmpLSE.setNom(ligne[ic.getLic_lse_arb()]);
				tmpLSE.setCodeELPPere(elp.getCodeELP());
				tmpLSE.setType(ligne[ic.getTyp_lse_arb()]);
				//tmpLSE.setNbchoix(Integer.parseInt(ligne[ic.getNbr_choix_arb()]));
				
				if (!ligne[ic.getNbr_choix_arb()].equals("")) tmpLSE.setNbchoix(Integer.parseInt(ligne[ic.getNbr_choix_arb()]));
				
				ELP tmpELP = new ELP (ligne [ic.getCod_elp_fils_arb()]);
				
				tmpELP.setNom(ligne[ic.getLic_elp_fils_arb()]);
				
				String ects = ligne[ic.getCrd_elp_fils_arb()];
				if (!ects.equals("")) 
					{
						ects = ects.replace(',', '.');
						tmpELP.setEcts(Float.parseFloat(ects));
					}
				
				tmpELP.setType(ligne[ic.getLic_nel_fils_arb()]);
				
				tmpLSE.getListeELP().add(tmpELP);
				elp.getListeLSE().add(tmpLSE);
				
			}
			//Sinon, on appelle la methode ajoutELPDansLSE
			else
			{
				ajoutELPDansLSE (elp.getListeLSE().get(indice), ligne);
			}
		}
		//On lance une exception si on tente d'appeler cette methode quand on est pas dans le fichier "arborescence"
		else throw new ImportException ("Appel de la methode AjoutLSEDansELP alors que le fichier de lecture n'est pas arborescence");
	}
	
	/**
	 * Methode qui permet d'extraire les données depuis le fichier "arborescence". Permet de contruire l'arbre à  partir d'un ELP passé en argument.
	 * @author SU
	 * @param elp Objet ELP
	 * @throws ImportException
	 * @throws IOException
	 */
	public void importerArborescence (ELP elp) throws ImportException, IOException
	{
		fichierEnCoursDeLecture = "arborescence";
		
		Iterator <String []> it = contenusFichierArborescence.iterator();
		it.next();
		while (it.hasNext())
		{
			String[] ligne = it.next();
			if (elp.getCodeELP().equals(ligne[ic.getCod_elp_pere_arb()]))
			{
				ajoutLSEDansELP (elp, ligne);
			}
		}
		for (int i = 0; i < elp.getListeLSE().size(); i++)
		{
			for (int j = 0; j <elp.getListeLSE().get(i).getListeELP().size(); j++)
			{
				importerArborescence (elp.getListeLSE().get(i).getListeELP().get(j));
			}
		}
		
	}

	/**
	 * 
	 * Methode qui renvoie l'arraylist de VET
	 * @return vets ArrayList d'objets VET
	 */
	public ArrayList<VET> getVets() {
		return vets;
	}
	
	
	/**
	 * Fonction qui permet de lire les fichiers ELP heure et Epreuve
	 * Pour un objet ELP en parametre, on cherche dans le deux fichiers les lignes donc on trouve le codeELP de cet objet
	 * Puis on stocke les données extraites de ces lignes.
	 * @param elp Objet ELP
	 * @author SU
	 */
	public void importerHeure_Epreuve (ELP elp)
	{
		Iterator<String[]> itHeure = contenusFichierELPHeure.iterator();
		Iterator<String[]> itEpreuve = contenusFichierEpreuve.iterator();
		String st;
		
		itHeure.next();
		itEpreuve.next();
		while (itHeure.hasNext())
		{
			String [] ligne = itHeure.next();
			if (elp.getCodeELP().equals(ligne[ic.getCod_elp_heure()]))
			{
				st = ligne[ic.getNbr_cm_heure()];
				if (!st.isEmpty()) 
				{
					st = st.replace(',', '.');
					elp.setNbrHrCM(Float.parseFloat(st));
				}
				
				st = ligne[ic.getNbr_td_heure()];
				if (!st.isEmpty()) 
				{
					st = st.replace(',', '.');
					elp.setNbrHrTD(Float.parseFloat(st));
				}
				
				st = ligne[ic.getNbr_tp_heure()];
				if (!st.isEmpty()) 
				{
					st = st.replace(',', '.');
					elp.setNbrHrTP(Float.parseFloat(st));
				}
				
				st = ligne[ic.getCod_scc_heure()];
				if (!st.isEmpty()) 
				{
					st = st.replace(',', '.');
					elp.setCodeCNU(Float.parseFloat(st));
				}
			}
		}
		
		while (itEpreuve.hasNext())
		{
			String [] ligne = itEpreuve.next();
			int n_session = 0;
			boolean testExistence = false;
			
			if (elp.getCodeELP().equals(ligne[ic.getCod_elp_epr()]))
			{
				st = ligne[ic.getN_session_epr()];
				if (!st.isEmpty()) 
				{
					st = st.replace(',', '.');
					n_session = Integer.parseInt(st);
				}
				
				
				
				if (n_session == 1 && ligne[ic.getCod_tep_epr()].equals("CT"))
				{
					st = ligne[ic.getDur_exam_s1_epr()];
					if (!st.isEmpty()) 
					{
						st = st.replace(',', '.');
						elp.setDureeS1(Float.parseFloat(st));
						
					}
					elp.setTypeExamS1(ligne[ic.getCod_nep_epr()]);
				}
				
				else if (n_session == 2 && ligne[ic.getCod_tep_epr()].equals("CT")) 
				{
					st = ligne[ic.getDur_exam_s2_epr()];
					if (!st.isEmpty()) 
					{
						st = st.replace(',', '.');
						elp.setDureeS2(Float.parseFloat(st));
						
					}
					elp.setTypeExamS2(ligne [ic.getCod_nep_epr()]);
				}
				
				else 
				{
					elp.setTypeExamS1("");
					elp.setTypeExamS2("");
				}
				
				if (ligne[ic.getCod_tep_epr()].equals("TR"))
				{
					elp.setStage(ligne[ic.getCod_nep_epr()]);
				}
				
				
				for(int i = 0; i < elp.getListeEPR().size(); i++)
				{
					if (elp.getListeEPR().get(i).getCodeEPR().equals(ligne[ic.getCod_epr_epr()])) 
					{
						testExistence = true;
						break;
					}
				}
				if(!testExistence) 
					{
						Epreuve epr = new Epreuve (ligne[ic.getCod_epr_epr()]);
						epr.setCod_tep(ligne[ic.getCod_tep_epr()]);
						epr.setSession(n_session);
						elp.getListeEPR().add(epr);
					}
			
			}
		}
		
		for (int i = 0; i < elp.getListeLSE().size(); i++)
		{
			for (int j = 0; j <elp.getListeLSE().get(i).getListeELP().size(); j++)
			{
				importerHeure_Epreuve (elp.getListeLSE().get(i).getListeELP().get(j));
			}
		}
		
	}
	
	/**
	 * Fonction qui prend en parametre un element de type T générique qui doit etre soit LSE, soit ELP ou soit Epreuve
	 * Cette fonction permet de lire le fichier formule et d'en extraire la formule et le coefficient
	 * @param element Objet de type générique T qui peut etre soit  un LSE, soit un ELP ou soit une Epreuve
	 * @author SU
	 */
	public <T> void importFormule (T element, String objPere)
	{
		Iterator <String[]> it = contenusFichierFormule.iterator();
		it.next();
		String[] ligne;
		String st;
		
		//Si element est un LSE
		if (element.getClass().equals(new LSE (null).getClass())) 
		{
			LSE elementLSE = (LSE) element;
			while (it.hasNext())
			{
				ligne = it.next();
				if (elementLSE.getCodeLSE().equals(ligne[ic.getCod_lse_manip()]) && objPere.equals(ligne[ic.getCod_rgc_manip()]))
				{
					if (!ligne[ic.getLib_fml_rgc()].equals("")) 
					{
						elementLSE.getFormuleCoeff().setFormule(ligne[ic.getLib_fml_rgc()]);
						
						st = ligne [ic.getCoe_obj_manip()];
						if (!st.equals(""))
						{
							st = st.replace(',', '.');
							elementLSE.getFormuleCoeff().setCoeff(Float.parseFloat(st));
						}
					}
					
				}
			}
			//Appel de la recursivité
			for (int i= 0; i <elementLSE.getListeELP().size(); i++)
			{
				importFormule (elementLSE.getListeELP().get(i), elementLSE.getCodeLSE());
			}
		}
		
		//Si element est un ELP
		else if (element.getClass().equals(new ELP (null).getClass())) 
		{
			ELP elementELP = (ELP) element;
	
			while (it.hasNext())
			{
				ligne = it.next();
				if (elementELP.getCodeELP().equals(ligne[ic.getCod_elp_manip()]) && objPere.equals(ligne[ic.getCod_rgc_manip()]))
				{
					
					if (!ligne[ic.getLib_fml_rgc()].equals("")) 
					{
						elementELP.getFormuleCoeff().setFormule(ligne[ic.getLib_fml_rgc()]);
						//System.out.println(elementELP.getCodeELP()+ "   " +elementELP.getFormule());
						st = ligne [ic.getCoe_obj_manip()];
						if (!st.equals(""))
						{
							st = st.replace(',', '.');
							elementELP.getFormuleCoeff().setCoeff(Float.parseFloat(st));
						}
						
					}
					
				}
			}
			//Appel de la recursivité
			for (int i= 0; i <elementELP.getListeLSE().size(); i++)
			{
				importFormule (elementELP.getListeLSE().get(i), elementELP.getCodeELP());
			}
			//Appel de la methode importFormule sur l'arraylist d'Epreuves
			for (int i= 0; i <elementELP.getListeEPR().size(); i++)
			{
				importFormule (elementELP.getListeEPR().get(i), elementELP.getCodeELP());
			}
		}

		//Si element est une Epreuve
		else if (element.getClass().equals(new Epreuve (null).getClass())) 
		{
			Epreuve elementEPR = (Epreuve) element;
			while (it.hasNext())
			{
				ligne = it.next();
				if (elementEPR.getCodeEPR().equals(ligne[ic.getCod_epr_manip()]) && objPere.equals(ligne[ic.getCod_rgc_manip()]))
				{
					if (!ligne[ic.getLib_fml_rgc()].equals("")) 
					{
						elementEPR.getFormuleCoeff().setFormule(ligne[ic.getLib_fml_rgc()]);
						//System.out.println(elementEPR.getCodeEPR()+ "   " +elementEPR.getFormule());
						st = ligne [ic.getCoe_obj_manip()];
						if (!st.equals(""))
						{
							st = st.replace(',', '.');
							elementEPR.getFormuleCoeff().setCoeff(Float.parseFloat(st));
						}
					}
					
				}
			}
		}

	}

	public Lecture getLec() {
		return lec;
	}
}




  