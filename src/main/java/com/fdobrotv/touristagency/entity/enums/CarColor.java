package com.fdobrotv.touristagency.entity.enums;

public enum CarColor {
    white,
    black,
    brown,
    yellow,
    red,
    blue,
    silver;

    public static CarColor fromValue(String value) {
        for (CarColor b : CarColor.values()) {
            if (b.name().equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}
