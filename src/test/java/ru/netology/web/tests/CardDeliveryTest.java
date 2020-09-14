package ru.netology.web.tests;
import static ru.netology.web.data.DataGenerator.*;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.web.data.DataGenerator;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryTest {
    public String city = getCity();
    public String currentDate = getDate(0);
    public String date = getDate(3);
    public String newDate = getDate(10);
    public String name = getName();
    public String phone = getPhone();

    SelenideElement form = $("#root");

    @BeforeEach
    void setUpAll() {
        open("http://localhost:9999");
    }

    @Test
    void shouldPlanAndReplanMeeting() {
        form.$("[data-test-id='city'] input").setValue(city);
        form.$("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue(name);
        form.$("[data-test-id='phone'] input").setValue(phone);
        form.$("[data-test-id='agreement']").click();
        $(byText("Запланировать")).click();
        $("[data-test-id='success-notification'] ").shouldHave(exactText("Успешно! Встреча успешно запланирована на " + date));

        form.$("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id='date'] input").setValue(newDate);
        $(byText("Запланировать")).click();

        $(byText("Перепланировать")).click();
        $(".notification_visible").shouldHave(exactText("Успешно! Встреча успешно запланирована на " + newDate));
    }

    @Test
    void shouldGetErrorMessageIfSendEmptyForm() {
        $(byText("Запланировать")).click();
        $(byText("Поле обязательно для заполнения")).shouldBe(visible);
    }

    @Test
    void shouldGetErrorMessageIfWrongCity() {
        form.$("[data-test-id='city'] input").setValue("Череповец");
        form.$("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue(name);
        form.$("[data-test-id='phone'] input").setValue(phone);
        form.$("[data-test-id='agreement']").click();
        $(byText("Запланировать")).click();
        $(byText("Доставка в выбранный город недоступна")).shouldBe(visible);
    }

    @Test
    void shouldGetErrorMessageIfWrongDate() {
        form.$("[data-test-id='city'] input").setValue(city);
        form.$("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id='date'] input").setValue(currentDate);
        form.$("[data-test-id='name'] input").setValue(name);
        form.$("[data-test-id='phone'] input").setValue(phone);
        form.$("[data-test-id='agreement']").click();
        $(byText("Запланировать")).click();
        $(byText("Заказ на выбранную дату невозможен")).shouldBe(visible);
    }


    @Test
    void shouldGetErrorMessageIfWrongName() {
        form.$("[data-test-id='city'] input").setValue(city);
        form.$("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Ivanov Ivan");
        form.$("[data-test-id='phone'] input").setValue(phone);
        form.$("[data-test-id='agreement']").click();
        $(byText("Запланировать")).click();
        $(byText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.")).shouldBe(visible);
    }

    @Test
    void shouldGetErrorMessageIfNotMarkCheckbox() {
        form.$("[data-test-id='city'] input").setValue(city);
        form.$("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue(name);
        form.$("[data-test-id='phone'] input").setValue(phone);
        $(byText("Запланировать")).click();
        $("[data-test-id='agreement'] .checkbox__text").shouldHave(text("Я соглашаюсь с условиями обработки и использования моих персональных данных"));
    }
}
