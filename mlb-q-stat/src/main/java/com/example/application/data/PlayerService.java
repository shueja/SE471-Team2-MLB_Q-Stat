package com.example.application.data;

import com.example.application.data.entity.Hitter;
import com.example.application.data.entity.Pitcher;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.*;

@Service
public class PlayerService {
    private static final String DATABASE_PATH = "jdbc:sqlite:MLBdatabase.db";
    private final Connection connection;
    public static enum HS {
        league("Team", "T", Hitter::team),
        gamesPlayed("Games Played", "G", Hitter::gamesPlayed),
        atBats("At Bats", "AB", Hitter::atBats),
        runs("Runs","R", Hitter::runs),
        hits("Hits", "H", Hitter::hits),
        totalBases("Total Bases", "TB", Hitter::totalBases),
        doubles("Doubles", "Doubles", Hitter::doubles),
        triples("Triples", "Triples", Hitter::triples),
        homeRuns("Home Runs", "HR", Hitter::homeRuns),
        runsBattedIn("Runs Batted In", "RBI", Hitter::rbis),
        walks("Base on Balls", "BB", Hitter::walks),
        intentionalWalks("Intentional Walks", "IBB", Hitter::intentionalWalks),
        strikeouts("Strikeouts", "SO", Hitter::strikeOuts),
        stolenBases("Stolen Bases", "SB", Hitter::stolenBases),
        caughtStealing("Caught Stealing", "CS", Hitter::caughtStealing),
        battingAvg("Batting Average", "AVG", Hitter::average),
        onBasePercentage("On-base percentage", "OBP", Hitter::obp),
        sluggingPercentage("Slugging percentage", "SLG", Hitter::slg),
        onBasePlusSlugging("On-base + slugging percentage", "OPS", Hitter::ops),
        groundoutAirout("Groundout/Airout Ratio", "GOAO", Hitter::groundoutFlyout);



        public final String humanName;
        public final String db;

        public final  com.vaadin.flow.function.ValueProvider<Hitter, String> playerFunction;
        private HS(String humanName, String db, com.vaadin.flow.function.ValueProvider<Hitter, String> playerFunction) {
            this.humanName = humanName;
            this.db = db;
            this.playerFunction = playerFunction;
        }

    }

    public static enum PS {
        league("Team", "Team", Pitcher::team),
        gamesPlayed("Games Played", "G", Pitcher::gamesPlayed),
        era("ERA", "ERA", Pitcher::era),
        gamesStarted("Games Started", "GS", Pitcher::gamesStarted),
        completeGames("Complete Games", "CG", Pitcher::completeGames),
        shutouts("Shutouts", "SHO", Pitcher::shutouts),
        holds("Holds", "HLD", Pitcher::holds),
        saves("Saves", "SV", Pitcher::saves),
        saveOpportunities("Save Opportunities", "SVO", Pitcher::saveOpps),
        inningsPitched("Innings Pitched", "IP", Pitcher::inningsPitched),
        hits("Hits", "H", Pitcher::hits),
        runs("Runs", "R", Pitcher::runs),
        earnedRunsAllowed("Earned Runs Allowed", "ER", Pitcher::earnedRunsAllowed),
        homeRunsAllowed("Home Runs Allowed", "HR", Pitcher::homeRunsAllowed),
        numberPitches("# of Pitches", "NP", Pitcher::numberPitches),
        hitBatter("Hit Batter", "HB", Pitcher::hitBatter),
        walks("Base on Balls", "BB", Pitcher::walks),
        intentionalWalks("Intentional Walks", "IBB", Pitcher::intentionalWalks),
        strikeouts("Strikeouts", "SO", Pitcher::strikeOuts),

        battingAvg("Batting Average", "AVG", Pitcher::average),
        whip("(Walks + Hits)/Inning Pitched", "WHIP", Pitcher::whip);

        public final String humanName;
        public final String db;

