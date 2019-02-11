
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;


public class GetDocumentFromURL {
	public static void main(String args[]) throws UnsupportedEncodingException, IOException {
		String placeToSave = "C:/Users/TMH/Desktop/Delphinium_Test/";
		
		URL url = new URL("http://repository.vnu.edu.vn/flowpaper/simple_document.php?subfolder=14/23/60/&doc=142360455579609585742048625500263847997&bitsid=c2795b93-0d0c-42ab-8845-49ebcdd6083e&uid=");
		
		
		Map<String, String> query_pairs = new LinkedHashMap<String, String>();
	    String query = url.getQuery();
	    String[] pairs = query.split("&");
	    for (String pair : pairs) {
	        int idx = pair.indexOf("=");
	        query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
	    }
	    
	    
	    for(int i = 1; i <= 10; ++i) {
	    	URL pageURL = new URL("http://repository.vnu.edu.vn/flowpaper/services/view.php?"
	    			+ "doc=" + query_pairs.get("doc")
	    			+ "&format=" + "png"
	    			+ "&page=" + i
	    			+ "&subfolder=" + query_pairs.get("subfolder"));
	    	
	    
	    	ReadableByteChannel rbc = Channels.newChannel(pageURL.openStream());
	    	FileOutputStream fos = new FileOutputStream(placeToSave + "img/" + query_pairs.get("doc") + "_" + i + ".png");
	    	fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
	    	fos.close();
	    	
	    	System.out.println("Downloaded " + pageURL);
	    }
	    
	    PDDocument document = new PDDocument();
	    for(int i = 1; i <= 10; ++i) {
	    	InputStream in = new FileInputStream(placeToSave + "img/" + query_pairs.get("doc") + "_" + i + ".png");
	    	BufferedImage bimg = ImageIO.read(in);
	    	float width = bimg.getWidth();
	    	float height = bimg.getHeight();
	    	PDPage page = new PDPage(new PDRectangle(width, height));
	    	document.addPage(page);
	    	PDPageContentStream contentStream = new PDPageContentStream(document, page);
	    	
	    	PDImageXObject image = PDImageXObject.createFromFile(placeToSave + "img/" + query_pairs.get("doc") + "_" + i + ".png", document);
	    	contentStream.drawImage(image, 0, 0);
	    	contentStream.close();
	    	in.close();
	    }
	    document.save(placeToSave + query_pairs.get("doc") + ".pdf");
	    document.close();
	    System.out.println("OK!");
	   
	}
}
