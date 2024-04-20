package com.example.application.views.player;

import com.example.application.data.PlayerService;
import com.example.application.data.enums.HitterStat;
import com.example.application.data.enums.PitcherStat;
import com.example.application.views.MainLayout;
import com.example.application.views.graph.Graph;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;

import java.util.function.BiConsumer;


@PageTitle("Player")
@Route(value = "player", layout = MainLayout.class)
@Uses(Icon.class)
public class PlayerView extends Div implements HasUrlParameter<Integer> {

    private PlayerService playerService;
    private int playerId;

    public PlayerView(PlayerService playerService) {
        this.playerService = playerService;
        setSizeFull();
        addClassNames("players-view");

    }

    private Component createHitterList(BiConsumer<HitterStat, Integer> updateGraph) {

        var playerOpt = playerService.getHitter(playerId);
        if (playerOpt.isPresent()) {
            var player = playerOpt.get();
            FormLayout form = new FormLayout();
            for (HitterStat stat : HitterStat.values()) {
                var title = new Span(String.valueOf(stat.accessor.apply(player)));
                form.addFormItem(title, stat.humanName).addClickListener(
                        (spanClickEvent -> updateGraph.accept(stat, playerId)));
            }
            return form;
        }

        return new Span();
    }

    private Component createPitcherList(BiConsumer<PitcherStat, Integer> updateGraph) {

        var playerOpt = playerService.getPitcher(playerId);
        if (playerOpt.isPresent()) {
            var player = playerOpt.get();
            FormLayout form = new FormLayout();
            for (PitcherStat stat : PitcherStat.values()) {
                var title = new Span(String.valueOf(stat.accessor.apply(player)));
                form.addFormItem(title, stat.humanName).addClickListener(
                        (spanClickEvent -> updateGraph.accept(stat, playerId)));
            }
            return form;
        }

        return new Span();
    }

    record GraphPoint(double x, double y) {}
    @Override
    public void setParameter(BeforeEvent beforeEvent, Integer integer) {
        playerId = integer.intValue();
        var hitterOpt = playerService.getHitter(playerId);
        var pitcherOpt = playerService.getPitcher(playerId);
        HorizontalLayout horiz = new HorizontalLayout();
        horiz.setSizeFull();
        VerticalLayout layout = new VerticalLayout();
        VerticalLayout graphPanel = new VerticalLayout();

        var graph = new Graph();
        var graphLabel = new Span("Graph shows: Batting Average");
        graphPanel.add(graph);
        graphPanel.add(graphLabel);
        horiz.add(graphPanel);
        horiz.add(layout);
        if (hitterOpt.isPresent() || pitcherOpt.isPresent()) {
            if (hitterOpt.isPresent()) {
                var player = hitterOpt.get();
                setTitle(player.name());
                setText("");
                layout.add(new H1(player.name()));
                graph.setPoints(playerService.getPlayerHistory(player.id(), HitterStat.battingAvg));
            }
            else {
                var player = pitcherOpt.get();
                setTitle(player.name());
                setText("");
                layout.add(new H1(player.name()));
                graph.setPoints(playerService.getPlayerHistory(player.id(), PitcherStat.battingAvg));
            }

            //layout.setSizeFull();
            layout.setPadding(false);
            layout.setSpacing(false);

        }
        if (hitterOpt.isPresent())
        {
            var comparison = playerService.CompareHitter(Integer.toString(playerId));

            if (comparison != null) {
                var similarList= new HorizontalLayout();
                if (!comparison.isEmpty()) {
                    similarList.add("Similar Overall To:");
                    for (var p: comparison) {
                        if ( p.id() != playerId) {
                            similarList.add(
                                    new Anchor(String.format("/player/%d", p.id()), p.name())
                            );
                        }
                    }
                    graphPanel.add(similarList);
                }

            }

            layout.add(createHitterList(
                    (HitterStat stat, Integer id)->{
                        graph.setPoints(playerService.getPlayerHistory(id, stat));
                        graphLabel.setText("Graph Shows: " + stat.humanName);

                    }));
        }
        if (pitcherOpt.isPresent())
        {
            var comparison = playerService.ComparePitcher(Integer.toString(playerId));

            if (comparison != null) {
                var similarList= new HorizontalLayout();
                if (!comparison.isEmpty()) {
                    similarList.add("Similar Overall To:");
                    for (var p: comparison) {
                        if ( p.id() != playerId) {
                            similarList.add(
                                    new Anchor(String.format("/player/%d", p.id()),p.name())
                            );
                        }
                    }
                    graphPanel.add(similarList);
                }

            }
            layout.add(createPitcherList(
                    (PitcherStat stat, Integer id)->{
                        graph.setPoints(playerService.getPlayerHistory(id, stat));
                        graphLabel.setText("Graph Shows: " + stat.humanName);

                    }));        }
        add(horiz);
    }
}
