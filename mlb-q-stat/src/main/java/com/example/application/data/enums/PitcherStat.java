package com.example.application.data.enums;

import com.example.application.data.entity.Pitcher;

import java.util.Comparator;
import java.util.function.Function;

public enum PitcherStat implements StatsEnum<Pitcher> {
    league("Team", "Team", Pitcher::team, String::compareTo),
    gamesPlayed("Games Played", "G", Pitcher::gamesPlayed, String::compareTo),
    era("ERA", "ERA", Pitcher::era, String::compareTo),
    gamesStarted("Games Started", "GS", Pitcher::gamesStarted, String::compareTo),
    completeGames("Complete Games", "CG", Pitcher::completeGames, String::compareTo),
    shutouts("Shutouts", "SHO", Pitcher::shutouts, String::compareTo),
    holds("Holds", "HLD", Pitcher::holds, String::compareTo),
    saves("Saves", "SV", Pitcher::saves, String::compareTo),
    saveOpportunities("Save Opportunities", "SVO", Pitcher::saveOpps, String::compareTo),
    inningsPitched("Innings Pitched", "IP", Pitcher::inningsPitched, String::compareTo),
    hits("Hits", "H", Pitcher::hits, String::compareTo),
    runs("Runs", "R", Pitcher::runs, String::compareTo),
    earnedRunsAllowed("Earned Runs Allowed", "ER", Pitcher::earnedRunsAllowed, String::compareTo),
    homeRunsAllowed("Home Runs Allowed", "HR", Pitcher::homeRunsAllowed, String::compareTo),
    numberPitches("# of Pitches", "NP", Pitcher::numberPitches, String::compareTo),
    hitBatter("Hit Batter", "HB", Pitcher::hitBatter, String::compareTo),
    walks("Base on Balls", "BB", Pitcher::walks, String::compareTo),
    intentionalWalks("Intentional Walks", "IBB", Pitcher::intentionalWalks, String::compareTo),
    strikeouts("Strikeouts", "SO", Pitcher::strikeOuts, String::compareTo),

    battingAvg("Batting Average", "AVG", Pitcher::average, String::compareTo),
    whip("(Walks + Hits)/Inning Pitched", "WHIP", Pitcher::whip, String::compareTo);

    public final String humanName;
    public String humanName() {return humanName;}
    public final String db;
    public String db() {return db;}

    public final Function<Pitcher, String> accessor;
    public Function<Pitcher, String> accessor() {return accessor;}

    public final Comparator<String> sorter;
    @Override
    public Comparator<Pitcher> sorter() {
        return (stat0, stat1) -> sorter.compare(accessor.apply(stat0), accessor.apply(stat1));
    }
    private PitcherStat(String humanName, String db, Function<Pitcher, String> accessor, Comparator<String> sorter) {
        this.humanName = humanName;
        this.db = db;
        this.accessor = accessor;
        this.sorter = sorter;
    }

}

