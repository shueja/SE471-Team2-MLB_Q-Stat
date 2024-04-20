package com.example.application.data.enums;

import com.example.application.data.entity.Pitcher;

public enum PitcherStat implements StatsEnum<Pitcher> {
    league("Team", "Team", Pitcher::team),
    gamesPlayed("Games Played", "G", Pitcher::gamesPlayed),
    era("ERA", "ERA", Pitcher::era),
    gamesStarted("Games Started", "GS", Pitcher::gamesStarted),
    completeGames("Complete Games", "CG", Pitcher::completeGames),
    shutouts("Shutouts", "SHO", Pitcher::shutouts),
    holds("Holds", "HLD", Pitcher::holds),
    saves("Saves", "SV", Pitcher::saves),
    saveOpportunities("Save Opportunities", "SVO", Pitcher::saveOpps),
    inningsPitched("Innings Pitched", "IP", Pitcher::inningsPitched),
    hits("Hits", "H", Pitcher::hits),
    runs("Runs", "R", Pitcher::runs),
    earnedRunsAllowed("Earned Runs Allowed", "ER", Pitcher::earnedRunsAllowed),
    homeRunsAllowed("Home Runs Allowed", "HR", Pitcher::homeRunsAllowed),
    numberPitches("# of Pitches", "NP", Pitcher::numberPitches),
    hitBatter("Hit Batter", "HB", Pitcher::hitBatter),
    walks("Base on Balls", "BB", Pitcher::walks),
    intentionalWalks("Intentional Walks", "IBB", Pitcher::intentionalWalks),
    strikeouts("Strikeouts", "SO", Pitcher::strikeOuts),

    battingAvg("Batting Average", "AVG", Pitcher::average),
    whip("(Walks + Hits)/Inning Pitched", "WHIP", Pitcher::whip);

    public final String humanName;
    public String humanName() {return humanName;}
    public final String db;
    public String db() {return db;}

    public final  com.vaadin.flow.function.ValueProvider<Pitcher, String> accessor;
    public com.vaadin.flow.function.ValueProvider<Pitcher, String> accessor() {return accessor;}
    private PitcherStat(String humanName, String db, com.vaadin.flow.function.ValueProvider<Pitcher, String> accessor) {
        this.humanName = humanName;
        this.db = db;
        this.accessor = accessor;
    }

}

