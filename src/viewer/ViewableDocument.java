package viewer;

import java.awt.Container;
import java.io.IOException;

/*
 * Interface representing the document
 */
public interface ViewableDocument {
	public Container getViewContainer() throws IOException ;
	public String getDocumentType();
}
