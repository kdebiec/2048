package game.gui;

import game.utils.Vector2d;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Tile extends StackPane {
    public static int TILE_X_SIZE;
    public static int TILE_Y_SIZE;
    private Color color;
    private Text text;
    private Rectangle border;
    private int sumNumber;

    private Vector2d position;

    public Vector2d getPosition() {
        return position;
    }

    public void setActive(boolean active) {
        if(active)
            this.color = getTileBackground(sumNumber);
        else
            this.color = Color.rgb(238, 228, 218, 0.35);

        border.setFill(this.color);
    }

    public void setNumber(int number) {
        sumNumber = number;
        this.text.setText(String.valueOf(number));
        this.color = getTileBackground(number);
        border.setFill(this.color);
    }

    public Color getTileBackground(int number) {
        switch (number) {
            case 2:		return Color.rgb(238, 228, 218, 1.0); //238 228 218 1.0      0xeee4da
            case 4: 	return Color.rgb(237, 224, 200, 1.0); //237, 224, 200, 1.0   0xede0c8
            case 8: 	return Color.rgb(242, 177, 121, 1.0); //242, 177, 121, 1.0   0xf2b179
            case 16: 	return Color.rgb(245, 149, 99, 1.0); //245, 149, 99, 1.0     0xf59563
            case 32: 	return Color.rgb(246, 124, 95, 1.0); //246, 124, 95, 1.0     0xf67c5f
            case 64:	return Color.rgb(246, 94, 59, 1.0 ); //246, 94, 59, 1.0      0xf65e3b
            case 128:	return Color.rgb(237, 207, 114, 1.0); //237, 207, 114, 1.0   0xedcf72
            case 256: 	return Color.rgb(237, 204, 97, 1.0); //237, 204, 97, 1.0     0xedcc61
            case 512: 	return Color.rgb(237, 200, 80, 1.0); //237, 200, 80, 1.0     0xedc850
            case 1024: 	return Color.rgb(237, 197, 63, 1.0); //237, 197, 63, 1.0     0xedc53f
            case 2048: 	return Color.rgb(237, 194, 46, 1.0); //237, 194, 46, 1.0     0xedc22e
        }
        return Color.rgb(205, 193, 180, 1.0); //0xcdc1b4
    }

    public Tile(int x, int y) {
        this.color = Color.rgb(238, 228, 218, 0.35);//Color.web("#eee4da");

        position = new Vector2d(x, y);
        border = new Rectangle(TILE_X_SIZE*0.9, TILE_Y_SIZE*0.9);
        setMinSize(TILE_X_SIZE, TILE_Y_SIZE);

        border.setArcWidth(TILE_X_SIZE*0.2);
        border.setArcHeight(TILE_Y_SIZE*0.2);

        this.text = new Text("0");
        this.text.setFont(Font.font ("Verdana", 18));

        setTranslateX(x*TILE_X_SIZE);
        setTranslateY(y*TILE_Y_SIZE);

        setAlignment(Pos.CENTER);
        getChildren().addAll(border);
        getChildren().addAll(text);
    }
}
