package game.gui;

import game.logic.Game;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
    private StackPane root = new StackPane();
    private BoardPane boardPane = new BoardPane();
    private SummaryPane summaryPane = new SummaryPane();

    private static Game game;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.game = new Game();
        boardPane.setGame(this.game);
        game.init();
        primaryStage.setTitle("2048 Game");
        Scene scene = new Scene(createScene());
        primaryStage.setScene(scene);

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                game.resetGame();
            }

            if (!game.isHasWon() && game.canMove()) {
                switch (event.getCode()) {
                    case LEFT:
                        game.left();
                        break;
                    case RIGHT:
                        game.right();
                        break;
                    case DOWN:
                        game.down();
                        break;
                    case UP:
                        game.up();
                        break;
                }
            }

            updateScene();
        });

        updateScene();
        primaryStage.show();
    }

    private Parent createScene() {
        Tile.TILE_X_SIZE = 200;
        Tile.TILE_Y_SIZE = 200;
        root.setPrefSize(4*Tile.TILE_X_SIZE+80, 5*Tile.TILE_Y_SIZE+80);

        boardPane.lazyPaneBuilder();
        root.getChildren().add(boardPane);

        summaryPane.lazyPaneBuilder();
        root.getChildren().add(summaryPane);

        return root;
    }

    public void updateScene() {
        if(game.isHasWon() || !game.canMove())
            summaryPane.gameFinished(game.getScore(), game.isHasWon());
        else
            summaryPane.hidePane();

        boardPane.updateBoard();
    }
}
