/********************** 版权声明 *************************
 * 文件名: LeftRight.java
 * 包名: separator
 * 版权:	杭州华量软件  pdfDemo
 * 职责:	
 ********************************************************
 *
 * 创建者：peijd   创建时间：2016年7月4日 下午7:25:33
 * 文件版本：V1.0 
 *
 *******************************************************/
package blocks.separator;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;
 
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
 
public class LeftRight {
 
    public static final String DEST = "results/objects/left_right.pdf";
 
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new LeftRight().createPdf(DEST);
        System.out.println("export Over!");
    }
 
    public void createPdf(String dest) throws FileNotFoundException, DocumentException {
        Document document = new Document();
 
        PdfWriter.getInstance(document, new FileOutputStream(dest));
 
        document.open();
 
        Chunk glue = new Chunk(new VerticalPositionMark());
        Paragraph p = new Paragraph("Text to the left");
        p.add(new Chunk(glue));
        p.add("Text to the right");
        document.add(p);
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);
        table.addCell(getCell("Text to the left", PdfPCell.ALIGN_LEFT));
        table.addCell(getCell("Text in the middle", PdfPCell.ALIGN_CENTER));
        table.addCell(getCell("Text to the right", PdfPCell.ALIGN_RIGHT));
        document.add(table);
        document.close();
    }
 
    public PdfPCell getCell(String text, int alignment) {
        PdfPCell cell = new PdfPCell(new Phrase(text));
        cell.setPadding(0);
        cell.setHorizontalAlignment(alignment);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }
}