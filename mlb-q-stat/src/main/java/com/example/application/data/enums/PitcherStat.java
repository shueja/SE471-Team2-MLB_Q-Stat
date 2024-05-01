package com.example.application.data.enums;

import com.example.application.data.entity.Pitcher;
import org.springframework.data.domain.Sort;

import java.util.Comparator;
import java.util.function.Function;

public enum PitcherStat implements StatsEnum<Pitcher> {
    league("Team", "Team", Pitcher::team, String::compareTo),
    gamesPlayed("Games Played", "G", Pitcher::gamesPlayed, IntSorter),
    era("ERA", "ERA", Pitcher::era, DoubleSorter),
    gamesStarted("Games Started", "GS", Pitcher::gamesStarted, IntSorter),
    completeGames("Complete Games", "CG", Pitcher::completeGames, IntSorter),
    shutouts("Shutouts", "SHO", Pitcher::shutouts, IntSorter),
    holds("Holds", "HLD", Pitcher::holds, IntSorter),
    saves("Saves", "SV", Pitcher::saves, IntSorter),
    saveOpportunities("Save Opportunities", "SVO", Pitcher::saveOpps, IntSorter),
    inningsPitched("Innings Pitched", "IP", Pitcher::inningsPitched, DoubleSorter),
    hits("Hits", "H", Pitcher::hits, IntSorter),
    runs("Runs", "R", Pitcher::runs, IntSorter),
    earnedRunsAllowed("Earned Runs Allowed", "ER", Pitcher::earnedRunsAllowed, IntSorter),
    homeRunsAllowed("Home Runs Allowed", "HR", Pitcher::homeRunsAllowed, IntSorter),
    numberPitches("# of Pitches", "NP", Pitcher::numberPitches, IntSorter),
    hitBatter("Hit Batter", "HB", Pitcher::hitBatter, IntSorter),
    walks("Base on Balls", "BB", Pitcher::walks, IntSorter),
    intentionalWalks("Intentional Walks", "IBB", Pitcher::intentionalWalks, IntSorter),
    strikeouts("Strikeouts", "SO", Pitcher::strikeOuts, IntSorter),

    battingAvg("Batting Average", "AVG", Pitcher::average, DoubleSorter),
    whip("(Walks + Hits)/Inning Pitched", "WHIP", Pitcher::whip, DoubleSorter);

    public final String humanName;
    public String humanName() {return humanName;}
    public final String db;
    public String db() {return db;}

    public final Function<Pitcher, String> accessor;
    public Function<Pitcher, String> accessor() {return accessor;}

    public final SortingStrategy<Pitcher> sorter;
    @Override
    public SortingStrategy<Pitcher> sorter() {
        return sorter;
    }
    private PitcherStat(String humanName, String db, Function<Pitcher, String> accessor, Comparator<String> sorter) {
        this.humanName = humanName;
        this.db = db;
        this.accessor = accessor;
        this.sorter = (stat0, stat1) -> sorter.compare(accessor.apply(stat0), accessor.apply(stat1));
    }

}

