package ru.netology;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;


import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static org.openqa.selenium.Keys.BACK_SPACE;


public class SelenideTest {


    @BeforeEach
    void open() {
        Selenide.open("http://localhost:9999");
    }

    public String getDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DAY_OF_MONTH, +4);
        return dateFormat.format(calendar.getTime());
    }


    @Test
    void test1() {

        Configuration.holdBrowserOpen = true;

        $("[data-test-id=city] input").setValue("Псков");
        $("[data-test-id=date] .input__control").click();
        $("[data-test-id=date] .input__control").sendKeys(Keys.CONTROL + "A");
        $("[data-test-id=date] .input__control").sendKeys(BACK_SPACE);
        $("[data-test-id=date] .input__control").setValue(getDate());
        $("[data-test-id=name] .input__control").setValue("Иван Иванов");
        $("[data-test-id=phone] .input__control").setValue("+79997894564");
        $("[data-test-id=agreement]").click();
        $x("//button[contains(@class, 'button_view_extra')]").click();
        $("[data-test-id=notification]").shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id=notification] [class='notification__content']").shouldHave(exactText("Встреча успешно забронирована на " + getDate()));
    }
}
