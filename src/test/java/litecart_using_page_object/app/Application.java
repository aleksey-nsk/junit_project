package litecart_using_page_object.app;

import litecart_using_page_object.pages.BasketPage;
import litecart_using_page_object.pages.ProductPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import litecart_using_page_object.pages.HomePage;
import java.util.concurrent.TimeUnit;

// В тестах вообще нигде не видно что используется Селениум.
// Внутрь класса Application прячем все технические подробности.
// Только в этом классе видим что используется Селениум.
// Именно тут создаётся драйвер, и здесь же он используется:
public class Application {

  private WebDriver driver;
  private HomePage homePage;
  private ProductPage productPage;
  private BasketPage basketPage;

  public Application() {
    System.setProperty("webdriver.chrome.driver", "C:\\Tools\\chromedriver_win32.exe");
    driver = new ChromeDriver(); // инициализация драйвера
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // задал неявное ожидание
    driver.manage().window().maximize();
    homePage = new HomePage(driver);
    productPage = new ProductPage(driver);
    basketPage = new BasketPage(driver);
  }

  public void quit() {
    driver.quit();
  }

  public void addOneProductToBasket() {
    System.out.println("\nМЕТОД ДЛЯ ДОБАВЛЕНИЯ 1 ТОВАРА В КОРЗИНУ");
    homePage.open();
    homePage.firstProductOpen();
    int oldAmountProductsInBasket = productPage.currentAmountProductsInBasket();
    productPage.selectSizeIfPresent();
    productPage.addProductToBasket();
    productPage.waitForCounterUpdating(oldAmountProductsInBasket);
  }

  public void deleteAllProductsFromBasket() {
    System.out.println("\nМЕТОД ДЛЯ УДАЛЕНИЯ ВСЕХ ТОВАРОВ ИЗ КОРЗИНЫ");
    homePage.open();
    homePage.openBasket();
    int oldAmountProductsInBasket = basketPage.currentAmountProductsInBasket();
    System.out.println("Начальное количество товаров в корзине = " + oldAmountProductsInBasket);
    for (int i = 1; i <= oldAmountProductsInBasket; i++) {
      int oldLines = basketPage.currentLinesInTable();
      basketPage.removeOneProduct();
      if (i < oldAmountProductsInBasket) {
        basketPage.waitForTableUpdating(oldLines);
      } else {
        basketPage.waitForEmptyBasketMessage();
      }
    }
  }
}