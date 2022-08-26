import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class testDate {
    public static void main(String[] args) {

    }

    public static void dateForm() {
        ChromeDriver driver = BrowserManager.getChromeDriver();
        driver.get("https://testpages.herokuapp.com/styled/html5-form-test.html");

        WebElement date = driver.findElement(By.xpath("//input[@id='date-picker']"));


        driver.quit();
    }
}