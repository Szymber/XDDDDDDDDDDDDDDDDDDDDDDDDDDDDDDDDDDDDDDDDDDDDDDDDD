package Model.Chessman;

import Model.MyColor;
import Model.Vector2;


public class Queen extends Chessman {
    public Queen(Vector2 position, MyColor color) {
        super(position, new Vector2[] {
            new Vector2(1, 1),
            new Vector2(1, 0),
            new Vector2(1, -1),
            new Vector2(0, -1),
            new Vector2(-1, -1),
            new Vector2(-1, 0),
            new Vector2(-1, 1),
            new Vector2(0, 1)
        }, "Queen", color, true);
    }
}