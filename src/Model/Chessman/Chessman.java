package Model.Chessman;

import Model.MyColor;
import java.awt.*;
import java.util.Arrays;

import javax.swing.ImageIcon;

import Controller.Properties;
import Model.Vector2;

public abstract class Chessman {
    protected Vector2 position;
    protected final Vector2[] validMoves;
    protected final String name;
    protected final MyColor color;
    protected final boolean severalPositionMove;
    protected final String imagePath;
    public Chessman(Vector2 position,Vector2[] validMoves, String name, MyColor color, boolean severalPositionMove) {
        this.position = position;
        this.color = color;
        this.validMoves = validMoves;
        this.name = name;
        this.severalPositionMove = severalPositionMove;
        this.imagePath = Properties.chessmanPath + ((this.color == MyColor.White) ? "White" : "Black") + this.name + ".png";
    }
    public void setPosition(Vector2 position) {
        this.position = position;
    }
    public Vector2 getPosition() {
        return position;
    }
    public MyColor getColor() {
        return color;
    }
    public String getName() {
        return name;
    }
    public boolean isValidMove(Vector2 newPosition) {
        
        if (!severalPositionMove) {
            Vector2 diff = newPosition.difference(this.getPosition());
            for (Vector2 move : validMoves)
                if (diff.equals(move))
                    return true;
        } else {
            double maxDistanceDiff = position.distanceTo(newPosition);
            double currentDistanceDiff = 0;
             
            for (int i = 1; ; i++, currentDistanceDiff = 0) {
                for (Vector2 move : validMoves) {
                    Vector2 currentPosition = position.sum(move.multiply(i));
                    if (newPosition.equals(currentPosition))
                        return true;
                    
                    if (currentDistanceDiff == 0)
                        currentDistanceDiff = position.distanceTo(currentPosition);
                    else
                        currentDistanceDiff = currentDistanceDiff < position.distanceTo(currentPosition) ?
                                                currentDistanceDiff : position.distanceTo(currentPosition); 

                }
                if (maxDistanceDiff < currentDistanceDiff)
                    break;
            }
        }
        return false;
    }


    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((imagePath == null) ? 0 : imagePath.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		result = prime * result + (severalPositionMove ? 1231 : 1237);
		result = prime * result + Arrays.hashCode(validMoves);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Chessman other = (Chessman) obj;
		if (color != other.color)
			return false;
		if (imagePath == null) {
			if (other.imagePath != null)
				return false;
		} else if (!imagePath.equals(other.imagePath))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		if (severalPositionMove != other.severalPositionMove)
			return false;
		if (!Arrays.equals(validMoves, other.validMoves))
			return false;
		return true;
	}
	public String getImagePath() {
        return imagePath;
    }

    public ImageIcon getIcon(int width, int height) {
        return new ImageIcon(new ImageIcon(getImagePath()).getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
    }
    
    public String toString() {
        String color = "Black";
        if (getColor() == MyColor.White) {
            color = "White";
        }
        return color + " " + getName();
    }
}