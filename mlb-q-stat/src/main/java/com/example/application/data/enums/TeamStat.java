package com.example.application.data.enums;

import com.example.application.data.entity.Team;

import java.util.Comparator;
import java.util.function.Function;

public enum TeamStat implements StatsEnum<Team> {
    wins("Wins", "W", Team::w, IntSorter),
    losses("Losses", "L", Team::l, IntSorter),
    winPct("Win Percentage", "PCT", Team::pct, DoubleSorter),
    gamesBack("Games Back","GB", Team::gb, IntSorter),
    home("Home Record", "HOME", Team::home, String::compareTo),
    away("Away Record", "AWAY", Team::away, String::compareTo),
    runsScored("Runs Scored", "RS", Team::rs, IntSorter),
    runsAllowed("Runs Allowed", "RA", Team::ra, IntSorter),
    runDiff("Runs Difference", "DIFF", Team::diff, IntSorter),
    streak("Streak", "STRK", Team::strk, String::compareTo),
    lastTen("Last 10 Games", "L10", Team::l10, String::compareTo);

    public final String humanName;

    public final String dbHeader;

    public final Function<Team, String> accessor;
    public final SortingStrategy<Team> sorter;

    @Override
    public SortingStrategy<Team> sorter() {
        return sorter;
    }

    private TeamStat(String humanName, String dbHeader, Function<Team, String> accessor, SortingStrategy<String> sorter) {
        this.humanName = humanName;
        this.dbHeader = dbHeader;
        this.accessor = accessor;
        this.sorter = (stat0, stat1) -> sorter.compare(accessor.apply(stat0), accessor().apply(stat1));
    }

    @Override
    public String humanName() {
        return humanName;
    }

    @Override
    public String db() {
        return dbHeader;
    }

    @Override
    public Function<Team, String> accessor() {
        return accessor;
    }

}

