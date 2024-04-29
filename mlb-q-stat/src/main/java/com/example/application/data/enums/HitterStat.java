package com.example.application.data.enums;

import com.example.application.data.entity.Hitter;

import java.util.Comparator;
import java.util.function.Function;

public enum HitterStat implements StatsEnum<Hitter> {
    league("Team", "T", Hitter::team, String::compareTo),
    gamesPlayed("Games Played", "G", Hitter::gamesPlayed, String::compareTo),
    atBats("At Bats", "AB", Hitter::atBats, String::compareTo),
    runs("Runs","R", Hitter::runs, String::compareTo),
    hits("Hits", "H", Hitter::hits, String::compareTo),
    totalBases("Total Bases", "TB", Hitter::totalBases, String::compareTo),
    doubles("Doubles", "Doubles", Hitter::doubles, String::compareTo),
    triples("Triples", "Triples", Hitter::triples, String::compareTo),
    homeRuns("Home Runs", "HR", Hitter::homeRuns, String::compareTo),
    runsBattedIn("Runs Batted In", "RBI", Hitter::rbis, String::compareTo),
    walks("Base on Balls", "BB", Hitter::walks, String::compareTo),
    intentionalWalks("Intentional Walks", "IBB", Hitter::intentionalWalks, String::compareTo),
    strikeouts("Strikeouts", "SO", Hitter::strikeOuts, String::compareTo),
    stolenBases("Stolen Bases", "SB", Hitter::stolenBases, String::compareTo),
    caughtStealing("Caught Stealing", "CS", Hitter::caughtStealing, String::compareTo),
    battingAvg("Batting Average", "AVG", Hitter::average, String::compareTo),
    onBasePercentage("On-base percentage", "OBP", Hitter::obp, String::compareTo),
    sluggingPercentage("Slugging percentage", "SLG", Hitter::slg, String::compareTo),
    onBasePlusSlugging("On-base + slugging percentage", "OPS", Hitter::ops, String::compareTo),
    groundoutAirout("Groundout/Airout Ratio", "GOAO", Hitter::groundoutFlyout, String::compareTo);



    public final String humanName;
    public final String db;

    public final Function<Hitter, String> accessor;
    public final Comparator<String> sorter;
    private HitterStat(String humanName, String db, Function<Hitter, String> accessor, Comparator<String> sorter) {
        this.humanName = humanName;
        this.db = db;
        this.accessor = accessor;
        this.sorter = sorter;
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
    public Function<Hitter, String> accessor() {
        return accessor;
    }

    @Override
    public Comparator<Hitter> sorter() {
        return (stat0, stat1) -> sorter.compare(accessor.apply(stat0), accessor().apply(stat1));
    }
}
