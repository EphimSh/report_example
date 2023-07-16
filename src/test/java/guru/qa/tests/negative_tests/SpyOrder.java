package guru.qa.tests.negative_tests;

import guru.qa.tests.TestBase;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
@Owner("EphimSh")
@Feature("Отслеживание заказа")
@Link(value = "primekraft", url = "https://primekraft.ru")
@Tag("smoke")
public class SpyOrder extends TestBase {
    @Test
    @Story("Отслеживания статуса чужого заказа")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Ввод случайного числа в поле ввода order_id")
    void spyOnOtherCustomersOrderStatus(){
        step("Перейти на главную страницу", () ->{
            open("https://primekraft.ru/personal/order/spy");
        });
        step("Ввести 124 в поле для ввода id заказа", () ->{
            $("[name=order_id]").setValue("124").pressEnter();
        });
        step("Можно отследить статус чужого заказа", () ->{
            $("div.rsform").shouldHave(text("Заказ №124"));
        });

    }
}
