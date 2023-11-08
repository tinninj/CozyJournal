// packages
package CozyJournal;

// imports
import java.awt.BorderLayout;
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

public class GUI {
    private JFrame frame;
    private JLabel latestJournalEntryLabel;
    
    
    ///This is a global variable so that it can be called by multiple methods
    //Variable for path for images that are added into the journal page
    private String uploadedImagePath = null;
    
    // colors --AP
    private Color bkgYellow = new Color(252, 252, 202);

    public GUI() {
		// Creates frame and sets dimensions/attributes
        frame = new JFrame();
        frame.setTitle("Cozy Journal");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(780, 780);
        frame.setVisible(true);

		// Adds navigation to the bottom and adds to frame
        JTabbedPane navigationPane = new JTabbedPane(JTabbedPane.BOTTOM);
		frame.add(navigationPane);


		// Creates pages of the app
        JPanel mainPage = createPage("Home");
        JPanel journalPage = createJournalPage("Journal");
        JPanel calendarPage = createPage("Calendar");
        JPanel analyticsPage = createPage("Analytics");

        //Add pages to navigation 
		navigationPane.addTab("Home", mainPage);
        navigationPane.addTab("Journal", journalPage);
        navigationPane.addTab("Calendar", calendarPage);
        navigationPane.addTab("Analytics", analyticsPage);
        
        //add logo to GUI
        Image icon = Toolkit.getDefaultToolkit().getImage("images/logo_dark.png");
        frame.setIconImage(icon);
    }
    
