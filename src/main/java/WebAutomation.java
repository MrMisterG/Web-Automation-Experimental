import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;


public class WebAutomation {
	
	public static void main(String [] args) {
	
		System.setProperty("webdriver.gecko.driver", "/Applications/geckodriver");
		WebDriver firefoxDriver = new FirefoxDriver();
		firefoxDriver.get("https://openlayers.org/en/latest/examples/popup.html");
		
		WebElement openLayersMap = firefoxDriver.findElement(By.id("map"));
		WebElement OLLogoButton = firefoxDriver.findElement(By.id("navbar-logo-container"));
		WebElement zoomInButton = firefoxDriver.findElement(By.className("ol-zoom-in"));
		WebElement zoomOutButton = firefoxDriver.findElement(By.className("ol-zoom-out"));
		WebElement navBar = firefoxDriver.findElement(By.className("navbar"));
		List<WebElement> paragraphs = firefoxDriver.findElements(By.tagName("p"));
		
		
		Toolbox.changeWindowLocation(firefoxDriver, 20, 80);
		
		
		try {
			MouseActions.moveMouseToMiddleOfElement(firefoxDriver, OLLogoButton);
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
		zoomOutButton.click();
		
		ImageCompare.addExclusionArea(Toolbox.getElementRectangle(navBar));
		ImageCompare.addExclusionArea(Toolbox.getElementRectangle(zoomInButton));
		ImageCompare.addExclusionArea(Toolbox.getElementRectangle(zoomOutButton));
		
		for (WebElement paragraph : paragraphs) {
			ImageCompare.addExclusionArea(Toolbox.getElementRectangle(paragraph));
		}
		
		BufferedImage resultImage = ImageCompare.compareImages(firefoxDriver, "/Users/Stuart/IdeaProjects/Web-Automation-Experimental/src/main/resources/expected_images/Screenshot_expected_test2.png");
		File resultsFile = new File("/Users/Stuart/IdeaProjects/Web-Automation-Experimental/src/main/resources/result_image/results_test.png");
		
		try {
			ImageIO.write(resultImage, "png", resultsFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		firefoxDriver.close();
		firefoxDriver.quit();
	}
}
