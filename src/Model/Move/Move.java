package Model.Move;

import Model.Vector2;

public class Move {
    public Vector2 from;
    public Vector2 to;
    public Move() {
        this(null, null);
    }
    public Move(Vector2 from, Vector2 to) {
        this.from = from;
        this.to = to;
    }
    public String toString() {
        return from.toString() + " " + to.toString();
    }
}