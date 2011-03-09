package viewer;

import java.awt.Container;
import java.awt.print.PrinterException;
import java.io.IOException;
import java.net.URL;

import javax.swing.JEditorPane;
import javax.swing.text.rtf.RTFEditorKit;


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
