package pages;

import Core.DriverFactory;
import com.sun.javafx.scene.traversal.Direction;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.PageFactory;
import utils.UtilsMobile;

import java.util.ArrayList;
import java.util.List;

import static Core.DriverFactory.getDriver;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CucumberTestActivity {

    public CucumberTestActivity() {
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }

    private final UtilsMobile utils = new UtilsMobile();

    @AndroidFindBy(xpath = "//*[contains(@text, 'viajante')]")
    private AndroidElement titleHome;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@resource-id='utility-card']//android.view.ViewGroup//android.widget.TextView")
    private AndroidElement fidelityCardsText;

    public void validateHomePage() throws InterruptedException {
        System.out.println("entrou aqui");
        assertEquals("Olá, viajante", titleHome.getText());
    }

    public void validateFidelityCards() {
        String[] cardNames = {"Comprar milhas", "Transferir milhas entre contas", "Reativar milhas", "Extensão da validade das milhas",
                "Como ganhar e usar milhas", "Categoria Smiles"};
        List<String> txtCards = new ArrayList<>();

        utils.swipeToElement(fidelityCardsText, Direction.UP, 10);

        Dimension size = DriverFactory.getDriver().manage().window().getSize();
        int y = size.getHeight() / 2;

        for (int i = 0; i <= cardNames.length; i++) {

            if (i == 5) {
                MobileElement lastCard = getDriver().findElement(By.xpath("(//android.view.ViewGroup[@resource-id='utility-card']//android.view.ViewGroup//android.widget.TextView)[2]"));
                txtCards.add(lastCard.getText());
                break;
            }
            txtCards.add(fidelityCardsText.getText());
            utils.swipeScreen(829, 151, y, y);
        }

        for (String txt : txtCards) {
            System.out.println(txt);
        }

        System.out.println("Validando textos...");

        boolean txtIsEqual = false;

        for(int i =0; i< txtCards.size();i++){
            if(txtCards.get(i).equals(cardNames[i])){
                txtIsEqual = true;
            }
            else{
                txtIsEqual = false;
            }
        }

        assertTrue(txtIsEqual);
    }


}
