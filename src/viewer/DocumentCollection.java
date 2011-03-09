package viewer;

import java.io.File;
import java.util.ArrayList;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


/*
 * Class representing a collection of documents
 */
public class DocumentCollection {
	private ArrayList<ViewableDocument> documents;
	
	DocumentCollection() {
		documents = new ArrayList<ViewableDocument>();
		initCollection();		

	}
	
	private void initCollection() {
		
		try {
			File file = new File("./resources/IncludedDocs.xml");
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			doc.getDocumentElement().normalize();
			NodeList nodeLst = doc.getElementsByTagName("document");

			for (int s = 0; s < nodeLst.getLength(); ++s) {

				Node fstNode = nodeLst.item(s);
		    
				if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
					ViewableDocument current = AbstractViewableDocument.CreateViewableDocument(fstNode);					
					documents.add(current);
				}
		    			     
		    }

		  
		  } catch (Exception e) {
		    e.printStackTrace();
		  }

	}
	
	public ViewableDocument getDocAt(int index) {
		return documents.get(index);
	}
	
	Iterable<String> getDocumentTypes() {
		ArrayList<String> returnVal = new ArrayList<String>();
		for (ViewableDocument document: documents) {
			returnVal.add(document.getDocumentType());			
		}
		return returnVal;
	}
	
	public int length() {
		return documents.size();
	}
	
	
}
