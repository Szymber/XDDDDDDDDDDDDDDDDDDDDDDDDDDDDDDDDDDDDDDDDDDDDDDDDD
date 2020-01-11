package Model.Move;

import java.util.ArrayList;

public class MovesHistory {
    private ArrayList<Move> moves;

    public MovesHistory() {
        this.moves = new ArrayList<Move>();
    }

    MovesHistory(ArrayList<Move> moves) {
        this.moves = moves;
    }

    public void add(Move move) {
        moves.add(move);
    }

    public int size() {
        return moves.size();
    }

    public void cut(int i) {
        moves = new ArrayList<Move>(this.moves.subList(0, i));
    }

    public Move get(int i) {
        return moves.get(i);
    }

    public String toString() {
        String str = "";
        for (Move move : moves)
            str += move + "\n";
        return str;
    }
}