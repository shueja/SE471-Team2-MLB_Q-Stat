package com.example.application.data.service;

import com.example.application.data.entity.Pitcher;
import com.example.application.data.enums.PitcherStat;

import java.util.Map;

public class PitcherFactory extends AbstractRecordFactory<Pitcher> {
    @Override
    public Pitcher fromRow(Map<String, ?> row) {
        return new Pitcher(
                Integer.parseInt(row.get("Player_ID").toString()),
                row.get("Player_Name").toString().replace('_', ' '),
                row.get("Season").toString(),
                row.get("Team").toString(),
                row.get(PitcherStat.gamesPlayed.db).toString(),
                row.get(PitcherStat.era.db).toString(),
                row.get(PitcherStat.gamesStarted.db).toString(),
                row.get(PitcherStat.completeGames.db).toString(),
                row.get(PitcherStat.shutouts.db).toString(),
                row.get(PitcherStat.holds.db).toString(),
                row.get(PitcherStat.saves.db).toString(),
                row.get(PitcherStat.saveOpportunities.db).toString(),
                row.get(PitcherStat.inningsPitched.db).toString(),
                row.get(PitcherStat.hits.db).toString(),
                row.get(PitcherStat.runs.db).toString(),
                row.get(PitcherStat.earnedRunsAllowed.db).toString(),
                row.get(PitcherStat.homeRunsAllowed.db).toString(),
                row.get(PitcherStat.numberPitches.db).toString(),
                row.get(PitcherStat.hitBatter.db).toString(),
                row.get(PitcherStat.walks.db).toString(),
                row.get(PitcherStat.intentionalWalks.db).toString(),
                row.get(PitcherStat.strikeouts.db).toString(),
                row.get(PitcherStat.battingAvg.db).toString(),
                row.get(PitcherStat.whip.db).toString());
    }
}
