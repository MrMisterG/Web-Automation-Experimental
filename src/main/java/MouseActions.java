import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.awt.*;
import java.awt.event.InputEvent;

class MouseActions {
	private static Robot robot;
	
	enum Direction {
		UP,
		DOWN,
		LEFT,
		RIGHT
	}
	
	private static Robot createRobot() {
		try {
			robot = new Robot();
		} catch (Exception e) { System.out.println("Exception whilst creating a robot"); }
		
		return robot;
	}
	
	static void leftClick() {
		Toolbox.printPointerLocation("leftClick");
		
		robot = createRobot();
		
		robot.delay(30);
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.delay(30);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	}
	
	static void rightClick() {
		Toolbox.printPointerLocation("rightClick");
		
		robot = createRobot();
		
		robot.delay(30);
		robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
		robot.delay(30);
		robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
	}
	
	static void mouseMove(Direction direction) {
		Toolbox.printPointerLocation("mouseMove (before)");
		robot = createRobot();
		
		int currentPointerLocationX = MouseInfo.getPointerInfo().getLocation().x;
		int currentPointerLocationY = MouseInfo.getPointerInfo().getLocation().y;
		
		switch(direction) {
			case UP:
				for (int i = 0; i < 50; i++ ) {
					robot.mouseMove(currentPointerLocationX, currentPointerLocationY + 1);
					robot.delay(10);
					currentPointerLocationY++;
				}
				break;
			case DOWN:
				for (int i = 0; i < 50; i++ ) {
					robot.mouseMove(currentPointerLocationX, currentPointerLocationY - 1);
					robot.delay(10);
					currentPointerLocationY--;
				}
				break;
			case LEFT:
				for (int i = 0; i < 50; i++ ) {
					robot.mouseMove(currentPointerLocationX - 1, currentPointerLocationY);
					robot.delay(10);
					currentPointerLocationX--;
				}
				break;
			case RIGHT:
				for (int i = 0; i < 50; i++ ) {
					robot.mouseMove(currentPointerLocationX + 1, currentPointerLocationY);
					robot.delay(10);
					currentPointerLocationX++;
				}
		}
		Toolbox.printPointerLocation("mouseMove (after)");
		
		
	}
	
	static void moveMouseToMiddleOfWindow (WebDriver webDriverWindow) {
		robot = createRobot();
		Toolbox.printPointerLocation("moveMouseToMiddleOfWindow (before)");
		
		Point windowLocation = webDriverWindow.manage().window().getPosition();
		int windowLocationX = windowLocation.x;
		int newPointY = windowLocation.y;
		int windowWidth = webDriverWindow.manage().window().getSize().width;
		int windowHeight = webDriverWindow.manage().window().getSize().height;
		
		robot.mouseMove(windowLocationX + windowWidth/2,newPointY + windowHeight/2); // move cursor to centre of the map
		robot.delay(50);
		Toolbox.printPointerLocation("moveMouseToMiddleOfWindow (after)");
	}
	
	
	
	static void moveMouseToMiddleOfElement (WebDriver webDriverWindow, WebElement webElement) {
		robot = createRobot();
		Toolbox.printPointerLocation("moveMouseToMiddleOfElement (before)");

		Point windowLocation = webDriverWindow.manage().window().getPosition(); // TODO move this duplicated code block out
		int windowLocationX = windowLocation.x;
		int windowLocationY = windowLocation.y; // 22px is the height of the apple menu bar

		Point elementLocation = webElement.getLocation();
		int elementWidth = webElement.getSize().width;
		int elementHeight = webElement.getSize().height;
		int elementMiddleLocationX = (int) elementWidth / 2 + elementLocation.x + windowLocationX;
		int elementMiddleLocationY = (int) elementHeight / 2 + elementLocation.y + 75 + windowLocationY;
		
		System.out.println(elementMiddleLocationX + " and " + elementMiddleLocationY);

		robot.mouseMove(elementMiddleLocationX + 50, elementMiddleLocationY + 50);
		
		for (int i = 49; i > 0; i-- ) {
			robot.mouseMove(elementMiddleLocationX+i, elementMiddleLocationY+i);
			robot.delay(10);
		}

		robot.delay(50);
		Toolbox.printPointerLocation("moveMouseToMiddleOfElement (after)");

	}
}
