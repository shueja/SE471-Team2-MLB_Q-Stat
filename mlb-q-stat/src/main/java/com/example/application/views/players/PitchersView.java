package com.example.application.views.players;

import com.example.application.data.PlayerService;
import com.example.application.data.entity.Pitcher;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.theme.lumo.LumoUtility;
import jakarta.annotation.PostConstruct;
import com.vaadin.flow.data.renderer.LitRenderer;


@PageTitle("Pitchers")
@Route(value = "pitchers", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@Uses(Icon.class)
public class PitchersView extends Div {

    private Grid<Pitcher> grid;
    private PlayerService playerService;

    public PitchersView(PlayerService playerService) {
        this.playerService = playerService;

    }
    @PostConstruct
    private void build() {
        setSizeFull();
        addClassNames("players-view");
        var grid =createGrid();
        grid.setItems(
                playerService.getAllPitchers()
        );
        VerticalLayout layout = new VerticalLayout(grid);
        layout.setSizeFull();
        layout.setPadding(false);
        layout.setSpacing(false);
        add(layout);
    }

    private Grid<Pitcher> createGrid() {
        grid = new Grid<>();
        grid.addColumn(
                LitRenderer
                        .<Pitcher>of("<a href=\"/player/${item.id}\">${item.name}</a>")
                        .withProperty("name", Pitcher::name).withProperty("id", Pitcher::id)
        ).setHeader("Name").setSortable(true).setAutoWidth(true);
        for (PlayerService.PS stat : PlayerService.PS.values()) {
            Span headerComponent = new Span();
            headerComponent.setText(stat.db);
            headerComponent.getElement().setProperty("title", stat.humanName);
            grid.addColumn(stat.playerFunction).setHeader(headerComponent).setAutoWidth(true);
        }
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.addClassNames(LumoUtility.Border.TOP, LumoUtility.BorderColor.CONTRAST_10);

        return grid;
    }
}
