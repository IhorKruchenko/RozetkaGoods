package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;

/**
 * Created by IgorKruchenko on 06.01.2017.
 */
public class PersonalPage extends BasePage {
    public PersonalPage(WebDriver driver){
        super(driver);
    }

    //search in ProfileList field 'name'
    //open 'name'
    public void searchAndOpenByProfileList(String name) {
        int tabsCounter = driver.findElements(By.xpath("/html/body/div[2]/div/div/div/nav/ul/li")).size();
        for (int i = 4; i <= tabsCounter; i++){
            String titleTabsCounter = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/nav/ul/li[" + i + "]/a/span")).getText();
            if (titleTabsCounter.contains(name)){
                driver.findElement(By.xpath(".//ul[@class='clearfix profile-m']/li[" + i + "]/a/span")).click();
                break;
            }
        }
    }

    //confirmation Open Profile 'name'
    public boolean confirmationOpenProfile(String name) {
        boolean isEmpty = true;
        if (driver.findElements(By.className(name)).size() == 0){
            System.out.println("No viewed products");
            isEmpty = false;
        }else {
            waitForConditions.until(ExpectedConditions.presenceOfElementLocated(By.className(name)));
        }
        return isEmpty;
    }

    //get Name and Price goods on page
    public ArrayList getNameAndPriceViewedGoods() {
        ArrayList goodsList = new ArrayList();
        int goods = driver.findElements(By.xpath(".//*[@class='g-i-td']/div")).size();
        for (int i = 1; i <= goods; i++) {
            String titleGoods = driver.findElement(By.xpath("//*[@id='goods_block']/div[1]/div/div[1]/div[" + i + "]/div/div[1]/a")).getText();
            String priceGoods = driver.findElement(By.xpath(".//*[@class='g-i-td'][" + i + "]//*[@class='g-price-uah']")).getText();
            priceGoods = priceGoods.substring(0, priceGoods.indexOf(" грн"));
            goodsList.add(titleGoods + " / " + priceGoods);
        }
        return goodsList;
    }

    // push button DeleteAll in Viewed Goods
    public void clearAll() {
        waitForConditions.until(ExpectedConditions.presenceOfElementLocated(By.name("delete_all"))).click();
    }
}
