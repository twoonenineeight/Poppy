package worker;

import java.net.URL;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.Map;

public class InfoCollector {
	private Map<String, String> query_pairs = new LinkedHashMap<String, String>();
	private static InfoCollector instance = null;
	
	private InfoCollector(String docURL) throws Exception {
		URL url = new URL(docURL);
	    String query = url.getQuery();
	    String[] pairs = query.split("&");
	    for (String pair : pairs) {
	        int idx = pair.indexOf("=");
	        query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
	    }
	}
	
	public static InfoCollector getInstance() {
		return instance;
	}
	
	public static void collectInfoFrom(String docURL) throws Exception {
		instance = new InfoCollector(docURL);
	}
	
	public static String getValueFromKey(String key) {
		return instance.query_pairs.get(key);
	}
}
