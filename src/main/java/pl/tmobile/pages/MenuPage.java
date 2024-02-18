package pl.tmobile.pages;

import static com.codeborne.selenide.Selenide.$x;

import com.codeborne.selenide.SelenideElement;

public class MenuPage {
  private SelenideElement deviceButton= $x(".//button[contains(text(), 'Urządzenia')]");
  private SelenideElement smartWatchesLink = $x("(//span[text()='Bez abonamentu'])[2]");

  public MenuPage navigateToMenu(){
    deviceButton.click();
    return this;
  }

  public pages.SmartWatchesPage navigateToSmartWatches(){
    smartWatchesLink.click();
    return new pages.SmartWatchesPage();
  }

}
