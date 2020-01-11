package Model.Move;

import Model.Vector2;

public class MoveCreator {
    private static MoveCreator instance = null;
    private MoveCreator() { }
    public static MoveCreator getInstance() {
        if (instance == null)
            instance = new MoveCreator();
        return instance;
    }

    private Vector2 from = null;
    private Vector2 to = null;
    public Move createMove() {
        if (from == null || to == null)
            return null;
        return new Move(from, to);
    }

    public void setFrom(Vector2 from) {
        this.from = from;
    }
    public void setTo(Vector2 to) {
        this.to = to;
    }
    
    public Vector2 getFrom() {return from; }
    public Vector2 getTo() {return to; }
    public void reset() {
        from = to = null;
    }
}