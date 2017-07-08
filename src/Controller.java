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
import javafx.util.Duration;

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
    @FXML
    public void handleChessAction(MouseEvent event){
        FlowPane pane = (FlowPane)event.getSource();
        String position = pane.getId().substring(1);
        Image image = null;

        if (game.player.equals(Player.PLAYER_X)) {
            image = new Image("img/cha.png");
            game.playChess(position);
            game.player = Player.PLAYER_O;
        }else if (game.player.equals(Player.PLAYER_O)){
            image = new Image("img/quan.png");
            game.playChess(position);
            game.player = Player.PLAYER_X;
        }
        ImageView imageView = new ImageView(image);
        pane.getChildren().add(imageView);

        pane.setAlignment(Pos.CENTER);
        pane.setDisable(true);

        //下棋动画
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(imageView.scaleXProperty(),0.1),
                        new KeyValue(imageView.scaleYProperty(),0.1)),
                new KeyFrame(new Duration(100),
                        new KeyValue(imageView.scaleXProperty(),1.25),
                        new KeyValue(imageView.scaleYProperty(),1.25)));
        timeline.play();

        Result result = game.testResult();
        handleResult(result);
        if(!result.equals(Result.GAMING)){
            endGame();
        }

    }
    private void handleResult(Result result){
        int num = 0;
        if (result.equals(Result.X_WIN)) {
            num = Integer.parseInt(xWinNum.getText());
            num++;
            xWinNum.setText(String.valueOf(num));
//            game.end();
        } else if (result.equals(Result.TIE)){
            num = Integer.parseInt(tieNum.getText());
            num++;
            tieNum.setText(String.valueOf(num));
//            game.end();
        }  else if (result.equals(Result.O_WIN)) {
            num = Integer.parseInt(oWinNum.getText());
            num++;
            oWinNum.setText(String.valueOf(num));
//            game.end();
        }
    }
    private void endGame(){
//        for (FlowPane chess:list){
            c11.setDisable(true);
            c12.setDisable(true);
            c13.setDisable(true);
            c21.setDisable(true);
            c22.setDisable(true);
            c23.setDisable(true);
            c31.setDisable(true);
            c32.setDisable(true);
            c33.setDisable(true);
//        }
    }
}
