package guru.qa.tests.PositiveTests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import guru.qa.tests.helper.Attach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CatalogTest {

    @BeforeEach
    void goToCatalogPage() {
        open("https:/primekraft.ru/catalog/");
    }

    @Test
    @Tags({
            @Tag("catalog"),
            @Tag("smoke")
    })
    void flashSaleItemCheck() {
        $$("#flashsale_wrapper").find(text("Товар дня")).should(exist);
    }

    @Tags({
            @Tag("catalog")
    })
    @ValueSource(strings = {
            "НОВИНКИ", "РАСПРОДАЖА", "Протеин", "Креатин", "Аминокислоты",
            "Коллаген", "Витамины и добавки", "Жиросжигатели", "Наборы питания",
            "Предтренировочные комплексы", "Гейнер", "Изотоники", "Связки и суставы",
            "Протеиновые батончики", "Протеиновое печенье", "Протеиновые маффины",
            "Арахисовая паста", "Джемы без сахара", "Протеиновые чипсы",
            "Сахарозаменители и вкусовые добавки", "Спортивный инвентарь",
            "Одежда и аксессуары", "Подарочные сертификаты"
    })
    @ParameterizedTest(name = "Меню каталога содержит кнопку {0}")
    void catalogMenuItemCheck(String categoryItem) {
        $$(".b-sidebar-nav li").find(text(categoryItem)).click();
        $("#pagetitle").shouldHave(text(categoryItem));
    }

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
        $$(".bx-filter-block .checkbox").find(text(checkbox)).click();
        $(".catalog-section [data-entity=items-row]").should(text(item));
        Attach.attachScreenshot("Фильтр:_" + checkbox + "_Товар:_" + item);
    }


    @Test
    @Tag("catalog")
    void itemAmountPopUpWindowCheck(){
        $$(".bx-filter-block").find(text("Вкус")).find(withText("Апельсин")).click();
        $("#modef").should(visible);
        $("[data-entity=items-row] article").hover().
                $$(".product-cat-properties dt")
                .find(text("Вкус")).sibling(0).shouldHave(text("Апельсин"));

    }
}
