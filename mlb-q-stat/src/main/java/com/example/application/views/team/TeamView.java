package com.example.application.views.team;

import com.example.application.data.TeamService;
import com.example.application.data.enums.TeamStat;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@PageTitle("Team")
@Route(value = "team", layout = MainLayout.class)
@Uses(Icon.class)
public class TeamView extends Div implements HasUrlParameter<String> {

    private TeamService teamService;

    public TeamView(TeamService teamService) {
        this.teamService = teamService;
        setSizeFull();
        addClassNames("players-view");

    }

    private Component createList(String teamId) {

        var player = teamService.getTeams().get(teamId);
        if (player != null) {
            FormLayout form = new FormLayout();
            for (TeamStat stat : TeamStat.values()) {
                form.addFormItem(new Span(String.valueOf(stat.accessor().apply(player))), stat.humanName);
            }
            return form;
        }

        return new Span();
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, String acronym) {
        var player = teamService.getTeams().get(acronym);
        if (player != null) {
            setText("");
            VerticalLayout layout = new VerticalLayout();
            layout.add(new H1(player.name()));
            layout.add(createList(acronym));
            layout.setSizeFull();
            layout.setPadding(false);
            layout.setSpacing(false);
            add(layout);
        }
    }

}
