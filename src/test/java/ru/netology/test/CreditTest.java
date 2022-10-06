package ru.netology.test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.pages.CreditPage;
import ru.netology.pages.MainPage;
import ru.netology.pages.PayPage;

import static com.codeborne.selenide.Selenide.closeWindow;
import static com.codeborne.selenide.Selenide.open;

public class CreditTest {
    MainPage mainPage = open("http://localhost:8080/", MainPage.class);

    @BeforeEach
    void setUP() {
        Configuration.holdBrowserOpen = true;
    }

    @AfterEach
    void tearDown() {
        closeWindow();
    }

    @Test
    void shouldSuccessTransactionWithPaymentCard() {
        mainPage.creditPage();
        var cardInfo = DataHelper.generatedDataWithApprovedCard();
        var creditPage = new CreditPage();
        creditPage.insertPayCreditCardData(cardInfo);
        creditPage.successFromBank();
    }
}
