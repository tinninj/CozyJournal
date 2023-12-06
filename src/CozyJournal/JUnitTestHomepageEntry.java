// package //
package CozyJournal;

// import JUnit //
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

// import GUI elements //
import javax.swing.*;
import java.awt.*;

// import file io //
import java.io.*;

// import database (sheets) // 
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.apache.poi.ss.usermodel.Row;

public class JUnitTestHomepageEntry {
	@Before 
	JPanel createPage(String title) {
		// page elements //
		JPanel page = new JPanel();
		JPanel entryPanel = new JPanel();
		
		// page layout //
		page.setLayout(new BorderLayout());
		page.add(entryPanel, BorderLayout.CENTER);
		
		JLabel latestEntryLabel = new JLabel("Previous Entry: ");
		try {
			FileInputStream fis = new FileInputStream("journal_entries.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheetAt(0);
			
			Row row = sheet.getRow(sheet.getLastRowNum());
			String entryDate = row.getCell(0).getStringCellValue();
			String entryTitle = row.getCell(1).getStringCellValue();
			String entryContent = row.getCell(2).getStringCellValue();
			
			JLabel latestEntryContent = new JLabel("Date: " + entryDate + " Title: " + entryTitle + " Entry: " + entryContent);
			
			entryPanel.add(latestEntryContent);
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
		
		entryPanel.add(latestEntryLabel);
		
		return page;
	}
	@Test
	public void testHomepageEntry() {
	// ARRANGE //
		// initialize string to hold entry content from the spreadsheet //
		String latestEntryContent = null;
	// ACT //
		// create homepage panel //
		JPanel homepage = createPage("Home");
		
		// check if entry panel has the correct entry //
		if(homepage.getComponentCount() > 0) {
			// get  entry panel //
			JPanel entryPanel = (JPanel) homepage.getComponent(0);
			// check if component exists and is a JLabel //
			if(entryPanel.getComponentCount() > 0 && entryPanel.getComponent(0) instanceof JLabel) {
				JLabel entryContentLabel = (JLabel) entryPanel.getComponent(0);
				// get text from the JLabel //
				String entryContentText = entryContentLabel.getText();
				
				// save entry from spreadsheet as a string //
				try {
					FileInputStream fis = new FileInputStream("journal_entries.xlsx");
					XSSFWorkbook workbook = new XSSFWorkbook(fis);
					XSSFSheet sheet = workbook.getSheetAt(0);
					
					Row row = sheet.getRow(sheet.getLastRowNum());
					String entryDate = row.getCell(0).getStringCellValue();
					String entryTitle = row.getCell(1).getStringCellValue();
					String entryContent = row.getCell(2).getStringCellValue();
					
					latestEntryContent = "Date: " + entryDate + " Title: " + entryTitle + " Entry: " + entryContent;
				}
				catch(IOException ex) {
					ex.printStackTrace();
				}
	// ASSERT //
				// check if JLabel text matches the latest entry from the spreadsheet //
				assertEquals(true, (entryContentText == latestEntryContent));
				
			}
		}
	}
}
