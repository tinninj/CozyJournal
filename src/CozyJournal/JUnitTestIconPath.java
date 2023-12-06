// package //
package CozyJournal;

//import JUnit //
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

//import file io //
import java.io.*;

public class JUnitTestIconPath {
	// check that the icon exists
	@Before
	public boolean isImagePathValid(String imagePath) {
		File file = new File(imagePath);
		return file.exists() && !file.isDirectory();
	}
	
	@Test
	public void testValidImagePath() {
	// ARRANGE //
		String validPath = "images/logo_dark.png";
	// ACT //
		Boolean valid = isImagePathValid(validPath);
	// ASSERT //
		assertEquals(true, valid);
	}
	
}
