package com.example.application.views.players;

import com.example.application.data.PlayerService;
import com.example.application.data.entity.Hitter;
import com.example.application.data.enums.HitterStat;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.data.renderer.LitRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@PageTitle("Hitters")
@Route(value = "hitters", layout = MainLayout.class)
@Uses(Icon.class)
public class HittersView extends GenericPlayersView {
    public HittersView(PlayerService playerService) {
        super(
                HitterStat.class,
                playerService.getAllHitters(),
                LitRenderer
                .<Hitter>of("<a href=\"/player/${item.id}\">${item.name}</a>")
                .withProperty("name", Hitter::name).withProperty("id", Hitter::id)
        );
    }
}
