/********************** 版权声明 *************************
 * 文件名: AddOverlappingImage.java
 * 包名: tables.images
 * 版权:	杭州华量软件  pdfDemo
 * 职责:	
 ********************************************************
 *
 * 创建者：peijd   创建时间：2016年7月4日 下午8:17:40
 * 文件版本：V1.0 
 *
 *******************************************************/
package tables.images;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPTableEvent;
import com.itextpdf.text.pdf.PdfWriter;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
 
 
public class AddOverlappingImage {
 
    public static final String DEST = "results/tables/add_overlapping_image.pdf";
 
    public class ImageContent implements PdfPTableEvent {
        protected Image content;
        public ImageContent(Image content) {
            this.content = content;
        }
        public void tableLayout(PdfPTable table, float[][] widths,
                float[] heights, int headerRows, int rowStart,
                PdfContentByte[] canvases) {
            try {
                PdfContentByte canvas = canvases[PdfPTable.TEXTCANVAS];
                float x = widths[3][1] + 10;
                float y = heights[3] - 10 - content.getScaledHeight();
                content.setAbsolutePosition(x, y);
                canvas.addImage(content);
            } catch (DocumentException e) {
                throw new ExceptionConverter(e);
            }
        }
    }
 
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new AddOverlappingImage().createPdf(DEST);
        System.out.println("export Over!");
    }
 
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PdfPTable table = new PdfPTable(5);
        table.setTableEvent(new ImageContent(Image.getInstance("resources/images/hero.jpg")));
        table.setWidthPercentage(100);
        PdfPCell cell;
        for (int r = 'A'; r <= 'Z'; r++) {
            for (int c = 1; c <= 5; c++) {
                cell = new PdfPCell();
                cell.addElement(new Paragraph(String.valueOf((char) r) + String.valueOf(c)));
                table.addCell(cell);
            }
        }
        document.add(table);
        document.close();
    }
}