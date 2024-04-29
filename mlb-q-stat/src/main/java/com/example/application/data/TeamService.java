package com.example.application.data;

import com.example.application.data.entity.Hitter;
import com.example.application.data.entity.Team;
import com.vaadin.flow.function.ValueProvider;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class TeamService {

        private HashMap<String, Team> teams = new HashMap<>();

    public TeamService() {
        System.out.println("STARTING TEAMSERVICE");
        try (BufferedReader br = new BufferedReader(new FileReader("teams.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                //System.out.println(line);
                String[] values = line.split(",");
                // avoid trying to serialize the header row
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
    }

    public HashMap<String, Team> getTeams() {return teams;}
    public List<Team> getAllTeams() {return teams.values().stream().toList();}
}
