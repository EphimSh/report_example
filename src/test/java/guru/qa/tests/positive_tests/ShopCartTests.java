package guru.qa.tests.positive_tests;

import guru.qa.tests.TestBase;
import guru.qa.tests.helper.Attach;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class ShopCartTests extends TestBase {
    @BeforeEach
    void setPreconditions(){
        step("go to primekraft.ru", () ->{
            open("https://primekraft.ru");
        });
    }
    @Test
    @Tags({
            @Tag("shop-cart"),
            @Tag("smoke")
    })
    @Feature("Корзина покупателя")
    @Story("добавление и удаление товара")
    @Owner("EphimSh")
    @Severity(SeverityLevel.CRITICAL)
    @Link(value = "primekraft", url = "https://primekraft.ru")
    @DisplayName("Успешное добавление и удаление товара")
    void addItemToShopCartThenRemoveIt(){
        step("Нажать на элемент каталога товаров 'Протеин' из выпадающего меню", () ->{
            $$("#mainMenu_LkGdQn div")
                    .find(text("Каталог")).hover()
                    .find(withText("Протеин"))
                    .click();
        });
        step("Нажать на карточку товара: 'Сывороточный протеин'", () ->{
            $$(".catalog-section [data-entity=item]")
                    .find(text("Сывороточный протеин"))
                    .find(".product-cat-title a").click();
            $(".product-detail").shouldHave(text("Сывороточный протеин"));
        });
        step("Добавить товар в корзину покупок", () ->{
            $(withText("В корзину")).click();
        });
        step("Добавленный товар должен иметь текст: 'Сывороточный протеин'", () ->{
            $(".l-head__head a[href*='/personal/cart/']").click();
            $("#basket-item-table").shouldHave(text("Сывороточный протеин"));
            Attach.attachScreenshot("item added");
        });
        step("Убрать добавленный товар из корзины покупок", () ->{
           $("#basket-item-table").find("[class*='item-remove']").should(visible).click();
        });
        step("В корзине нет товаров и присутствует текст: 'удален из корзины'", () ->{
            $("#basket-item-table").shouldHave(text("удален из корзины"));
        });
    }



}
