/********************** 版权声明 *************************
 * 文件名: WatermarkedImages1.java
 * 包名: images
 * 版权:	杭州华量软件  pdfDemo
 * 职责:	
 ********************************************************
 *
 * 创建者：peijd   创建时间：2016年7月8日 上午8:17:58
 * 文件版本：V1.0 
 *
 *******************************************************/
package images;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
 
public class WatermarkedImages1 {
    public static final String IMAGE1 = "resources/images/hero.jpg";
    public static final String IMAGE2 = "resources/images/dog.bmp";
    public static final String IMAGE3 = "resources/images/fox.bmp";
    public static final String IMAGE4 = "resources/images/background.jpg";
    public static final Font FONT = new Font(FontFamily.HELVETICA, 12, Font.NORMAL, GrayColor.GRAYWHITE);
    public static final String DEST = "results/images/watermark_template.pdf";
 
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new WatermarkedImages1().createPdf(DEST);
        System.out.println("export over!");
    }
 
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PdfContentByte cb = writer.getDirectContentUnder();
        document.add(getWatermarkedImage(cb, Image.getInstance(IMAGE1), "Bruno"));
        document.add(getWatermarkedImage(cb, Image.getInstance(IMAGE2), "Dog"));
        document.add(getWatermarkedImage(cb, Image.getInstance(IMAGE3), "Fox"));
        Image img = Image.getInstance(IMAGE4);
        img.scaleToFit(400, 700);
        document.add(getWatermarkedImage(cb, img, "Bruno and Ingeborg"));
        document.close();
    }
 
    public Image getWatermarkedImage(PdfContentByte cb, Image img, String watermark) throws DocumentException {
        float width = img.getScaledWidth();
        float height = img.getScaledHeight();
        PdfTemplate template = cb.createTemplate(width, height);
        template.addImage(img, width, 0, 0, height, 0, 0);
        ColumnText.showTextAligned(template, Element.ALIGN_CENTER,
                new Phrase(watermark, FONT), width / 2, height / 2, 30);
        return Image.getInstance(template);
    }
}
