package litecart_using_page_object.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends Page {

  public HomePage(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  public void open() {
    System.out.println("Открываю домашнюю страницу магазина");
    driver.get("http://localhost/litecart/en/");
  }

  @FindBy(xpath = "//div[@id='cart']/a[@class='link']")
  private WebElement openBasketButton;

  @FindBy(xpath = "//div[@id='box-most-popular']//ul/li[1]")
  private WebElement firstProduct; // первый товар в категории Most Popular

  public void firstProductOpen() {
    System.out.println("Нажимаю на первый товар в категории Most Popular");
    firstProduct.click();
  }

  public void openBasket() {
    System.out.println("Жму кнопку открытия корзины");
    openBasketButton.click();
  }
}