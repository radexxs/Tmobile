package pl.tmobile.pages;

import static com.codeborne.selenide.Selenide.$$x;
import com.codeborne.selenide.ElementsCollection;

public class SmartWatchesPage {
  private ElementsCollection firstSmartWatch = $$x(".//*[contains(@data-qa, 'LST_ProductCard')]");


  public ProductPage selectFirstSmartWatches(){
    firstSmartWatch.get(0).click();
    return new ProductPage();
  }

}
