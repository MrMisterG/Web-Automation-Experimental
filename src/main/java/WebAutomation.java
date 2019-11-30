import org.openqa.selenium.*;
import org.openqa.selenium.Point;
import org.openqa.selenium.firefox.FirefoxDriver;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;

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
			Thread.sleep(9000);
			MouseActions.moveMouseToMiddleOfElement(firefoxDriver, openLayersMap);
			Thread.sleep(9000);
			MouseActions.moveMouseToMiddleOfElement(firefoxDriver, zoomInButton);
		} catch (Exception e) {
			System.out.println("there has been an exception during robot mouse movement");
			e.printStackTrace();
		}
		
		try {
			BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
			ImageIO.write(image, "png", new File("/Users/Stuart/Pictures/ScreenCap.png"));
		} catch (Exception e) {
			System.out.println("Exception during BufferedImage stuff: " + e);
			e.printStackTrace();
		}
		
		firefoxDriver.close();
		firefoxDriver.quit();
	}
}
