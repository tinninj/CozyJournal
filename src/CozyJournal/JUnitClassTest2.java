package CozyJournal;
import static org.junit.Assert.assertEquals;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.jupiter.api.Assertions;

import java.awt.image.BufferedImage;
import java.awt.*;

import javax.imageio.ImageIO;

import javax.swing.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class JUnitClassTest2 {
    JTabbedPane navigationPane = new JTabbedPane(JTabbedPane.BOTTOM);
    private JButton saveButton;
	private JTextArea textArea;
	private JTextField titleField;


    private JPanel createPage(String label) { // to do: use the label or remove it all together, because currently its not doing anything
        JPanel page = new JPanel();   // create a new JPanel
        return page;
    }


    @Before
    public void setUp() throws Exception {
    	
    }

    @Test
    public void testTabLabels() {
    	// Arrange
    	JPanel mainPage = createPage("Home"); // literally copy code from GUI file
        JPanel journalPage = createPage("Journal");
        JPanel calendarPage = createPage("Calendar");
        JPanel analyticsPage = createPage("Analytics");
        
        //Act
        // Add pages to navigation
        navigationPane.addTab("Home", mainPage);  //literally copy code from GUI file
        navigationPane.addTab("Journal", journalPage);
        navigationPane.addTab("Calendar", calendarPage);
        navigationPane.addTab("Analytics", analyticsPage);
        
        //Assert(Checking the values)
    	  assertEquals("Home", navigationPane.getTitleAt(0)); // Checks that on the navigation Home is in index 0
          assertEquals("Journal", navigationPane.getTitleAt(1));
          assertEquals("Calendar", navigationPane.getTitleAt(2));
          assertEquals("Analytics", navigationPane.getTitleAt(3));
    }
    
    @Test
	public void JunitMethod() { // To do: rename test
    	//Arrange
    	saveButton = new JButton("Save");
		textArea = new JTextArea();
		titleField = new JTextField();
    	textArea.setText("Test Text"); // should mimic a user writing something in the text area
	    titleField.setText("Test Title"); // should mimic a user writing a title in the title field area
	    saveButton.addActionListener(new ActionListener() { // should save the text written in the text area and text field
	        @Override
			public void actionPerformed(ActionEvent e) { // and also make the text area and fields empty again to write another entry
	            textArea.setText("");
	            titleField.setText("");
	        }
	    });
	    //Act
	    saveButton.doClick(); // will trigger the actionlistener to perform its actions whrn you actually click the save button
	    //Assert
	    assertEquals("", textArea.getText()); // basically text area should have empty strings after hitting the save button same for text field
	    assertEquals("", titleField.getText());
	}

    
    
    
    
    //Jada's
  //check to see if icon exists in the correct path
  	public boolean isImagePathValid(String imagepath) {
  		File file = new File(imagepath);
  		// Check if it exists and is not a directory
  		return file.exists()&& !file.isDirectory();
  	}
  //Test that the path is valid
  	@Test
  	public void testValidImagePath() {
  		String validPath = "images/logo_dark.png";
  		assertTrue(isImagePathValid(validPath));
  	}

  	//Test that the path is not invalid
  	@Test
  	public void testInvalidImagePath() {
  		String invalidPath = "images";
  		assertFalse(isImagePathValid(invalidPath));
  	}
  	

  	
}
