package pages;

import com.sun.javafx.scene.traversal.Direction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.support.PageFactory;
import utils.UtilsMobile;

import java.util.List;

import static Core.DriverFactory.getDriver;

public class ProductsHome extends BasePage {

    public ProductsHome() {
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }

    private static final Logger logger = LogManager.getLogger(ProductsHome.class);
    private final UtilsMobile utils = new UtilsMobile();

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Products']")
    private AndroidElement titleProducts;

    @AndroidFindBy(xpath = "//android.view.ViewGroup//android.widget.ImageView//following-sibling::android.widget.TextView")
    private List<AndroidElement> listProducts;

    public void validateMainPage() {
        logger.info("Validando que app está na home");
        utils.waitUntilsExist(titleProducts);
        Assert.assertEquals("Products", titleProducts.getText());
        logger.info("Home validada com sucesso!!");
    }

    public void validateProductsList() {
        logger.info("Validando lista de produtos na home");
        utils.waitUntilsExist(titleProducts);
        while (listProducts.size() < 5) {
            logger.info("Fazendo swipe na tela...");
            utils.swipeScreen(Direction.UP);
        }
        Assert.assertEquals(5, listProducts.size());
        logger.info("Lista de produtos na home validado com sucesso!!");
    }

}
