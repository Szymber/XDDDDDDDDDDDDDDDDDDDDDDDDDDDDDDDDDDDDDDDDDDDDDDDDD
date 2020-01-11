package Controller;

import Model.Move.*;
import Model.Board.*;
import Model.Chessman.Chessman;
import Model.Chessman.ChessmanCreator;
import Model.*;

import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;

public class GameCreator {
    private static GameCreator instance = null;
    private GameCreator() { }
    public static GameCreator getInstance() {
        if (instance == null)
            instance = new GameCreator();
        return instance;
    }
    private Integer pawnNumber = null;
    private Integer pawnsPlaced = null;
    private String pawnType = null;
    private Vector2[] pawnPositions = null;

    public Game createGame() {
        Grid grid = Controller.getGridCreator().createGrid();
        Chessman[] chessPieces = new Chessman[pawnNumber];
        for (int i = 0; i < pawnNumber; i++) {
            if (pawnPositions[i] == null)
                return null;
            MyColor color = MyColor.Black;
            if (i < pawnNumber / 2)
                color = MyColor.White;
            chessPieces[i] = ChessmanCreator.create(pawnType, pawnPositions[i], color);
            if (chessPieces[i] == null)
                return null;
        }
        return new Game(grid, chessPieces);
    }

    public Game createGame(String filename) {
        Game game = null;
        try {
            Scanner scnr = new Scanner(new FileReader(filename));
            Vector2 gridSize = new Vector2(scnr.nextInt(), scnr.nextInt());
            int unavailablePointsCount = scnr.nextInt();
            Vector2[] unavailablePoints = new Vector2[unavailablePointsCount];
            for (int  i = 0; i < unavailablePointsCount; i++)
                unavailablePoints[i] = new Vector2(scnr.nextInt(), scnr.nextInt());
            
            Grid grid = new Grid(gridSize, unavailablePoints);


            pawnNumber = scnr.nextInt();
            pawnType = scnr.next();
            Chessman[] chessPiecesTarget = new Chessman[pawnNumber];

            for (int i = 0; i < pawnNumber / 2; i++)
                chessPiecesTarget[i] = ChessmanCreator.create(pawnType, new Vector2(scnr.nextInt(), scnr.nextInt()), MyColor.White);
            for (int i = 0; i < pawnNumber / 2; i++)
                chessPiecesTarget[i + pawnNumber/2] = ChessmanCreator.create(pawnType, new Vector2(scnr.nextInt(), scnr.nextInt()), MyColor.Black);

            Chessman[] chessPieces = new Chessman[pawnNumber];
            for (int i = 0; i < pawnNumber; i++)
                chessPieces[i] = ChessmanCreator.createOppositeColor(chessPiecesTarget[i]);

            for (int i = 0; i < chessPieces.length; i++)
                grid.getField(chessPieces[i].getPosition()).setEmpty(false);

            game = new Game(grid, chessPieces);

            MovesHistory movesHistory = new MovesHistory();
            int moveCounter = scnr.nextInt();
            
            for (int i = 0; i < moveCounter; i++) {
                Vector2 from, to;
                from = new Vector2(scnr.nextInt(), scnr.nextInt());
                to = new Vector2(scnr.nextInt(), scnr.nextInt());
                movesHistory.add(new Move(from, to));
                game.makeMove(from, to);
            }
            scnr.close();
        } catch (IOException | InputMismatchException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return game;
    }

    public void setpawnNumber(int i) {
        pawnNumber = i;
        pawnPositions = new Vector2[pawnNumber];
        pawnsPlaced = 0;
    } 

    public int getPawnNumber() {
        return pawnNumber;
    }

    public Vector2[] getPawnPositions() {
        return pawnPositions;
    }

    public int addPawnPosition(Vector2 position) {
        pawnPositions[pawnsPlaced] = position;
        pawnsPlaced++;
        return (int) pawnsPlaced;
    }

    public void setPawnType(String pawnType) {
        this.pawnType = pawnType;
    }

    public String getPawnType() {
        return  pawnType;
    }

    public void reset() {
        pawnNumber = null;
        pawnType = null;
        pawnPositions = null;
    }
}