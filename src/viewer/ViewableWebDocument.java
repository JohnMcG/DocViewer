package viewer;

import java.io.IOException;
import java.net.URL;

import javax.swing.JEditorPane;

public class ViewableWebDocument implements ViewableDocument {

	URL m_url;
	
	ViewableWebDocument(URL url) {
		m_url = url;
	}
	@Override
	public JEditorPane getEditorPane() throws IOException {
		return new JEditorPane(m_url);
	}

}
