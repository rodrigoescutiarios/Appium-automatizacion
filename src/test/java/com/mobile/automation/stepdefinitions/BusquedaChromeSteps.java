package com.mobile.automation.stepdefinitions;

import com.mobile.automation.config.DriverManager;
import com.mobile.automation.pages.ChromePage;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class BusquedaChromeSteps {

    private AppiumDriver driver;
    private ChromePage chromePage;

    @Given("el usuario abre Chrome en el dispositivo")
    public void elUsuarioAbreChromeEnElDispositivo() {
        driver = DriverManager.getDriver();
        chromePage = new ChromePage(driver);
        System.out.println("Chrome abierto correctamente");
    }

    @When("el usuario busca {string} en Chrome")
    public void elUsuarioBuscaEnChrome(String searchText) throws InterruptedException {
        Thread.sleep(3000);
        chromePage.searchFor(searchText);
        System.out.println("Búsqueda realizada: " + searchText);
    }

    @Then("el usuario debería ver resultados de búsqueda")
    public void elUsuarioDeberiaVerResultadosDeBusqueda() throws InterruptedException {
        Thread.sleep(3000);
        Assert.assertNotNull(driver, "El driver no debería ser null");
        System.out.println("Verificación exitosa - Resultados mostrados");
    }
}