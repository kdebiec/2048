package game.gui;

import game.logic.Game;
import game.utils.Vector2d;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class BoardPane extends BorderPane {
    private Pane tilesPane = new Pane();
    public Text score = new Text("0");
    private Text scoreHeadingText = new Text("Score");
    private Game game;

    public void setGame(Game game) {
        this.game = game;
    }

    public void updateBoard() {
        int[][] cells = game.getCells();
        for(Node children : tilesPane.getChildren()){
            Tile tile = (Tile) children;
            Vector2d pos = tile.getPosition();
            tile.setActive(cells[pos.x][pos.y] > 0);
            tile.setNumber(cells[pos.x][pos.y]);

            if(!game.canMove()) {
                tile.setActive(false);
            }
            else
                tile.setActive(true);
        }
        score.setText(String.valueOf(game.getScore()));
    }

    public void lazyPaneBuilder() {
        this.setPrefSize(4*Tile.TILE_X_SIZE+80, 5*Tile.TILE_Y_SIZE+80);
        this.setBackground(new Background(new BackgroundFill(Color.web("#faf8ef"), CornerRadii.EMPTY, Insets.EMPTY)));
        this.setPadding(new Insets(Tile.TILE_X_SIZE*0.05));

        List<Tile> tiles = new ArrayList<>();

        for(int i = 0;i<4;i++) {
            for(int j = 0;j<4;j++) {
                tiles.add(new Tile(i,j));
            }
        }

        tilesPane.setBackground(new Background(new BackgroundFill(Color.web("#bbada0"), new CornerRadii(Tile.TILE_X_SIZE*0.1), Insets.EMPTY)));
        tilesPane.setMaxSize(4*Tile.TILE_X_SIZE, 4*Tile.TILE_Y_SIZE);
        tilesPane.getChildren().addAll(tiles);
        this.setCenter(tilesPane);

        Pane headingPane = new StackPane();
        headingPane.setPrefSize(Tile.TILE_X_SIZE*4, Tile.TILE_X_SIZE/2);
        headingPane.setBackground(new Background(new BackgroundFill(Color.web("#faf8ef"), CornerRadii.EMPTY, Insets.EMPTY)));

        BorderPane scorePane = new BorderPane();
        scorePane.setPrefSize(Tile.TILE_X_SIZE,Tile.TILE_X_SIZE*3/4);
        scorePane.setMaxSize(Tile.TILE_X_SIZE,Tile.TILE_X_SIZE*3/4);
        scorePane.setBackground(new Background(new BackgroundFill(Color.web("#bbada0"), new CornerRadii(Tile.TILE_X_SIZE*0.2), Insets.EMPTY)));

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().add(scoreHeadingText);
        scoreHeadingText.setFont(Font.font ("Verdana", 15));
        vBox.getChildren().add(score);
        score.setFont(Font.font ("Verdana", 20));
        scorePane.setCenter(vBox);
        headingPane.getChildren().add(scorePane);
        this.setTop(headingPane);
    }
}
