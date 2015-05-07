package output;

public class HTML_Generator {
	
	public static String creatDoc(String title, String content){
		return "<!DOCTYPE html>\n<html><head><title>" + title + "</title></head>\n<body>"
				+ content + "</body></html>";
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
