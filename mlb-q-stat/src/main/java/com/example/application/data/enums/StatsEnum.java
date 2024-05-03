package com.example.application.data.enums;

import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * READ ONLY INTERFACE
 * @param <R> The record this enum is about , Hitter, Pitcher, etc
 */
public interface StatsEnum<R> {
    public String humanName();

    public String db();

    public Function<R, String> accessor();

    public SortingStrategy<R> sorter();

    /**
     * if arg1 is less, this returns negative
     */
    static SortingStrategy<String> IntSorter = (String arg1, String arg2) -> {
        int a1;
        try {
            a1 = Integer.parseInt(arg1);
        } catch (Exception e) {
            return -1;
        }
        int a2;
        try {
            a2 = Integer.parseInt(arg2);
        } catch (Exception e) {
            return 1;
        }
        return a1 - a2;
    };
    static SortingStrategy<String> DoubleSorter = (String arg1, String arg2) -> {
        double a1;
        try {
            a1 = Double.parseDouble(arg1);
        } catch (Exception e) {
            return -1;
        }
        double a2;
        try {
            a2 = Double.parseDouble(arg2);
        } catch (Exception e) {
            return 1;
        }
        return (int) Math.signum(a1 - a2);
    };
}


