package com.example.application.views.players;

import com.example.application.data.enums.StatsEnum;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class FilterPanel<E extends StatsEnum<?>> extends HorizontalLayout {

    public final Runnable clearFilter;

    public final Button clear;
    public final String emptyFilterText = "Click a statistic to filter based on it.";
    public Span label = new Span(emptyFilterText);
    public FilterPanel(Runnable clearFilter) {
        this.clearFilter = clearFilter;
        add(label);
        clear = new Button("Clear Filter");
        clear.addClickListener((e)->{
            label.setText(emptyFilterText);
            clearFilter.run();
        });
        add(clear);
    }

    public void setFilterTarget(E target) {
        label.setText(String.format("Filter: %s (%s)", target.db(), target.humanName()));
    }
}
