package com.fizzbuzz.apirest.models.services;

public abstract class Categorizer {
	
    public Categorizer registerNextRule(Categorizer next) {
        Next = next;
        return Next;
    }

    protected Categorizer Next;

    public Categorizer getNext() {
        return Next;
    }

    private void setNext(Categorizer next) {
        Next = next;
    }

    public abstract String checkRule(int number);
}