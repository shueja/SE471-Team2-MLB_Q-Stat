public class Player {
    String team, name;
    int player_number ;

    public Player(String name, String team, int player_number){
        this.name = name ;
        this.player_number = player_number ;
        this.team = team ;

    }
    public Player(){
        this.team = " ";
        this.name= " " ;
        this.player_number = 0 ;
    }
}
