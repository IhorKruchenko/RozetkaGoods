package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.Assert.assertEquals;

/**
 * Created by IgorKruchenko on 06.01.2017.
 */
public class AuthorizationPage extends BasePage {
    public AuthorizationPage(WebDriver driver){
        super(driver);
    }

    //open pege 'url' in browser
    //assert title page equals 'title'
    public void open(String url, String title) {
        driver.get(url);
        assertEquals(title, driver.getTitle());
    }

    //open Personal Area
    //assert title page equals 'title'
    public void openPersonalArea(String title) {
        waitForConditions.until(ExpectedConditions.presenceOfElementLocated(By.name("profile"))).click();
        assertEquals(title, driver.getTitle());
    }

    //open page 'url' in browser
    //open authorization form on page
    //choice of authentication method 'type'
    //insert field 'login' and 'password'
    public void authorizationForm(String type, String url, String login, String password) throws InterruptedException {
        open(url, "ROZETKA — Вход в интернет-магазин");
        String parentHandle = driver.getWindowHandle();
        driver.findElement(By.name("signin")).click();
        waitForConditions.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'popup-auth')]")));
        if (type.equals("vkontakte")){
            driver.findElement(By.xpath(".//*[@class='popup-css popup-auth']//*[@type='vkontakte']")).click();
            Thread.sleep(2000);
            for(String childHandle : driver.getWindowHandles()){
                if (!childHandle.equals(parentHandle)){
                    driver.switchTo().window(childHandle);
                }
            }
            waitForConditions.until(ExpectedConditions.presenceOfElementLocated(By.name("email"))).sendKeys(login);
            waitForConditions.until(ExpectedConditions.presenceOfElementLocated(By.name("pass"))).sendKeys(password);
            waitForConditions.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@type='submit']"))).click();
            driver.switchTo().window(parentHandle);
        } else if (type.equals("facebook")){
            driver.findElement(By.xpath(".//*[@class='popup-css popup-auth']//*[@type='facebook']")).click();
            Thread.sleep(2000);
            for(String childHandle : driver.getWindowHandles()){
                if (!childHandle.equals(parentHandle)){
                    driver.switchTo().window(childHandle);
                }
            }
            waitForConditions.until(ExpectedConditions.presenceOfElementLocated(By.name("email"))).sendKeys(login);
            waitForConditions.until(ExpectedConditions.presenceOfElementLocated(By.name("pass"))).sendKeys(password);
            waitForConditions.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@type='submit']"))).click();
            waitForConditions.until(ExpectedConditions.presenceOfElementLocated(By.name("__CONFIRM__"))).click();
            driver.switchTo().window(parentHandle);
        } else if (type.equals("email")){
            driver.findElement(By.xpath(".//*[@class='popup-css popup-auth']//input[@name='login']")).sendKeys(login);
            driver.findElement(By.xpath(".//*[@class='popup-css popup-auth']//input[@name='password']")).sendKeys(password);
            driver.findElement(By.name("auth_submit")).click();
            waitForConditions.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id ='personal_information_content']")));
        }else {
            System.out.println("Selecting no authentication method " + type);
        }
    }
}
