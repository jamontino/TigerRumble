package com.jam.app.core.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class RoyalNameTest {

    @Test
    public void test_toString() {
        RoyalName name = new RoyalName("Jam", "I", 1);
        assertEquals("Jam I", name.toString());
    }
}