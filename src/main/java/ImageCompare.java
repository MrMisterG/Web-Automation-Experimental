import com.github.romankh3.image.comparison.ImageComparison;
import com.github.romankh3.image.comparison.model.ComparisonResult;
import com.github.romankh3.image.comparison.model.Rectangle;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImageCompare {
	
	
	static BufferedImage compareImages(WebDriver driver, String pathExpectedImage) {
		
		BufferedImage screenCaptureImage;
		BufferedImage expectedImage;
		BufferedImage resultImage;
		
		List<Rectangle> excludedAreas = new ArrayList<Rectangle>();
		excludedAreas.add(new Rectangle(0,0,1680,100)); // test values. TODO The goal would be to pass just a WebElement to exclude
		excludedAreas.add(new Rectangle(0,500,1680,1050));
		
		// get the expected image
		try {
			File file = new File(pathExpectedImage);
			expectedImage = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
			expectedImage = null;
		}
		
		// get the actual image
		try {
			File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			screenCaptureImage = ImageIO.read(screenshotFile);
		} catch (IOException e) {
			screenCaptureImage = null;
			e.printStackTrace();
		}
		
		// compare the images
		ImageComparison imageComparison = new ImageComparison(expectedImage, screenCaptureImage);
		imageComparison.setThreshold(10);
		imageComparison.setRectangleLineWidth(4);
		imageComparison.setDestination(new File("result_image/result.png"));
		imageComparison.setPixelToleranceLevel(0.2);
		imageComparison.setExcludedAreas(excludedAreas);
		imageComparison.setDrawExcludedRectangles(true);
		
		ComparisonResult comparisonResult;
		try {
			comparisonResult = imageComparison.compareImages();
			System.out.println("Image comparison result (MATCH/MISMATCH etc.): " + comparisonResult.getComparisonState());
		} catch (IOException e) {
			comparisonResult = null;
			e.printStackTrace();
		}
		
		try {
			resultImage = comparisonResult.getResult();
		} catch (NullPointerException e) {
			resultImage = null;
			e.printStackTrace();
		}
		
		return resultImage;
	}
}
