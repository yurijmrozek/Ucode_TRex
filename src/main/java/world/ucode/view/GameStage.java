package world.ucode.view;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import world.ucode.model.GameLoop;
import java.util.ArrayList;
import java.util.List;

public class GameStage {
    private AnchorPane gamePane;

    private final Stage menuStage;

    private Rectangle player;

    private List<Rectangle> groundList;



    public GameStage(Stage menuStage) {
        this.menuStage = menuStage;

        initializeGameStage();
    }

    public void initializeGameStage() {
        gamePane = new AnchorPane();
        Scene gameScene = new Scene(gamePane, 900, 720);
        Stage gameStage = new Stage();
        gameStage.setResizable(false);
        gameStage.setScene(gameScene);
        gamePane.setStyle("-fx-background-color: black;");

        menuStage.close();
        gameStage.show();


        createGround();
        createPlayer();

        GameLoop loop = new GameLoop(gameScene, gameStage, player, groundList);
        loop.createKeyListener();
        loop.startGameLoop();
    }

    private void createPlayer() {
        Image playerIdleAnim = new Image("charIdle.gif");

        player = new Rectangle(124, 124);
        player.setFill(new ImagePattern(playerIdleAnim));
        player.setX(60);
        player.setY(720 - 186);
        player.setTranslateY(0);

        gamePane.getChildren().add(player);
    }

    private void createGround() {
        groundList = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            Image groundImage = new Image("ground.png");
            Rectangle groundRectangle = new Rectangle(900, 124);
            groundRectangle.setFill(new ImagePattern(groundImage));
            groundRectangle.setX(i == 0 ? 0 : 900);
            groundRectangle.setY(720 - 124);
            gamePane.getChildren().add(groundRectangle);
            groundList.add(groundRectangle);
        }
    }

}