package viewer;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class DocumentCollection {
	private ArrayList<ViewableDocument> documents;
	
	DocumentCollection() {
		documents = new ArrayList<ViewableDocument>();
		try {
			ViewableDocument doc1 =  
				new ViewableURLDocument(new URL("file:///"
						+ System.getProperty("user.dir")
						+ "/resources/directions.txt"), "Directions");
			documents.add(doc1);
			
			ViewableDocument doc2 = 
				new ViewableURLDocument(new URL("http://docs.google.com/View?id=dhs6mc8s_6461g9hdgmhb"),
						"Resume");
			
			documents.add(doc2);
		}
		catch(MalformedURLException e)
		{
			JOptionPane.showMessageDialog(null, "Bad url: " + e.getMessage());
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
	
	
}
