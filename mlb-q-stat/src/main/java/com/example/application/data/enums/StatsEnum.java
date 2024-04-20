package com.example.application.data.enums;

public interface StatsEnum<R> {
    public String humanName();
    public String db();
    public com.vaadin.flow.function.ValueProvider<R, String> accessor();
}
