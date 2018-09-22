package com.jam.app.core;

import org.junit.Test;

import static org.junit.Assert.*;

public class MainTest {

    @Test
    public void main() {
        String[] args = {
                "Louis IX",
                "Louis VIII",
                "David II"
        };

        Main.main(args);
    }
}