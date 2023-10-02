package CozyJournalPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUI implements ActionListener{
	//add JFrame
	private JFrame frame;
	//add JPanel
	private JPanel panel;
	//add JButton
	private JButton button;
	//add JLable
	private JLabel label;
	
	public GUI(){
		
				
		//creates frame
		frame = new JFrame();
		//set title of frame
		frame.setTitle("Cozy Journal");
		//exit out of application
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//prevent frame from being resized
		frame.setResizable(false);
		//set size of frame(x an y)
		frame.setSize(780,780);
		//make frame visible
		frame.setVisible(true);
		//change background color of frame
		frame.getContentPane().setBackground(new Color(252,252,202,255));
		
		//will change this later
		frame.setLayout(null);
				
		/*Change the icon for the GUI(NOT WORKING!)
		* ImageIcon image = new ImageIcon("notebook.png");
		* frame.setIconImage(image.getImage());
		*/
				
		
				
		//creates panel
		JPanel titlePanel = new JPanel();
		titlePanel.setBackground(new Color(252,252,202,255));
		titlePanel.setBorder(BorderFactory.createLineBorder(new Color(143,96,70,255)));
		titlePanel.setBounds(0,0,780,50);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(new Color(252,252,202,255));
		centerPanel.setBorder(BorderFactory.createLineBorder(new Color(143,96,70,255)));
		centerPanel.setBounds(100,100,550,400);
		
		JPanel moodPanel = new JPanel();
		moodPanel.setBackground(new Color(252,252,202,255));
		moodPanel.setBorder(BorderFactory.createLineBorder(new Color(143,0,70,255)));
		moodPanel.setBounds(0,600,780,50);
		
		JPanel navigationPanel = new JPanel();
		navigationPanel.setBackground(new Color(0,0,0));
		navigationPanel.setBorder(BorderFactory.createLineBorder(new Color(143,96,70,255)));
		navigationPanel.setBounds(0,0,550,340);
	

		
		//add panels to the frame
		frame.add(titlePanel);
		frame.add(centerPanel);
		frame.add(moodPanel);
		frame.add(navigationPanel);
	}

	public static void main(String[] args) {
		
		new GUI();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
