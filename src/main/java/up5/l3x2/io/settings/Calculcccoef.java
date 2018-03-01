package up5.l3x2.io.settings;


import up5.l3x2.exception.FormuleExceptions;
import up5.l3x2.formule.ExpressionDeFormule;
import up5.l3x2.model.ELP;


public class Calculcccoef {


	public static void parcourarbo(Import imp) throws FormuleExceptions{


		for(int i =0; i <imp.getVets().size(); i++)
		{
			for (int j = 0; j<imp.getVets().get(i).getListeLSE().size(); j++)
			{
				for (int k = 0; k < imp.getVets().get(i).getListeLSE().get(j).getListeELP().size(); k++)
				{
					calculCoeff(imp.getVets().get(i).getListeLSE().get(j).getListeELP().get(k));
				}
			}
		}

	}


	public static void  calculCoeff	(ELP elp) throws FormuleExceptions
	{	
		double sommecoefcc=0;
		double sommecoefct=0;
		double coefcc=0;
		int verifnbnote=0;
		ExpressionDeFormule expfor; 
		boolean ponderee = true;
		boolean max = true;
		boolean formuleIntrouvable = false;
		
		for (int i =0 ; i< elp.getListeEPR().size(); i ++)
		{
			if (elp.getListeEPR().get(i).getSession() == 1 &&!elp.getListeEPR().get(i).getFormuleCoeff().getFormule().equals("") && !elp.getListeEPR().get(i).getFormuleCoeff().getFormule().equals("n1"))
			{
				expfor = new ExpressionDeFormule(elp.getListeEPR().get(i).getFormuleCoeff().getFormule());
				
				if(expfor.isFormulePonderee() != -1){
					if(elp.getListeEPR().get(i).getCod_tep().equals("CC"))
					{
						sommecoefcc+=elp.getListeEPR().get(i).getFormuleCoeff().getCoeff();
						verifnbnote++;
					}
					else
					{
						sommecoefct+=elp.getListeEPR().get(i).getFormuleCoeff().getCoeff();
						verifnbnote++;
					}
					max = false;

				}
				else if(expfor.isFormuleMax()){
					if(elp.getListeEPR().get(i).getCod_tep().equals("CC"))
					{
						sommecoefcc+=elp.getListeEPR().get(i).getFormuleCoeff().getCoeff();
						verifnbnote++;
					}
					else
					{
						sommecoefct+=elp.getListeEPR().get(i).getFormuleCoeff().getCoeff();
						verifnbnote++;
					}
					ponderee = false;
				}
				else 
				{
					ponderee = false;
					max = false;
				}
			}
			else 
			{
				elp.setPartCC("error");
				formuleIntrouvable = true;
			}
		}

		if(ponderee && !max){		
			coefcc=(sommecoefcc/(sommecoefct+sommecoefcc))*100;
			//if(verifnbnote==expfor.isFormulePonderee())
			/*
			if(true)
			{		*/
				elp.setPartCC(Math.round(coefcc)+"%");
			/*}
			else
			{
				elp.setPartCC("-1");
			}*/
		}
		else if (max && !ponderee){
				coefcc=(sommecoefcc/(sommecoefct+sommecoefcc))*100;
				//if(verifnbnote==elp.getCodeELP().loanverifnbnote())
				/*if(true)
				{*/
				if(coefcc != 0)
					elp.setPartCC("0% ou " +Math.round(coefcc)+"%");
				/*
				}
				else
				{
					elp.setPartCC("-1");
				}*/
				else
				{
					elp.setPartCC("error");
					
				}
	

		}
		else if (!ponderee && !max)
		{
			elp.addMessage("FormuleError");
		}
		else if (formuleIntrouvable)
		{
			elp.addMessage("FormuleIntrouvable");
		}

		
			for (int j = 0; j<elp.getListeLSE().size(); j++)
			{
				for (int k = 0; k < elp.getListeLSE().get(j).getListeELP().size(); k++)
				{
					calculCoeff(elp.getListeLSE().get(j).getListeELP().get(k));
				}
			}


		
	}
}


