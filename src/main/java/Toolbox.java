import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

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
}
