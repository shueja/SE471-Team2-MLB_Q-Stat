package com.example.application.data.enums;

import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.Function;

public interface StatsEnum<R> {
    public String humanName();
    public String db();
    public Function<R, String> accessor();
    public Comparator<R> sorter();
}
