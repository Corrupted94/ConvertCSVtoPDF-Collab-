package up5.l3x2.pdf;


import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ElementListener;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.api.Spaceable;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEvent;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;

import up5.l3x2.exception.*;
import up5.l3x2.io.*;
import up5.l3x2.io.settings.*;
import up5.l3x2.model.*;
import up5.l3x2.model.*;
import up5.l3x2.app.*;


public class Creation_pdf {
	 
	private static Import imp;
	private static PdfPTable table ;
	private static PdfPTable tableIndex;
	private static Font font3,fontInfo;
	private static Font fontCode,fontVet;
	private static Font fontNom,fontType;
	private static int indice = 0;
	private static ArrayList<String> vet ;
	private static PdfWriter writer;
	private static Document document;

	
	   private final static Map<String, PdfTemplate> tocPlaceholder = new HashMap<>();

	   private final Map<String, Integer>     pageByTitle    = new HashMap<>();
	
public Creation_pdf(String nomFichier,Import imp,ArrayList<String> vet){	
       
		this.imp=imp;
		this.vet= vet;
	
		try{
    	    	
			document = new Document();        
			writer = PdfWriter.getInstance(document, new FileOutputStream(nomFichier));  
			HeaderFooterPdf event = new HeaderFooterPdf();
		 
			writer.setPageEvent((PdfPageEvent) event);
         
			document.open();    
        
			Image img = Image.getInstance("Images/ParisDescartes.png");
			img.scaleToFit(150f, 200f);
			document.add(img);
				
			index(document);
			document.newPage();
		
			document.setPageSize(PageSize.A4.rotate());
			document.newPage();
			document.add(creationTable());
        
			document.close();
        
       }catch(Exception e){
    	   
       }
    }

public static PdfPTable creationTable() throws IOException, ImportException{
	
	float[] columnWidths = {18,60, 10, 10, 10,10, 9, 13, 10,17, 20, 12, 20,12, 12,25};
	table = new PdfPTable(columnWidths);

	Font font1 = new Font(Font.FontFamily.TIMES_ROMAN  , 7, Font.BOLD,new BaseColor(0xff, 0x53, 0x0d));
	Font font2 = new Font(Font.FontFamily.TIMES_ROMAN  , 7, Font.BOLD,new BaseColor(0x08, 0x49, 0x75));
	font3 = new Font(Font.FontFamily.TIMES_ROMAN  , 8, Font.BOLD ,new BaseColor(0x2c, 0x3e, 0x50));
	fontCode =  new Font(Font.FontFamily.TIMES_ROMAN  , 7, Font.BOLD,new BaseColor(0x1f, 0x3e, 0x4e));
	fontNom =  new Font(Font.FontFamily.TIMES_ROMAN  , 8, Font.BOLD,new BaseColor(0xff, 0x53, 0x0d));
	fontType = new Font(Font.FontFamily.TIMES_ROMAN  , 8, Font.BOLD,new BaseColor(0xff, 0x53, 0x0d));
	fontVet = new Font(Font.FontFamily.TIMES_ROMAN  , 9, Font.BOLD,new BaseColor(0xb2, 0x36, 0x00));
	fontInfo = new Font(Font.FontFamily.TIMES_ROMAN  , 6, Font.BOLD,new BaseColor(0xb2, 0x36, 0x00));
	
	PdfPCell v4 = new PdfPCell(new Paragraph("",font2));
	PdfPCell MathInfo = new PdfPCell(new Paragraph("Projet Tutoré",font2));
	PdfPCell v5 = new PdfPCell(new Paragraph("",font2));
	PdfPCell session1 = new PdfPCell(new Paragraph("Session unique ou Première session",font2));
	PdfPCell session2 = new PdfPCell(new Paragraph("Seconde session s'il y a lieu",font2));
	PdfPCell stage = new PdfPCell(new Paragraph("Stage memoire projets",font2));
	PdfPCell info = new PdfPCell(new Paragraph("Info",font2));
	
	PdfPCell codeApogee = new PdfPCell(new Paragraph("Code Apogée Version d'étape",font1));
	PdfPCell v2 = new PdfPCell(new Paragraph(""));
	PdfPCell ects = new PdfPCell(new Paragraph("ECTS",font1));
	PdfPCell coeff = new PdfPCell(new Paragraph("Coeff",font1));
	PdfPCell cm = new PdfPCell(new Paragraph("CM",font1));
	PdfPCell td = new PdfPCell(new Paragraph("TD",font1));
	PdfPCell tp = new PdfPCell(new Paragraph("TP",font1));
	PdfPCell sectionsCNU = new PdfPCell(new Paragraph("Sections CNU",font1));
	PdfPCell seuilComp = new PdfPCell(new Paragraph("Seuil comp.",font1));
	PdfPCell controleContinu = new PdfPCell(new Paragraph("% contrôle continu",font1));
	PdfPCell epreuvesTerminales = new PdfPCell(new Paragraph("Epreuves terminales",font1));
	PdfPCell dureeEpreuvesEcrites = new PdfPCell(new Paragraph("Durée épreuves écrites",font1));
	PdfPCell epreuves = new PdfPCell(new Paragraph("Epreuves",font1));
	PdfPCell dureeEpreuvesEcrites2 = new PdfPCell(new Paragraph("Durée épreuves écrites",font1));
	PdfPCell v3 = new PdfPCell(new Paragraph(""));
	PdfPCell v6 = new PdfPCell(new Paragraph(""));
	
	v5.setColspan(7);
	session1.setColspan(3);
	session2.setColspan(2);
	
	table.addCell(v4);
	table.addCell(MathInfo);
	table.addCell(v5);
	table.addCell(session1);
	table.addCell(session2);
	table.addCell(stage);
	table.addCell(info);
	
	table.addCell(codeApogee);
	table.addCell(v2);
	table.addCell(ects);
	table.addCell(coeff);
	table.addCell(cm);
	table.addCell(td);
	table.addCell(tp);
	table.addCell(sectionsCNU);
	table.addCell(seuilComp);
	table.addCell(controleContinu);
	table.addCell(epreuvesTerminales);
	table.addCell(dureeEpreuvesEcrites);
	table.addCell(epreuves);
	table.addCell(dureeEpreuvesEcrites2);
	table.addCell(v3);
	table.addCell(v6);  
	
	/*for (int i = 0; i <imp.getVets().size(); i++){
		fonction(imp.getVets().get(i));	
	}*/
	
	fonctionVet(vet);
	
	table.setWidthPercentage(100);
	
	return table;
}


public static <T> void fonction (T element){
	
	int indTemp = indice;
	StringBuffer espacement = new StringBuffer ();
	indice++;
	
	for (int k=1; k <indice; k++)
	{
		espacement.append("  ");
	}
	
	if (element.getClass().equals((new VET (null)).getClass()))
	{	
		VET elementVET = (VET) element;
		document.newPage();
		for (int i =0; i <elementVET.getListeLSE().size(); i++)
		{
			/*table.addCell(new PdfPCell(new Paragraph(""+ elementVET.getListeLSE().get(i).getCodeLSE(),fontCode)));
			
			
			Paragraph par = new Paragraph(elementVET.getListeLSE().get(0).getNom(), fontNom);
            Chapter chapter = new Chapter(new Paragraph(par), i);
            chapter.setNumberDepth(0);
            try {
				document.add(chapter);
				//table.addCell(new PdfPCell(new Paragraph(""+espacement +elementVET.getListeLSE().get(i).getNom(),fontVet)));	
				
			} catch (Exception e1) {
				
			}
		     BaseFont baseFont=null;
			try {
				baseFont = BaseFont.createFont();
			} catch (DocumentException | IOException e) {
				
				e.printStackTrace();
			}		 	
		 	
		      
		      final PdfTemplate template = tocPlaceholder.get(elementVET.getListeLSE().get(0).getNom());
		      template.beginText();
		      template.setFontAndSize(baseFont, 12);
		      template.setTextMatrix(50 - baseFont.getWidthPoint(String.valueOf( writer.getPageNumber()), 12), 0);
		      template.showText(String.valueOf( writer.getPageNumber()));
		      template.endText();
				
			table.addCell(fa(new PdfPCell(new Paragraph(fctVirgule(elementVET.getEcts()),font3))));
			
			table.completeRow();*/
		 
			table.addCell(new PdfPCell(new Paragraph(""+ elementVET.getListeLSE().get(i).getCodeLSE(),fontCode)));
			
			table.addCell(new PdfPCell(new Paragraph(""+espacement+ elementVET.getListeLSE().get(i).getNom(),fontVet)));	
						
			table.addCell(fa(new PdfPCell(new Paragraph(fctVirgule(elementVET.getEcts()),font3))));
			
			table.completeRow();
			
			for (int j = 0; j<elementVET.getListeLSE().get(i).getListeELP().size(); j++)
				fonction (elementVET.getListeLSE().get(i).getListeELP().get(j));
				
		}
	
		
	}
	
	else if (element.getClass().equals((new ELP (null)).getClass()))
	{
		ELP elementELP = (ELP)element;
		
		
		table.addCell(new PdfPCell(new Paragraph(""+elementELP.getCodeELP(),fontCode)));
		
		Phrase type = new Phrase(espacement+fctSP(elementELP.getType()),fontType);
		type.add(new Phrase(" "+elementELP.getNom(),fontNom));
		
		for (int i =0; i <elementELP.getListeLSE().size(); i++)
		{	
			type.add(new Phrase(" "+fctNbrchoix(elementELP.getListeLSE().get(i)),fontNom));
		}
		
		PdfPCell typenom = new PdfPCell(type);
		table.addCell(typenom);

		
		table.addCell(fa(new PdfPCell(new Paragraph(fctVirgule(elementELP.getEcts()),font3))));
		table.addCell(fa(new PdfPCell(new Paragraph(""+fctVirgule(elementELP.getFormuleCoeff().getCoeff()),font3))));
		table.addCell(fa(new PdfPCell(new Paragraph(fctVirgule(elementELP.getNbrHrCM()),font3))));
		table.addCell(fa(new PdfPCell(new Paragraph(fctVirgule(elementELP.getNbrHrTD()),font3))));
		table.addCell(fa(new PdfPCell(new Paragraph(fctVirgule(elementELP.getNbrHrTP()),font3))));
		table.addCell(fa(new PdfPCell(new Paragraph(fctVirgule(elementELP.getCodeCNU()),font3))));
		table.addCell(new PdfPCell(new Paragraph("",font3)));
		table.addCell(alignCc(new PdfPCell(new Paragraph(""+fctCC(elementELP.getPartCC()),font3))));
		table.addCell(alignCc(new PdfPCell(new Paragraph(""+fctEpreuve(elementELP.getTypeExamS1()),font3))));
		table.addCell(fa(new PdfPCell(new Paragraph(fctVirgule(elementELP.getDureeS1()),font3))));
		table.addCell(alignCc(new PdfPCell(new Paragraph(""+fctEpreuve(elementELP.getTypeExamS2()),font3))));
		table.addCell(fa(new PdfPCell(new Paragraph(fctVirgule(elementELP.getDureeS2()),font3))));
		table.addCell(new PdfPCell(new Paragraph("",font3)));
		table.addCell(new PdfPCell(new Paragraph(""+elementELP.getMessage(),fontInfo)));	
		
		
		
		for (int i =0; i <elementELP.getListeLSE().size(); i++)
		{	
			for (int j = 0; j<elementELP.getListeLSE().get(i).getListeELP().size(); j++){
				fonction (elementELP.getListeLSE().get(i).getListeELP().get(j));
				
			}
		}
	}
	
	
	indice = indTemp++;
}

//fonction d'elimination des zeros inutiles
public static String fctVirgule(float a){
	if(a!=-1){	
	 
	 String fString = Float.toString(a);
     int position = fString.lastIndexOf(".");
	 for(int i=position;i<fString.length();i++){
		 if(fString.charAt(i) == '0'){
			 fString=fString.substring(0, position);
             
		 }
	 }
	 return fString;
	}
	else return "";
	
}

public static void fonctionVet (ArrayList<String> vet){
	//il faut passer à fonction(..) une vet 
	for(int v=0;v<vet.size();v++){
		
		for (int i = 0; i <imp.getVets().size(); i++){
			
			if((vet.get(v)).equals(imp.getVets().get(i).getListeLSE().get(0).getNom())){
				fonction(imp.getVets().get(i));
				
			}
			
		}

	}
}

public static PdfPCell fa(PdfPCell p){
	
	//p.setBackgroundColor(new BaseColor(0xf0,0xf1,0xeb));
	p.setHorizontalAlignment(Element.ALIGN_RIGHT);
	return p;
}
public static PdfPCell alignCc(PdfPCell p){
	
	p.setHorizontalAlignment(Element.ALIGN_CENTER);
	p.setPaddingBottom(4);
	return p;
}
//fonction des types des LSE
public static String fctNbrchoix(LSE a){
	String choix="";
	
	if(a.getType().equals("Obligatoire"))choix="";
	
	else if(a.getType().equals("Obligatoire à choix")){
		switch(a.getNbchoix()){
			case 0 : choix="";break;
			case 1 : choix="(Un au choix)";break;
			case 2 : choix="(Deux au choix)";break;
			case 3 : choix="(Trois au choix)";break;
		}
	}
	
	else if(a.getType().equals("Facultative")){
		choix="(Facultative)";
	}
	
	return choix;
}

public static String fctEpreuve(String epr){
	String res="";
		if(epr.equals("ECR")){
			res="épreuve écrite";
			
			
		}
		else if(epr.equals("ORA")){
			res="épreuve orale";
			
		}
		
	return res;
}
public static String fctSP(String t){
	String res="";	
		if(t.equals("Semestre")){
			res="";
		}
		else if(t.equals("Parcours")){
			
		}
		else{
			res=t;
		}
	return res;	
}
public static String fctCC(String c){
	String res="";
		if(c.equals("error")){
			res="";
		}
		else if(c.equals("0% ou 0%")){
			res="";
		}
		else{
			res=c;
		}
	return res;
}
public static void index(Document document) throws DocumentException{

	Paragraph pp = new Paragraph("Sommaire",new Font(Font.FontFamily.TIMES_ROMAN , 20,Font.BOLD));
	pp.setSpacingBefore(40f);
	pp.setSpacingAfter(5f);
	
	document.add(pp);
	
	for(int v=0;v<vet.size();v++){
		
     for (int i = 0; i < imp.getVets().size(); i++)
     {
 		if((vet.get(v)).equals(imp.getVets().get(i).getListeLSE().get(0).getNom())){
         final String title = imp.getVets().get(i).getListeLSE().get(0).getNom();
         final Chunk chunk = new Chunk(title);
         document.add(new Paragraph(chunk));

      
         document.add(new VerticalPositionMark() {
             @Override
             public void draw(final PdfContentByte canvas, final float llx, final float lly, final float urx, final float ury, final float y)
             {
                 final PdfTemplate createTemplate = canvas.createTemplate(50, 50);
                 tocPlaceholder.put(title, createTemplate);

                 canvas.addTemplate(createTemplate, urx - 50, y);
             }
         });
     }
	}
     }
	
	
}
public void pageNumberFct(String title) throws DocumentException, IOException{
	}
/*public static Paragraph index() {
		
	Anchor target = new Anchor("The quick onyx goblin jumps over the lazy dwarf.");
    anchor.setName("targetLink");
    Paragraph para2 = new Paragraph();
    para2.setSpacingBefore(550);
    para2.add(target);
    document.add(para2);
	Paragraph p = new Paragraph();
			for (int i = 0; i <imp.getVets().size(); i++){
				
				//System.out.println("ca marche");
				Anchor anchor=new Anchor(""+imp.getVets().get(i).getListeLSE().get(0).getNom());
				String d="#"+imp.getVets().get(i).getListeLSE().get(0).getNom();
				anchor.setReference(d);
				p.add(anchor+"\n");
				p.add(imp.getVets().get(i).getListeLSE().get(0).getNom());
			}
			
			return p;
	
	
}*/

}