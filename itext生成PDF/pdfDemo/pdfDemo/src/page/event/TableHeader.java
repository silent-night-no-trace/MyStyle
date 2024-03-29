/********************** 版权声明 *************************
 * 文件名: TableHeader.java
 * 包名: page.event
 * 版权:	杭州华量软件  pdfDemo
 * 职责:	
 ********************************************************
 *
 * 创建者：peijd   创建时间：2016年7月6日 上午8:59:01
 * 文件版本：V1.0 
 *
 *******************************************************/
package page.event;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
 
public class TableHeader {
    public static final String DEST = "results/events/table_header.pdf";
 
    public class HeaderTable extends PdfPageEventHelper {
        protected PdfPTable table;
        protected float tableHeight;
        public HeaderTable() {
            table = new PdfPTable(1);
            table.setTotalWidth(523);
            table.setLockedWidth(true);
            table.addCell("Header row 1");
            table.addCell("Header row 2");
            table.addCell("Header row 3");
            tableHeight = table.getTotalHeight();
        }
 
        public float getTableHeight() {
            return tableHeight;
        }
 
        public void onEndPage(PdfWriter writer, Document document) {
            table.writeSelectedRows(0, -1,
                    document.left(),
                    document.top() + ((document.topMargin() + tableHeight) / 2),
                    writer.getDirectContent());
        }
    }
 
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new TableHeader().createPdf(DEST);
        System.out.println("export over!");
    }
 
    public void createPdf(String filename) throws IOException, DocumentException {
        TableHeader.HeaderTable event = new TableHeader.HeaderTable();
        // step 1
        Document document = new Document(PageSize.A4, 36, 36, 20 + event.getTableHeight(), 36);
        // step 2
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filename));
        writer.setPageEvent(event);
        // step 3
        document.open();
        // step 4
        for (int i = 0; i < 50; i++)
            document.add(new Paragraph("Hello World!"));
        document.newPage();
        document.add(new Paragraph("Hello World!"));
        document.newPage();
        document.add(new Paragraph("Hello World!"));
        // step 5
        document.close();
    }
}