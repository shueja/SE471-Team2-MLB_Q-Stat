package com.example.application.data.entity;

public record Hitter(int id, String name, String season, String team,
                     String gamesPlayed,
                     String atBats, String runs, String hits,
                     String totalBases, String doubles, String triples, String homeRuns,
                     String rbis, String walks, String intentionalWalks, String strikeOuts,
                     String stolenBases, String caughtStealing,
                     String average, String obp, String slg, String ops, String groundoutFlyout) {
}