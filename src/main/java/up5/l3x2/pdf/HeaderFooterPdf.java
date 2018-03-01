package up5.l3x2.pdf;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

import up5.l3x2.io.settings.Import;

import com.itextpdf.text.pdf.ColumnText;

public class HeaderFooterPdf extends PdfPageEventHelper{
	
		public Map<String, Integer> pageNum = new HashMap<String,Integer>();
	
	    public void onEndPage(PdfWriter writer, Document document) {
	    	Font fontHF = new Font(Font.FontFamily.TIMES_ROMAN, 7, Font.ITALIC);
	    	
	    	Phrase header = new Phrase("this is a header", fontHF);
	    	Phrase footer = new Phrase("Page " + document.getPageNumber(),fontHF);
	    	
	    	if(writer.getPageNumber()!=1){
	    	//ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER,header,(document.right() - document.left()) / 2 + document.leftMargin(),document.top() + 10, 0);
	    	ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER,footer,(document.right() - document.left()) / 2 + document.leftMargin(),document.bottom() - 10, 0);
	    	}
	    	
	    }
	    
	    public void onChapter(PdfWriter writer, Document document, float paragraphPosition,Paragraph vetNom){
	    	this.pageNum.put(vetNom.getContent(), writer.getPageNumber());	
	    }
	    //public void onChapter(final PdfWriter writer, final Document document, final float paragraphPosition, final Paragraph title
	  
}
