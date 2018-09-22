package com.jam.app.core.converter;

import com.jam.app.core.model.RoyalName;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RoyalNameComparatorTest {

    private RoyalNameComparator testSubject;

    @Before
    public void setUp() {
        testSubject = new RoyalNameComparator();
    }

    @Test
    public void compare_Name() {
        RoyalName name1 = new RoyalName("Albert", "I", 1);
        RoyalName name2 = new RoyalName("Bernard", "I", 1);
        assertEquals(-1, testSubject.compare(name1, name2));
    }

    @Test
    public void compare_Numeral() {
        RoyalName name1 = new RoyalName("Albert", "I", 1);
        RoyalName name2 = new RoyalName("Albert", "II", 2);
        assertEquals(-1, testSubject.compare(name1, name2));
    }
}