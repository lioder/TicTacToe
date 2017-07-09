import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class Controller {
    @FXML FlowPane c11;
    @FXML FlowPane c12;
    @FXML FlowPane c13;
    @FXML FlowPane c21;
    @FXML FlowPane c22;
    @FXML FlowPane c23;
    @FXML FlowPane c31;
    @FXML FlowPane c32;
    @FXML FlowPane c33;

    @FXML Label xWinNum;
    @FXML Label tieNum;
    @FXML Label oWinNum;
    Game game;

    public Controller(){
         game = new Game();
    }

    /**
     * 处理点击事件（根据游戏状态判断处理方式）
     * 1.over 新建游戏
     * 2.gaming 下棋
     * @param event
     */
    @FXML
    public void handleChessAction(MouseEvent event){
        if (game.gameStatus.equals("over")){
            game = new Game();
            clearChess();
        } else {
            FlowPane pane = (FlowPane) event.getSource();
            String position = pane.getId().substring(1);
            Image image = null;
            File musicFile = null;

            if (game.player.equals(Player.PLAYER_X)) {
                image = new Image("img/cha.png");
                game.playChess(position);
                game.player = Player.PLAYER_O;
                musicFile = new File("./src/note-high.wav");
            } else if (game.player.equals(Player.PLAYER_O)) {
                image = new Image("img/quan.png");
                game.playChess(position);
                game.player = Player.PLAYER_X;
                musicFile = new File("./src/note-low.wav");
            }
            ImageView imageView = new ImageView(image);
            pane.getChildren().add(imageView);

            pane.setAlignment(Pos.CENTER);
            pane.setDisable(true);

            //下棋音乐
            play(musicFile);
            //下棋动画
            Timeline timeline = new Timeline();
            timeline.getKeyFrames().addAll(
                    new KeyFrame(Duration.ZERO,
                            new KeyValue(imageView.scaleXProperty(), 0.1),
                            new KeyValue(imageView.scaleYProperty(), 0.1)),
                    new KeyFrame(new Duration(100),
                            new KeyValue(imageView.scaleXProperty(), 1.25),
                            new KeyValue(imageView.scaleYProperty(), 1.25)));
            timeline.play();
            //下棋音乐


            Result result = game.testResult();
            handleResult(result);
            if (!result.equals(Result.GAMING)) {
                game.end();
                endGame();
            }
        }

    }

    /**
     * 根据result改变积分label，播放游戏结束音乐
     * @param result
     */
    private void handleResult(Result result){
        int num = 0;
        if (result.equals(Result.X_WIN)) {
            num = Integer.parseInt(xWinNum.getText());
            num++;
            xWinNum.setText(String.valueOf(num));
            play(new File("./src/game-over.wav"));
        } else if (result.equals(Result.TIE)){
            num = Integer.parseInt(tieNum.getText());
            num++;
            tieNum.setText(String.valueOf(num));
            play(new File("./src/game-over-tie.wav"));
        }  else if (result.equals(Result.O_WIN)) {
            num = Integer.parseInt(oWinNum.getText());
            num++;
            oWinNum.setText(String.valueOf(num));
            play(new File("./src/game-over.wav"));
        }
    }

    /**
     * 将所有的棋子为设为可点击状态
     */
    private void endGame(){
            c11.setDisable(false);
            c12.setDisable(false);
            c13.setDisable(false);
            c21.setDisable(false);
            c22.setDisable(false);
            c23.setDisable(false);
            c31.setDisable(false);
            c32.setDisable(false);
            c33.setDisable(false);
    }

    /**
     * 清除场上所有棋子
     */
    private void clearChess(){
        c11.getChildren().clear();
        c12.getChildren().clear();
        c13.getChildren().clear();

        c21.getChildren().clear();
        c22.getChildren().clear();
        c23.getChildren().clear();

        c31.getChildren().clear();
        c32.getChildren().clear();
        c33.getChildren().clear();
    }
    /**
     * 封装的音乐播放方法，根据name选择文件
     */
    private void play(File name){
        try {
            AudioStream as = new AudioStream(new FileInputStream(name));
            AudioPlayer.player.start(as);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
