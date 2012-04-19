package rdf;

public class VarExtractor {
	
	public String SimpleExtract(String line){
		String result=null;
		
		int begin=line.indexOf("<uri>")+5;
		int end=line.lastIndexOf("</uri>");
		result = line.substring(begin, end);
		
		return result;
	}
	
	public boolean checkAppereance(String line, String param){
		return line.contains("<binding name=\""+param+"\">");
	}

}
