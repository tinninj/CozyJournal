package CozyJournal;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JunitClassTest4 {
    private GUI newGui;

    @BeforeEach
    public void createGui() {
        newGui = new GUI();
    }

    @Test
    public void testLatestJournalEntryOnHomepage() {
        JPanel homepage = another_createPage("Journal Entry");

        // Check if the center panel has at least one component (the latest entry label)
        assertTrue(homepage.getComponentCount() > 0);

        // Get the latest entry label from the center panel
        JLabel latestJournalEntryLabel = (JLabel) homepage.getComponent(0);

    }

    private JPanel another_createPage(String title) {
        //wanted to add what the home page code here
        return new JPanel();
    }
}
