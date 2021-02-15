package ru.netology.dielvery;


import com.github.javafaker.Faker;

import java.util.Locale;

public class DataGenerator {
    private DataGenerator() {}

    public static class Request{
        private Request() {}

    public static RequestForDelivery cardDelivery(String locale) {
            Faker faker = new Faker(new Locale("ru"));
            return new RequestForDelivery(
                    faker.name().fullName(),
                    faker.address().city(),
                    faker.phoneNumber().phoneNumber()
            );

    }

    }
}
