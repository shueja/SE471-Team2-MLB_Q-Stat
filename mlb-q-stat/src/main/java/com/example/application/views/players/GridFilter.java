package com.example.application.views.players;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class GridFilter <H>  {
    public final H target;
    public final Comparator<H> filter;
    public final Predicate<H> predicate;
    public GridFilter(Comparator<H> filter, H target){
        this.target = target;
        this.filter = filter;
        this.predicate = (item->filter.compare(item, target)==0);
    }

    public List<H> apply(List<H> original) {
        return original.stream().filter(predicate).toList();
    }
}
