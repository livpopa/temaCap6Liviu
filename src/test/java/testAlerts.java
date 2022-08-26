import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public class testAlerts {
    public static void main(String[] args) {
        promptAlert();
        fakeAlert();
        formTests();
        windowsLinks();

    }


    public static void promptAlert() {
        ChromeDriver driver = null;
        try {
            driver = BrowserManager.getChromeDriver();
            driver.get("https://testpages.herokuapp.com/styled/alerts/alert-test.html");

            WebElement promptButton = driver.findElement(By.id("promptexample"));
            WebElement textPrompt = driver.findElement(By.id("promptreturn"));
            promptButton.click();
            Alert alert = driver.switchTo().alert();
            System.out.println("SMS: " + alert.getText());
            alert.sendKeys("Textul se afiseaza");
            alert.accept();
            WebElement getTextAlert = driver.findElement(By.id("promptexplanation"));
            System.out.println(getTextAlert.getText());

            System.out.println("Alerta este deschis dupa dismiss? " + isAlertOpen(driver));
        } catch (UnhandledAlertException e) {
            System.out.println("Ramura catch");
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }

    public static void fakeAlert() {
        ChromeDriver driver = null;
        try {
            driver = BrowserManager.getChromeDriver();
            driver.get("https://testpages.herokuapp.com/styled/alerts/fake-alert-test.html");

            WebElement alertBoxButton = driver.findElement(By.id("fakealert"));
            alertBoxButton.click();
            WebElement fake = driver.findElement(By.id("dialog-ok"));

            fake.click();

            WebElement modalField = driver.findElement(By.id("modaldialog"));
            modalField.click();
            WebElement okButton = driver.findElement(By.id("dialog-ok"));

            okButton.click();

            System.out.println("Alerta este deschis dupa dismiss? " + isAlertOpen(driver));
        } catch (Exception | Error e) {
            System.out.println("Ramura catch");
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }


    public static void formTests() {
        ChromeDriver driver = null;
        try {
            driver = BrowserManager.getChromeDriver();
            driver.get("https://testpages.herokuapp.com/styled/basic-html-form-test.html");
            WebElement form = driver.findElement(By.id("HTMLFormElements"));
            WebElement username = driver.findElement(By.name("username"));
            WebElement password = driver.findElement(By.name("password"));
            WebElement textArea = driver.findElement(By.name("comments"));
            WebElement chooseFileButton = driver.findElement(By.name("filename"));
            WebElement hiddenInput = driver.findElement(By.name("hiddenField"));
            List<WebElement> checkboxes = driver.findElements(By.name("checkboxes[]"));
            List<WebElement> radioBoxes = driver.findElements(By.name("radioval"));
            Select select = new Select(driver.findElement(By.name("multipleselect[]")));
            Select dropdown = new Select(driver.findElement(By.name("dropdown")));
            WebElement cancelButton = driver.findElement(By.cssSelector("input[type='reset']"));
            WebElement submitButton = driver.findElement(By.cssSelector("input[type='submit']"));
            WebElement afterSubmit = driver.findElement(By.id("_valuesubmitbutton"));

            username.sendKeys("Mircea Batranul");
            password.sendKeys("ferrariF50");
            textArea.clear();
            textArea.sendKeys("Testul cu textul");
            chooseFileButton.sendKeys("C:\\Users\\v-liviupopa\\IdeaProjects\\poza.png");
            System.out.println(hiddenInput.getAttribute("value"));
            checkboxes.get(1).click();
            radioBoxes.get(2).click();
            select.deselectAll();
            select.selectByValue("ms1");
            select.selectByValue("ms2");
            dropdown.selectByIndex(2);

            form.submit();
            System.out.println(afterSubmit.getText());

        } catch (Exception | Error e) {
            e.printStackTrace();
            if (driver != null) {
                File file = driver.getScreenshotAs(OutputType.FILE);
                File destinationFile = new File("C:\\Users\\v-liviupopa\\IdeaProjects\\poza.png");
                try {
                    FileUtils.copyFile(file, destinationFile);
                } catch (IOException ex) {
                    System.out.println("Screenshot does not proceeded");
                }
            } else {
                System.out.println("Driver instance failed to create successfully");
            }
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }

    public static void windowsLinks() {
        ChromeDriver driver = null;
        try {
            driver = BrowserManager.getChromeDriver();
            driver.get("https://testpages.herokuapp.com/styled/windows-test.html");

            WebElement firstLink = driver.findElement(By.linkText("Basic Ajax in new page"));
            firstLink.click();

            String mainWindow = driver.getWindowHandle();
            Set<String> otherWindow = driver.getWindowHandles();

            for (String window : otherWindow) {
                if (!window.equals(mainWindow)) {
                    driver.switchTo().window(window);
                    break;
                }
            }
            Select select = new Select(driver.findElement(By.name("id")));
            select.selectByValue("2");
            Select dropdown = new Select(driver.findElement(By.name("language_id")));
            dropdown.selectByIndex(2);
            WebElement codeButton = driver.findElement(By.cssSelector("input[value='Code In It']"));
            codeButton.click();
            WebElement text = driver.findElement(By.className("explanation"));
            System.out.println(text.getText());
            driver.switchTo().window(mainWindow);
            WebElement secondLink = driver.findElement(By.id("goattributes"));
            secondLink.click();

        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }

    public static boolean isAlertOpen(ChromeDriver driver) {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}