/********************** 版权声明 *************************
 * 文件名: PageBorder.java
 * 包名: page.event
 * 版权:	杭州华量软件  pdfDemo
 * 职责:	
 ********************************************************
 *
 * 创建者：peijd   创建时间：2016年7月6日 上午8:49:02
 * 文件版本：V1.0 
 *
 *******************************************************/
package page.event;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
 
 
public class PageBorder {
    public static final String DEST = "results/events/page_border.pdf";
 
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new PageBorder().createPdf(DEST);
        System.out.println("export over!");
    }
 
    public class RedBorder extends PdfPageEventHelper {
        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            PdfContentByte canvas = writer.getDirectContent();
            Rectangle rect = document.getPageSize();
            rect.setBorder(Rectangle.BOX); // left, right, top, bottom border
            rect.setBorderWidth(5); // a width of 5 user units
            rect.setBorderColor(BaseColor.RED); // a red border
            rect.setUseVariableBorders(true); // the full width will be visible
            canvas.rectangle(rect);
        }
    }
 
    public void createPdf(String filename) throws IOException, DocumentException {
        // step 1
        Document document = new Document();
        // step 2
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filename));
        RedBorder event = new RedBorder();
        writer.setPageEvent(event);
        // step 3
        document.open();
        // step 4
        List<Integer> factors;
        for (int i = 2; i < 301; i++) {
            factors = getFactors(i);
            if (factors.size() == 1) {
                document.add(new Paragraph("This is a prime number!"));
            }
            for (int factor : factors) {
                document.add(new Paragraph("Factor: " + factor));
            }
            document.newPage();
        }
        // step 5
        document.close();
    }
 
    public static List<Integer> getFactors(int n) {
        List<Integer> factors = new ArrayList<Integer>();
        for (int i = 2; i <= n; i++) {
          while (n % i == 0) {
            factors.add(i);
            n /= i;
          }
        }
        return factors;
    }
}