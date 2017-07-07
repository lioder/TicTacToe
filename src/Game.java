/**
 * Created by Kry·L on 2017/7/5.
 */
public class Game {
    Player player;
    ChessBoard chessBoard;
    public Game(){
        chessBoard= new ChessBoard();
        player = Player.PLAYER_X;
    }
    public Result testResult(){
        Result result = chessBoard.testWin();
        return result;
    }
    public void playChess(String position){
        chessBoard.playChess(position,this.player);
    }
}
