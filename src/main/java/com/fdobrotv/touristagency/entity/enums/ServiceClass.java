package com.fdobrotv.touristagency.entity.enums;


public enum ServiceClass {
    ONE("*"),
    TWO("**"),
    THREE("***"),
    FOUR("****"),
    FIVE("*****");

    private final String stars;

    ServiceClass(String stars) {
        this.stars = stars;
    }

    public String getStars() {
        return stars;
    }

    @Override
    public String toString() {
        return this.stars;
    }

    public static ServiceClass fromValue(String value) {
        for (ServiceClass b : ServiceClass.values()) {
            if (b.name().equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}
