package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

/**
 * Created by IgorKruchenko on 06.01.2017.
 */
public class BasePage {
    public WebDriver driver;
    public WebDriverWait waitForConditions;
    public static ArrayList Goods = new ArrayList();
    public static ArrayList viewedgoods = new ArrayList();

    public BasePage(WebDriver driver){
        this.driver = driver;
        waitForConditions = new WebDriverWait(driver, 10);
    }
}
