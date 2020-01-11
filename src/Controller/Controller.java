package Controller;

import Model.Move.*;
import Model.Board.*;
import Model.*;

import View.*;
import View.Menu.BottomMenuType;

public abstract class Controller {
    private static MoveCreator moveCreator = MoveCreator.getInstance();
    private static GridCreator gridCreator = GridCreator.getInstance();
    private static GameCreator gameCreator = GameCreator.getInstance();
    private static IGameWindow gameWindow = null;
    private static BottomMenuType bottomMenuType = BottomMenuType.Default;
    private static TextStyle textStyle = Properties.neutralTextStyle;
    private static String message = "";
    private static Game game = null;
    private static String filename;

    public static void handleMyEvent(MyEvent e) {
        if (e == MyEvent.LoadGame) 
        {
            game = gameCreator.createGame(filename);
            moveCreator.reset();
            gameCreator.reset();
            gridCreator.reset();
            bottomMenuTypeReset();            
            if (game == null) {
                message = "Failed to load game: \n" + filename;
                textStyle = Properties.errorTextStyle;
                gameWindow.updateGameWindow();
            } else {
                message = game.toString();
                gameWindow.updateGameWindow();
                textStyle = Properties.neutralTextStyle;
            }
            gameWindow.showInfo();
        }
        else if (e == MyEvent.SaveGame) 
        {
            if (game.saveGame(filename)) {
                message = "Game progress has been saved:\n" + filename;
                textStyle = Properties.successTextStyle;
            } else {
                message = "Failed to save game progress:\n" + filename;
                textStyle = Properties.errorTextStyle;
            }
            gameWindow.showInfo();
        }
        else if (e == MyEvent.MoveBack) 
        {
            if(game.moveBack()) {
                message = game.toString();
                textStyle = Properties.neutralTextStyle;
                gameWindow.updateGameWindow();
            } else {
                message = "No more moves";
                textStyle = Properties.errorTextStyle;
            }
            moveCreator.reset();
            gameWindow.showInfo();
        } 
        else if (e == MyEvent.MoveForward) 
        {
            if(game.moveForward()) {
                message = game.toString();
                textStyle = Properties.neutralTextStyle;
                gameWindow.updateGameWindow();
            } else {
                message = "No more moves";
                textStyle = Properties.errorTextStyle;
            }
            moveCreator.reset();
            gameWindow.showInfo();;
        } 
        else if (e == MyEvent.UnavailableFieldSelected) 
        {
            message = "This field is unavailable";
            textStyle = Properties.errorTextStyle;
            gameWindow.showInfo();
        } 
        else if (e == MyEvent.InvalidFilename) 
        {
            message = "Invalid input/output file \n .txt files allowed only";
            textStyle = Properties.errorTextStyle;
            gameWindow.showInfo();
        } 
        else if (e == MyEvent.PawnSelected) 
        {
            message = "Pawn selected";
            textStyle = Properties.neutralTextStyle;
            gameWindow.showInfo();
        }
        else if (e == MyEvent.EmptyFieldSelected) 
        {
            message = "There is nothing interrested in this field...";
            textStyle = Properties.neutralTextStyle;
            gameWindow.showInfo();
        } 
        else if (e == MyEvent.TryToMove) {
            if (game.makeMove(moveCreator.createMove())) {
                message = game.toString(); 
                moveCreator.reset();
                textStyle = Properties.neutralTextStyle;
                moveCreator.reset();
                gameWindow.updateGameWindow();
                gameWindow.showInfo();
            } else {
                message = "Invalid move"; 
                textStyle = Properties.errorTextStyle;
                gameWindow.showInfo();
                moveCreator.setTo(null);
            }
        }
        else if (e == MyEvent.PawnUnselected) {
            message = game.toString();
            textStyle = Properties.neutralTextStyle;
            gameWindow.showInfo();
        } 
        else if (e == MyEvent.GamePropertiesSelecting) 
        {
            message = "Create your own game";
            textStyle = Properties.neutralTextStyle;
            bottomMenuType = BottomMenuType.GamePropertiesSelecting;
            gameWindow.updateGameWindow();
            gameWindow.showInfo();
        }
        else if (e == MyEvent.UnavailableFieldsSelecting) 
        {
            message = "Create your own game";
            textStyle = Properties.neutralTextStyle;
            bottomMenuType = BottomMenuType.UnavailableFieldsSelecting;
            gameWindow.updateGameWindow();
            gameWindow.showInfo();
        }
        else if (e == MyEvent.PawnsPlacing) 
        {
            message = "Create your own game";
            textStyle = Properties.neutralTextStyle;
            bottomMenuType = BottomMenuType.PawnsPlacing;
            gameWindow.updateGameWindow();
            gameWindow.showInfo();
        }
        else if (e == MyEvent.NewCustomGame) 
        {
            bottomMenuTypeReset();
            game = gameCreator.createGame();
            if (game != null) {
                message = "Your game is ready:\n" + game.toString();
                textStyle = Properties.neutralTextStyle;
                gameWindow.updateGameWindow();
                gameWindow.showInfo();
            } else {
                message = "Something went wrong...";
                textStyle = Properties.errorTextStyle;
                gameWindow.showInfo();
            }
        }

        if (game != null)
            if (game.checkStatus() == GameStatus.Finished) {
                message = "You won the game!";
                textStyle = Properties.successTextStyle;
                gameWindow.updateGameWindow();
                gameWindow.showInfo();
                gameWindow.winGame();
            }
                
    }

    private static void bottomMenuTypeReset() {
        bottomMenuType = BottomMenuType.Default;
    }
    public static void setGameWindow(IGameWindow gameWindow) {
        Controller.gameWindow = gameWindow;
    }
    public static MoveCreator getMoveCreator() {
        return moveCreator;
    }
    public static GridCreator getGridCreator() {
        return gridCreator;
    }
    public static GameCreator getGameCreator() {
        return gameCreator;
    }
    public static TextStyle getTextStyle() {
        return textStyle;
    }
    public static String getMessage() {
        return message;
    }
    public static BottomMenuType getBottomMenuType() {
        return bottomMenuType;
    }
    public static Game getGame() {
        return Controller.game;
    }
    public static void setFilename(String filename) {
        Controller.filename = filename;
    }

}