    private JPanel createPage(String title) {
        JPanel page = new JPanel();
        JPanel titlePanel = new JPanel();
        JPanel centerPanel = new JPanel();
        JPanel entryPanel = new JPanel();
        
        // creates the layout for the page
        page.setLayout(new BorderLayout());
        // sets location of the panels
        page.add(titlePanel, BorderLayout.NORTH);
        page.add(centerPanel, BorderLayout.CENTER);
        page.add(entryPanel, BorderLayout.CENTER);
        
        // sets attributes of the title panel
        titlePanel.setPreferredSize(new Dimension(780, 50));
		titlePanel.setBackground(bkgYellow);
		titlePanel.setBorder(BorderFactory.createLineBorder(new Color(143, 96, 70)));
		
		// sets attributes of the center panel
        centerPanel.setBackground(bkgYellow);
        
        // sets attributes of the entry panel
        entryPanel.setBackground(bkgYellow);
        
        // creates the title label and its attributes
        JLabel titleLabel = new JLabel(title);
        titleLabel.setForeground(new Color(143, 96, 70));
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titlePanel.add(titleLabel);
        
        // display the most recent journal entry on the home page (needs styling -- AP)
        if(title.equals("Home")) {
        	latestJournalEntryLabel = new JLabel("Previous Entry: ");
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

	// journal page attributes
    private JPanel createJournalPage(String title) {
        JPanel page = createPage(title);
        JPanel centerPanel = new JPanel();
        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        JPanel entryPanel = new JPanel();
        JPanel titlePanel = new JPanel(); // also includes mood --AP
        JPanel journalTextPanel = new JPanel(); // main text box --AP
        JPanel uploadPanel = new JPanel();

        // SetLayout: 1 row, 2 columns
        centerPanel.setLayout(new GridLayout(1, 2));
        centerPanel.add(leftPanel);
        centerPanel.add(rightPanel);
        
        // grid layout for right panel (3 rows, 1 column) -- added by AP
        rightPanel.setLayout(new GridLayout(2, 1));
        rightPanel.add(entryPanel);
        rightPanel.add(uploadPanel);
        
        // add title panel to the top of the entry panel --AP
        entryPanel.add(titlePanel, BorderLayout.NORTH);
        entryPanel.add(journalTextPanel, BorderLayout.CENTER);

        // Add to page
        page.add(centerPanel, BorderLayout.CENTER);

        // Set background Color
        leftPanel.setBackground(bkgYellow);
        rightPanel.setBackground(bkgYellow);
        titlePanel.setBackground(bkgYellow);
        journalTextPanel.setBackground(bkgYellow);
        entryPanel.setBackground(bkgYellow);

        leftPanel.setBorder(BorderFactory.createLineBorder(new Color(143, 96, 70)));
        rightPanel.setBorder(BorderFactory.createLineBorder(new Color(143, 96, 70)));

        // Adjust the size of the left and right panels
        leftPanel.setPreferredSize(new Dimension(400, 400));  // Increase the width
        rightPanel.setPreferredSize(new Dimension(400, 400));
        titlePanel.setPreferredSize(new Dimension(350, 155));
        entryPanel.setPreferredSize(new Dimension(300, 400));
        journalTextPanel.setPreferredSize(new Dimension(350, 400));
        titlePanel.setLayout(new GridLayout(0, 1)); // 1 column, multiple rows (allows labels to start on new lines --AP)

        // Create a JTextArea to display the journal entries
        JTextArea journalTextArea = new JTextArea(10, 30);
        journalTextArea.setEditable(false); // Make it non-editable

        // Load and display past journal entries
        loadJournalEntries(journalTextArea);
        
        JScrollPane textScrollPane = new JScrollPane(journalTextArea);
        textScrollPane.setPreferredSize(new Dimension(300, 300)); // Adjust the size as needed

        leftPanel.add(textScrollPane); // Add the text area to the left panel

        // Add textbox and title to Journal on the right panel
        JTextArea textArea = new JTextArea(7, 35); // 7 rows, 40 columns
        textArea.setLineWrap(true); // text wrapping added by AP
        JTextField titleField = new JTextField(20);

        // Add save Button
        JButton saveButton = new JButton("Save");

        // action listener for the save button
        saveButton.addActionListener(e -> {
            String text = textArea.getText();
            String titleText = titleField.getText();
            String date = java.time.LocalDate.now().toString();

            try {
                // Open the existing Excel workbook
                FileInputStream fis = new FileInputStream("journal_entries.xlsx");
                XSSFWorkbook workbook = new XSSFWorkbook(fis);
                XSSFSheet sheet = workbook.getSheetAt(0);

                // Find the last row with data or start from the first row if the sheet is empty
                int lastRowNum = sheet.getLastRowNum();
                Row row = sheet.createRow(lastRowNum + 1);

                // Create cells for date, title, and text
                Cell dateCell = row.createCell(0);
                dateCell.setCellValue(date);

                Cell titleCell = row.createCell(1);
                titleCell.setCellValue(titleText);

                Cell textCell = row.createCell(2);
                textCell.setCellValue(text);
                
                /////JT: This should added the image path to the excel spreadsheet. 
                //It needs to be pulled from the spreadsheet to display the correct image.
                ////
                ////
                //add file path to the spreadsheet
                Cell imagePathCell = row.createCell(3);
                imagePathCell.setCellValue(uploadedImagePath);

                // Write the workbook back to the file
                FileOutputStream fos = new FileOutputStream("journal_entries.xlsx");
                workbook.write(fos);
                fos.close();
                fis.close();

                // Clear the text area and title field after saving
                textArea.setText("");
                titleField.setText("");
                uploadedImagePath = null;

                // Append only the new entry to the JTextArea
                journalTextArea.append("Date: " + date + ", Title: " + titleText + ", Text: " + text + "\n");

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        
        // mood selection list
        String [] moods = {"Happy", "Sad", "Angry", "Stressed", "Nervous", "Relax"};
        // mood selection drop down
        JComboBox<String> comboBox = new JComboBox<>(moods);
        // Set the position and size of the JComboBox
        comboBox.setPreferredSize(new Dimension(300, 20)); // edited by AP

        // Add text box, title, and save button to right panel
        titlePanel.add(new JLabel("Title: "));
        titlePanel.add(titleField);
        titlePanel.add(new JLabel("Mood: ")); // added by AP
        titlePanel.add(comboBox); // added by AP 
        titlePanel.add(new JLabel("<html><br>Entry: <html>"));
        journalTextPanel.add(textArea);
        journalTextPanel.add(saveButton);

        // image upload panel
        uploadPanel.setBackground(new Color(139, 69, 19));
        uploadPanel.setPreferredSize(new Dimension(150, 150)); // Adjust the size
        // upload image button (opens files) -- AP
        JButton uploadButton = new JButton("Upload Image");
        uploadPanel.add(uploadButton);
        
        
        // file chooser -- AP
        	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        	// NOTE: currently choosing a second image does not replace the first and the images are displayed too small				 //
        	// also the picture isn't saved to the database 																			 // 
        	// saving it to the database could involve creating a duplicate image in a specified folder (within the project) and saving  // 
        	// the duplicate's path in a cell in the spreadsheet																		 //
        	// changing the image would overwrite the previously choosen image 															 //
    		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        final JFileChooser fc = new JFileChooser();
        // action listener for upload image button
        uploadButton.addActionListener(e -> {
        	// handle button action
        	if(e.getSource() == uploadButton) {
        		int retVal = fc.showOpenDialog(null);
        		if(retVal == JFileChooser.APPROVE_OPTION) {
        			File file = fc.getSelectedFile();
        			//String imgPath = file.getAbsolutePath();
        			try {
        				ImagePanel imagePanel = new ImagePanel();
        				BufferedImage image = ImageIO.read(file);
        				imagePanel.setImage(image);
        				uploadPanel.add(imagePanel);
        				uploadPanel.revalidate();
        				uploadPanel.repaint();
        			}
        			catch(IOException ex) {
        				ex.printStackTrace();
        			}
        		}
        }});

        return page;
    }

    private void loadJournalEntries(JTextArea journalTextArea) {
        try {
            FileInputStream fis = new FileInputStream("journal_entries.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheetAt(0);

            // Iterate through rows in the Excel sheet and add them to the JTextArea
            for (Row row : sheet) {
                String date = row.getCell(0).getStringCellValue();
                String title = row.getCell(1).getStringCellValue();
                String text = row.getCell(2).getStringCellValue();

                // Append the data to the JTextArea
                journalTextArea.append("Date: " + date + ", Title: " + title + ", Text: " + text + "\n");

                //CAN BE REMOVED.TESTING IF INFORMATION IS ADDED TO JTextArea
                System.out.println("Date: " + date + ", Title: " + title + ", Text: " + text);
            }

            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   
    // This method is supposed to save the image into a folder so it can be seen on multiple machines
    //It is not finished so the next programmer needs to finish the method so that the uploaded image is added to the
    // "images" folder and that the file path is also saved in the excel spreadsheet
  /*  private void saveImageToFolder(File uploadedImage) {
        String imageFileName = "image_" + System.currentTimeMillis(); // Generate a unique file name
        String destinationDirectory = "images";

        

    }*/



    
    // image panel class
    public class ImagePanel extends JPanel {
    	BufferedImage image = null;
    	public ImagePanel() {
    	}
    	@Override
	    public void paintComponent(Graphics g) {
	    	super.paintComponent(g);
	    	g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
	    }
    	public void setImage(BufferedImage image) {
    		this.image = image;
    		repaint();
    	}
    }

	/*private JPanel createCalendarPage(String title){
		JPanel page = createPage(title);
		JPanel calendarPanel = new JPanel();

		//create JCalendar

		JCalendar calendar = new JCalendar();

		//Add calendar to calendarPanel
		calendarPanel.add(calendar);

		//Add caledarPanel to calendarPage
		page.add(calendarPanel, BorderLayout.CENTER);

		return page;
	}*/

    public static void main(String[] args) {
        new GUI();
    }
}