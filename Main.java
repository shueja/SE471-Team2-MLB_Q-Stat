package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
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
    /*public static String[] teamNames = new String[]{"White Sox", "Cubs", "Guardians", "Reds", "Tigers", "Brewers", "Royals", "Pirates", "Twins", "Cardinals", "Orioles", "Braves", "Red Sox", "Marlins", "Yankees", "Mets", "Rays", "Phillies", "bluejays", "Nationals", "Astros", "dbacks", "Angels", "Rockies", "Athletics", "Dodgers", "Mariners", "Padres", "rangers", "Giants"};

    public static void main(String[] args) {
        for(int i = 0 ; i < teamNames.length;i++) {
            teamNames[i] = teamNames[i].toLowerCase().replaceAll(" ", "");
        }

        try{
            // Loading (fetching and parsing the HTML)
            for(String team: teamNames){
            String urlName = String.format("https://www.mlb.com/%s/roster", team);
            Document doc = Jsoup.connect(urlName).get();
            Elements link = doc.select("td.info > a") ;

            //Elements values = doc.select("td") ;
            //for(Element el:link) {
                System.out.println(link.attr("href"));
            //}
                System.out.println(team);
            }





        }catch (Exception e) {
            e.printStackTrace();
        }
    }*/

      /* try{
        // Loading (fetching and parsing the HTML)
        String urlName = "https://www.mlb.com/standings";
        Document doc = Jsoup.connect(urlName).get();
        //Elements link = doc.select("tdtext") ;

        Elements values = doc.select("td.text") ;
        System.out.println(values);






    }catch (Exception e) {
        e.printStackTrace();
    }
}*/
    //public static Vector<String> teamNames = new Vector<>() ;

    public static String[] teamNames = new String[]{"White Sox", "Cubs", "Guardians", "Reds", "Tigers", "Brewers", "Royals", "Pirates", "Twins", "Cardinals", "Orioles", "Braves", "Red Sox", "Marlins", "Yankees", "Mets", "Rays", "Phillies", "bluejays", "Nationals", "Astros", "dbacks", "Angels", "Rockies", "Athletics", "Dodgers", "Mariners", "Padres", "rangers", "Giants"};
    public static MLB_DB_Object tester = new MLB_DB_Object("jdbc:sqlite:/Users/joshualarson/MLB_Q_STAT.db") ;

    public static void main(String[] args) {
//        Date start = new Date();
//       MLB_Scraper2 scrapee = new MLB_Scraper2();

        MlbScraper2 scrapah = new MlbScraper2();
       // scrapah.selina();
//
//       // Vector <Vector<String>> rosters = new Vector<>() ;
//        List<List<String>> rosters2 = new Vector<>() ;
//       // rosters = scrapee.fetch_rosters() ;
//        //scrapee.fetch_player_stats(rosters);
//       rosters2 = scrapah.fetchRostersConcurrently() ;
//        //scrapah.fetch_player_stats(rosters2);
//        Date end = new Date();
//        System.out.println(end.getTime()-start.getTime());
 //   MLB_DB_Object tester = new MLB_DB_Object("jdbc:sqlite:/Users/joshualarson/MLB_Q_STAT.db") ;
    List player  =  scrapah.stat_prep();
    for(Object i : player)
        System.out.println(i);
    }




}
