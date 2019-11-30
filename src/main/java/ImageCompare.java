import com.github.romankh3.image.comparison.ImageComparison;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageCompare {
	
	
	static BufferedImage compareImages(WebDriver driver, String pathExpectedImage) {
		
		BufferedImage screenshotImage;
		BufferedImage expectedImage;
		BufferedImage resultImage;
		
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		
		try {
			screenshotImage = ImageIO.read(screenshotFile);
		} catch (IOException e) {
			screenshotImage = null;
			e.printStackTrace();
		}
		
		try {
			expectedImage = null;
		} catch (Exception e) {
			expectedImage = null;
			e.printStackTrace();
			
		}
		
		try {
			resultImage = null;
		} catch (Exception e) {
			resultImage = null;
			e.printStackTrace();
		}
		
		ImageComparison imageComparison = new ImageComparison(expectedImage, screenshotImage);
		
		return resultImage;
	}
}
