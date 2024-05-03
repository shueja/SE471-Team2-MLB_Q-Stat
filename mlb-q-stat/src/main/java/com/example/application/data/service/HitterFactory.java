package com.example.application.data.service;

import com.example.application.data.entity.Hitter;
import com.example.application.data.entity.Pitcher;
import com.example.application.data.enums.HitterStat;
import com.example.application.data.enums.PitcherStat;

import java.util.Map;

public class HitterFactory extends AbstractRecordFactory<Hitter> {
    @Override
    public Hitter fromRow(Map<String, ?> row) {
        return new Hitter(Integer.parseInt(row.get("Player_ID").toString()), row.get("Player_Name").toString().replace('_', ' '),
                row.get("Season").toString(),
                row.get("Team").toString(),
                row.get(HitterStat.gamesPlayed.db).toString(),
                row.get(HitterStat.atBats.db).toString(),
                row.get(HitterStat.runs.db).toString(),
                row.get(HitterStat.hits.db).toString(),
                row.get(HitterStat.totalBases.db).toString(),
                row.get(HitterStat.doubles.db).toString(),
                row.get(HitterStat.triples.db).toString(),
                row.get(HitterStat.homeRuns.db).toString(),
                row.get(HitterStat.runsBattedIn.db).toString(),
                row.get(HitterStat.walks.db).toString(),
                row.get(HitterStat.intentionalWalks.db).toString(),
                row.get(HitterStat.strikeouts.db).toString(),
                row.get(HitterStat.stolenBases.db).toString(),
                row.get(HitterStat.caughtStealing.db).toString(),
                row.get(HitterStat.battingAvg.db).toString(),
                row.get(HitterStat.onBasePercentage.db).toString(),
                row.get(HitterStat.sluggingPercentage.db).toString(),
                row.get(HitterStat.onBasePlusSlugging.db).toString(),
                row.get(HitterStat.groundoutAirout.db).toString()
        );
    }
}
