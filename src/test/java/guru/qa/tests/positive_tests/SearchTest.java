package guru.qa.tests.positive_tests;

import com.codeborne.selenide.Condition;
import guru.qa.tests.TestBase;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class SearchTest extends TestBase {



    @Test
    @Tags({
            @Tag("search"),
            @Tag("smoke")
    })
    @Feature("Поиск")
    @Story("Успешный поиск товара")
    @Owner("EphimSh")
    @Severity(SeverityLevel.CRITICAL)
    @Link(value = "primekraft", url = "https://primekraft.ru")
    @DisplayName("Поиск товара 'BCAA'")
    void searchTest() {
        step("Перейти на главную страницу", () ->{
            open("https://primekraft.ru");
        });
        step("Нажать на кнопку поиска", () ->{
            $(".menu-search-button").click();
        });
        step("Ввести 'BCAA' и нажать на кнопку enter", () ->{
            $("#popup-title-search-input").setValue("BCAA").pressEnter();
        });

        step("В результатах поиска отображается текст: BCAA ", () -> {
           $(".b-search-page-block").shouldHave(Condition.text("BCAA"));
        });
    }
}
