package com.fizzbuzz.apirest.models.services;

import java.lang.String;

public class DefaultCategorizer extends Categorizer {
    @Override
    public String checkRule(int number) {
        return Integer.toString(number);
    }
}