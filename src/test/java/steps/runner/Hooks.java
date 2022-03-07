package steps.runner;

import beans.ValuesHelper;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.TestDataReader;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import static Core.DriverFactory.*;

public class Hooks {
	static String path;
	static WebDriver driver;
	private static final Logger log = LoggerFactory.getLogger(Hooks.class);
	static TestDataReader data = new TestDataReader();
	public static ValuesHelper helper = new ValuesHelper();
	
	@Before()
	public static void setup(Scenario scenario) {
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

//	@After
//	public static void killDriver(){
// 		killDriver();
//	}

}
