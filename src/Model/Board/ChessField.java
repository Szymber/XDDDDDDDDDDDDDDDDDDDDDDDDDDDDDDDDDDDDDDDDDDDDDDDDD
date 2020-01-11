package Model.Board;

import Model.Vector2;

public class ChessField {
    private final Vector2 position;
    protected boolean available;
    protected boolean empty;
    protected char sign;

    public ChessField(Vector2 position) {
        this.position = position;
        this.available = true;
        this.empty = true;
    }

    public ChessField(Vector2 position, char sign) {
        this.position = position;
        this.available = true;
        this.empty = true;
        this.sign = sign;
    }
    public boolean isAvailable() {
        return this.available;
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }
    public boolean isEmpty() {
        return this.empty;
    }
    public void setEmpty(boolean empty) {
        this.empty = empty;
    }
    public Vector2 getPosition() {
        return this.position;
    }
    public char getSign() { return sign; }
    public void setSign(char sign) { this.sign = sign; }
}