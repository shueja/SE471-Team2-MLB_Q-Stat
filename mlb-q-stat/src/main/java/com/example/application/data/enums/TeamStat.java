package com.example.application.data.enums;

import com.example.application.data.entity.Team;

import java.util.Comparator;
import java.util.function.Function;

public enum TeamStat implements StatsEnum<Team> {
    wins("Wins", "W", Team::w, String::compareTo),
    losses("Losses", "L", Team::l, String::compareTo),
    winPct("Win Percentage", "PCT", Team::pct, String::compareTo),
    gamesBack("Games Back","GB", Team::gb, String::compareTo),
    home("Home Record", "HOME", Team::home, String::compareTo),
    away("Away Record", "AWAY", Team::away, String::compareTo),
    runsScored("Runs Scored", "RS", Team::rs, String::compareTo),
    runsAllowed("Runs Allowed", "RA", Team::ra, String::compareTo),
    runDiff("Runs Difference", "DIFF", Team::diff, String::compareTo),
    streak("Streak", "STRK", Team::strk, String::compareTo),
    lastTen("Last 10 Games", "L10", Team::l10, String::compareTo);

    public final String humanName;

    public final String dbHeader;

    public final Function<Team, String> accessor;
    public final Comparator<String> sorter;

    @Override
    public Comparator<Team> sorter() {
        return (stat0, stat1) -> sorter.compare(accessor.apply(stat0), accessor().apply(stat1));
    }

    private TeamStat(String humanName, String dbHeader, Function<Team, String> accessor, Comparator<String> sorter) {
        this.humanName = humanName;
        this.dbHeader = dbHeader;
        this.accessor = accessor;
        this.sorter = sorter;
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

