package guru.qa.tests.positive_tests;

import guru.qa.tests.TestBase;
import guru.qa.tests.helper.Attach;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

@Owner("EphimSh")
@Feature("Страница каталога товаров")
@Tag("catalog")
@Link(value = "primekraft", url = "https://primekraft.ru")
public class CatalogTest extends TestBase {

    @BeforeEach
    void goToCatalogPage() {
        step("Перейти на страницу каталога товаров", () -> {
            open("/catalog/");
        });
    }

    @Test
    @Disabled
    @Story("Видимость flash-sale товара")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Flash-sale товар присутствует на странице каталога")
    @Tags({
            @Tag("catalog"),
            @Tag("smoke")
    })
    void flashSaleItemCheck() {
        step("Flash-sale товар присутствует на странице каталога товаров", () -> {
            $$("#flashsale_wrapper").find(text("Товар дня")).should(exist);
        });
    }

    @Story("Кнопки бокового меню каталога товаров")
    @Severity(SeverityLevel.CRITICAL)
    @Tags({
            @Tag("catalog")
    })
    @ValueSource(strings = {
            "НОВИНКИ", "РАСПРОДАЖА", "Протеин", "Креатин", "Аминокислоты"
    })
    @ParameterizedTest(name = "Меню каталога содержит кнопку {0}")
    void catalogMenuItemCheck(String categoryItem) {
        step("Нажать на кнопку категории: " + categoryItem, () -> {
            $$(".b-sidebar-nav li").find(text(categoryItem)).click();
        });
        step("После перехода на страницу выбранной категории page-title содержит: " + categoryItem, () -> {
            $("#pagetitle").shouldHave(text(categoryItem));
        });
    }
    @Disabled
    @Story("Фильтр товаров по бренду")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Filter-box виден и функционирует")
    @Tags({
            @Tag("catalog"),
            @Tag("smoke")
    })
    @CsvSource(value = {
            "PRIMEKRAFT | CREATINE MONOHYDRATE",
            "PRIMEBAR | PRIMEBAR",
            "PLANTAGO | Plantago",
            "WOWBAR | WOWBAR"
    }, delimiter = '|')
    @ParameterizedTest(name = "Фильтр по кнопке {0} показывает товар {1}")
    void filterCheckBoxButtonTest(String checkbox, String item) {
        step("Нажать на чекбокс: " + checkbox, () -> {
            $$(".bx-filter-block .checkbox").find(text(checkbox)).click();
        });
        step("Отфильтрованный каталог товаров содержит название бренда: " + item, () -> {
            $(".catalog-section [data-entity=items-row]").should(text(item));
            Attach.attachScreenshot("Фильтр:_" + checkbox + "_Товар:_" + item);
        });
    }

    @Test
    @Story("Фильтр товаров по вкусу")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Filter-box виден и функционирует")
    void itemAmountPopUpWindowCheck() {
        step("Нажать на чекбокс фильтра по вкусу: Апельсин", () -> {
            $$(".bx-filter-block").find(text("Вкус")).find(withText("Апельсин")).click();
        });
        step("pop-up окно отображает количество отфильтрованых товаров", () -> {
            $("#modef").should(visible);
            Attach.attachScreenshot("pop-up_окошко_на_месте");
        });
        step("Отфильтрованный каталог товаров отображает только товары со вкусом: Апельсин", () -> {
            $("[data-entity=items-row] article").hover().
                    $$(".product-cat-properties dt")
                    .find(text("Вкус")).sibling(0).shouldHave(text("Апельсин"));
        });
    }
}
