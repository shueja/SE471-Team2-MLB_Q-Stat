package com.example.application.views.graph;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.littemplate.LitTemplate;

import java.util.ArrayList;

@Tag("mlb-graph")
@JsModule("./src/graph.ts")
@NpmPackage(value = "d3", version = "7.8.5")
@NpmPackage(value= "@types/d3", version="7.4.3")
public class Graph extends LitTemplate {

    /**
     * Creates the hello world template.
     */
    public Graph() {

        getElement().setProperty("points", "[]");
    }

    public void setPoints(double[][] points) {
        ArrayList<String> pointStrings = new ArrayList<>();
        for (double[] point : points) {
            pointStrings.add(String.format("[%f,%f]", point[0], point[1]));
        }
        String pointArrStr = pointStrings.toString();
        System.out.println(pointArrStr);
        getElement().setProperty("points", pointArrStr);
    }
}