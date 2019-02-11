package worker;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class PDFBuilder {
	public static void build(String imgFolderPath, String imgPrefix, String placeToSave, String fileName, int pageNum) throws Exception {
		PDDocument document = new PDDocument();
	    for(int i = 1; i <= pageNum; ++i) {
	    	InputStream in = new FileInputStream(imgFolderPath + imgPrefix + "_" + i + ".png");
	    	BufferedImage bimg = ImageIO.read(in);
	    	float width = bimg.getWidth();
	    	float height = bimg.getHeight();
	    	PDPage page = new PDPage(new PDRectangle(width, height));
	    	document.addPage(page);
	    	PDPageContentStream contentStream = new PDPageContentStream(document, page);
	    	
	    	PDImageXObject image = PDImageXObject.createFromFile(imgFolderPath + imgPrefix + "_" + i + ".png", document);
	    	contentStream.drawImage(image, 0, 0);
	    	contentStream.close();
	    	in.close();
	    }
	    document.save(placeToSave + fileName + ".pdf");
	    document.close();
	}
	
}

