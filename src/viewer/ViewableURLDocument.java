package viewer;

import java.awt.Container;
import java.awt.print.PrinterException;
import java.io.IOException;
import java.net.URL;

import javax.swing.JEditorPane;
import javax.swing.text.rtf.RTFEditorKit;


public class ViewableURLDocument extends ViewableTypedDocument {

	private URL m_url;
	private JEditorPane editorPane= null;
	public enum Format {TEXT, RTF}
	Format m_format;
	
	ViewableURLDocument(URL url, String type) {
		super(type);
		m_url = url;
		m_format = Format.TEXT;
	}
	
	ViewableURLDocument(URL url, String type, Format format) {
		super(type);
		m_url = url;
		m_format = format;
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
		if (m_format.equals(Format.RTF)) {
			RTFEditorKit rtf = new RTFEditorKit();
			editorPane.setEditorKit(rtf);
		}
		editorPane.setEditable(false);
	}

	
	public void print() throws PrinterException {
		// Should not print what is not already displayed
		assert (editorPane != null);
		editorPane.print();
	}
	
	

}
