package documentMangement;

import documentMangement.tools.TextTools;
import documentMangement.tools.XMLTools;

public class Main {

	public static void main(String[] args) {
 		String type = args[0];
		String searchFor = args[1];
		String replaceWith = args[2];
		String fileNameIn = args[3];
		String fileNameOut = args[4];
  
		switch (type) {
		case "text":
			TextTools.replaceValue( fileNameIn, fileNameOut,searchFor, replaceWith);
			break ;
		case "xml": XMLTools.replaceAttribute( fileNameIn, fileNameOut,searchFor, replaceWith);
			break ;
 		default:
			throw new RuntimeException("Type not supported");
		}
	}

}
