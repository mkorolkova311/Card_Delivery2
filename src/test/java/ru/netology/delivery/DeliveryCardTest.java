package ru.netology.delivery;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.dielvery.DataGenerator;
import ru.netology.dielvery.RequestForDelivery;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DeliveryCardTest {
   private final RequestForDelivery user = DataGenerator.Request.cardDelivery("ru");

    @Test
   public void shouldDeliveryCardTest() {
        open("http://localhost:7777/");
        $("[placeholder=\"Город\"]").setValue(user.getCity());
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        String inputDate = LocalDate.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.uuuu"));
        $("[data-test-id=date] input").setValue(inputDate);
        $("[data-test-id=name] input").setValue(user.getName());
        $("[data-test-id=phone] input").setValue(user.getPhone());
        $("[data-test-id=agreement]").click();
        $(withText("Запланировать")).click();
        $("[data-test-id=success-notification]").waitUntil(Condition.visible,15000);
        $(".notification__content").shouldHave(Condition.exactText("Встреча успешно запланирована на " + inputDate));
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        inputDate = LocalDate.now().plusDays(5).format(DateTimeFormatter.ofPattern("dd.MM.uuuu"));
        $("[data-test-id=date] input").setValue(inputDate);
        $(withText("Запланировать")).click();
        $("[data-test-id=replan-notification] button").waitUntil(Condition.visible,15000).click();
        $(".notification__content").shouldHave(Condition.exactText("Встреча успешно запланирована на " + inputDate));

    }

}