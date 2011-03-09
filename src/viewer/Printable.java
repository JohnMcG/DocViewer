package viewer;

import java.awt.print.PrinterException;


/*
 * Interface indicating that a file can be printed
 */
public interface Printable {

	public void print() throws PrinterException;
}
