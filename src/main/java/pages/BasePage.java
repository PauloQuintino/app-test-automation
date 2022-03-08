package pages;

import beans.LoggerHelper;
import beans.ValuesHelper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;


public class BasePage {

	private ValuesHelper helper = new ValuesHelper();

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
		selectDateDays.selectByVisibleText(texto);;
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
