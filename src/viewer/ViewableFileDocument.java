/**
 * 
 */
package viewer;

import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JEditorPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.EditorKit;
import javax.swing.text.rtf.RTFEditorKit;

/**
 * @author john
 *
 *
 * Class representing a document in a file
 */
public class ViewableFileDocument extends AbstractViewableDocument {

	private String m_filePath;
	public enum Format {TEXT, RTF};
	Format m_format;
	
	ViewableFileDocument(String filePath, Format format, String type) {
		super(type);
		m_filePath = filePath;
		m_format = format;
	}
	/* (non-Javadoc)
	 * @see viewer.AbstractViewableDocument#initEditorPane()
	 */
	@Override
	protected void initEditorPane() throws IOException {		
		EditorKit kit = this.getEditorKit();
		editorPane = new JEditorPane();
		editorPane.setEditorKit(kit);
		FileInputStream fi = new FileInputStream(m_filePath);
		try {
			kit.read(fi, editorPane.getDocument(), 0);
		} catch (BadLocationException e) {
			
		}
	}
	
	private EditorKit getEditorKit() {
		EditorKit retval = null;
		if (m_format.equals(Format.TEXT)) {
			retval = new DefaultEditorKit();
		} else if (m_format.equals(Format.RTF)) {
			retval = new RTFEditorKit();
		}
		return retval;		
	}
	

}
