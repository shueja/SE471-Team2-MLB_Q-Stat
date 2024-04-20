package com.example.application.views.players;

import com.example.application.data.PlayerService;
import com.example.application.data.enums.StatsEnum;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.LitRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;

import java.util.List;


@PageTitle("Hitters")
@Route(value = "hitters", layout = MainLayout.class)
@Uses(Icon.class)
public class GenericPlayersView extends Div {

    public <
            H,
            E extends StatsEnum<H>
            > GenericPlayersView(
                    Class<H> recordClass,
                    Class<E> enumClass,
                    List<H> list,
                    LitRenderer<H> nameColumn) {
        setSizeFull();
        addClassNames("players-view");
        createGrid(recordClass, enumClass, list, nameColumn);


    }

    private <H, E extends StatsEnum<H>>
    void createGrid(
            Class<H> recordClass, Class<E> enumClass, List<H> list,
    LitRenderer<H> nameColumn) {
        Grid<H> grid = new Grid<>();
        grid.addColumn(
            nameColumn
        ).setHeader("Name").setSortable(true).setAutoWidth(true);
        for (E stat : enumClass.getEnumConstants()) {
            Span headerComponent = new Span();
            headerComponent.setText(stat.db());
            headerComponent.getElement().setProperty("title", stat.humanName());
            grid.addColumn(stat.accessor()).setHeader(headerComponent).setAutoWidth(true);
        }
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.addClassNames(LumoUtility.Border.TOP, LumoUtility.BorderColor.CONTRAST_10);
        grid.setItems(
                list
        );
        VerticalLayout layout = new VerticalLayout(grid);
        layout.setSizeFull();
        layout.setPadding(false);
        layout.setSpacing(false);
        add(layout);
    }
}
