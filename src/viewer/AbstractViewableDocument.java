package viewer;

import java.awt.Container;
import java.awt.print.PrinterException;
import java.io.IOException;

import javax.swing.JEditorPane;


public abstract class AbstractViewableDocument implements ViewableDocument, Printable {

	private String m_type;
	
	protected JEditorPane editorPane;
	
	protected AbstractViewableDocument(String type) {
		m_type = type;
	}
	
	
	@Override
	public Container getViewContainer() throws IOException {
		if (editorPane == null) {
			this.initEditorPane();
		}
		return editorPane;
	}
	
	@Override
	public String getDocumentType() {
		return m_type;
	}
	
	public void print() throws PrinterException {
		// Should not print what is not already displayed
		assert (editorPane != null);
		editorPane.print();
	}
	
	protected abstract void initEditorPane() throws IOException;

}