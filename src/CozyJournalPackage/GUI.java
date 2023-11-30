// packages
package CozyJournalPackage;

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
import java.net.URL;
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
    
    // Global variables
    // Variable for storing the path of images uploaded to the journal page
    private String uploadedImagePath  = null;
    
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
        
        // sets icon
        Image icon = Toolkit.getDefaultToolkit().getImage("source_images/logo_dark.png");
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
        JPanel uploadPanel = new JPanel(); // contains the uploadButton and image panels
        JPanel uploadButtonPanel = new JPanel();
        JPanel uploadedImagePanel = new JPanel();
        //JPanel uploadButtonPanel = new JPanel(); // contains only the upload image button --AP
        //JPanel imagePanel = new JPanel(); // contains the uploaded image
        
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
        
        // mood selection list
        String [] moods = {"Happy", "Sad", "Angry", "Stressed", "Nervous", "Relax"};
        // mood selection drop down
        JComboBox<String> comboBox = new JComboBox<>(moods);
        // Set the position and size of the JComboBox
        comboBox.setPreferredSize(new Dimension(300, 20)); // edited by AP

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
                
                ////////////////////////////////////// SAVE IMAGE - AP //////////////////////////////////////
                // add image path to the spreadsheet (make sure a copy of the image is saved into a folder)
                /////////// note: uploadedImagePath is the original path to the original image. We need to create a dupliicate of the original 
                		// image, save it to source_images, and save this new path to the cell in the spreadsheet // 
                if(uploadedImagePath != null) {
	                Cell imagePathCell = row.createCell(3);
	                // create duplicate of selected image and save it to source_images folder
	                File originalPath = new File(uploadedImagePath);
	                BufferedImage dupeImage = ImageIO.read(originalPath);
	                
	                // datetime value used as file name for duplicate image (allows for unique file names
	                String dupeImagePath = "images/" + System.currentTimeMillis();
	                
	                // save duplicate image in source_image folder
	                ImageIO.write(dupeImage, "png", new File(dupeImagePath));
	                
	                imagePathCell.setCellValue(dupeImagePath);
                }
                ////////////////////////////////////////////////////////////////////////////////////////////
                // save mood to the database
                String mood = comboBox.getEditor().getItem().toString();
                // create cell for storing the mood
                Cell moodCell = row.createCell(4);
                // add text from combo box to cell
                moodCell.setCellValue(mood);
                

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

        // Add text box, title, and save button to right panel
        titlePanel.add(new JLabel("Title: "));
        titlePanel.add(titleField);
        titlePanel.add(new JLabel("Mood: ")); // added by AP
        titlePanel.add(comboBox); // added by AP 
        titlePanel.add(new JLabel("<html><br>Entry: <html>"));
        journalTextPanel.add(textArea);
        journalTextPanel.add(saveButton);

        ////////// Grid Bag Layout of upload panel //////////
        // image upload panel
        uploadPanel.setBackground(new Color(139, 69, 19));
        uploadPanel.setPreferredSize(new Dimension(150, 150)); // Adjust the size
        uploadPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        // constraints for button panel
        c.weightx = 0.5;
        c.fill = GridBagConstraints.VERTICAL;
        c.gridx = 0;
        c.gridy = 0;
        uploadButtonPanel.setBackground(new Color(139, 69, 19));
        uploadPanel.add(uploadButtonPanel, c);
        
        // add button to panel 
        JButton uploadButton = new JButton("Upload Image");
        uploadButtonPanel.add(uploadButton);
        
        // constraints for uploaded image panel 
        c.ipady = 150;
        c.weightx = 0.0;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        uploadedImagePanel.setBackground(new Color(139, 69, 19));
        uploadPanel.add(uploadedImagePanel, c);
        
        
        
        
        // upload image button (opens files) -- AP
        //JButton uploadButton = new JButton("Upload Image");
        //uploadPanel.add(uploadButton);
        
        // file chooser -- AP
        	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        	// NOTE: currently choosing a second image does not replace the first and the images are displayed too small				 //
        	// also the picture isn't saved to the database 																			 // 
        	// saving it to the database could involve creating a duplicate image in a specified folder (within the project) and saving  // 
        	// the duplicate's path in a cell in the spreadsheet																		 //
        	// changing the image would overwrite the previously choosen image 															 //
    		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        // JLabel to display the uploaded image
        JLabel imageLabel = new JLabel();
        uploadedImagePanel.add(imageLabel);
       
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
        				// imagePanel = new ImagePanel();
        				BufferedImage originalImage = ImageIO.read(file);
        				
        				// resize image
        				int desiredWidth = 150; // adjustable
        				int desiredHeight = 150; // adjustable 
        				
        				Image resizedImage = originalImage.getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH);
        				
        				// set the resized image to the existing image label
        				imageLabel.setIcon(new ImageIcon(resizedImage));
        				
        				// add padding above the image using the EmptyBorder method
        				//imageLabel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));;
        				
        				// update display
        				uploadedImagePanel.revalidate();
        				uploadedImagePanel.repaint();
        				
        				// store image path in uploadedImagePath global variable
        				uploadedImagePath = file.getPath();
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
