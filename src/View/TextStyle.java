package View;


import java.awt.Font;
import java.awt.Color;

public class TextStyle {
    private Font font;
    private Color color;

    public TextStyle(Font font, Color color) {
        this.font = font;
        this.color = color;
    }

    public Font getFont() {
        return font;
    }

    public Color getColor() {
        return color;
    }
}