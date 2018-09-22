package com.jam.app.core.converter;

import com.github.fracpete.romannumerals4j.RomanNumeralFormat;
import com.jam.app.core.model.RoyalName;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class RoyalNameConverterTest {

    private RoyalNameConverter testSubject;

    @Before
    public void setUp() {
        testSubject = new RoyalNameConverter(new RomanNumeralFormat());
    }

    @Test
    public void convert() {
        RoyalName expected = new RoyalName("Albert", "I", 1);
        RoyalName actual = testSubject.convert("Albert I");
        assertEquals(expected, actual);
    }

    @Test
    public void convert_WhenNullInput_ThenThrowError() {
        try {
            testSubject.convert(null);
            failAssert();
        } catch (Exception e) {
            //if you pass a null, you pay for it
        }
    }

    @Test //Each names[i] is a single string composed of 2 space-separated values: firstName and ordinal.
    public void convert_WhenInputContainsOnlyOneParameter_ThenThrowError() {
        String arbitraryInput = "AbcasdII";
        try {
            testSubject.convert(arbitraryInput);
            failAssert();
        } catch (RuntimeException e) {
            assertEquals(e.getMessage(), String.format("Failed to parse [AbcasdII]. Passed name should contain 2 strings separated by a space", arbitraryInput));
        }
    }

    @Test //Each names[i] is a single string composed of 2 space-separated values: firstName and ordinal.
    public void convert_WhenEmptyString_ThenThrowError() {
        String arbitraryInput = "";
        try {
            testSubject.convert(arbitraryInput);
            failAssert();
        } catch (RuntimeException e) {
            assertEquals(e.getMessage(), String.format("Failed to parse []. Passed name should contain 2 strings separated by a space", arbitraryInput));
        }
    }

    @Test //Each names[i] is a single string composed of 2 space-separated values: firstName and ordinal.
    public void convert_WhenInputContainsMoreThanTwoParameters_ThenThrowError() {
        String arbitraryInput = "Apple Banana III";
        try {
            testSubject.convert(arbitraryInput);
            failAssert();
        } catch (RuntimeException e) {
            assertEquals(String.format("Failed to parse [Apple Banana III]. Passed name should contain 2 strings separated by a space", arbitraryInput), e.getMessage());
        }
    }

    @Test //ordinal is a valid Roman numeral representing a number between 1 and 50, inclusive.
    public void convert_WhenOrdinalIsMoreThanAllowedMaximum_ThenThrowError() {
        try {
            testSubject.convert("Louie XCVI");
            failAssert();
        } catch (RuntimeException e) {
            assertEquals("Failed to parse [Louie XCVI]. Max Ordinal allowed is 50. Input was 96", e.getMessage());
        }
    }

    @Test //ordinal is a valid Roman numeral representing a number between 1 and 50, inclusive.
    public void convert_WhenOrdinalIsMissing_ThenThrowError() {
        try {
            testSubject.convert("Louie ");
            failAssert();
        } catch (RuntimeException e) {
            assertEquals("Failed to parse [Louie ]. Passed name should contain 2 strings separated by a space", e.getMessage());
        }
    }

    @Test //ordinal is a valid Roman numeral representing a number between 1 and 50, inclusive.
    public void convert_WhenNegativeOrdinal_ThenThrowError() {
        try {
            testSubject.convert("Louie -IV");
            failAssert();
        } catch (RuntimeException e) {
            assertEquals("Failed to parse [Louie -IV]. Unparseable number: \"-IV\"", e.getMessage());
        }
    }

    @Test //1 ≤ |firstName| ≤ 20
    public void convert_WhenNameExceedsCharacterLimit_ThenThrowError() {
        try {
            testSubject.convert("Abcdefghijklmnopqrstuvwxyz III");
            failAssert();
        } catch (RuntimeException e) {
            assertEquals("Failed to parse [Abcdefghijklmnopqrstuvwxyz III]. Name length should be between 1 and 20 characters.", e.getMessage());
        }
    }

    @Test //1 ≤ |firstName| ≤ 20
    public void convert_WhenNameIsMissing_ThenThrowError() {
        try {
            testSubject.convert(" III");
            failAssert();
        } catch (RuntimeException e) {
            assertEquals("Failed to parse [ III]. Name length should be between 1 and 20 characters.", e.getMessage());
        }
    }

    @Test
    //Each firstName starts with an uppercase letter ascii[A-Z] and its remaining characters are lowercase letters ascii[a-z].
    public void convert_WhenNameIsAllLowercase_ThenThrowError() {
        try {
            testSubject.convert("abc III");
            failAssert();
        } catch (RuntimeException e) {
            assertEquals("Failed to parse [abc III]. Name should start with an uppercase letter followed by lowercase letters", e.getMessage());
        }
    }

    @Test
    //Each firstName starts with an uppercase letter ascii[A-Z] and its remaining characters are lowercase letters ascii[a-z].
    public void convert_WhenNameContainsNonAlphabetCharacters_ThenThrowError() {
        try {
            testSubject.convert("Abc!@#$123 III");
            failAssert();
        } catch (RuntimeException e) {
            assertEquals("Failed to parse [Abc!@#$123 III]. Name should start with an uppercase letter followed by lowercase letters", e.getMessage());
        }
    }

    @Test
    //Each firstName starts with an uppercase letter ascii[A-Z] and its remaining characters are lowercase letters ascii[a-z].
    public void convert_WhenNameIsAllUpperCase_ThenThrowError() {
        try {
            testSubject.convert("ABC III");
            failAssert();
        } catch (RuntimeException e) {
            assertEquals("Failed to parse [ABC III]. Name should start with an uppercase letter followed by lowercase letters", e.getMessage());
        }
    }

    private void failAssert() {
        fail("Expected exception not thrown");
    }
}