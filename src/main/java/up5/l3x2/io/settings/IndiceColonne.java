package up5.l3x2.io.settings;

import java.io.IOException;

import up5.l3x2.io.OpenCSV;

/**
 * Cette Classe permet la récupération dynamique des indices de colonnes pour chaque fichier
 * @author SU
 */
public class IndiceColonne {

	private Lecture lec;
	
	//Indice des colonnes pour le fichier VET 1er niveau
	private int code_vet;
	private int nbr_crd_vet;
	private int cod_lse_vet;
	private int lic_lse_vet;
	private int typ_lse_vet;
	private int cod_elp_vet;
	private int lic_elp_vet;
	private int lic_nel_vet;
	private int nbr_crd_elp_vet;
	private int nbr_choix_vet;
	
	public int getNbr_choix_vet() {
		return nbr_choix_vet;
	}
	//Indice des colonnes pour le fichier arborescence
	private int code_lse_arb;
	private int lic_lse_arb;
	private int typ_lse_arb;
	private int nbr_choix_arb;
	private int cod_elp_pere_arb;
	private int cod_elp_fils_arb;
	private int lic_elp_fils_arb;
	private int lic_nel_fils_arb;
	private int crd_elp_fils_arb;
	
	//Indice des colonnes pour le fichier ELP_HEURE
	private int cod_elp_heure;
	private int nbr_cm_heure;
	private int nbr_td_heure;
	private int nbr_tp_heure;
	private int cod_scc_heure;
	
	//Indice des colonnes pour le fichier epreuve
	private int cod_epr_epr;
	private int cod_elp_epr;
	private int n_session_epr;
	private int dur_exam_s1_epr;
	private int dur_exam_s2_epr;
	private int cod_tep_epr;
	private int cod_nep_epr;
	
	//Indice des colonnes pour le fichier formule

	private int lib_fml_rgc;
	private int cod_lse_manip;
	private int cod_elp_manip;
	private int cod_epr_manip;
	private int cod_obj_manip;
	private int coe_obj_manip;
	private int cod_rgc_manip;
	
	
	
	public void afficher ()
	{
		System.out.println("\n**Affichage des indices de colonnes**\n");
		System.out.println("codeVET : " +code_vet);
		System.out.println("nb_cred_vet : " + nbr_crd_vet);
		System.out.println("cod_lse_vet : " + cod_lse_vet);
		System.out.println("lic_lse_vet : " + lic_lse_vet);
		System.out.println("typ_lse_vet : " + typ_lse_vet);
		System.out.println("cod_elp_vet : " + cod_elp_vet);
		System.out.println("lic_elp_vet :" + lic_elp_vet);
		System.out.println("lic_nel_vet : " + lic_nel_vet);
		System.out.println("nbr_crd_elp_vet : " +nbr_crd_elp_vet);
		
		System.out.println("code_lse_arb : " + code_lse_arb);
		System.out.println("lic_lse_arb : " + lic_lse_arb);
		System.out.println("typ_lse_arb : " + typ_lse_arb);
		System.out.println("nbr_choix_arb : " + nbr_choix_arb);
		System.out.println("cod_elp_pere_arb : " + cod_elp_pere_arb);
		System.out.println("cod_elp_fils_arb : " + cod_elp_fils_arb);
		System.out.println("lic_elp_fils_arb : " + lic_elp_fils_arb);
		System.out.println("lic_nel_fils_arb : " + lic_nel_fils_arb);
		System.out.println("crd_elp_crd_arb : " + crd_elp_fils_arb);
		
		System.out.println("cod_elp_heure : " + cod_elp_heure);
		System.out.println("nbr_cm_heure : " + nbr_cm_heure);
		System.out.println("nbr_td_heure : " + nbr_td_heure);
		System.out.println("nbr_tp_heure : " + nbr_tp_heure);
		System.out.println("cod_scc_heure : " + cod_scc_heure);
		
		System.out.println("cod_elp_epr : " + cod_elp_epr);
		System.out.println("n_session_epr : " + n_session_epr);
		System.out.println("dur_exam_s1_epr : " + dur_exam_s1_epr);
		System.out.println("dur_exam_s2_epr : " + dur_exam_s2_epr);
		System.out.println("cod_nep_epr : " + cod_tep_epr + "\n");
		
	}
	
