package yandex.boyko;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.partialLinkText;

public class LambdaStepTest {
  private static final String REPOSITROY = "eroshenkoam/allure-example";
  private static final int NUMBER = 68;

  @Test
  public void lambdaStepTest() {
    step("Открываем главную страницу", () -> {
      open("https://github.com");
    });
    step("поиск репозитория " + REPOSITROY, () -> {
      $(".header-search-input").setValue(REPOSITROY);
      $(".header-search-input").submit();
    });
    step("переходим в репозиторий " + REPOSITROY, () -> {
      $(linkText(REPOSITROY)).click();
    });
    step("открываем таб Issues ", () -> {
      Allure.addAttachment("Page Source", "text/html", WebDriverRunner.source(), "html");
      $(partialLinkText("Issues")).click();
    });
    step("проверяем наличие issue с номером " + NUMBER, () -> {
      $(withText("#" + NUMBER)).shouldHave(Condition.visible);
    });
  }

}




