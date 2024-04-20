package com.example.application.views.players;

import com.example.application.data.PlayerService;
import com.example.application.data.entity.Hitter;
import com.example.application.data.entity.Pitcher;
import com.example.application.data.enums.PitcherStat;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.data.renderer.LitRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;


@PageTitle("Pitchers")
@Route(value = "pitchers", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@Uses(Icon.class)
public class PitchersView extends GenericPlayersView {
    public PitchersView(PlayerService playerService) {
        super(
                Pitcher.class,
                PitcherStat.class,
                playerService.getAllPitchers(),
                LitRenderer
                        .<Pitcher>of("<a href=\"/player/${item.id}\">${item.name}</a>")
                        .withProperty("name", Pitcher::name).withProperty("id", Pitcher::id));
    }
}
