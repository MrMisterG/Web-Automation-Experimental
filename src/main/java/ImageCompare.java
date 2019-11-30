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

class ImageCompare {
	
	private static List<Rectangle> excludedAreas = new ArrayList<Rectangle>();
	
	static void addExclusionArea(Rectangle rectangle) {
		excludedAreas.add(rectangle);
	}
	
	static BufferedImage compareImages(WebDriver driver, String pathExpectedImage) {
		
		BufferedImage screenCaptureImage;
		BufferedImage expectedImage;
		BufferedImage resultImage;
		
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
		imageComparison.setRectangleLineWidth(3);
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
