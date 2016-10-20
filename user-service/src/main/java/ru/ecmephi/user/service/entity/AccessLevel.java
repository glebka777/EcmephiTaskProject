package ru.ecmephi.user.service.entity;

public enum AccessLevel {
    ADMINISTRATOR,
    USER;

    @Override
    public String toString() {
        String name = super.toString().toLowerCase();
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

}

