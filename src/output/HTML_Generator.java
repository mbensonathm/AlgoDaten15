package output;

public class HTML_Generator {
	
	public static String createDoc(String title, String content, String imagePath){
		String image = "<h3 id=TrieImage>Trie Image</h3><div><IMG id=\"TrieImage\" SRC=\"file://" + imagePath +" ALT=\"Tries\" width=\"100%\" height=\"100%\"></div>\n";
		String nav = "<nav><ul><li><a href=\"#TrieImage\">Trie Image</a></li><li><a href=\"#TokenSequence1\">Token Sequence: Original</a></li><li><a href=\"#TokenSequence2\">Token Sequence: Suspect</a></li><li><a href=\"#Scoring\">Scoring</a></li><li><a href=\"#Matrix\">Matrix</a></li><li><a href=\"#LineOutput\">Three Line Output</a></li><li><a href=\"#ColumnOutput\">Three Column Output</a></li></ul></nav>";
		return "<!DOCTYPE html>\n<html>\n<head>\n<title>" + title + "</title>\n<meta charset='utf-8'>\n<STYLE type=\"text/css\">\ndiv#container{margin-left:180px;}\nh3{margin-top:10px; margin-left: 10px;}\nnav{margin:2px; position:fixed;}\nul{padding: 0; list-style:none;}\na{text-decoration:none; padding: 5px 0; width: 150px; float:left; background: #acd4f2}\ndiv.seq{margin-top:10px; margin-bottom:10px; font-family: Consolas; display: block; clear: right; background-color:#f0f0f0; -moz-border-radius: 5px; -webkit-border-radius: 5px; -khtml-border-radius: 5px; border-radius: 5px;}\ndiv.three{display: inline-block; font-family: Consolas; width: 300px; background-color:#f0f0f0;}\n</STYLE></head>\n<body>" + 
				nav + "<div id=\"container\">" +image + content + "</body></html>";
	}
	
	public static String divTags(String content){
		return "<div class=\"seq\">" + content + "</div>\n"; 
	}
	
	public static String tableTags(String table){
		return "<div><table border=2>" + table + "</table></div>\n";
	}
	
	public static String trTags(String content){
		return "<tr>" + content + "</tr>\n";
	}
	
	public static String tdTags(String content){
		return "<td>" + content + "</td>";
	}
	
	public static String threeColumns(String[] content){
		String s = "<h3 id=\"ColumnOutput\">Three Column Output</h3>";
		s += "<div class=\"three\">" + "<strong>Original</strong><br/>" + content[0] + "</div>";
		s += "<div class=\"three\">" + "<strong>Consensus</strong><br/>" + content[1] + "</div>";
		s += "<div class=\"three\">" + "<strong>Suspect</strong><br/>" + content[2] + "</div>";
		s = "<div>" + s + "</div>";
		return s;
	}

}
