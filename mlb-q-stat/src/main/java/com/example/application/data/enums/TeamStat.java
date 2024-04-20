package com.example.application.data.enums;

import com.example.application.data.PlayerService;
import com.example.application.data.entity.Team;
import com.vaadin.flow.function.ValueProvider;

public enum TeamStat implements StatsEnum<Team> {
    wins("Wins", "W", Team::w),
    losses("Losses", "L", Team::l),
    winPct("Win Percentage", "PCT", Team::pct),
    gamesBack("Games Back","GB", Team::gb),
    home("Home Record", "HOME", Team::home),
    away("Away Record", "AWAY", Team::away),
    runsScored("Runs Scored", "RS", Team::rs),
    runsAllowed("Runs Allowed", "RA", Team::ra),
    runDiff("Runs Difference", "DIFF", Team::diff),
    streak("Streak", "STRK", Team::strk),
    lastTen("Last 10 Games", "L10", Team::l10);

    public final String humanName;

    public final String dbHeader;

    public final  com.vaadin.flow.function.ValueProvider<Team, String> teamFunction;
    private TeamStat(String humanName, String dbHeader, com.vaadin.flow.function.ValueProvider<Team, String> playerFunction) {
        this.humanName = humanName;
        this.dbHeader = dbHeader;
        this.teamFunction = playerFunction;
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
    public ValueProvider<Team, String> accessor() {
        return teamFunction;
    }

}

