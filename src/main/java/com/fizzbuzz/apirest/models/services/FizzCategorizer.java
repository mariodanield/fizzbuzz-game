package com.fizzbuzz.apirest.models.services;

import java.lang.String;

public class FizzCategorizer extends Categorizer {
    @Override
    public String checkRule(int number) {
        if(number % 3 == 0)
            return "Fizz";

        return Next.checkRule(number);
    }
}