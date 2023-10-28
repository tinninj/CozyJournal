package CozyJournalPackage;
import static org.junit.Assert.*;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import org.junit.Before;
import org.junit.Test;

public class JUnitClassTest2 {
    JTabbedPane navigationPane = new JTabbedPane(JTabbedPane.BOTTOM); 
    

    private JPanel createPage(String label) {
        JPanel page = new JPanel();   // create a new JPanel
        return page;
    }


    @Before
    public void setUp() throws Exception {
    	//Creates pages of the app
        JPanel mainPage = createPage("Home"); // literally copy code from GUI file
        JPanel journalPage = createPage("Journal");
        JPanel calendarPage = createPage("Calendar");
        JPanel analyticsPage = createPage("Analytics");

        // Add pages to navigation
        navigationPane.addTab("Home", mainPage);  //literally copy code from GUI file
        navigationPane.addTab("Journal", journalPage);
        navigationPane.addTab("Calendar", calendarPage);
        navigationPane.addTab("Analytics", analyticsPage);
    }

    @Test
    public void testTabLabels() {
    	  assertEquals("Home", navigationPane.getTitleAt(0)); // Checks that on the navigation Home is in index 0
          assertEquals("Journal", navigationPane.getTitleAt(1));
          assertEquals("Calendar", navigationPane.getTitleAt(2));
          assertEquals("Analytics", navigationPane.getTitleAt(3));
    }
   
}

    