	public IndiceColonne (Lecture lec) throws IOException
	{
		this.lec= lec;
		this.remplirIndice();
	}
	

	/**
	 * Methode qui permet de chercher les indices des colonnes et de les stocker respectivement dans les attributs. 
	 * @author SU
	 * @throws IOException
	 */
	public void remplirIndice () throws IOException
	{
		//Chercher les indices de colonnes du fichier VET
		String [] ligne = OpenCSV.lire1erligneCSV(lec.getNomRepertoire() + "/" + lec.getVet_1er_niveau());
		for (int i=0; i<ligne.length; i++)
		{
			
			switch (ligne[i])
			{
			case "LIC_ETP" : 
				code_vet = i ; 
				break;
			case "NBR_CRD_VET" : 
				nbr_crd_vet = i;
				break;
			case "COD_LSE" : 
				cod_lse_vet = i;
				break;
			case "LIC_LSE" : 
				lic_lse_vet = i;
				break;
			case "TYP_LSE" : 
				typ_lse_vet = i;
				break;
			case "COD_ELP" : 
				cod_elp_vet = i;
				break;
			case "LIC_ELP" : 
				lic_elp_vet = i;
				break;
			case "LIC_NEL" : 
				lic_nel_vet = i;
				break;
			case "NBR_CRD_ELP" : 
				nbr_crd_elp_vet = i;
				break;
			case "NBR_MAX_ELP_OBL_CHX_VET" : 
				nbr_choix_vet = i;
				break;
			}
			
		}
		//Chercher les indices de colonnes du fichier arborescence
		ligne = OpenCSV.lire1erligneCSV(lec.getNomRepertoire() + "/" + lec.getArborescence());
		for (int i=0; i<ligne.length; i++)
		{
			
			switch (ligne[i])
			{
			case "COD_LSE" : 
				code_lse_arb = i ; 
				break;
			case "LIC_LSE" : 
				lic_lse_arb = i;
				break;
			case "TYP_LSE" : 
				typ_lse_arb = i;
				break;
			case "NBR_MAX_ELP_OBL_CHX" : 
				nbr_choix_arb = i;
				break;
			case "COD_ELP_PERE" : 
				cod_elp_pere_arb = i;
				break;
			case "COD_ELP_FILS" : 
				cod_elp_fils_arb = i;
				break;
			case "LIC_ELP_FILS" : 
				lic_elp_fils_arb = i;
				break;
			case "LIC_NEL_FILS" : 
				lic_nel_fils_arb = i;
				break;
			case "CRD_ELP_FILS" : 
				crd_elp_fils_arb = i;
				break;

			}
			
		}
		
		//Chercher les indices de colonnes pour le fichier ELP_HEURE
		ligne = OpenCSV.lire1erligneCSV(lec.getNomRepertoire() + "/" + lec.getElp_heure());
		for (int i=0; i<ligne.length; i++)
		{
			
			switch (ligne[i])
			{
			case "COD_ELP" : 
				cod_elp_heure = i ; 
				break;
			case "NBR_HEU_CM_ELP" : 
				nbr_cm_heure = i ; 
				break;
			case "NBR_HEU_TD_ELP" : 
				nbr_td_heure = i ; 
				break;
			case "NBR_HEU_TP_ELP" : 
				nbr_tp_heure = i ; 
				break;
			case "COD_SCC" : 
				cod_scc_heure = i ; 
				break;
			
			}
		}
		
		//Chercher les indices des colonnes du fichier EPREUVE
		ligne = OpenCSV.lire1erligneCSV(lec.getNomRepertoire() + "/" + lec.getEpreuve());
		for (int i=0; i<ligne.length; i++)
		{
			
			switch (ligne[i])
			{
			case "COD_ELP" : 
				cod_elp_epr = i ; 
				break;
			case "COD_EPR" : 
				cod_epr_epr = i ; 
				break;
			case "N_SESSION" : 
				n_session_epr = i ; 
				break;
			case "DUR_EXA_S1_SUNIQUE_EPR" : 
				dur_exam_s1_epr = i ; 
				break;
			case "DUR_EXA_S2_EPR" : 
				dur_exam_s2_epr = i ; 
				break;
			case "COD_TEP" : 
				cod_tep_epr = i ; 
				break;
			case "COD_NEP" : 
				cod_nep_epr = i ; 
				break;
			
			}
		}
		//Fichier formule 
		ligne = OpenCSV.lire1erligneCSV(lec.getNomRepertoire() + "/" + lec.getFormule());
		for (int i=0; i<ligne.length; i++)
		{
			
			switch (ligne[i])
			{
			case "COD_OBJ_MNP" : 
				cod_obj_manip = i ; 
				break;
			case "COD_LSE_MANIP" : 
				cod_lse_manip = i ; 
				break;
			case "COD_ELP_MANIP" : 
				cod_elp_manip = i ; 
				break;
			case "COD_EPR_MANIP" : 
				cod_epr_manip = i ; 
				break;
			case "LIB_FML_RGC" : 
				lib_fml_rgc = i ; 
				break;
			case "COE_OBJ_MNP" : 
				coe_obj_manip = i ; 
				break;
			case "COD_OBJ_RGC_1" : 
				cod_rgc_manip = i ; 
				break;
			
			}
		}
		
	}
	

