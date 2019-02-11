package worker;

import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class ImageDownloader {
	public static void download(String placeToSave, String doc, String format, String subfolder, int pageNum) throws Exception {   
	    
	    for(int i = 1; i <= pageNum; ++i) {
	    	URL pageURL = new URL("http://repository.vnu.edu.vn/flowpaper/services/view.php?"
	    			+ "doc=" + doc
	    			+ "&format=" + format
	    			+ "&page=" + i
	    			+ "&subfolder=" + subfolder);
	    	System.out.println("Downloading " + pageURL);
	    	
	    	ReadableByteChannel rbc = Channels.newChannel(pageURL.openStream());
	    	FileOutputStream fos = new FileOutputStream(placeToSave + "img/" + doc + "_" + i + ".png");
	    	fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
	    	fos.close();
	    	
	    	System.out.println("Downloaded");
	    }
	}
	
}
