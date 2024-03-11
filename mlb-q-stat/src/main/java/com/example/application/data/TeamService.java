package com.example.application.data;

import com.example.application.data.entity.Team;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Optional;

@Service
public class TeamService {

    public static enum TeamStat {
        wins("Wins", "W", Team::w),
        losses("Losses", "L", Team::l),
        winPct("Win Percentage", "PCT", Team::pct),
        gamesBack("Games Back","GB", Team::gb),
        home("Home Record", "HOME", Team::home),
        away("Away Record", "AWAY", Team::away),
        runsScored("Runs Scored", "RS", Team::rs),
        runsAllowed("Runs Allowed", "RA", Team::ra),
        runDiff("Runs Difference", "DIFF", Team::diff),
        streak("Streak", "STRK", Team::strk),
        lastTen("Last 10 Games", "L10", Team::l10);

        public final String humanName;
        public final String dbHeader;

        public final  com.vaadin.flow.function.ValueProvider<Team, ?> teamFunction;
        private TeamStat(String humanName, String dbHeader, com.vaadin.flow.function.ValueProvider<Team, ?> playerFunction) {
            this.humanName = humanName;
            this.dbHeader = dbHeader;
            this.teamFunction = playerFunction;
        }

    }
    private HashMap<String, Team> teams = new HashMap<>();

    public TeamService() {
        System.out.println("STARTING TEAMSERVICE");
        try (BufferedReader br = new BufferedReader(new FileReader("teams.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                //System.out.println(line);
                String[] values = line.split(",");
                if (!values[0].equals("ACR")) {
                    teams.put(
                            values[0],
                            new Team(
                                    values[0],
                                    values[1],
                                    "2023",
                                    values[2],
                                    values[3],
                                    values[4],
                                    values[5],
                                    values[6],
                                    values[7],
                                    values[8],
                                    values[9],
                                    values[10],
                                    values[11],
                                    values[12]

                            )
                    );
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        // populating players from a a database would go her
    }

    public HashMap<String, Team> getTeams() {return teams;}
}
