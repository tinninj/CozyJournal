/*package CozyJournal;

import static org.junit.Test;
import static org.junit.Before;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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

public class JUnitTest3 {

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
	
	

	
	private GUI newGui;
	
	@Before
	public void createGui() {
		newGui = new GUI();
		
	}

	 JPanel createPage(String title) {
	        JPanel page = new JPanel();
	        JPanel entryPanel = new JPanel();
	        
	        // creates the layout for the page
	        page.setLayout(new BorderLayout());
	        // sets location of the panels
	        page.add(entryPanel, BorderLayout.CENTER);
	        
	       
	  
	        JLabel latestJournalEntryLabel = new JLabel("Previous Entry: ");
	        	// display previous entry on the home page
	        	try {
	        		// open database 
	        		FileInputStream fis = new FileInputStream("journal_entries.xlsx");
	                XSSFWorkbook workbook = new XSSFWorkbook(fis);
	                XSSFSheet sheet = workbook.getSheetAt(0);
	                
	                // access last row with data
	                Row row = sheet.getRow(sheet.getLastRowNum());
	                String entryDate = row.getCell(0).getStringCellValue();
	                String entryTitle = row.getCell(1).getStringCellValue();
	                String entryContent = row.getCell(2).getStringCellValue();
	                JLabel latestJournalEntryContent = new JLabel("Date: " + entryDate + " Title: " + entryTitle + " Entry: " + entryContent);
	                
	                // add entry as a JLabel on homepage
	                entryPanel.add(latestJournalEntryContent);
	        	}
	        	catch (IOException ex) {
	                ex.printStackTrace();
	            }
	        	
	        	// add latest journal entry to the center panel
	        	entryPanel.add(latestJournalEntryLabel);
	        }

	        return page;
	    }
	
	
	@Test
	//Testing the last journal entry on main page
	public void testLatestJournalEntry() {
;
		 
		 
	

		 
		 
	}
	

}*/
