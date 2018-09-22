package com.jam.app.core;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        ArrayList<String> strings = Lists.newArrayList(args);
        RoyalRumble royalRumble = new RoyalRumble();
        List<String> sortedList = royalRumble.getSortedList(strings);

        for (String s : sortedList) {
            System.out.println(s); // Display the string.
        }

    }
}
