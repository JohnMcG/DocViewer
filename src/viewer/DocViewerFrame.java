package viewer;

import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;

import javax.swing.JComboBox;
import javax.swing.JLabel;

public class DocViewerFrame extends JFrame implements ActionListener {

	
	private static final long serialVersionUID = -8108386488877345580L;
	private enum Direction {FORWARD, BACKWARD};
	private JScrollPane scrollPane = null;
	private DocumentCollection docCollection = new DocumentCollection();
	JComboBox comboBox;
	private PrintAction printAction = new PrintAction();
	private ExitAction exitAction = new ExitAction();
	private ViewableDocument selectedDoc = null;
	private NavAction forwardAction = new NavAction(Direction.FORWARD);
	private NavAction backwardAction = new NavAction(Direction.BACKWARD);
	private int selectedIndex = 0;
	
	
	private class PrintAction extends AbstractAction {

		/**
		 * 
		 */
		private static final long serialVersionUID = 281915821496666542L;
		PrintAction() {
			super("Print...");
			this.putValue(AbstractAction.MNEMONIC_KEY, KeyEvent.VK_P);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				selectedDoc.print();
			} catch (PrinterException e1) {
				JOptionPane.showMessageDialog(null, "Printing failed: " + e1.getMessage());
			}			
		}
		
		
	}
	
	private class ExitAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1404223376159722545L;

		ExitAction() {
			super("Exit");
			this.putValue(AbstractAction.MNEMONIC_KEY, KeyEvent.VK_X);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
	
	private class FileAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 610797174787606225L;
		private int m_index = 0;

		FileAction(int index, String fileType) {
			super(fileType);
			m_index = index;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			displayDocument(docCollection.getDocAt(m_index));
		}
	}
		
	private class NavAction extends AbstractAction {
	
		/**
		 * 
		 */
		private static final long serialVersionUID = -4787828591731445236L;
		private Direction m_direction;
		NavAction(Direction direction) {
			super(direction.equals(Direction.FORWARD) ? ">" : "<");
			m_direction = direction;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			navigate(m_direction);
		}
	}

	/**
	 * 
	 */

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame myFrame = new DocViewerFrame();
		myFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	DocViewerFrame() {
		this.initMenu();
		this.initToolBar();
		try {
			ViewableDocument doc = docCollection.getDocAt(0);
			this.displayDocument(doc);
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Fatal error: " + e.getMessage());
			
		}
		this.setSize(1000, 1000);
		setVisible(true);	
	}
	
	private void initToolBar()
	{
		JToolBar toolBar = new JToolBar();

		JLabel label = new JLabel();
		label.setText("Choose the document to view: ");
		toolBar.add(label);
		
		comboBox = new JComboBox();
		Iterable<String> docTypes = docCollection.getDocumentTypes();
		for (String type: docTypes) {
			comboBox.addItem(type);
		}
		comboBox.addActionListener(this);
		toolBar.add(comboBox);
		
		toolBar.addSeparator();
		
		toolBar.add(backwardAction);
		toolBar.add(forwardAction);
		
		toolBar.addSeparator();
			
		toolBar.add(printAction);
		
		toolBar.setFloatable(false);
		
	    this.getContentPane().add(toolBar, BorderLayout.NORTH);

	}
	
	private void initMenu() {
		JMenuBar menuBar = new JMenuBar();

		JMenu filemenu = new JMenu("File");
		filemenu.setMnemonic(KeyEvent.VK_F);
		//filemenu.setMnemonic(mnemonic)
		filemenu.add(printAction);
		filemenu.addSeparator();
		int index = 0;
		Iterable<String> docTypes = docCollection.getDocumentTypes();
		for (String type: docTypes) {
			filemenu.add(new FileAction(index++, type));
		}

		filemenu.addSeparator();
		filemenu.add(exitAction);
		menuBar.add(filemenu);
	
		
		this.setJMenuBar(menuBar);
	}
	
	private void displayDocument(ViewableDocument doc) {
		this.updateState(doc);
		try {
			  this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				if (scrollPane != null)
					this.remove(scrollPane);
				try {
					Container container = doc.getViewContainer();
					scrollPane = new JScrollPane(container);
				}
				catch(IOException e) {
					JOptionPane.showMessageDialog(null, "Problem accessing document: " + e.getMessage());
				}
				this.getContentPane().add(scrollPane, BorderLayout.CENTER);
			} finally {
				this.repaint();
				this.setVisible(true);
				this.setCursor(Cursor.getDefaultCursor());
			}
				
		
	}

	private void updateState(ViewableDocument doc) {		
			selectedDoc = doc;
			comboBox.setSelectedItem(doc.getDocumentType());
			selectedIndex = comboBox.getSelectedIndex();
			backwardAction.setEnabled(selectedIndex != 0);
			forwardAction.setEnabled(selectedIndex < docCollection.length() - 1);
				
	}

	
	private void handleSelectionEvent(ActionEvent arg0) {
		this.displayDocument(docCollection.getDocAt(comboBox.getSelectedIndex()));
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {		
		if (arg0.getSource().equals(comboBox)) {		
			this.handleSelectionEvent(arg0);
		}
	}
	
	private void navigate(Direction direction) {
		int newIndex = direction.equals(Direction.FORWARD) 
			? selectedIndex + 1 
			: selectedIndex - 1;
		assert(newIndex > 0 && newIndex < docCollection.length());
		this.displayDocument(docCollection.getDocAt(newIndex));
	}

}

