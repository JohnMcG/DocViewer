package viewer;

import java.io.IOException;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import java.awt.Container;

public class DocViewerFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8108386488877345580L;
	private JEditorPane editPane = null;
	private JScrollPane scrollPane = null;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new DocViewerFrame();
	}
	
	DocViewerFrame() {
		JToolBar toolbar = new JToolBar();
		Container contentPane = this.getContentPane();
	    contentPane.add(toolbar, BorderLayout.NORTH);
	    this.setSize(300,500);
		// pack();
		try {
			ViewableWebDocument doc = 
				new ViewableWebDocument(new URL("http://docs.google.com/View?id=dhs6mc8s_6461g9hdgmhb"));
			this.displayDocument(doc);
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Fatal error: " + e.getMessage());
			
		}
		
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
		this.getContentPane().add(scrollPane, BorderLayout.CENTER);
		this.pack();
		
	}

}
