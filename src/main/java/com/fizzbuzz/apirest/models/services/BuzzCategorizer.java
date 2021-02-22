package com.fizzbuzz.apirest.models.services;

import java.lang.String;

public class BuzzCategorizer extends Categorizer {
    @Override
    public String checkRule(int number) {
        if(number % 5 == 0)
            return "Buzz";

        return Next.checkRule(number);
    }
}