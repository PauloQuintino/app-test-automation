package steps.app;

import io.cucumber.java.pt.Dado;
import pages.CucumberTestActivity;

import java.net.MalformedURLException;

public class CucumberTestStep {
    CucumberTestActivity home = new CucumberTestActivity();

    @Dado("que estou no aplicativo")
    public void que_estou_no_aplicativo() throws InterruptedException {
        home.validateHomePage();
    }
}
