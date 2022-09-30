package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;

public class DataHelper {


    private static Faker faker = new Faker(new Locale("en"));

    @Value
    public static class CardInfo {
        String cardNumber;
        String month;
        String year;
        String cardOwner;
        String cvc;
    }
}
