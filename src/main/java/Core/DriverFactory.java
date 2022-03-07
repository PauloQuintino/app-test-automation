package Core;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverFactory {
    private static String path;
    private static AndroidDriver<MobileElement> driver = null;


    public static AndroidDriver<MobileElement> getDriver() {
        if (driver == null) {
            createMobileDriver();
        }
        return driver;
    }

    public static void createMobileDriver() {
        path = System.getProperty("user.dir");
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("automationName", "UiAutomator2");
        cap.setCapability("autoGrantPermissions", true);
        cap.setCapability("deviceName", "emulator-5554");
        cap.setCapability("udid", "emulator-5554");
        cap.setCapability("platformVersion", "11");
        cap.setCapability("platformName", "Android");
        cap.setCapability("app", path + "/Apps/app-dev-release-v10122.apk");
        cap.setCapability("appPackage", "com.br.smiles.dev");
        cap.setCapability("appActivity", "com.br.smiles.MainActivity");
        cap.setCapability("newCommandTimeout", 3000);
        try {
            driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), cap);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }

    public static void killDriver(){
        if(driver != null){
            driver.quit();
            driver = null;
        }
    }


}
