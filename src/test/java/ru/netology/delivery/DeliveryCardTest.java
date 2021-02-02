package ru.netology.delivery;

import com.codeborne.selenide.Condition;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DeliveryCardTest {
    private Faker faker;
    @BeforeEach
    void setUpAll() {
        faker = new Faker(new Locale("ru"));
    }

    @Test
   public void shouldDeliveryCardTest() {
        open("http://localhost:7777/");
        String city = faker.address().city();
        String name = faker.name().fullName();
        String phone = faker.phoneNumber().phoneNumber();
        $("[placeholder=\"Город\"]").setValue(city);
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        String inputDate = LocalDate.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.uuuu"));
        $("[data-test-id=date] input").setValue(inputDate);
        $("[data-test-id=name] input").setValue(name);
        $("[data-test-id=phone] input").setValue(phone);
        $("[data-test-id=agreement]").click();
        $(withText("Запланировать")).click();
        $("[data-test-id=success-notification]").waitUntil(Condition.visible,15000);
        $(".notification__content").shouldHave(Condition.exactText("Встреча успешно запланирована на " + inputDate));
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        inputDate = LocalDate.now().plusDays(5).format(DateTimeFormatter.ofPattern("dd.MM.uuuu"));
        $("[data-test-id=date] input").setValue(inputDate);
        $(withText("Запланировать")).click();
        $("[data-test-id=replan-notification]").waitUntil(Condition.visible,15000).click();
        $(".notification__content").shouldHave(Condition.exactText("Встреча успешно запланирована на " + inputDate));

    }

}