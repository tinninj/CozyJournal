/*package CozyJournal;
//import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Test;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.junit.Before;
import javax.swing.JTextArea;
import javax.swing.JTextField;



public class JUnitClass {
	private JButton saveButton;
	private JTextArea textArea;
	private JTextField titleField;


	@Before
	public void setup() {
		saveButton = new JButton("Save");
		textArea = new JTextArea();
		titleField = new JTextField();
	}
	@Test
	public void JunitMethod() {
	    textArea.setText("Test Text"); // should mimic a user writing something in the text area
	    titleField.setText("Test Title"); // should mimic a user writing a title in the title field area
	    saveButton.addActionListener(new ActionListener() { // should save the text written in the text area and text field
	        public void actionPerformed(ActionEvent e) { // and also make the text area and fields empty again to write another entry
	            textArea.setText("");
	            titleField.setText("");
	        }
	    });
	    saveButton.doClick(); // will trigger the actionlistener to perform its actions whrn you actually click the save button
	    assertEquals("", textArea.getText()); // basically text area should have empty strings after hitting the save button same for text field
	    assertEquals("", titleField.getText());
	}

	public static void main(String[] args) {

	}
}
*/