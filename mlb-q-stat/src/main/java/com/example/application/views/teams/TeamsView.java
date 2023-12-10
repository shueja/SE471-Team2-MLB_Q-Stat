package com.example.application.views.teams;

import com.example.application.data.TeamService;
import com.example.application.data.entity.Team;
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


@PageTitle("Teams")
@Route(value = "teams", layout = MainLayout.class)
@Uses(Icon.class)
public class TeamsView extends Div {

    private Grid<Team> grid;
    private TeamService teamService;

    public TeamsView(TeamService teamService) {
        this.teamService = teamService;
        setSizeFull();
        addClassNames("players-view");
        var grid =createGrid();
        grid.setItems(
                teamService.getTeams().values()
        );
        VerticalLayout layout = new VerticalLayout(grid);
        layout.setSizeFull();
        layout.setPadding(false);
        layout.setSpacing(false);
        add(layout);
    }
//
//    private HorizontalLayout createMobileFilters() {
//        // Mobile version
//        HorizontalLayout mobileFilters = new HorizontalLayout();
//        mobileFilters.setWidthFull();
//        mobileFilters.addClassNames(LumoUtility.Padding.MEDIUM, LumoUtility.BoxSizing.BORDER,
//                LumoUtility.AlignItems.CENTER);
//        mobileFilters.addClassName("mobile-filters");
//
//        Icon mobileIcon = new Icon("lumo", "plus");
//        Span filtersHeading = new Span("Filters");
//        mobileFilters.add(mobileIcon, filtersHeading);
//        mobileFilters.setFlexGrow(1, filtersHeading);
//        mobileFilters.addClickListener(e -> {
//            if (filters.getClassNames().contains("visible")) {
//                filters.removeClassName("visible");
//                mobileIcon.getElement().setAttribute("icon", "lumo:plus");
//            } else {
//                filters.addClassName("visible");
//                mobileIcon.getElement().setAttribute("icon", "lumo:minus");
//            }
//        });
//        return mobileFilters;
//    }

//    public static class Filters extends Div implements Specification<Player> {
//
//        private final TextField name = new TextField("Name");
//        private final TextField phone = new TextField("Phone");
//        private final DatePicker startDate = new DatePicker("Date of Birth");
//        private final DatePicker endDate = new DatePicker();
//        private final MultiSelectComboBox<String> occupations = new MultiSelectComboBox<>("Occupation");
//        private final CheckboxGroup<String> roles = new CheckboxGroup<>("Role");
//
//        public Filters(Runnable onSearch) {
//
//            setWidthFull();
//            addClassName("filter-layout");
//            addClassNames(LumoUtility.Padding.Horizontal.LARGE, LumoUtility.Padding.Vertical.MEDIUM,
//                    LumoUtility.BoxSizing.BORDER);
//            name.setPlaceholder("First or last name");
//
//            occupations.setItems("Insurance Clerk", "Mortarman", "Beer Coil Cleaner", "Scale Attendant");
//
//            roles.setItems("Worker", "Supervisor", "Manager", "External");
//            roles.addClassName("double-width");
//
//            // Action buttons
//            Button resetBtn = new Button("Reset");
//            resetBtn.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
//            resetBtn.addClickListener(e -> {
//                name.clear();
//                phone.clear();
//                startDate.clear();
//                endDate.clear();
//                occupations.clear();
//                roles.clear();
//                onSearch.run();
//            });
//            Button searchBtn = new Button("Search");
//            searchBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
//            searchBtn.addClickListener(e -> onSearch.run());
//
//            Div actions = new Div(resetBtn, searchBtn);
//            actions.addClassName(LumoUtility.Gap.SMALL);
//            actions.addClassName("actions");
//
//            add(name, phone, createDateRangeFilter(), occupations, roles, actions);
//        }
//
//        private Component createDateRangeFilter() {
//            startDate.setPlaceholder("From");
//
//            endDate.setPlaceholder("To");
//
//            // For screen readers
//            startDate.setAriaLabel("From date");
//            endDate.setAriaLabel("To date");
//
//            FlexLayout dateRangeComponent = new FlexLayout(startDate, new Text(" – "), endDate);
//            dateRangeComponent.setAlignItems(FlexComponent.Alignment.BASELINE);
//            dateRangeComponent.addClassName(LumoUtility.Gap.XSMALL);
//
//            return dateRangeComponent;
//        }
//
//        @Override
//        public Predicate toPredicate(Root<Player> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//            List<Predicate> predicates = new ArrayList<>();
//
//            if (!name.isEmpty()) {
//                String lowerCaseFilter = name.getValue().toLowerCase();
//                Predicate firstNameMatch = criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")),
//                        lowerCaseFilter + "%");
//                Predicate lastNameMatch = criteriaBuilder.like(criteriaBuilder.lower(root.get("lastName")),
//                        lowerCaseFilter + "%");
//                predicates.add(criteriaBuilder.or(firstNameMatch, lastNameMatch));
//            }
//            return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
//        }
//
//        private String ignoreCharacters(String characters, String in) {
//            String result = in;
//            for (int i = 0; i < characters.length(); i++) {
//                result = result.replace("" + characters.charAt(i), "");
//            }
//            return result;
//        }
//
//        private Expression<String> ignoreCharacters(String characters, CriteriaBuilder criteriaBuilder,
//                Expression<String> inExpression) {
//            Expression<String> expression = inExpression;
//            for (int i = 0; i < characters.length(); i++) {
//                expression = criteriaBuilder.function("replace", String.class, expression,
//                        criteriaBuilder.literal(characters.charAt(i)), criteriaBuilder.literal(""));
//            }
//            return expression;
//        }
//
//    }

    private Grid<Team> createGrid() {
        grid = new Grid<Team>();
        grid.addColumn(
                LitRenderer
                        .<Team>of("<a href=\"/team/${item.acronym}\">${item.name}</a>")
                        .withProperty("name", Team::name).withProperty("acronym", Team::acronym)
        ).setHeader("Name").setSortable(true).setAutoWidth(true);
        for (TeamService.TeamStat stat : TeamService.TeamStat.values()) {
            Span headerComponent = new Span();
            headerComponent.setText(stat.dbHeader);
            headerComponent.getElement().setProperty("title", stat.humanName);
            grid.addColumn(stat.teamFunction).setHeader(headerComponent).setSortable(true).setAutoWidth(true);
        }
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.addClassNames(LumoUtility.Border.TOP, LumoUtility.BorderColor.CONTRAST_10);

        return grid;
    }

//    private void refreshGrid() {
//        grid.getDataProvider().refreshAll();
//    }

}
