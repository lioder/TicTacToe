import javafx.application.Platform;

/**
 * Created by Kry·L on 2017/7/5.
 */
public class Chess {
    private Player player;
    int x;
    int y;
    public Chess(){
        this.player = Player.NONE;
    }
    public  Chess(int x, int y){
        this.x = x;
        this.y = y;
    }
    public Chess(String position){
        this.x = position.charAt(0) - 0x31;
        this.y = position.charAt(1) - 0x31;
    }
    public int getX(){
        return this.x;
    }
    public int getY(){
        return  this.y;
    }
    public void setPlayer(Player player){
        this.player = player;
    }
    public Player getPlayer(){
        return  this.player;
    }
}
