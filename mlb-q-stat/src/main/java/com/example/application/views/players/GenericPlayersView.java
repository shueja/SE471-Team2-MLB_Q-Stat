package com.example.application.views.players;

import com.example.application.data.PlayerService;
import com.example.application.data.enums.StatsEnum;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.LitRenderer;
import com.vaadin.flow.function.ValueProvider;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;

import java.util.Comparator;
import java.util.List;


@PageTitle("Hitters")
@Route(value = "hitters", layout = MainLayout.class)
@Uses(Icon.class)
public class GenericPlayersView <
        H,
        E extends StatsEnum<H>
        > extends Div {

    private HorizontalLayout filterPanel;
    private Grid<H> grid;
    private List<H> fullList;
    public  GenericPlayersView(
                    Class<E> enumClass,
                    List<H> list,
                    LitRenderer<H> nameColumn) {
        setSizeFull();
        addClassNames("players-view");
        filterPanel = new FilterPanel<E>(this::clearFilter);
        add(filterPanel);
        createGrid(enumClass, list, nameColumn);


    }

    private
    void createGrid(
           Class<E> enumClass, List<H> list,
    LitRenderer<H> nameColumn) {
        fullList = list;
        grid = new Grid<>();
        grid.addColumn(
            nameColumn
        ).setHeader("Name").setSortable(true).setAutoWidth(true);
        for (E stat : enumClass.getEnumConstants()) {
            Span headerComponent = new Span();
            headerComponent.setText(stat.db());
            headerComponent.getElement().setProperty("title", stat.humanName());
            var accessor = stat.accessor();
            grid.addComponentColumn(row -> {
                Button button = new Button(accessor.apply(row));
                button.addClickListener(click ->
                        setFilter(stat.sorter(), row));
                button.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
                return button;
            })

                    .setHeader(headerComponent)
                    .setSortable(true).setComparator(stat.sorter()).setAutoWidth(true);
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

    private void clearFilter() {
        grid.setItems(fullList);
    }
    private void setFilter(Comparator<H> filter, H target) {
        grid.setItems(new GridFilter<H>(filter, target).apply(fullList));
//        The framework-intended way to do this is below but that doesn't actually implement the filter pattern as taught.
//        ListDataProvider<H> listDataProvider = (ListDataProvider<H>) grid.getDataProvider();
//        listDataProvider.setFilter(item -> {
//            return filter.compare(target, item) == 0;
//        });
    }
}
