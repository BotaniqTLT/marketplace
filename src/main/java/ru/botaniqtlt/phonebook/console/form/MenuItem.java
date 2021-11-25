package ru.botaniqtlt.phonebook.console.form;

import java.util.function.Supplier;

public class MenuItem {
    String name;
    Supplier<FormResponse> action;

    public MenuItem(String name, Supplier<FormResponse> action) {
        this.name = name;
        this.action = action;

    }

    public static MenuItem menuItem(String name, Supplier<FormResponse> action) {
        return new MenuItem(name, action);
    }
}
