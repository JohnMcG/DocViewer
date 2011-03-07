package viewer;

import java.io.IOException;

import javax.swing.JEditorPane;

public interface ViewableDocument {
	JEditorPane getEditorPane() throws IOException ;
}
