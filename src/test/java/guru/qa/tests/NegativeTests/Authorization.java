package guru.qa.tests.NegativeTests;

import guru.qa.tests.TestBase;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;



public class Authorization extends TestBase {

    @Test
    @Tag("smoke")
    @Feature("Авторизация")
    @Story("Попытка авторизации с неправильными данными")
    @Owner("EphimSh")
    @Severity(SeverityLevel.CRITICAL)
    @Link(value = "primekraft", url = "https://primekraft.ru")
    @DisplayName("Авторизация с неправильными данными")
    void wrongCredAuthorization(){
        step("Перейти на главную страницу", () ->{
            open("https://primekraft.ru");
        });
        step("Нажать на кнопку 'Войти'", () ->{
            $(".l-head [href*=auth]").should(visible).click();
        });
        step("Ввести неверные данные", () ->{
            $("[name=phone]").setValue("fake@mail.com");
            $("[name=password]").setValue("fakepassword");
            $("[data-auth-title='Войти']").click();
        });
        step("В окошке авторизации есть сообщение об успешном входе", () ->{
            $(".fancybox-content").shouldHave(text("Вы успешно авторизированы"));
        });
    }


}
