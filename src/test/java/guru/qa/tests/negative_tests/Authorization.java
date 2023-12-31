package guru.qa.tests.negative_tests;

import guru.qa.tests.TestBase;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

@Owner("EphimSh")
@Feature("Авторизация")
@Link(value = "primekraft", url = "https://primekraft.ru")
@Tag("smoke")
public class Authorization extends TestBase {

    @Test
    @Story("Попытка авторизации с неправильными данными")
    @Severity(SeverityLevel.CRITICAL)
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
        sleep(2000);
        step("В окошке авторизации есть сообщение об ошибке", () ->{
            $(".fancybox-content").shouldHave((text("Неверный логин или пароль")));
        });
    }


}
