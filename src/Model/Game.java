package Model;

import Model.Chessman.*;
import Model.Move.*;
import Model.Board.*;

import java.util.ArrayList;
import java.io.*;
import java.io.IOException;
import java.util.InputMismatchException;

public class Game {
    private MovesHistory movesHistory;
    private int moveCounter;
    private Grid grid;
    private Chessman[] chessPieces;
    private Chessman[] chessPiecesTarget;

    public Game(Grid grid, Chessman[] chessPieces) {
        this.moveCounter = 0;
        this.movesHistory = new MovesHistory();
        this.grid = grid;
        this.chessPieces = chessPieces;
        this.chessPiecesTarget = new Chessman[chessPieces.length];
        for (int i = 0; i < chessPieces.length; i++) {
            grid.getField(chessPieces[i].getPosition()).setEmpty(false);
            chessPiecesTarget[i] = ChessmanCreator.createOppositeColor(chessPieces[i]);
        }
    }
    
    public boolean saveGame(String filename) {
        try {
            FileWriter fileWriter = new FileWriter(filename);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            printWriter.println(grid.getSize());

            ArrayList<Vector2> unavailableFields = new ArrayList<Vector2>();
            for (int i = 0; i < grid.getSize().getX(); i++)
                for (int j = 0; j < grid.getSize().getY(); j++)
                    if (!grid.getField(new Vector2(i, j)).isAvailable())
                        unavailableFields.add(new Vector2(i, j));

            printWriter.println(unavailableFields.size());
            for (Vector2 v : unavailableFields)
                printWriter.println(v);
                
            printWriter.println(chessPiecesTarget.length + " " + chessPiecesTarget[0].getName());        
            //target white (black start position)
            for (Chessman chessman:chessPiecesTarget) {
                if (chessman.getColor() == MyColor.White)
                    printWriter.println(chessman.getPosition());
            }
            //target black (white start position)
            for (Chessman chessman:chessPiecesTarget) {
                if (chessman.getColor() == MyColor.Black)
                    printWriter.println(chessman.getPosition());
            }

            printWriter.println(movesHistory.size());
            printWriter.println(movesHistory);
            printWriter.close();
        } catch (IOException | InputMismatchException e) {
            System.out.println(e.getMessage());
            return false;
        }        
        return true;
    }

    public GameStatus checkStatus() { // checkForWin
        int correct = 0;
        for (Chessman chessman : chessPieces)
            for (Chessman chessmanTarget : chessPiecesTarget) {
                if (chessman.equals(chessmanTarget))
                    correct++;
            }
        if (correct == chessPieces.length)
            return GameStatus.Finished;
        return GameStatus.InProgress;
    }

    private boolean justMove(Vector2 from, Vector2 to) { // change position, don't cut moves list 
        if (from.getX() >= grid.getSize().getX() || from.getX() < 0)
            return false;
        else if (from.getY() >= grid.getSize().getY() || from.getY() < 0)
            return false;
        else if (to.getX() >= grid.getSize().getX() || to.getX() < 0)
            return false;
        else if (to.getY() >= grid.getSize().getY() || to.getY() < 0)
            return false;
        ChessField fieldFrom = grid.getField(from);
        ChessField fieldTo = grid.getField(to);
        if (!fieldFrom.isAvailable())
            return false;
        else if (!fieldTo.isAvailable())
            return false;
        
        else if (fieldFrom.isEmpty())
            return false;
        else if (!fieldTo.isEmpty())
            return false;
        else {
            Chessman selectedChessman = null;
            for (Chessman chessman : chessPieces)
                if (chessman.getPosition().equals(fieldFrom.getPosition())) {
                    selectedChessman = chessman;
                    break;
                }
            if (selectedChessman != null)
                if (selectedChessman.isValidMove(to)) {
                    selectedChessman.setPosition(to);
                    fieldFrom.setEmpty(true);
                    fieldTo.setEmpty(false);
                    return true;
            }
        }
        return false;
    }

    public boolean makeMove(Move move) {
        return makeMove(move.from, move.to);
    }

    public boolean makeMove(Vector2 from, Vector2 to) {
        movesHistory.cut(moveCounter);
        this.moveCounter = movesHistory.size();        
        boolean correctMove = this.justMove(from, to);
        if(correctMove) {
           moveCounter++;
           movesHistory.add(new Move(from, to));
        }
        return correctMove;
    }

    public boolean moveBack() {
        if (moveCounter == 0)
            return false;

        moveCounter--;
        Move move = movesHistory.get(moveCounter);
        return justMove(move.to, move.from);
    }

    public boolean moveForward() {
        if (this.moveCounter == movesHistory.size())
            return false;
        Move move = movesHistory.get(moveCounter);
        moveCounter++;        
        return justMove(move.from, move.to);
    }

    public Grid getGrid() {
        return grid;
    }

    public Chessman[] getChessPieces() {
        return chessPieces;
    }

    public MovesHistory getHistory() {
        return movesHistory;
    }

    public String toString() {
        String str = "To win you have to put:";

        String whiteTarget = "\nWhite " + chessPieces[0].getName() + " on ";
        String blackTarget = "\nBlack " + chessPieces[0].getName() + " on ";
        for (Chessman chessman : chessPiecesTarget) {
            if (chessman.getColor() == MyColor.White) {
                whiteTarget += grid.getField(chessman.getPosition()).getSign() + " ";
            } else {
                blackTarget += grid.getField(chessman.getPosition()).getSign() + " ";
            }
        }
        return str + whiteTarget + blackTarget;
    }
}