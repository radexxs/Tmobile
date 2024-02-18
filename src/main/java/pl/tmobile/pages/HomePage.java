package pl.tmobile.pages;

import static com.codeborne.selenide.Selenide.*;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;


public class HomePage {


  private SelenideElement basketIcon = $x(".//a[@data-ma='menu-basket']//div");



  public String getBasketIconProductQuantity(){
    return basketIcon.getText();
  }
}
