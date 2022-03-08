package steps.runner;

import Core.DriverFactory;
import beans.LoggerHelper;
import beans.ValuesHelper;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import utils.TestDataReader;

import static Core.DriverFactory.getDriver;

public class Hooks {
    static String path;
    static WebDriver driver;
    LoggerHelper log = new LoggerHelper(Hooks.class);
    static TestDataReader data = new TestDataReader();
    public static ValuesHelper helper = new ValuesHelper();

    @Before()
    public void setup(Scenario scenario) {
        log.info("Iniciando automação...");
        getDriver();
        log.info("Feature name: " + scenario.getName());
        helper.setScenarioName(scenario.getName());
        Object[] arraysTag = scenario.getSourceTagNames().toArray();
        for (Object tag : arraysTag) {
            data.setCtKey(tag.toString().replace("@", ""));
            System.out.println(data.getCtKey());
        }
    }

    @After
    public void killDriver() {
        DriverFactory.killDriver();
    }

}
