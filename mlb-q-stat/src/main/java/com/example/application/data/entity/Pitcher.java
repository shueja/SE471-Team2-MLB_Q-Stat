package com.example.application.data.entity;

//Player Name,Player ID,Season,Team,LG,W,L,ERA,G,GS,CG,SHO,HLD,SV,SVO,IP,H,R,ER,HR,NP,HB,BB,IBB,SO,AVG,WHIP
public record Pitcher(int id, String name, String season, String team,
                      String gamesPlayed,
                      String era, String gamesStarted, String completeGames,
                      String shutouts, String holds, String saves, String saveOpps, String inningsPitched,
                      String hits, String runs, String earnedRunsAllowed, String homeRunsAllowed,
                      String numberPitches, String hitBatter,
                      String walks, String intentionalWalks,
                      String strikeOuts, String average, String whip) {
}