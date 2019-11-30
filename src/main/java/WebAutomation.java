import org.openqa.selenium.*;
import org.openqa.selenium.Point;
import org.openqa.selenium.firefox.FirefoxDriver;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import com.github.romankh3.image.comparison.model.Rectangle;
public class WebAutomation {
	
	public static void main(String [] args) {
	
		System.setProperty("webdriver.gecko.driver", "/Applications/geckodriver");
		WebDriver firefoxDriver = new FirefoxDriver();
		firefoxDriver.get("https://openlayers.org/en/latest/examples/popup.html");
		
		WebElement openLayersMap = firefoxDriver.findElement(By.id("map"));
		WebElement someButton = firefoxDriver.findElement(By.id("navbar-logo-container"));
		WebElement zoomInButton = firefoxDriver.findElement(By.className("ol-zoom-in"));
		
		/*
		 Important things:
		 - window size
		 - window position?
		 - map size
		 - map position
		 */
		
		openLayersMap.getSize();
		openLayersMap.getRect();
		
		firefoxDriver.manage().window().setPosition(new Point(0, 22));
		Point point = firefoxDriver.manage().window().getPosition();
		System.out.println("current window location (x, y): " + point.x + ", " + point.y);
		
		try {
			MouseActions.moveMouseToMiddleOfElement(firefoxDriver, someButton);
			Thread.sleep(1000);
			MouseActions.moveMouseToMiddleOfElement(firefoxDriver, openLayersMap);
			Thread.sleep(1000);
			MouseActions.moveMouseToMiddleOfElement(firefoxDriver, zoomInButton);
		} catch (Exception e) {
			System.out.println("there has been an exception during robot mouse movement");
			e.printStackTrace();
		}
		
		MouseActions.leftClick();
		MouseActions.leftClick();
		
		BufferedImage resultImage = ImageCompare.compareImages(firefoxDriver, "/Users/Stuart/IdeaProjects/MyTestsArtifactId/src/main/resources/expected_images/Screenshot_expected_test2.png");
		File resultsFile = new File("/Users/Stuart/IdeaProjects/MyTestsArtifactId/src/main/resources/result_image/results_test.png");
		
		try {
			ImageIO.write(resultImage, "png", resultsFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ImageCompare.addExclusionArea(Toolbox.getElementPositionRelativeToScreen(firefoxDriver, zoomInButton));
		// ImageCompare.addExclusionArea(new Rectangle(0,0,1680,100)); // test values. TODO The goal would be to pass just a WebElement to exclude
		// ImageCompare.addExclusionArea(new Rectangle (0,500,1680,1050));
		
		ImageCompare.compareImages(firefoxDriver, "/Users/Stuart/IdeaProjects/MyTestsArtifactId/src/main/resources/expected_images/Screenshot_expected_test2.png");
		
		firefoxDriver.close();
		firefoxDriver.quit();
	}
}
