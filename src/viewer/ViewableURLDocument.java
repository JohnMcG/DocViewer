package viewer;

import java.awt.Container;
import java.awt.print.PrinterException;
import java.io.IOException;
import java.net.URL;

import javax.swing.JEditorPane;


public class ViewableURLDocument extends ViewableTypedDocument {

	private URL m_url;
	private JEditorPane editorPane= null;
	
	ViewableURLDocument(URL url, String type) {
		super(type);
		m_url = url;
	}
	@Override
	public Container getViewContainer() throws IOException {
		if (editorPane == null) {
			this.initEditorPane();
		}
		return editorPane;
	}

	private void initEditorPane() throws IOException {
		editorPane =  new JEditorPane(m_url);
		editorPane.setEditable(false);
	}

	
	public void print() throws PrinterException {
		// Should not print what is not already displayed
		assert (editorPane != null);
		editorPane.print();
	}
	
	

}
