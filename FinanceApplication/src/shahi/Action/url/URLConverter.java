package shahi.Action.url;

import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;

public class URLConverter {
	public static URL convertToURLEscapingIllegalCharacters(String string){
	    try {
	        String decodedURL = URLDecoder.decode(string, "UTF-8");
	        URL url = new URL(decodedURL);
	        URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef()); 
	        return uri.toURL(); 
	    } catch (Exception ex) {
	        ex.printStackTrace();
	        return null;
	    }
	}

	public static String toInitCaps(String inputString) {
		StringBuilder result = new StringBuilder();
		if (inputString != null && inputString.length() > 0) {
			for (String f : inputString.split(" ")) {
				if (result.length() > 0) {
					result.append(" ");
				}
                                if(f.trim().length()>1){
				result.append(f.substring(0, 1).toUpperCase()).append(
						f.substring(1, f.length()).toLowerCase());
                                }
                                else{
                                    if(f.trim().length()==1){
                                        result.append(f.substring(0, 1).toUpperCase());
                                    }
                                }
			}
		}
		return result.toString();
	}

}
