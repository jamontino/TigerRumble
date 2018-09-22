package com.jam.app.core.model;

import java.util.Objects;

public class RoyalName {

    private String name;
    private String romanNumeralOrdinal;
    private int ordinal;

    public RoyalName(String name, String romanNumeralOrdinal, int ordinal) {
        this.name = name;
        this.romanNumeralOrdinal = romanNumeralOrdinal;
        this.ordinal = ordinal;
    }

    public String getName() {
        return name;
    }

    public String getRomanNumeralOrdinal() {
        return romanNumeralOrdinal;
    }

    public int getOrdinal() {
        return ordinal;
    }

    @Override
    public String toString() {
        return String.format("%s %s", name, romanNumeralOrdinal);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoyalName royalName = (RoyalName) o;
        return ordinal == royalName.ordinal &&
                Objects.equals(name, royalName.name) &&
                Objects.equals(romanNumeralOrdinal, royalName.romanNumeralOrdinal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, romanNumeralOrdinal, ordinal);
    }
}
