package ru.netology.test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.pages.MainPage;
import ru.netology.pages.PayPage;

import static com.codeborne.selenide.Selenide.closeWindow;
import static com.codeborne.selenide.Selenide.open;

public class PaymentTest {
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
        mainPage.payPage();
        var cardInfo = DataHelper.generatedDataWithApprovedCard();
        var payPage = new PayPage();
        payPage.insertPayCardData(cardInfo);
        payPage.successFromBank();
    }

//    @Test
//    void shouldDeclineTransactionWithPaymentCard(){
//        mainPage.payPage();
//        var cardInfo = DataHelper.generatedDataWithDeclinedCard();
//        var payPage = new PayPage();
//        payPage.insertPayCardData(cardInfo);
//        payPage.errorRejectedFromBank();
//    }

    @Test
    void shouldDeclineTransactionWithRandomNumberCard() {
        mainPage.payPage();
        var cardInfo = DataHelper.generatedDataWithRandomCard();
        var payPage = new PayPage();
        payPage.insertPayCardData(cardInfo);
        payPage.errorRejectedFromBank();
    }

    @Test
    void shouldShowAttentionTextEmptyCardNumberField(){
        mainPage.payPage();
        var cardInfo = DataHelper.generatedDataWithApprovedCard();
        var payPage = new PayPage();
        payPage.insertPayCardWithEmptyCardNumber(cardInfo);
        payPage.attentionUnderNumberCard("Неверный формат");

    }

    @Test
    void shouldShowAttentionTextEmptyMonthField(){
        mainPage.payPage();
        var cardInfo = DataHelper.generatedDataWithApprovedCard();
        var payPage = new PayPage();
        payPage.insertPayCardWithEmptyMonth(cardInfo);
        payPage.attentionUnderMonth("Неверный формат");
    }

    @Test
    void shouldShowAttentionTextEmptyYearField(){
        mainPage.payPage();
        var cardInfo = DataHelper.generatedDataWithApprovedCard();
        var payPage = new PayPage();
        payPage.insertPayCardEmptyYear(cardInfo);
        payPage.attentionUnderYear("Неверный формат");
    }

    @Test
    void shouldShowAttentionTextEmptyOwnerField(){
        mainPage.payPage();
        var cardInfo = DataHelper.generatedDataWithApprovedCard();
        var payPage = new PayPage();
        payPage.insertPayCardEmptyOwner(cardInfo);
        payPage.attentionUnderOwner("Поле обязательно для заполнения");
    }

    @Test
    void shouldShowAttentionTextEmptyCVCField(){
        mainPage.payPage();
        var cardInfo = DataHelper.generatedDataWithApprovedCard();
        var payPage = new PayPage();
        payPage.insertPayCardEmptyCVC(cardInfo);
        payPage.attentionUnderCVC("Неверный формат");
    }



}
