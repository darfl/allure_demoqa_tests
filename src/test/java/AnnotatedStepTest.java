import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

public class AnnotatedStepTest extends TestBase {
    private static final String REPOSITORY = "qa-guru/knowledge-base";
    private static final String ISSUE = "Qa auto";


    @Test
    public void AnnotatedStepsTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebStepsPage steps = new WebStepsPage();

        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.clickOnRepositoryLink(REPOSITORY);
        steps.openIssueTab();
        steps.shouldSeeTheIssue(ISSUE);
    }
}
