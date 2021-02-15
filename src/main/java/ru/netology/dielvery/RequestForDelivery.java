package ru.netology.dielvery;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor


public class RequestForDelivery {
    @Getter private final String name;
    @Getter private final String city;
    @Getter private final String phone;

}
