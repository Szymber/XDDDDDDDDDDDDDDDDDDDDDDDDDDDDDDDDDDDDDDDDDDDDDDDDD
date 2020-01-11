package Model.Chessman;

import Model.MyColor;
import Model.Vector2;


public class Knight extends Chessman {
    public Knight(Vector2 position, MyColor color) {
        super(position, new Vector2[] {
            new Vector2(2, 1),
            new Vector2(2, -1),
            new Vector2(-2, 1),
            new Vector2(-2, -1),
            new Vector2(1, 2),
            new Vector2(1, -2),
            new Vector2(-1, 2),
            new Vector2(-1, -2)
        }, "Knight", color, false);
    }
}