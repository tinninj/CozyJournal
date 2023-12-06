// package //
package CozyJournal;

// import JUnit //
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

// import GUI elements //
import javax.swing.*;

public class JUnitTestNavigationBar {
	JTabbedPane navigationPane = new JTabbedPane(JTabbedPane.BOTTOM);
	
	private JPanel createPage(String label) {
		JPanel page = new JPanel();
		return page;
	}
	
	@Before 
	public void setUp() throws Exception {
	}
	
	@Test
	public void testTabLabels() {
	// ARRANGE //
		JPanel mainPage = createPage("Home");
		JPanel journalPage = createPage("Journal");
    // ACT //
        // add pages to navigation //
        navigationPane.addTab("Home", mainPage);
        navigationPane.addTab("Journal", journalPage);
    // ASSERT //
        // test that the pages were added at the correct indexes
        assertEquals("Home", navigationPane.getTitleAt(0));
        assertEquals("Journal", navigationPane.getTitleAt(1));
        
	}
	
}
