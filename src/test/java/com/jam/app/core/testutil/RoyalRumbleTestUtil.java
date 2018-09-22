package com.jam.app.core.testutil;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RoyalRumbleTestUtil {
    public static List<String> getArbitraryLargeList() {
        return IntStream.range(0, 100).mapToObj(i -> UUID.randomUUID().toString()).collect(Collectors.toList());
    }
}