	public int getCod_elp_heure() {
		return cod_elp_heure;
	}

	public int getNbr_cm_heure() {
		return nbr_cm_heure;
	}

	public int getNbr_td_heure() {
		return nbr_td_heure;
	}

	public int getNbr_tp_heure() {
		return nbr_tp_heure;
	}

	public int getCod_scc_heure() {
		return cod_scc_heure;
	}

	public int getCod_elp_epr() {
		return cod_elp_epr;
	}

	public int getN_session_epr() {
		return n_session_epr;
	}

	public int getDur_exam_s1_epr() {
		return dur_exam_s1_epr;
	}

	public int getDur_exam_s2_epr() {
		return dur_exam_s2_epr;
	}

	public int getCod_tep_epr() {
		return cod_tep_epr;
	}

	public int getCode_vet() {
		return code_vet;
	}
	public int getNbr_crd_vet() {
		return nbr_crd_vet;
	}
	public int getCod_lse_vet() {
		return cod_lse_vet;
	}
	public int getLic_lse_vet() {
		return lic_lse_vet;
	}
	public int getTyp_lse_vet() {
		return typ_lse_vet;
	}
	public int getCod_elp_vet() {
		return cod_elp_vet;
	}
	public int getLic_elp_vet() {
		return lic_elp_vet;
	}
	public int getLic_nel_vet() {
		return lic_nel_vet;
	}
	public int getNbr_crd_elp_vet() {
		return nbr_crd_elp_vet;
	}
	public int getCode_lse_arb() {
		return code_lse_arb;
	}
	public int getLic_lse_arb() {
		return lic_lse_arb;
	}
	public int getTyp_lse_arb() {
		return typ_lse_arb;
	}
	public int getNbr_choix_arb() {
		return nbr_choix_arb;
	}
	public int getCod_elp_pere_arb() {
		return cod_elp_pere_arb;
	}
	public int getCod_elp_fils_arb() {
		return cod_elp_fils_arb;
	}
	public int getLic_elp_fils_arb() {
		return lic_elp_fils_arb;
	}
	public int getLic_nel_fils_arb() {
		return lic_nel_fils_arb;
	}
	public int getCrd_elp_fils_arb() {
		return crd_elp_fils_arb;
	}

	public int getLib_fml_rgc() {
		return lib_fml_rgc;
	}

	public int getCod_lse_manip() {
		return cod_lse_manip;
	}

	public int getCod_elp_manip() {
		return cod_elp_manip;
	}

	public int getCod_epr_manip() {
		return cod_epr_manip;
	}	
	public int getCod_epr_epr() {
		return cod_epr_epr;
	}

	public int getCod_obj_manip() {
		return cod_obj_manip;
	}

	public int getCoe_obj_manip() {
		return coe_obj_manip;
	}

	public int getCod_nep_epr() {
		return cod_nep_epr;
	}

	public int getCod_rgc_manip() {
		return cod_rgc_manip;
	}
}
