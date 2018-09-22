package com.jam.app.core.converter;

import com.jam.app.core.model.RoyalName;

import java.text.NumberFormat;
import java.text.ParseException;

public class RoyalNameConverter {
    private static final int MAX_ALLOWED_ORDINAL = 50;
    private static final int MAX_ALLOWED_NAME_LENGTH = 20;
    private static final String NAME_REGEX = "\\s+";
    private static final int ALLOWED_PARAMETER_LENGTH = 2;

    //TODO: this converter uses "romannumerals4j" library. Will create my own implementation if time permits
    private NumberFormat formatter;
    private String input;

    public RoyalNameConverter(NumberFormat formatter) {
        this.formatter = formatter;
    }

    public RoyalName convert(String rawName) {
        input = rawName;
        String[] splits = getParsedInput();
        String romanNumeralOrdinal =  getRomanNumeralOrdinal(splits);

        return new RoyalName(getName(splits), romanNumeralOrdinal, getOrdinal(romanNumeralOrdinal));
    }

    private String[] getParsedInput() {
        String[] parameter = input.split(NAME_REGEX);

        if (parameter.length != ALLOWED_PARAMETER_LENGTH) {
            throw new RuntimeException(String.format("Failed to parse [%s]. Passed name should contain %s strings separated by a space", input, ALLOWED_PARAMETER_LENGTH));
        }
        return parameter;
    }

    private String getName(String[] splits) {
        String name = splits[0];

        if (!isNameLengthValid(name)) {
            throw new RuntimeException(String.format("Failed to parse [%s]. Name length should be between 1 and %s characters.", input, MAX_ALLOWED_NAME_LENGTH));
        }

        if (!isNameValid(name)) {
            throw new RuntimeException(String.format("Failed to parse [%s]. Name should start with an uppercase letter followed by lowercase letters", input, MAX_ALLOWED_NAME_LENGTH));
        }

        return name;
    }

    private boolean isNameLengthValid(String name) {
        return name.length() > 0 && name.length() <= MAX_ALLOWED_NAME_LENGTH;
    }

    private boolean isNameValid(String name) {
        char firstChar = name.charAt(0);
        boolean isValid = Character.isLetter(firstChar) && Character.isUpperCase(firstChar);

        for (char c : name.substring(1).toCharArray()) {
            isValid = isValid && Character.isLetter(c) && Character.isLowerCase(c);
        }

        return isValid;
    }

    private String getRomanNumeralOrdinal(String[] splits) {
        return splits[1];
    }

    private int getOrdinal(String romanNumeralOrdinal) {
        int ordinal;

        try {
            ordinal = formatter.parse(romanNumeralOrdinal).intValue();
        } catch (ParseException e) {
            throw new RuntimeException(String.format("Failed to parse [%s]. %s", input, e.getMessage()), e);
        }

        if (ordinal > MAX_ALLOWED_ORDINAL) {
            throw new RuntimeException(String.format("Failed to parse [%s]. Max Ordinal allowed is %s. Input was %s", input, MAX_ALLOWED_ORDINAL, ordinal));
        }

        return ordinal;
    }
}
