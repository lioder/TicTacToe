/**
 * Created by Kry·L on 2017/7/5.
 */
public class ChessBoard {
    int chessCount = 0;
    Chess[][] chessboard = new Chess[3][3];
    public ChessBoard(){
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                chessboard[i][j] = new Chess();
            }
        }
    }
    public void playChess(String position,Player player){
        Chess chess = new Chess(position);
        chess.setPlayer(player);
        playChess(chess);
    }
    public void playChess(Chess chess){
        chessboard[chess.getX()][chess.getY()] = chess;
        chessCount++;
    }
    public Result testWin(){
        //1.判断行
        for (int i = 0; i < 3; i++){
            if (!(chessboard[i][0].getPlayer().equals(Player.NONE)) && (chessboard[i][0].getPlayer().equals(chessboard[i][1].getPlayer())
                    && chessboard[i][1].getPlayer().equals(chessboard[i][2].getPlayer()))) {
                return winner(chessboard[i][0].getPlayer());
            }
        }
        //2.判断列
        for (int i = 0; i < 3; i++){
            if (!(chessboard[0][i].getPlayer().equals(Player.NONE)) && (chessboard[0][i].getPlayer().equals(chessboard[1][i].getPlayer())
                    && chessboard[1][i].getPlayer().equals(chessboard[2][i].getPlayer())))
                return winner(chessboard[0][i].getPlayer());
        }
        //3.4.判断对角线
        if (!(chessboard[0][0].getPlayer().equals(Player.NONE)) && (chessboard[0][0].getPlayer().equals(chessboard[1][1].getPlayer())
                && chessboard[1][1].getPlayer().equals(chessboard[2][2].getPlayer())))
            return winner(chessboard[0][0].getPlayer());
        if (!(chessboard[0][2].getPlayer().equals(Player.NONE)) && (chessboard[0][2].getPlayer().equals(chessboard[1][1].getPlayer())
                && chessboard[1][1].getPlayer().equals(chessboard[2][0].getPlayer())))
            return winner(chessboard[2][0].getPlayer());
        if (this.chessCount == 9)
            return Result.TIE;
        else
            return Result.GAMING;
    }
    //根据player类型返回result
    private Result winner(Player player){
        if (player.equals(Player.PLAYER_X))
            return Result.X_WIN;
        else
            return Result.O_WIN;
    }
}
