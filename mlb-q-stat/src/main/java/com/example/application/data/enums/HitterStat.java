package com.example.application.data.enums;

import com.example.application.data.entity.Hitter;
import com.vaadin.flow.function.ValueProvider;

public enum HitterStat implements StatsEnum<Hitter> {
    league("Team", "T", Hitter::team),
    gamesPlayed("Games Played", "G", Hitter::gamesPlayed),
    atBats("At Bats", "AB", Hitter::atBats),
    runs("Runs","R", Hitter::runs),
    hits("Hits", "H", Hitter::hits),
    totalBases("Total Bases", "TB", Hitter::totalBases),
    doubles("Doubles", "Doubles", Hitter::doubles),
    triples("Triples", "Triples", Hitter::triples),
    homeRuns("Home Runs", "HR", Hitter::homeRuns),
    runsBattedIn("Runs Batted In", "RBI", Hitter::rbis),
    walks("Base on Balls", "BB", Hitter::walks),
    intentionalWalks("Intentional Walks", "IBB", Hitter::intentionalWalks),
    strikeouts("Strikeouts", "SO", Hitter::strikeOuts),
    stolenBases("Stolen Bases", "SB", Hitter::stolenBases),
    caughtStealing("Caught Stealing", "CS", Hitter::caughtStealing),
    battingAvg("Batting Average", "AVG", Hitter::average),
    onBasePercentage("On-base percentage", "OBP", Hitter::obp),
    sluggingPercentage("Slugging percentage", "SLG", Hitter::slg),
    onBasePlusSlugging("On-base + slugging percentage", "OPS", Hitter::ops),
    groundoutAirout("Groundout/Airout Ratio", "GOAO", Hitter::groundoutFlyout);



    public final String humanName;
    public final String db;

    public final  com.vaadin.flow.function.ValueProvider<Hitter, String> accessor;
    private HitterStat(String humanName, String db, com.vaadin.flow.function.ValueProvider<Hitter, String> accessor) {
        this.humanName = humanName;
        this.db = db;
        this.accessor = accessor;
    }

    @Override
    public String humanName() {
        return humanName;
    }

    @Override
    public String db() {
        return db;
    }

    @Override
    public ValueProvider<Hitter, String> accessor() {
        return accessor;
    }
}
