package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.lang.Runnable;








// mlb.com
// Get team name by parsing through the team's tabs:
// mlb.com/{team_name}
// mlb.com/{team_name}/{roster}
//   Parse through each player's name

// mlb.com/player/{first_name}-{last_name}-{p-id}
// The player's name and player ID can be identified through parsing (td class = "info", a href="....">(name))



public class Main {
    public static void main(String[] args) {
        MlbDatabaseManager dbManager = new MlbDatabaseManager();
        dbManager.updateStatsP();
}


}

