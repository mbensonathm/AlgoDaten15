package output;

public class HTML_Generator {
	
	public static String createDoc(String title, String content){
		return "<!DOCTYPE html>\n<html>\n<head>\n<title>" + title + "</title>\n<STYLE type=\"text/css\">div{margin: 10px; display: block; clear: right; background-color:#f0f0f0; -moz-border-radius: 5px; -webkit-border-radius: 5px; -khtml-border-radius: 5px; border-radius: 5px;}</STYLE></head>\n<body>" + content + "</body></html>";
	}
	
	public static String divTags(String content){
		return "<div>" + content + "</div>\n"; 
	}
	
	public static String tableTags(String table){
		return "<table border=2>" + table + "</table>\n";
	}
	
	public static String trTags(String content){
		return "<tr>" + content + "</tr>\n";
	}
	
	public static String tdTags(String content){
		return "<td>" + content + "</td>";
	}
	
	

}
