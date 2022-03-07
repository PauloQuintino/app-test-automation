package pages;

import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import static Core.DriverFactory.getDriver;
import static org.junit.Assert.assertEquals;

public class CucumberTestActivity {

    public CucumberTestActivity() {
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }

    @AndroidFindBy(xpath = "//*[contains(@text, 'viajante')]")
    private AndroidElement titleHome;

//    private MobileElement titleHome = getDriver().findElement(By.xpath("//*[contains(@text, 'viajante')]"));

    public void validateHomePage() throws InterruptedException {
        System.out.println("entrou aqui");
        assertEquals("Olá, viajante", titleHome.getText());
    }
}
