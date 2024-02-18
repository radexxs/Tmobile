package com;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.junit.Assert.assertEquals;
import pl.tmobile.steps.BaseTest;
import org.junit.Test;
import pages.CartPage;
import pages.HomePage;
import pages.ProductPage;
import pages.SmartWatchesPage;

public class HomePageTest extends BaseTest {

  HomePage homePage = new HomePage();
  SmartWatchesPage smartWatchesPage = new SmartWatchesPage();
  ProductPage productPage = new ProductPage();
  CartPage cartPage = new CartPage();

 @Test
 public void addProductToCart() {
  // Przekierowanie na stronę główną i weryfikacja URL
  open("/");
  assertEquals("https://www.t-mobile.pl/", getWebDriver().getCurrentUrl());

  // kliknięcie w górne menu
  homePage.navigateToMenu();
  $x("(.//span[text()='Bez abonamentu'])[2]").shouldBe(visible);

  //kliknięcie w bezAbonamentu
  homePage.navigateToSmartWatches();
  $$x(".//*[contains(@data-qa, 'LST_ProductCard')]").shouldBe(sizeGreaterThan(0));

  // Wybór pierwszego smartwatcha i weryfikacja widoczności nazwy produktu
  smartWatchesPage.selectFirstSmartWatches();
  $x(".//h1[@data-qa='PRD_ProductName']").shouldBe(visible);

  // Dodanie produktu do koszyka i weryfikacja cen
  String promisedStartPrice = productPage.getStartPriceValue();
  String promisedMonthlyPaymentAmount = productPage.getMonthlyPaymentAmountValue();
  productPage.addProductToBasket();

  String cartStartPrice = cartPage.getStartPriceValue();
  String cartMonthlyPaymentAmount = cartPage.getMonthlyPaymentAmountValue();

  assertEquals("Cena na start się nie zgadza.", promisedStartPrice, cartStartPrice);
  assertEquals("Miesięczna rata się nie zgadza.",promisedMonthlyPaymentAmount, cartMonthlyPaymentAmount);

  // Powrót na stronę główną i weryfikacja ilości produktów w koszyku
  open("/");
  assertEquals("Niepoprawna wartość", "1", homePage.getBasketIconProductQuantity());
 }

}