        public final  com.vaadin.flow.function.ValueProvider<Pitcher, String> playerFunction;
        private PS(String humanName, String db, com.vaadin.flow.function.ValueProvider<Pitcher, String> playerFunction) {
            this.humanName = humanName;
            this.db = db;
            this.playerFunction = playerFunction;
        }

    }
    private HashMap<Integer, Hitter> players = new HashMap<>();
    /**
     * Constructs an instance of MlbDatabaseManager, establishes a connection to the database,
     * and initializes the web scraping API.
     */
    protected Connection ConnectToDataBase() {
        Connection connection = null;
        try {
            // Load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");

            // Connect to the database
            connection = DriverManager.getConnection(DATABASE_PATH);

            System.out.println("Connected to the MLB database.");

            // Initialize the web scraping API
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    public PlayerService() {
        connection = ConnectToDataBase();
//        File csv = new File("./hitters_1.csv");
//        List<List<String>> records = new ArrayList<>();
        System.out.println("STARTING PLAYERSERVICE");

        // populating players from a a database would go her
    }

    public Optional<Hitter> getBetter(int id, HS stat) {
        return Optional.empty();
    }
    public Optional<Hitter> getWorse(int id, HS stat) {
        return Optional.empty();
    }

    public List<Hitter> getPlayers() {
        //QueryTeam("LAA");
        return getAllHitters();}
    public Optional<Hitter> getHitter(int id) {
        List<Hitter> players = getHitters("Player_Id = " + id);
        if (players.isEmpty()) {
            return Optional.empty();
        }
        else {
            return Optional.of(players.get(0));
        }
    }
    public List<Map<String, Object>> toList(ResultSet rs) throws SQLException {
        ResultSetMetaData md = rs.getMetaData();
        int columns = md.getColumnCount();
        List<Map<String, Object>> list = new ArrayList<>();
        while (rs.next()) {
            Map<String, Object> row = new HashMap<>(columns);
            for (int i = 1; i <= columns; ++i) {
                Object value = rs.getObject((i));
                if (value == null) {
                    var col = md.getColumnName(i);
                    if (!col.equals("Player_ID")) {
                        value = "--";
                    }
                    else {
                        value = 0;
                    }
                }
                row.put(md.getColumnName(i), value);
            }
            list.add(row);
        }

        return list;
    }

    public Hitter fromHitterRow(Map<String, ?> row) {
        return new Hitter(Integer.parseInt(row.get("Player_ID").toString()), row.get("Player_Name").toString().replace('_', ' '),
                row.get("Season").toString(),
                row.get("Team").toString(),
                row.get(HS.gamesPlayed.db).toString(),
                row.get(HS.atBats.db).toString(),
                row.get(HS.runs.db).toString(),
                row.get(HS.hits.db).toString(),
                row.get(HS.totalBases.db).toString(),
                row.get(HS.doubles.db).toString(),
                row.get(HS.triples.db).toString(),
                row.get(HS.homeRuns.db).toString(),
                row.get(HS.runsBattedIn.db).toString(),
                row.get(HS.walks.db).toString(),
                row.get(HS.intentionalWalks.db).toString(),
                row.get(HS.strikeouts.db).toString(),
                row.get(HS.stolenBases.db).toString(),
                row.get(HS.caughtStealing.db).toString(),
                row.get(HS.battingAvg.db).toString(),
                row.get(HS.onBasePercentage.db).toString(),
                row.get(HS.sluggingPercentage.db).toString(),
                row.get(HS.onBasePlusSlugging.db).toString(),
                row.get(HS.groundoutAirout.db).toString()
        );
    }

    public Pitcher fromPitcherRow(Map<String, ?> row) {
        return new Pitcher(
                Integer.parseInt(row.get("Player_ID").toString()), row.get("Player_Name").toString().replace('_', ' '),
                row.get("Season").toString(),
                row.get("Team").toString(),
                row.get(PS.gamesPlayed.db).toString(),
                row.get(PS.era.db).toString(),
                row.get(PS.gamesStarted.db).toString(),
                row.get(PS.completeGames.db).toString(),
                row.get(PS.shutouts.db).toString(),
                row.get(PS.holds.db).toString(),
                row.get(PS.saves.db).toString(),
                row.get(PS.saveOpportunities.db).toString(),
                row.get(PS.inningsPitched.db).toString(),
                row.get(PS.hits.db).toString(),
                row.get(PS.runs.db).toString(),
                row.get(PS.earnedRunsAllowed.db).toString(),
                row.get(PS.homeRunsAllowed.db).toString(),
                row.get(PS.numberPitches.db).toString(),
                row.get(PS.hitBatter.db).toString(),
                row.get(PS.walks.db).toString(),
                row.get(PS.intentionalWalks.db).toString(),
                row.get(PS.strikeouts.db).toString(),
                row.get(PS.battingAvg.db).toString(),
                row.get(PS.whip.db).toString());
    }

    public List<Hitter> getHittersOnTeam(String team) {
        return getHitters(String.format("Team IN ('%s') AND  Season = 2023", team));
    }
    public List<Hitter> getAllHitters() {
        return getHitters("Season = 2023");
    }
    public List<Hitter> getHitters(String where) {//team should be the two letter abreviation
        try(Statement statement = connection.createStatement()){
            List<Hitter> result = new ArrayList<>() ;
            String query = String.format("SELECT * FROM HittersSeasonTable hst WHERE %s;", where);
            ResultSet resultSet = statement.executeQuery(query);
            result= toList(resultSet).stream().map(this::fromHitterRow).toList();
            return result;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Pitcher> getPitchers(String where) {//team should be the two letter abreviation
        try(Statement statement = connection.createStatement()){
            List<Pitcher> result = new ArrayList<>() ;
            String query = String.format("SELECT * FROM PitchersSeasonTable hst WHERE %s;", where);
            ResultSet resultSet = statement.executeQuery(query);
            result= toList(resultSet).stream().map(this::fromPitcherRow).toList();
            return result;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Pitcher> getPitchersOnTeam(String team) {
        return getPitchers(String.format("Team IN ('%s') AND  Season = 2023", team));
    }
    public List<Pitcher> getAllPitchers() {
        return getPitchers("Season = 2023");
    }

    public Optional<Pitcher> getPitcher(int id) {
        List<Pitcher> players = getPitchers("Player_Id = " + id);
        if (players.isEmpty()) {
            return Optional.empty();
        }
        else {
            for (Pitcher p: players) {
                if (p.season().equals("2023")) {
                    return Optional.of(p);
                }
            }
            return Optional.empty();
        }
    }
    public double[][] getPlayerHistory(int id, PS stat) {
        var list = getPitchers("Player_Id = " + id);
        System.out.println(list.toString());
        ArrayList<double[]> result = new ArrayList<>();
        for (Pitcher p : list) {
            int season;
            try {

                season = Integer.parseInt(p.season());
            } catch (NumberFormatException e) {
                continue;
            }
            double statValue;
            try {
                statValue = Double.parseDouble(stat.playerFunction.apply(p));
            } catch (NumberFormatException e) {
                continue;
            }
            result.add(new double[]{season, statValue});

        }
        System.out.println(result.toString());
        double[][] arr = new double[result.size()][2];
        System.out.println(arr);
        return result.toArray(arr);
    }

    public double[][] getPlayerHistory(int id, HS stat) {
        var list = getHitters("Player_Id = " + id);
        System.out.println(list.toString());
        ArrayList<double[]> result = new ArrayList<>();
        for (Hitter p : list) {
            int season;
            try {

                season = Integer.parseInt(p.season());
            } catch (NumberFormatException e) {
                continue;
            }
            double statValue;
            try {
                statValue = Double.parseDouble(stat.playerFunction.apply(p));
            } catch (NumberFormatException e) {
                continue;
            }
            result.add(new double[]{season, statValue});

        }
        System.out.println(result.toString());
        double[][] arr = new double[result.size()][2];
        System.out.println(arr);
        return result.toArray(arr);
    }

    // Big queries for comparison

    public List<Pitcher> ComparePitcher(String playerId){//team should be the two letter abreviation
        try(Statement statement = connection.createStatement()){
            List<Pitcher> result = new ArrayList<>() ;
            String query = String.format(
                    """
                    DROP TABLE IF EXISTS TempResult;
                    CREATE TEMPORARY TABLE TempResult AS
                    WITH GivenPlayer AS (
                        SELECT *
                        FROM PitchersSeasonTable pst
                        WHERE Player_ID = %s AND SEASON = 2023
                    )
                    SELECT * ,((1000 -(ABS(pst.W - gp.W) + ABS(pst.L - gp.L) + ABS(pst.G - gp.G) + ABS(pst.GS - gp.GS) + ABS(pst.GS - gp.GS)/20 +
                                        ABS(pst.ERA - gp.ERA) + ABS(pst.W / pst.G - gp.W / gp.G) +(ABS(pst.W / pst.G - gp.W / gp.G)/.002)%%100+ ABS(pst.CG - gp.CG) +
                                        ABS(pst.H - gp.H) + ABS(pst.BB - gp.BB) + ABS(pst.SO - gp.SO) + ABS(pst.WHIP - gp.WHIP) +
                                        ABS(pst.W - gp.W) + ABS(pst.L - gp.L) * 2 +ABS(pst.SHO - gp.SHO) +
                                        CASE WHEN ABS(pst.ERA - gp.ERA) <= 0.02 THEN 100 ELSE 0 END + ABS(pst.G - gp.G) / 10 + ABS(pst.GS - gp.GS) / 20 +
                                        ABS(pst.CG - gp.CG) / 20 + ABS(pst.IP - gp.IP) / 50 +ABS(pst.SO - gp.SO) / 30 +
                                        ABS(pst.BB - gp.BB) / 10 + ABS(pst.SHO - gp.SHO) / 5 + ABS(pst.SV - gp.SV) / 3
                                        ))) AS SIMILARITY
                    From PitchersSeasonTable pst , GivenPlayer gp
                    WHERE pst.Player_ID != gp.Player_ID AND pst.Season =2023
                    ORDER BY SIMILARITY DESC LIMIT 3;       
                            """, playerId);
            statement.executeUpdate(query);

            query = String.format( "-- Select all rows from the temporary table and order them\n" +
                    "SELECT DISTINCT *\n" +
                    "FROM TempResult;\n",playerId,playerId);
            ResultSet resultSet = statement.executeQuery(query);
            result = toList(resultSet).stream().map(this::fromPitcherRow).toList();
            return result;
        }catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }
    public List<Hitter> CompareHitter(String playerId){//team should be the two letter abreviation
        try(Statement statement = connection.createStatement()){
            List<Hitter> result = new ArrayList<>() ;
            String query = String.format("""
                            DROP TABLE IF EXISTS TempResult;
                            CREATE TEMPORARY TABLE TempResult AS
                            WITH GivenPlayer AS (
                                SELECT *
                                FROM HittersSeasonTable hst
                                WHERE Player_ID = %s   AND SEASON = 2023
                            )
                            SELECT * ,( (1000 - ( ABS(hst.HR - gp.HR) + ABS(hst.RBI - gp.RBI) +
                                                 ABS(hst.BB - gp.BB) + ABS(hst.SO - gp.SO) + ABS(hst.SB - gp.SB) + ABS(hst.AVG - gp.AVG) +
                                                 ABS(hst.OBP - gp.OBP) + ABS(hst.SLG - gp.SLG) + ABS(hst.G - gp.G) + ABS(hst.R - gp.R) +
                                                 ABS(hst.H - gp.H) + ABS(hst.AB - gp.AB) + ABS(hst.Doubles - gp.Doubles) + ABS(hst.Triples - gp.Triples) +
                                                 ABS(hst.OPS - gp.OPS) + ABS(hst.G - gp.G) / 20 + ABS(hst.AB - gp.AB) / 75 + ABS(hst.R - gp.R) / 10 +
                                                 ABS(hst.H - gp.H) / 15 + ABS(hst.Doubles - gp.Doubles) / 5 + ABS(hst.Triples - gp.Triples) / 4 + ABS(hst.HR - gp.HR) / 2  +
                                                 ABS(hst.RBI - gp.RBI) / 10 + ABS(hst.BB - gp.BB) / 25 + ABS(hst.SO - gp.SO) / 150 + ABS(hst.SB - gp.SB) / 20 +
                                                 ABS(hst.AVG - gp.AVG) / 0.001 + ABS(hst.SLG - gp.SLG) / 0.002))) AS SIMILARITY
                            From HittersSeasonTable hst , GivenPlayer gp
                            WHERE hst.Player_ID != gp.Player_ID AND hst.Season =2023
                            ORDER BY SIMILARITY DESC LIMIT 3;
                                        
                            --Select Player_Name, Player_ID
                            --From tempTable;
                    """

                    ,playerId);
            statement.executeUpdate(query);

            query = String.format( "-- Select all rows from the temporary table and order them\n" +
                    "SELECT *\n" +
                    "FROM TempResult;\n",playerId,playerId);
            ResultSet resultSet = statement.executeQuery(query);
            result = toList(resultSet).stream().map(this::fromHitterRow).toList();
            return result;
        }catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

}
