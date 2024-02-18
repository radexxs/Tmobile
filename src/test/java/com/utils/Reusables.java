package utils;

import com.codeborne.selenide.SelenideElement;

public class Reusables {

  public String getPrice(SelenideElement webElement){
    return webElement.getText().replace(" zł", "");
  }

}
