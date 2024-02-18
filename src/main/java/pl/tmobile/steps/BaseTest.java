package pl.tmobile.steps;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.After;
import org.junit.Before;


public class BaseTest {

  SelenideElement buttonCookies = $x(".//button[@id='didomi-notice-agree-button']");

  public void closeCookieConsentMessage(){
    if(buttonCookies.isDisplayed()){
      buttonCookies.click();
    }
  }

  @Before
  public void setup(){
    Configuration.browser = "chrome";
    Configuration.baseUrl = "https://www.t-mobile.pl";
    open("/");
    getWebDriver().manage().window().maximize();
    closeCookieConsentMessage();
  }

  @After
  public void treamdown(){
    getWebDriver().quit();
  }
}
