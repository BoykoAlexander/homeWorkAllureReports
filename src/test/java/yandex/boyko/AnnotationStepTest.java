package yandex.boyko;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.partialLinkText;

public class AnnotationStepTest {


  @Step("Открываем главную страницу")
  public void openMainPage() {
    open("https://github.com");
  }

  @Step("Ищем репозиторий {repository}")
  public void searchRepository(String repository) {
    $(".header-search-input").setValue(repository);
    $(".header-search-input").submit();
  }

  @Step("Открываем искомый репозиторий {repository}")
  public void openRepositoryPage(String repository) {
    $(linkText(repository)).click();
  }

  @Step("Открываем Issue таб")
  public void openIssueTab() {
    $(partialLinkText("Issues")).click();
  }

  @Step("Проверяем номер Issue {number}")
  public void AssertionIssueNumber(int number) {
    $(withText("#" + number)).shouldHave(Condition.visible);
  }

  @Attachment(value = "Screenshot", type = "text/html", fileExtension = "html")
  public byte[] attachPageSource() {
    return WebDriverRunner.source().getBytes(StandardCharsets.UTF_8);
  }

  @Test
  @Owner("boykoAlexander")
  @Feature("Issues")
  @Story("Поиск Issues не авторизованым пользователем")
  @DisplayName("Поиск Issues не авторизованым пользователем")
  @Severity(SeverityLevel.CRITICAL)
  @Link(value = "GitHub", url = "https://github.com")
  public void annotationTest() {
    AnnotationStepTest steps = new AnnotationStepTest();
    steps.openMainPage();
    steps.searchRepository("eroshenkoam/allure-example");
    steps.openRepositoryPage("eroshenkoam/allure-example");
    steps.openIssueTab();
    steps.AssertionIssueNumber(68);
  }
}
