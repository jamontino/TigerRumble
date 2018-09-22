package com.jam.app.core;

import com.github.fracpete.romannumerals4j.RomanNumeralFormat;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.jam.app.core.converter.RoyalNameComparator;
import com.jam.app.core.converter.RoyalNameConverter;
import com.jam.app.core.model.RoyalName;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;


public class RoyalRumble {
    public static final int MAX_ALLOWED_NAMES = 50;
    private final RoyalNameConverter nameConverter;
    private final RomanNumeralFormat formatter;
    private final RoyalNameComparator nameComparator = new RoyalNameComparator();

    public RoyalRumble() {
        formatter = new RomanNumeralFormat();
        nameConverter = new RoyalNameConverter(formatter);
    }

    public List<String> getSortedList(List<String> namesAsString) {
        validate(namesAsString);

        List<RoyalName> royalNames = convertToRoyalNames(namesAsString);
        royalNames.sort(nameComparator);

        return convertToStringList(royalNames);
    }

    private void validate(List<String> namesAsString) {
        if (namesAsString.size() > MAX_ALLOWED_NAMES){
            throw new RuntimeException(String.format("Number of elements exceeds allowed parameter of %s", MAX_ALLOWED_NAMES));
        }
        checkForDuplicates(namesAsString);
    }

    private void checkForDuplicates(List<String> namesAsString) {
        HashSet<String> checker = Sets.newHashSet();
        List<String> duplicates = Lists.newArrayList();
        for (String name : namesAsString) {
            if (!checker.add(name)){
                duplicates.add(name);
            }
        }

        if (!duplicates.isEmpty()){
            throw new RuntimeException("List contains duplicates! " + duplicates);
        }
    }

    private List<RoyalName> convertToRoyalNames(List<String> namesAsString) {
        return namesAsString.stream().map(nameConverter::convert).collect(Collectors.toList());
    }

    private List<String> convertToStringList(List<RoyalName> royalNames) {
        return royalNames.stream().map(RoyalName::toString).collect(Collectors.toList());
    }
}
