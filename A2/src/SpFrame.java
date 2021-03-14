import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.border.*;

/**
This frame contains multiple shapes and a control panel
to change the size and colour of the shape.
*/

public class SpFrame extends JFrame {

	
	private JRadioButton circle;
	private JRadioButton rect;
	private JComboBox sizeCombo;
	private JLabel label;
	private Graphics g;
	private JMenuBar menu = new JMenuBar();
	private JMenu fileMenu = new JMenu("File");
	private JMenuItem loadItem = new JMenuItem("Load");
	private JMenuItem saveItem = new JMenuItem("Save");
	private String filename = null;
	private JTextArea textArea = new JTextArea();
	private ActionListener listener;
	
	
	/**
    Constructs the frame.
 */
	
	public SpFrame() {
		
		label = new JLabel();
	    add(label, BorderLayout.CENTER);
	    
		listener = new ChoiceListener();
		
		controlPanel();
		
		MenuBar();
		
	}
	
	class ChoiceListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == loadItem)
				loadFile();
			else if(e.getSource() == saveItem)
				saveFile();
			
			paintComponent(g);
			
		}
		
	}
	
	/**
    Creates the control panel to change the shape and frame layout
 */
	public void controlPanel() {
		
		
		JPanel crb = createRadioButtons();
		JPanel cjb = createjButtons();
		JPanel ccb = createComboBox();
		
		JPanel cp = new JPanel();
		cp.setLayout(new BoxLayout(cp, BoxLayout.Y_AXIS));
		cp.add(crb);
		cp.add(cjb);
		cp.add(ccb);
		
		getContentPane().add(cp,BorderLayout.BEFORE_FIRST_LINE);
		
		
		JPanel daPanel = drawArea();
		
		JPanel cp2 = new JPanel();
		cp2.setLayout(new GridLayout());
		cp2.add(daPanel);
		
		getContentPane().add(cp2, BorderLayout.CENTER);
		
	}
	/**
    Creates the radio buttons for selecting either circle or rectangle.
    @return the panel containing the radio buttons
 */
	
	public JPanel createRadioButtons(){
		
		// shape selection
		circle = new JRadioButton("circle");
		circle.addActionListener(listener);
		rect = new JRadioButton("rectangle");
		rect.addActionListener(listener);
		rect.setSelected(true);
				
		//Add radio buttons to group
		ButtonGroup shape = new ButtonGroup();
		shape.add(circle);
		shape.add(rect);
		
		JPanel p1 = new JPanel();
		p1.setBorder(new TitledBorder(new EtchedBorder(), "Selection Area"));
		p1.add(rect);
		p1.add(circle);
		
		return p1;
		
	}
	
	/**
	 Creates colour buttons.
	 * @return jButton panels
	 */
		
	public JPanel createjButtons() {
		// Color labels
		JButton red = new JButton("RED");
		red.setBackground(Color.RED);
		red.addActionListener(listener);
		
		JButton yellow = new JButton("YELLOW");
		yellow.setBackground(Color.YELLOW);
		yellow.addActionListener(listener);
		
		JButton blue = new JButton("BLUE");
		blue.setBackground(Color.BLUE);
		blue.addActionListener(listener);
		
		JPanel p2 = new JPanel();
		p2.add(red);
		p2.add(yellow);
		p2.add(blue);
		
		return p2;
		
	}
	
	/**
    Creates the combo box with the size choices.
    @return the panel containing the combo box
 */
	
	public JPanel createComboBox() {
		
		//Combo Box
		sizeCombo = new JComboBox();
		sizeCombo.addItem("Big");
		sizeCombo.addItem("Medium");
		sizeCombo.addItem("Small");
		sizeCombo.addActionListener(listener);
		
		JPanel p = new JPanel();
		p.add(sizeCombo);
		
		return p;
		
	}
	
	/**
	 Sets panel and border for draw area
	 * @return panel
	 */
	
	public JPanel drawArea() {
		
		JPanel p4 = new JPanel();
		p4.setBorder(new TitledBorder(new EtchedBorder(), "Draw Area"));
		
		return p4;
		
	}
	
	/**
	 creates the menu bar "file" with load and save.
	  @return fileMenu
	 */
	public JMenu MenuBar() {
		
		
		fileMenu.add(loadItem);
		fileMenu.add(saveItem);
		loadItem.addActionListener(listener);
		saveItem.addActionListener(listener);
		menu.add(fileMenu);
		setJMenuBar(menu);
		
		
		return fileMenu;
		
		
	}
	
	/** Creates the load option
	    Load option cancels if no saved drawing.
	 	  */
	 
	
	private void loadFile() {
		 JFileChooser fc = new JFileChooser();
		    String name = null;
		    if (fc.showOpenDialog(null) != JFileChooser.CANCEL_OPTION)
		      name = fc.getSelectedFile().getAbsolutePath();
		    else
		      return;  // user cancelled
		    try {
		      Scanner in = new Scanner(new File(name));  // might fail
		      filename = name;
		      textArea.setText("");
		      while (in.hasNext())
		        textArea.append(in.nextLine() + "\n");
		      in.close();
		    }
		    catch (FileNotFoundException e) {
		      JOptionPane.showMessageDialog(null, "No saved drawing");
		    }
	}
	
	/**
	 Saves the current shapes to the file "shapeDrawing.txt
	 */
	private void saveFile() {
		
		JFrame save = new JFrame();
		JFileChooser fc = new JFileChooser();
		fc.setSelectedFile(new File("shapeDrawing.txt"));
		fc.showSaveDialog(save);
		fc.setDialogTitle("Message");
		JOptionPane.showMessageDialog(null,"Drawing successfully saved!");
		
	}
	
	/**
    Gets user choice for shape, size, and colour.
    and sets the composition of the shapes.
 */
	public void paintComponent(Graphics g) {
		
		Graphics2D g2 = (Graphics2D)g;
		
		if (rect.isSelected()) {
			g2.drawRect(30, 30, 30, 30);
		}
		else {
			g2.drawOval(30, 30, 30, 30);
		}
		
		g2.setColor(getBackground());
		
		  Graphics size;
	      
	      
	      
	      if(sizeCombo.getSelectedItem() == "Big")
	      {
	    	  size = g2.create(30, 30, 30, 30);
	      }
	      else if(sizeCombo.getSelectedItem() == "Medium");
	      {
	    	  size = g2.create(40,40,40,40);
	      }
	      if(sizeCombo.getSelectedItem() == "Small");
	      {
	    	  size = g2.create(50, 50, 50, 50);
	      }

	    
		 	label.getGraphics();
		 	label.repaint();
	}
	
		
	
	
	

}
