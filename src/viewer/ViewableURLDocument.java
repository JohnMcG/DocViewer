package viewer;


import java.io.IOException;
import java.net.URL;
import javax.swing.JEditorPane;


/*
 * Class representing a viewable document accessible by a URL
 */
public class ViewableURLDocument extends AbstractViewableDocument {

	private URL m_url;
	
	ViewableURLDocument(URL url, String type) {
		super(type);
		m_url = url;
	}
	
	protected void initEditorPane() throws IOException {
		editorPane =  new JEditorPane(m_url);
		editorPane.setEditable(false);
	}
	
	

}
