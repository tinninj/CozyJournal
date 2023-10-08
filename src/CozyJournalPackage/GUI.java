package CozyJournalPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream; 
import java.io.IOException;
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
    private DefaultTableModel tableModel;

    public GUI() {
		//Creates frame and sets dimensions/attributes
        frame = new JFrame();
        frame.setTitle("Cozy Journal");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(780, 780);
        frame.setVisible(true);

		//Adds navigation to the bottom and adds to frame
        JTabbedPane navigationPane = new JTabbedPane(JTabbedPane.BOTTOM);
		frame.add(navigationPane);


		//Creates pages of the app
        JPanel mainPage = createPage("Home");
        JPanel journalPage = createJournalPage("Journal");
        JPanel calendarPage = createPage("Calendar");
        JPanel analyticsPage = createPage("Analytics");

        //Add pages to navigation 
		navigationPane.addTab("Home", mainPage);
        navigationPane.addTab("Journal", journalPage);
        navigationPane.addTab("Calendar", calendarPage);
        navigationPane.addTab("Analytics", analyticsPage);

        
    }

    private JPanel createPage(String title) {
        JPanel page = new JPanel();
        JPanel titlePanel = new JPanel();
        JPanel centerPanel = new JPanel();

        page.setLayout(new BorderLayout());
        page.add(titlePanel, BorderLayout.NORTH);
        page.add(centerPanel, BorderLayout.CENTER);

        titlePanel.setPreferredSize(new Dimension(780, 50));
		titlePanel.setBackground(new Color(252, 252, 202));
		titlePanel.setBorder(BorderFactory.createLineBorder(new Color(143, 96, 70)));

        centerPanel.setBackground(new Color(252, 252, 202));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setForeground(new Color(143, 96, 70));
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titlePanel.add(titleLabel);

        return page;
    }

	
    private JPanel createJournalPage(String title) {
        JPanel page = createPage(title);
        JPanel centerPanel = new JPanel();
        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();

        // SetLayout: 1 row, 2 columns
        centerPanel.setLayout(new GridLayout(1, 2));
        centerPanel.add(leftPanel);
        centerPanel.add(rightPanel);

        // Add to page
        page.add(centerPanel, BorderLayout.CENTER);

        // Set background Color
        leftPanel.setBackground(new Color(252, 252, 202));
        rightPanel.setBackground(new Color(252, 252, 202));

        leftPanel.setBorder(BorderFactory.createLineBorder(new Color(143, 96, 70)));
        rightPanel.setBorder(BorderFactory.createLineBorder(new Color(143, 96, 70)));

        // Adjust the size of the left and right panels
        leftPanel.setPreferredSize(new Dimension(400, 400));  // Increase the width
        rightPanel.setPreferredSize(new Dimension(400, 400));

        // Create a JTextArea to display the journal entries
        JTextArea journalTextArea = new JTextArea(10, 30);
        journalTextArea.setEditable(false); // Make it non-editable

        // Load and display past journal entries
        loadJournalEntries(journalTextArea);
        
        JScrollPane textScrollPane = new JScrollPane(journalTextArea);
        textScrollPane.setPreferredSize(new Dimension(300, 300)); // Adjust the size as needed

        leftPanel.add(textScrollPane); // Add the text area to the left panel

        // Add textbox and title to Journal on the right panel
        JTextArea textArea = new JTextArea(10, 30); // 10 rows, 30 columns
        JTextField titleField = new JTextField(20);

        // Add save Button
        JButton saveButton = new JButton("Save");

        // action listener for the save button
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

                // Write the workbook back to the file
                FileOutputStream fos = new FileOutputStream("journal_entries.xlsx");
                workbook.write(fos);
                fos.close();
                fis.close();

                // Clear the text area and title field after saving
                textArea.setText("");
                titleField.setText("");

                // Append only the new entry to the JTextArea
                journalTextArea.append("Date: " + date + ", Title: " + titleText + ", Text: " + text + "\n");

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        // Add text box, title, and save button to right panel
        rightPanel.add(new JLabel("Title: "));
        rightPanel.add(titleField);
        rightPanel.add(new JLabel("Entry: "));
        rightPanel.add(textArea);
        rightPanel.add(saveButton);

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
