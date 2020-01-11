package Model.Board;

import java.util.ArrayList;

import Model.Vector2;

public class GridCreator {
    private static GridCreator instance = null;
    private GridCreator() { }
    public static GridCreator getInstance() {
        if (instance == null)
            instance = new GridCreator();
        return instance;
    }

    private ArrayList<Vector2> unavailableFields = null;
    private Vector2 size = null;
   
    public Grid createGrid() {
        if (size == null || unavailableFields == null)
            return null;
        else if (size.getX() <= 0 || size.getY() <= 0) {
            return null;
        }
        for (Vector2 field : unavailableFields) {
            if (field.getX() > size.getX())
                return null;
            if (field.getY() > size.getY())
                return null;
        }

        return new Grid(size, unavailableFields);
    }
    public void setSize(Vector2 size) {
        this.size = size;
    }
    public void setUnavailableFields(ArrayList<Vector2> unavailableFields) {
        this.unavailableFields = unavailableFields;
    }
    public void addUnavailableField(Vector2 unavailableField) {
        this.unavailableFields.add(unavailableField);
    }
    public void removeUnavailableField(Vector2 unavailableField) {
        unavailableFields.remove(unavailableField);
    }

    public Vector2 getSize() { return size; }
    public ArrayList<Vector2> getUnavailableFields() { return unavailableFields; }
    public void reset() {
        size = null;
        unavailableFields = null;
    }
}