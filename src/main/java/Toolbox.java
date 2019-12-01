import com.github.romankh3.image.comparison.model.Rectangle;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.awt.*;


class Toolbox {
	
	static void changeWindowLocation(WebDriver firefoxDriver, int newPointX, int newPointY) {
		firefoxDriver.manage().window().setPosition(new Point(newPointX, newPointY));
	}
	
	static void changeWindowDimensions (WebDriver webDriver, int dimensionWidth, int dimensionHeight) {
		webDriver.manage().window().setSize(new Dimension(dimensionWidth, dimensionHeight));
	}
	
	static void printPointerLocation(String whichMethod) {
		int currentPointerLocationX = MouseInfo.getPointerInfo().getLocation().x;
		int currentPointerLocationY = MouseInfo.getPointerInfo().getLocation().y;
		
		System.out.println(whichMethod + " prints current pointer location (x, y): " + currentPointerLocationX + ", " + currentPointerLocationY);
	}
	
	/**
	 * This method takes a Selenium WebElement and returns the Rectangle, representing its size and location on a Selenium screenshot
	 * (MacOS and FireFox UI components are excluded from this screenshot)
	 *
	 * @param webElement The Selenium WebElement that will be the stencil used for the rectangle.
	 * @return returns a Rectangle (minX, minY, maxX, maxY) drawn around the given WebElement.
	 * minX, minY is the pixel location of the top left corner;
	 * maxX, maxY is the location of the bottom right corner.
	 */
	static Rectangle getElementRectangle(WebElement webElement) {
		
		Point elementLocation = webElement.getLocation();
		int elementWidth = webElement.getSize().width;
		int elementHeight = webElement.getSize().height;
		
		int elementMinX = (int) elementLocation.x;
		int elementMinY = (int) elementLocation.y;
		int elementMaxX = elementMinX + elementWidth;
		int elementMaxY = elementMinY + elementHeight;
		
		System.out.println(elementMinX + " and " + elementMinY);
		
		return new Rectangle(elementMinX,elementMinY,elementMaxX,elementMaxY);
	}
}
