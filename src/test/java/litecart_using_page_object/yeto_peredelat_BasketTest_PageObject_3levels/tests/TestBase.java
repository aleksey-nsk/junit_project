package litecart_using_page_object.yeto_peredelat_BasketTest_PageObject_3levels.tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import litecart_using_page_object.yeto_peredelat_BasketTest_PageObject_3levels.app.Application;

public class TestBase {

  public static Application app;

  @BeforeClass
  public static void start() {
    System.out.print("\n\n***** Внутри метода start() *****\n\n");
    app = new Application(); // экземпляр класса Application
  }

  @AfterClass
  public static void stop() {
    System.out.print("\n\n***** Внутри метода stop() *****\n\n");
    app.quit();
  }
}