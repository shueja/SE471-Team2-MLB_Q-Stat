package com.example.application.views.teams;

import com.example.application.data.TeamService;
import com.example.application.data.entity.Team;
import com.example.application.data.enums.TeamStat;
import com.example.application.views.MainLayout;
import com.example.application.views.players.GenericPlayersView;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.data.renderer.LitRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@PageTitle("Teams")
@Route(value = "teams", layout = MainLayout.class)
@Uses(Icon.class)
public class TeamsView extends GenericPlayersView {

    public TeamsView(TeamService teamService) {
        super(
                Team.class,
                TeamStat.class,
                teamService.getAllTeams(),
                LitRenderer
                        .<Team>of("<a href=\"/team/${item.acronym}\">${item.name}</a>")
                        .withProperty("name", Team::name).withProperty("acronym", Team::acronym)
        );

    }
}
