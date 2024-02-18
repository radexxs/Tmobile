package pl.tmobile.pages;

import static com.codeborne.selenide.Selenide.$x;

import com.codeborne.selenide.SelenideElement;
import utils.Reusables;

public class ProductPage {
  private SelenideElement startPrice = $x("(//div[@class='dt_price_change']//div)[3]");
  private SelenideElement monthlyPaymentAmount =$x("(.//div[@class='dt_price_change'])[4]");
  private SelenideElement buttonAddToBasekt = $x("(.//button[@data-qa='PRD_AddToBasket'])[2]");
  private SelenideElement productHeder = $x(".//h1[@data-qa='PRD_ProductName']");

  Reusables reusables = new Reusables();

  public String getMonthlyPaymentAmountValue(){
    return reusables.getPrice(monthlyPaymentAmount);
  }

  public String getStartPriceValue(){
    return reusables.getPrice(startPrice);
  }



  public CartPage addProductToBasket(){
    buttonAddToBasekt.click();
    return new CartPage();
  }
}
