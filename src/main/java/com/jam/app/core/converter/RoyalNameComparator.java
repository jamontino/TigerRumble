package com.jam.app.core.converter;

import com.jam.app.core.model.RoyalName;

import java.util.Comparator;

public class RoyalNameComparator implements Comparator<RoyalName> {
    @Override
    public int compare(RoyalName o1, RoyalName o2) {
        int i = o1.getName().compareTo(o2.getName());
        if (i==0){
            return o1.getOrdinal() < o2.getOrdinal() ? -1 : 1;
        }
        return i;
    }
}
