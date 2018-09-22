package com.jam.app.core;

import com.google.common.collect.Lists;
import com.jam.app.core.testutil.RoyalRumbleTestUtil;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


public class RoyalRumbleTest {

    private RoyalRumble testSubject;

    @Before
    public void setUp() {
        testSubject = new RoyalRumble();
    }

    @Test
    public void getSortedList_WhenValidInputPassed() {
        ArrayList<String> input = Lists.newArrayList("Morty I", "Rick II");
        assertEquals(input, testSubject.getSortedList(input));
    }

    @Test
    public void getSortedList_WhenNumbersNotOrdered() {
        List<String> input = Lists.newArrayList("Albert III", "Albert I");
        List<String> expected = Lists.newArrayList("Albert I", "Albert III");
        List<String> sortedList = testSubject.getSortedList(input);
        assertEquals(expected, sortedList);
    }

    @Test
    public void getSortedList_WhenNamesNotOrdered() {
        List<String> input = Lists.newArrayList("Xavier I", "Albert III");
        List<String> expected = Lists.newArrayList("Albert III", "Xavier I");
        List<String> sortedList = testSubject.getSortedList(input);
        assertEquals(expected, sortedList);
    }

    @Test
    public void getSortedList_WhenComplexInput() {
        List<String> input = Lists.newArrayList("Louis IX", "Louis VIII", "David II");
        List<String> expected = Lists.newArrayList("David II", "Louis VIII", "Louis IX");

        List<String> sortedList = testSubject.getSortedList(input);
        assertEquals(expected, sortedList);
    }


    @Test //constraint : 1 ≤ n ≤ 50
    public void getSortedList_WhenInvalidNumberOfElements_ThenThrowError() {
        try{
            testSubject.getSortedList(RoyalRumbleTestUtil.getArbitraryLargeList());
            fail("Expected exception not thrown!");
        }catch (Exception e){
            assertEquals("Number of elements exceeds allowed parameter of 50", e.getMessage());
        }
    }

    @Test //The elements in names are pairwise distinct.
    public void getSortedList_WhenDuplicateEntriesPresent_ThenThrowError() {
        List<String> input = Lists.newArrayList("Apple I", "Banana II", "Carrot III", "Apple I", "Banana II");
        try{
            testSubject.getSortedList(input);
            fail("Expected exception not thrown!");
        }catch (RuntimeException e){
            assertEquals("List contains duplicates! [Apple I, Banana II]", e.getMessage());
        }
    }
}