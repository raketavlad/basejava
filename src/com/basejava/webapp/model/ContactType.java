package com.basejava.webapp.model;

public enum ContactType {
    PHONE_NUMBER("Номер телефона"),
    SKYPE("Skype"),
    EMAIL("Почта"),
    LINKEDLN("Профиль linkedln"),
    GITHUB("Профиль GitHub"),
    STACKOVERFLOW("Профиль StackOverflow"),
    HOME_PAGE("Домашняя страница");

    private String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }



}
