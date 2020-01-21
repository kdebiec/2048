package game.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class SummaryPane extends BoardPane {
    private Text headingText = new Text();
    private Text scoreText = new Text();

    public void hidePane() {
        setVisible(false);
    }

    public void gameFinished(int score, boolean won) {
        if(won) headingText.setText("You have won!");
        else headingText.setText("You have lost!");

        scoreText.setText("You're score: ".concat(String.valueOf(score)));
        setVisible(true);
    }

    public void lazyPaneBuilder() {
        setBackground(new Background(new BackgroundFill(Color.web("#faf8ef", 0.5), CornerRadii.EMPTY, Insets.EMPTY)));

        headingText.setFont(Font.font ("Verdana", 20));
        scoreText.setFont(Font.font ("Verdana", 20));

        VBox div = new VBox();
        div.setAlignment(Pos.CENTER);
        div.getChildren().add(headingText);
        div.getChildren().add(scoreText);
        div.getChildren().add(new Text("Press ESC to play again"));
        setCenter(div);
    }

    public SummaryPane() {
        setVisible(true);
    }
}
