package ru.netology.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;

public class CreditPage {
    private SelenideElement heading = Selenide.$x("//h3[text()='Кредит по данным карты']");
    private SelenideElement cardNumber = Selenide.$x("//span[text()'Номер карты']/following-sibling::span/input");
    private SelenideElement month = Selenide.$x("//span[text()'Месяц']/following-sibling::span/input");
    private SelenideElement year = Selenide.$x("//span[text()='Год']/following-sibling::span/input");
    private SelenideElement owner = Selenide.$x("//span[text()'Владелец']/following-sibling::span/input");
    private SelenideElement cvc = Selenide.$x("//span[text()'CVC/CVV']/following-sibling::span/input");
    private SelenideElement proceedButton = Selenide.$x("//span[text()='Продолжить']");
    private SelenideElement errorRejected = Selenide.$x("//div[text()='Ошибка! Банк отказал в проведении операции.']]");
    private SelenideElement success = Selenide.$x("//div[text()='Успешно']");

    public CreditPage(){
        heading.shouldBe(visible);
    }

    public void insertPayCreditCardData(DataHelper.CardInfo cardInfo){
        cardNumber.setValue(cardInfo.getCardNumber());
        month.setValue(cardInfo.getMonth());
        year.setValue(cardInfo.getYear());
        owner.setValue(cardInfo.getCardOwner());
        cvc.setValue(cardInfo.getCvc());
        proceedButton.click();
    }
    public void errorRejectedFromBank(){
        errorRejected.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void successFromBank(){
        success.shouldBe(visible, Duration.ofSeconds(15));
    }

}
