package documentMangement.tools;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import documentMangement.constants.Constants;

public class XMLTools {

	void replaceValue(String input, String output, String searchFor, String replaceWith) {

	}

	public static void replaceAttribute(String xmlFilePath, String output, String oldValue, String newValue) {
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(Constants.INPUT_PATH + "/" + xmlFilePath);
			Element rootElement = doc.getDocumentElement();
			replaceAtr(rootElement, oldValue, newValue);
			writeDocument(doc ,output) ;
		
		} catch (ParserConfigurationException | SAXException | IOException  e) {
			e.printStackTrace();
		}
	}

	private static void writeDocument(Document doc, String output) {
		try {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(Constants.RESOURCES_OUTPUT_PATH + "/" + output));
		transformer.transform(source, result);
	} catch ( TransformerException e) {
		e.printStackTrace();
	}
	}

	private static void replaceAtr(Node rootElement, String oldValue, String newValue) {

		NamedNodeMap attributes = rootElement.getAttributes();
		if (attributes != null) {
			for (int i = 0; i < attributes.getLength(); i++) {
				String correntValue = attributes.item(i).getNodeValue();
				if (correntValue != null && correntValue.equals(oldValue)) {
					attributes.item(i).setNodeValue(newValue);
				}
			}
		}
		NodeList childs = rootElement.getChildNodes();
		if (childs != null) {
			for (int i = 0; i < childs.getLength(); i++) {
				replaceAtr(childs.item(i), oldValue, newValue);
			}
			return;
		}
	}
}