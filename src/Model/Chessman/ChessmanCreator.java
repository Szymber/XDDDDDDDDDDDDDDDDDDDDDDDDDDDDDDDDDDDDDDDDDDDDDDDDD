package Model.Chessman;

import Model.MyColor;
import Model.Vector2;

public abstract class ChessmanCreator {
    public static Chessman create(String name, Vector2 position, MyColor color) {
        if (name.contains("Knight"))
            return new Knight(position, color);
        else if (name.contains("King"))
            return new King(position, color);
        else if (name.contains("Rook"))
            return new Rook(position, color);
        else if (name.contains("Queen"))
            return new Queen(position, color);
        else
            return null;
    }
    public static Chessman createOppositeColor(Chessman chessman) {
    	if (chessman == null)
    			return null;
        return create(chessman.getName(), chessman.getPosition(), chessman.getColor() == MyColor.White ? MyColor.Black : MyColor.White);
    }
}