package Model.Board;

import java.util.ArrayList;

import Model.Vector2;

public class Grid {
    private Vector2 size;
    private ChessField[][] grid;

    public Grid(Vector2 size) {
        this(size, new ArrayList<Vector2>());
    }

    public Grid(Vector2 size, ArrayList<Vector2> unavailableFields) {
        this.size = size;
        grid = new ChessField[size.getX()][];
        for (int i = 0; i < size.getX(); i++) {
            grid[i] = new ChessField[size.getY()];
            for (int j = 0; j < size.getY(); j++) {       
                grid[i][j] = new ChessField(new Vector2(i, j));
            }
        }
        for (Vector2 field : unavailableFields)
            grid[field.getX()][field.getY()].setAvailable(false);

        char c = 'A';
        for (ChessField[] row : grid)
            for (ChessField chessField : row)
                if (chessField.isAvailable())
                    chessField.setSign(c++);
    }

    public Grid(Vector2 size, Vector2[] unavailableFields) {
        this.size = size;
        grid = new ChessField[size.getX()][];
        for (int i = 0; i < size.getX(); i++) {
            grid[i] = new ChessField[size.getY()];
            for (int j = 0; j < size.getY(); j++) {       
                grid[i][j] = new ChessField(new Vector2(i, j));
            }
        }
        for (Vector2 field : unavailableFields)
            grid[field.getX()][field.getY()].setAvailable(false);

        char c = 'A';
        for (ChessField[] row : grid)
            for (ChessField chessField : row)
                if (chessField.isAvailable())
                    chessField.setSign(c++);;
    }

    public Vector2 getSize() {
        return size;

    };

    public ChessField getField(Vector2 position) {
        if ( position.getX() >= this.size.getX() || position.getY() >= this.size.getY()
            || position.getX() < 0 || position.getY() < 0 )
                return null;
        return grid[position.getX()][position.getY()];
    }
}
