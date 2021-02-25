package com.anz.demo.dto;

public enum AccountType {
    CURRENT_AC("Current Account"), SAVING_AC("Saving Account");

    private String name;

    AccountType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
