import com.github.romankh3.image.comparison.ImageComparison;
import com.github.romankh3.image.comparison.model.ImageComparisonResult;
import com.github.romankh3.image.comparison.model.ImageComparisonState;
import com.github.romankh3.image.comparison.model.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

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
        ImageComparisonResult comparisonResult = new ImageComparison(expectedImage, screenCaptureImage)
                .setThreshold(10)
                .setRectangleLineWidth(3)
                .setPixelToleranceLevel(0.2)
                .setExcludedAreas(excludedAreas)
                .setDrawExcludedRectangles(true)
                .compareImages();

        System.out.println(
                "Image comparison result (MATCH/MISMATCH etc.): " + comparisonResult.getImageComparisonState());

        return comparisonResult.getImageComparisonState() == ImageComparisonState.MISMATCH ? comparisonResult
                .getResult() : null;
    }
}
