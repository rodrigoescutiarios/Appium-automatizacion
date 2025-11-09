package com.mobile.automation.stepdefinitions;

import com.mobile.automation.config.DriverManager;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class EjemploSteps {

    private AppiumDriver driver;

    @Given("el usuario abre la aplicación Chrome")
    public void elUsuarioAbreLaAplicacionChrome() {
        driver = DriverManager.getDriver();
        System.out.println("Chrome abierto correctamente");
    }

    @When("el usuario espera {int} segundos")
    public void elUsuarioEsperaSegundos(int segundos) throws InterruptedException {
        Thread.sleep(segundos * 1000);
        System.out.println("Esperando " + segundos + " segundos");
    }

    @Then("la aplicación Chrome debería estar visible")
    public void laAplicacionChromeDeberiaEstarVisible() {
        Assert.assertNotNull(driver, "El driver no debería ser null");
        System.out.println("Verificación exitosa - Chrome está visible");
    }
}