package guru.qa.tests.PositiveTests;

import com.codeborne.selenide.Condition;
import guru.qa.tests.TestBase;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class SuccessfulSearchTest extends TestBase {
    @Test
    @Tag("search")
    @Feature("Automation test example")
    @Story("Successful item searching")
    @Owner("EphimSh")
    @Severity(SeverityLevel.CRITICAL)
    @Link(value = "primekraft", url = "https://primekraft.ru")
    @DisplayName("item search")
    void searchTest() {
        open("https://primekraft.ru");
        //remove ad-block
        executeJavaScript("$('#prime_exitblock').remove()");
        step("Нажать на кнопку поиска", () ->{
            $(".menu-search-button").click();
        });
        step("Ввести 'BCAA' и нажать на кнопку найти", () ->{
            $("#popup-title-search-input").setValue("BCAA").pressEnter();
        });

        step("В результатах поиска отображается текст: BCAA ", () -> {
           $(".b-search-page-block").shouldHave(Condition.text("BCAA"));
        });
    }
}
