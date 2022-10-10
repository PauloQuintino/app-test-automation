package pages;

import Core.DriverFactory;
import beans.LoggerHelper;
import beans.ValuesHelper;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import utils.TestDataReader;

import java.time.Duration;


public class BasePage {

    private ValuesHelper helper = new ValuesHelper();
    public static TestDataReader data = new TestDataReader();

    @FindBy(xpath = "//img[contains(@class, 'logo')]")
    private WebElement logo;

    public void selectWithValue(WebElement elemento, String valor) {
        Select selectDateDays = new Select(elemento);
        selectDateDays.selectByValue(valor);
    }

    public void selectWithText(WebElement elemento, String texto) {
        Select selectDateDays = new Select(elemento);
        selectDateDays.selectByVisibleText(texto);
        ;
    }

    public void selectFirst(WebElement elemento) {
        Select select = new Select(elemento);
        select.selectByIndex(0);
    }

    public void backToHome() {
        logo.click();
    }

    public void sleep(int milissegundos) {
        try {
            Thread.sleep(milissegundos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void selectPerText(WebElement elemento, String texto) {
        Select selectDateDays = new Select(elemento);
        selectDateDays.selectByVisibleText(texto);
        ;
    }



//	public void takeScreenshot(String screenshot){
//		try {
//			Date date = new Date();
//			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy-HH");
//			File srcFile = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.FILE);
//			String scenarioName = helper.getScenarioName();
//				FileUtils.copyFile(srcFile, new File(
//						"Evidence\\" + sdf.format(date) + "\\" + scenarioName + "\\" + screenshot + ".png"));
//		}
//		catch (Exception e) {
//
//		}
//
//	}
}
