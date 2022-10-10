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

public class CucumberTestActivity extends BasePage {

    public CucumberTestActivity() {
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }

    private final UtilsMobile utils = new UtilsMobile();

    @AndroidFindBy(xpath = "//*[contains(@text, 'viajante')]")
    private AndroidElement titleHome;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@resource-id='utility-card']//android.view.ViewGroup//android.widget.TextView")
    private AndroidElement fidelityCardsText;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@resource-id='btn_bypassRecaptcha']")
    private AndroidElement bypassButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Acesse ou crie sua conta']")
    private AndroidElement accessOrCreateAccount;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Acessar conta']")
    private AndroidElement accessAccount;

    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='identifier']")
    private AndroidElement inputLogin;

    @AndroidFindBy(xpath = "//android.widget.Button[@text='Continuar']")
    private AndroidElement buttonContinue;

    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='password']")
    private AndroidElement inputPassword;

    @AndroidFindBy(xpath = "//android.widget.Button[@text='Entrar']")
    private AndroidElement buttonEnter;

    public void validateHomePage() throws InterruptedException {
        System.out.println("entrou aqui");
        assertEquals("Olá, viajante", titleHome.getText());
    }

    public void acessLogin(){
        System.out.println("Acessando tela de login...");
        utils.waitUntilsExist(accessOrCreateAccount);
        bypassButton.click();
        accessOrCreateAccount.click();
        System.out.println("Clicado no botão 'Acesse ou crie sua conta'");
        utils.waitUntilsExist(accessAccount);
        accessAccount.click();
        System.out.println("Clicado no botão 'Acessar conta'");
    }

    public void login(){
        System.out.println("Inserindo login...");
        utils.waitUntilsExist(inputLogin);
        inputLogin.sendKeys(data.getDt("User"));
        System.out.println("Login inserido: " + data.getDt("User"));
        sleep(2000);
        buttonContinue.click();

        System.out.println("Inserindo senha...");
        utils.waitUntilsExist(inputPassword);
        inputPassword.sendKeys(data.getDt("Password"));
        System.out.println("Senha inserido: " + data.getDt("Password"));
        sleep(2000);
        buttonEnter.click();

        sleep(2000);
    }

    public void validateFidelityCards() {
        String[] cardNames = {"Comprar milhas", "Transferir milhas entre contas", "Reativar milhas", "Extensão da validade das milhas",
                "Como ganhar e usar milhas", "Categoria Smiles"};
        List<String> txtCards = new ArrayList<>();

        utils.swipeToElement(fidelityCardsText, Direction.UP, 10);

        Dimension size = DriverFactory.getDriver().manage().window().getSize();
        int y = size.getHeight() / 2;
        int x = size.getWidth() / 2;

        for (int i = 0; i <= cardNames.length; i++) {

            if (i == 5) {
                MobileElement lastCard = getDriver().findElement(By.xpath("(//android.view.ViewGroup[@resource-id='utility-card']//android.view.ViewGroup//android.widget.TextView)[2]"));
                txtCards.add(lastCard.getText());
                break;
            }
            txtCards.add(fidelityCardsText.getText());
            utils.swipeScreen(829, 151, y, y);
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
