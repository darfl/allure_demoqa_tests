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

public class GitHubIssueTest {
    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://github.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void issueSearchTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        open("/");
        $("[placeholder='Search or jump to...']").click();
        $("#query-builder-test").setValue("qa-guru/knowledge-base").pressEnter();
        $(By.linkText("qa-guru/knowledge-base")).click();
        $("#issues-tab").click();
        $(byText("Qa auto")).should(exist);
    }
}
