package com.fizzbuzz.apirest.models.services;

import java.lang.String;

public class FizzBuzzCategorizer extends Categorizer {
    public String checkRule(int number) {
        if(number % 15 == 0)
            return "FizzBuzz";

        return Next.checkRule(number);
    }
}