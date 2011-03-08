package viewer;

import java.awt.Container;
import java.awt.print.PrinterException;
import java.io.IOException;


public interface ViewableDocument {
	public Container getViewContainer() throws IOException ;
	public String getDocumentType();
	public void print() throws PrinterException;
}
