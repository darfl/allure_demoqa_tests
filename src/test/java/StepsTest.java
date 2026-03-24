import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class StepsTest {
    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://github.com";
        Configuration.pageLoadStrategy = "eager";
    }

    private static String REPOSITORY = "qa-guru/knowledge-base";
    private static String ISSUE = "Qa auto";


    @Test
    void lambdaStepTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем главную страницу GitHub", () -> {
            open("/");
        });
        step("Ищем репозиторий" + " " + REPOSITORY, () -> {
            $("[placeholder='Search or jump to...']").click();
            $("#query-builder-test").setValue(REPOSITORY).pressEnter();
        });
        step("Кликаем по ссылке репозитория" + " " + REPOSITORY, () -> {
            $(By.linkText(REPOSITORY)).click();
        });
        step("Открываем Issues", () -> {
            $("#issues-tab").click();
        });
        step("Проверяем наличие Issue c названием" + " " + ISSUE, () ->  {
            $(byText(ISSUE)).should(exist);
        });
    }

    @Test
    public void testAnnotatedStepsTest() {
        WebStepsPage steps = new WebStepsPage();

        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.clickOnRepositoryLink(REPOSITORY);
        steps.openIssueTab();
        steps.shouldSeeTheIssue(ISSUE);
    }
}
