import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.Set;

public class endPoint {
    public static void main(String[] args) {
        thirdTests();
    }

    public static void thirdTests() {
        ChromeDriver driver = null;
        try {
            driver = BrowserManager.getChromeDriver();
            driver.get("https://demoqa.com/browser-windows ");
            WebElement newTab = driver.findElement(By.id("tabButton"));
            newTab.click();

            String parentWindow = driver.getWindowHandle();
            Set<String> windowHandles = driver.getWindowHandles();
            for (String window : windowHandles) {
                if (!window.equals(parentWindow)) {
                    driver.switchTo().window(window);
                    break;
                }
            }

            System.out.println("First link's Text: " + driver.findElement(By.id("sampleHeading")).getText());
            driver.close();
            driver.switchTo().window(parentWindow);
            driver.findElement(By.id("windowButton")).click();
            Set<String> windowHandles2 = driver.getWindowHandles();
            for (String window : windowHandles2) {
                if (!window.equals(parentWindow)) {
                    driver.switchTo().window(window);
                    break;
                }
            }
            System.out.println("Second Link's Text: " + driver.findElement(By.id("sampleHeading")).getText());
            driver.close();

            driver.switchTo().window(parentWindow);
            driver.findElement(By.id("messageWindowButton")).click();

            File file = driver.getScreenshotAs(OutputType.FILE);
            File destinationFile = new File("C:\\Users\\v-liviupopa\\IdeaProjects\\image.png");
            FileUtils.copyFile(file, destinationFile);

        } catch (Exception | Error e) {
            System.out.println("Error");
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}