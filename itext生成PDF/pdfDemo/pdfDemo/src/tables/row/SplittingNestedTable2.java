/********************** 版权声明 *************************
 * 文件名: SplittingNestedTable2.java
 * 包名: tables.row
 * 版权:	杭州华量软件  pdfDemo
 * 职责:	
 ********************************************************
 *
 * 创建者：peijd   创建时间：2016年7月5日 下午9:05:50
 * 文件版本：V1.0 
 *
 *******************************************************/
package tables.row;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
 
/**
 * @author Bruno Lowagie (iText Software)
 */
public class SplittingNestedTable2 {
    public static final String DEST = "results/tables/splitting_nested_table2.pdf";
 
    public static void main(String[] args) throws IOException,
            DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new SplittingNestedTable2().createPdf(DEST);
        System.out.println("export over!");
    }
 
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document(new Rectangle(300, 150));
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        document.add(new Paragraph("Table with setSplitLate(true):"));
        PdfPTable table = new PdfPTable(2);
        table.setSpacingBefore(10);
        PdfPCell cell = new PdfPCell( new Phrase("GROUPS"));
        cell.setRotation(90);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        PdfPTable inner = new PdfPTable(1);
        inner.addCell("row 1");
        inner.addCell("row 2");
        inner.addCell("row 3");
        inner.addCell("row 4");
        inner.addCell("row 5");
        cell = new PdfPCell(inner);
        cell.setPadding(0);
        table.addCell(cell);
        document.add(table);
        document.newPage();
        document.add(new Paragraph("Table with setSplitLate(false):"));
        table.setSplitLate(false);
        document.add(table);
        document.close();
    }
}
