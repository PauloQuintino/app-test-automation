package steps.app;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import pages.CucumberTestActivity;
import pages.ProductsHome;

import java.net.MalformedURLException;

public class CucumberTestStep {
    CucumberTestActivity home = new CucumberTestActivity();
    ProductsHome productsHome = new ProductsHome();

    @Dado("que estou no aplicativo")
    public void que_estou_no_aplicativo() throws InterruptedException {
        home.validateHomePage();
    }

    @Dado("que estou na home do app MyDemoApp")
    public void queEstouNaHomeDoAppMyDemoApp(){
        productsHome.validateMainPage();
    }

    @Entao("deve ser apresentados produtos na home")
    public void deveSerApresentadosProdutosNaHome(){
        productsHome.validateProductsList();
    }

    @Dado("que o usuário acesse a home deslogada")
    public void queOUsuarioAcessAHomeDeslogada() throws InterruptedException {
        home.validateHomePage();
    }

    @Entao("devo visualizar os cards de fidelidade")
    public void devoVisualizarOsCardsDeFidelidade(){
        home.validateFidelityCards();
    }

    @E("realize o login")
    public void realizeOLogin(){
        home.acessLogin();
        home.login();
    }


}
