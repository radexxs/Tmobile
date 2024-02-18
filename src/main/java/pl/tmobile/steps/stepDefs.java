package pl.tmobile.steps;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.junit.Assert.assertEquals;
import static com.codeborne.selenide.Selenide.open;

import base.BaseTest;
import com.codeborne.selenide.Configuration;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepDefs {

  pages.HomePage homePage = new pages.HomePage();
  pages.SmartWatchesPage smartWatchesPage = new pages.SmartWatchesPage();
  pages.ProductPage productPage = new pages.ProductPage();
  pages.CartPage cartPage = new pages.CartPage();


  String promisedStartPrice;
  String promisedMonthlyPaymentAmount;
  String cartStartPrice;
  String cartMonthlyPaymentAmount;

  @Given("Otwieram przegladarke i przechodze na strone tmobile")
  public void otwieramprzegladarkeprzechodzenastronetmobile(){
    base.BaseTest baseTest = new BaseTest();
    Configuration.browser = "chrome";
    Configuration.baseUrl = "https://www.t-mobile.pl";
    open("/");
    getWebDriver().manage().window().maximize();
    baseTest.closeCookieConsentMessage();
  }

  @Then("uzytkowni Powinienem zobaczyc strone główna TMobile")
  public void uzytkowniPowinienemzobaczycstronegłównaTMobile(){
    Assert.assertEquals("https://www.t-mobile.pl/", getWebDriver().getCurrentUrl());
  }


  @When("Uzytkownik wybiera sekcje Urzadzenia i wybiera te bez abonamentu")
  public void UzytkownikwybierasekcjeUrzadzeniaiwybieratebezabonamentu(){
    homePage.navigateToSmartWatches();
  }

  @Then("Powinna zostac wyswietlona lista smartfonow")
  public void Powinnazostacwyswietlonalistasmartfonow(){
    $$x(".//*[contains(@data-qa, 'LST_ProductCard')]").shouldBe(sizeGreaterThan(0));
  }

  @When("Klikam w pierwszy element z listy smartfonow")
  public void Klikamwpierwszyelementzlistysmartfonow(){
    smartWatchesPage.selectFirstSmartWatches();
  }

  @Then("Powinna zostac wyswietlona strona produktu")
  public void Powinnazostacwyswietlonastronaproduktu(){
    $x(".//h1[@data-qa='PRD_ProductName']").shouldBe(visible);
  }

  @When("Dodaje produkt do koszyka")
  public void Dodajeproduktdokoszyka (){
    promisedStartPrice = productPage.getStartPriceValue();
    promisedMonthlyPaymentAmount = productPage.getMonthlyPaymentAmountValue();
    productPage.addProductToBasket();
  }


  @Then("Strona Twoj koszyk zostaje wyswietlona z odpowiednimi kwotami")
  public void StronaTwojkoszykzostajewyswietlonazodpowiednimikwotami(){
     cartStartPrice = cartPage.getStartPriceValue();
     cartMonthlyPaymentAmount = cartPage.getMonthlyPaymentAmountValue();

    Assert.assertEquals("Cena na start się nie zgadza.", promisedStartPrice, cartStartPrice);
    Assert.assertEquals("Miesięczna rata się nie zgadza.",promisedMonthlyPaymentAmount, cartMonthlyPaymentAmount);
  }

  @When("Uzytkownik wraca na strone głown TMobile")
  public void UzytkownikwracanastronegłwnTMobile(){
    open("/");
  }

  @Then("Widoczna jest ikonka koszyka z liczba produktow w koszyku")
  public void Widocznajestikonkakoszykazliczbaproduktowwkoszyku(){
    assertEquals("Niepoprawna wartość", "1", homePage.getBasketIconProductQuantity());
    getWebDriver().quit();
  }

}
