package viewer;

import java.awt.Container;
import java.awt.print.PrinterException;
import java.io.IOException;
import java.net.URL;

import javax.swing.JEditorPane;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/*
 * Class to represent a viewable document displayed in a JEditorPane
 * with a type
 */
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
	
	public static ViewableDocument CreateViewableDocument(Node fstNode) throws Exception {
		ViewableDocument retval = null;
		NamedNodeMap attributes = fstNode.getAttributes();
		String sourceType = attributes.getNamedItem("sourceType").getNodeValue();
		String location = attributes.getNamedItem("location").getNodeValue();
		String type = attributes.getNamedItem("type").getNodeValue();
		if (sourceType.equals("url")) {
			retval = new ViewableURLDocument(new URL(location), type);
		} else if (sourceType.equals("file")) {
			String sFormat = attributes.getNamedItem("format").getNodeValue();
			ViewableFileDocument.Format format = sFormat.equals("rtf")
				? ViewableFileDocument.Format.RTF
				: ViewableFileDocument.Format.TEXT;
			retval = new ViewableFileDocument(location,
				format,
				type);																				
		}
		return retval;
	}
	
	protected abstract void initEditorPane() throws IOException;

}