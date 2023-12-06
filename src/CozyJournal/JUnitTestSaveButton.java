// package //
package CozyJournal;

// IMPORTS //
// import JUnit //
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

// import actions //
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// import GUI elements //
import javax.swing.*;

// JUnit test class for testing that the save button clears all fields //
public class JUnitTestSaveButton {
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
	public void JUnitMethod() {
	// ARRANGE //
		// mimics a user writing in the text box //
		textArea.setText("Test Text");
		// mimics a user adding a title to the entry //
		titleField.setText("Test Title");
		
		// action to be performed when clicking the save button //
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
				titleField.setText("");
			}
		});
		
	// ACT //
		// mimics a user clicking the save button //
		saveButton.doClick();
		
	// ASSERT //
		// all text areas should be cleared //
		assertEquals("", textArea.getText());
		assertEquals("", titleField.getText());
	}
	public static void main(String[] args) {
	}
}
