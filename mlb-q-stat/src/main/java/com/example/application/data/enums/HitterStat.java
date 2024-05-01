package com.example.application.data.enums;

import com.example.application.data.entity.Hitter;
import org.springframework.data.domain.Sort;

import java.util.Comparator;
import java.util.function.Function;

public enum HitterStat implements StatsEnum<Hitter> {
    league("Team", "T", Hitter::team, String::compareTo),
    gamesPlayed("Games Played", "G", Hitter::gamesPlayed, IntSorter),
    atBats("At Bats", "AB", Hitter::atBats, IntSorter),
    runs("Runs","R", Hitter::runs, IntSorter),
    hits("Hits", "H", Hitter::hits, IntSorter),
    totalBases("Total Bases", "TB", Hitter::totalBases, IntSorter),
    doubles("Doubles", "Doubles", Hitter::doubles, IntSorter),
    triples("Triples", "Triples", Hitter::triples, IntSorter),
    homeRuns("Home Runs", "HR", Hitter::homeRuns, IntSorter),
    runsBattedIn("Runs Batted In", "RBI", Hitter::rbis, IntSorter),
    walks("Base on Balls", "BB", Hitter::walks, IntSorter),
    intentionalWalks("Intentional Walks", "IBB", Hitter::intentionalWalks, IntSorter),
    strikeouts("Strikeouts", "SO", Hitter::strikeOuts, IntSorter),
    stolenBases("Stolen Bases", "SB", Hitter::stolenBases, IntSorter),
    caughtStealing("Caught Stealing", "CS", Hitter::caughtStealing, IntSorter),
    battingAvg("Batting Average", "AVG", Hitter::average, DoubleSorter),
    onBasePercentage("On-base percentage", "OBP", Hitter::obp, DoubleSorter),
    sluggingPercentage("Slugging percentage", "SLG", Hitter::slg, DoubleSorter),
    onBasePlusSlugging("On-base + slugging percentage", "OPS", Hitter::ops, DoubleSorter),
    groundoutAirout("Groundout/Airout Ratio", "GOAO", Hitter::groundoutFlyout, DoubleSorter);



    public final String humanName;
    public final String db;

    public final Function<Hitter, String> accessor;
    public final SortingStrategy<Hitter> sorter;
    private HitterStat(String humanName, String db, Function<Hitter, String> accessor, SortingStrategy<String> sorter) {
        this.humanName = humanName;
        this.db = db;
        this.accessor = accessor;
        this.sorter = (stat0, stat1) -> sorter.compare(accessor.apply(stat0), accessor().apply(stat1));
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
    public SortingStrategy<Hitter> sorter() {
        return sorter;
    }
}
