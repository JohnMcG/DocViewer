package viewer;

import java.io.IOException;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

public class DocViewerFrame extends JFrame {

	private JEditorPane editPane = null;
	private JScrollPane scrollPane = null;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DocViewerFrame myFrame = new DocViewerFrame();
		try {
			ViewableWebDocument doc = 
				new ViewableWebDocument(new URL("http://docs.google.com/View?id=dhs6mc8s_6461g9hdgmhb"));
			myFrame.displayDocument(doc);
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Fatal error: " + e.getMessage());
			
		}

	}
	
	DocViewerFrame() {
		JLabel jlbHelloWorld = new JLabel("Hello World");
		add(jlbHelloWorld);
		this.setSize(300,500);
		// pack();
		setVisible(true);	
	}
	
	private void displayDocument(ViewableDocument doc) {
		if (scrollPane != null)
			this.remove(editPane);
		try {
			scrollPane = new JScrollPane(doc.getEditorPane());
		}
		catch(IOException e) {
			JOptionPane.showMessageDialog(null, "Problem accessing document: " + e.getMessage());
		}
		this.add(scrollPane);
		this.pack();
		
	}

}
