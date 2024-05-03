package com.example.application.data.service;

import java.util.Map;

public abstract class AbstractRecordFactory<H> {
    public abstract H fromRow(Map<String, ?> row);
}
