import com.github.romankh3.image.comparison.model.Rectangle;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.awt.*;


class Toolbox {
	
	static void changeWindowDimensions (WebDriver webDriver, int dimensionWidth, int dimensionHeight) {
		webDriver.manage().window().setSize(new Dimension(dimensionWidth, dimensionHeight));
	}
	
	static void printPointerLocation(String whichMethod) {
		int currentPointerLocationX = MouseInfo.getPointerInfo().getLocation().x;
		int currentPointerLocationY = MouseInfo.getPointerInfo().getLocation().y;
		
		System.out.println(whichMethod + " prints current pointer location (x, y): " + currentPointerLocationX + ", " + currentPointerLocationY);
	}
	
	static Rectangle getElementPositionRelativeToScreen (WebDriver webDriverWindow, WebElement webElement) {
		
		Point windowLocation = webDriverWindow.manage().window().getPosition();
		int windowLocationX = windowLocation.x;
		int windowLocationY = windowLocation.y; // 22px is the height of the apple menu bar
		
		Point elementLocation = webElement.getLocation();
		int elementWidth = webElement.getSize().width;
		int elementHeight = webElement.getSize().height;
		
		int elementMinX = (int) elementLocation.x + windowLocationX;
		int elementMinY = (int) elementLocation.y + 75 + windowLocationY; // 75px is the height of the FireFox header title bar
		int elementMaxX = elementMinX + elementWidth;
		int elementMaxY = elementMinY + elementHeight;
		
		System.out.println(elementMinX + " and " + elementMinY);
		
		return new Rectangle(elementMinX,elementMinY,elementMaxX,elementMaxY);
	}
}
