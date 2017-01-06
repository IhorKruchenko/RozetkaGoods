package steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libs.ActionStep;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.AuthorizationPage;
import pages.BasePage;
import pages.PersonalPage;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by IgorKruchenko on 06.01.2017.
 */
public class Authorization {

    public WebDriver driver;
    public WebDriverWait waitForConditions;
    public static ArrayList goods = BasePage.Goods;
    public static ArrayList goodsView = BasePage.viewedgoods;

    @Before
    public void start(){
        //open Chrome
        System.setProperty("webdriver.chrome.driver", "src\\lib\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        waitForConditions = new WebDriverWait(driver, 10);
    }

    @After
    public void end(){
        //close ChromeDriver and browser
        driver.close();
        driver.quit();
    }

    @Given("^Войти в личный кабинет \"([^\"]*)\" через \"([^\"]*)\" под \"([^\"]*)\" с паролем \"([^\"]*)\"$")
    public void openPersonalArea(String urlTest, String type, String loginTest, String passwordtest) throws InterruptedException {
        //open Personal Area
        AuthorizationPage authorizationPage = new AuthorizationPage(driver);
        authorizationPage.authorizationForm(type, urlTest, loginTest, passwordtest);
    }

    @When("^Очистить список просмотренных товаров$")
    public void clearGoodsInViewedGoods() {
        //open Viewed Goods in Personal Area
        PersonalPage personalPage = new PersonalPage(driver);
        personalPage.searchAndOpenByProfileList("Просмотренные товары");
        //check Viewed Products
        //push button DeleteAll in Viewed Goods
        if (personalPage.confirmationOpenProfile("recent-goods-header")) {
            personalPage.clearAll();
            personalPage.confirmationOpenProfile("recent-goods-header");
        }
    }

    @And("^Считать детали товара \"([^\"]*)\"$")
    public void setNameAndPriceByUrl(String url) {
        //open pege 'url' in browser
        driver.get(url);
        waitForConditions.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='detail-title']")));
        //get name goods on page
        String nameElement = driver.findElement(By.xpath("//*[@class='detail-title']")).getText();
        //get price goods on page
        String priceElement = driver.findElement(By.xpath("//*[@id='price_label']")).getText();
        goods.add(nameElement + "/ " + priceElement);
    }

    @And("^Считать список просмотренных товаров$")
    public void setNameAndPriceAllGoods() {
        //open Personal Area
        AuthorizationPage authorizationPage = new AuthorizationPage(driver);
        authorizationPage.openPersonalArea("ROZETKA — Личные данные | Личный кабинет");
        //search in ProfileList field "Просмотренные товары" and open page
        PersonalPage personalPage = new PersonalPage(driver);
        personalPage.searchAndOpenByProfileList("Просмотренные товары");
        //confirmation Open Profile 'recent-goods-header'
        //get Name and Price goods on page
        if (personalPage.confirmationOpenProfile("recent-goods-header")){
            goodsView = personalPage.getNameAndPriceViewedGoods();
        }
    }

    @Then("^Сверить детали товаров$")
    public void verifyGoods() {
        ActionStep.matchArray(goods, goodsView);
    }
}
