package CozyJournalPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class GUI {
    private JFrame frame;

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

	//method to create Journal Page
    private JPanel createJournalPage(String title) {
        JPanel page = createPage(title);
        JPanel centerPanel = new JPanel();
        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();

		//SetLayout: 1 row, 2 columns
        centerPanel.setLayout(new GridLayout(1, 2));
        centerPanel.add(leftPanel);
        centerPanel.add(rightPanel);

		//Add to page
        page.add(centerPanel, BorderLayout.CENTER);

		//Set background Color
        leftPanel.setBackground(new Color(252, 252, 202));
        rightPanel.setBackground(new Color(252, 252, 202));


        leftPanel.setBorder(BorderFactory.createLineBorder(new Color(143, 96, 70)));
        rightPanel.setBorder(BorderFactory.createLineBorder(new Color(143, 96, 70)));

        // Adjust the size of the left and right panels
        leftPanel.setPreferredSize(new Dimension(200, 400));
        rightPanel.setPreferredSize(new Dimension(400, 400));

        return page;
        
        
    }

    public static void main(String[] args) {
        new GUI();
    }
}
