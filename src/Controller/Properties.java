package Controller;

import View.*;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.Border;
import java.awt.Font;
import java.awt.Color;

public abstract class Properties {

    public static final int windowHeight = 900;
    public static final int windowWidth = 1400;
    public static final Color  lightFieldColor = new Color(255,255,255);
    public static final Color  darkFieldColor = new Color(89, 100, 255);
    public static final Color  unavailableFieldColor = new Color(245,255,105);

    public static final String[] pawnList = new String[]{"Knight", "Queen", "Rook", "King"};
    public static final Border checkedButtonBorder = new LineBorder(Color.GREEN, 5);
    public static final Border defaultButtonBorder = new LineBorder(Color.BLACK, 3);

    public static final Border checkedSmallButtonBorder = new LineBorder(unavailableFieldColor, 2);
    public static final Border defaultSmallButtonBorder = new LineBorder(Color.BLACK, 2);

    public static final Border smallBorderBlackPosition = new LineBorder(Color.BLACK, 7);
    public static final Border smallBorderWhitePosition = new LineBorder(Color.GRAY, 7);

    public static final TextStyle errorTextStyle = new TextStyle(new Font("Arial", Font.BOLD, 15), Color.RED);
    public static final TextStyle neutralTextStyle = new TextStyle(new Font("Arial", Font.BOLD, 13), Color.BLACK);
    public static final TextStyle successTextStyle = new TextStyle(new Font("Arial", Font.BOLD, 15), Color.GREEN);

    public static final ImageIcon prevMoveIcon = new ImageIcon(new ImageIcon("resources/img/prevMove.png").getImage().getScaledInstance(90, 70, Image.SCALE_DEFAULT));        
    public static final ImageIcon nextMoveIcon = new ImageIcon(new ImageIcon("resources/img/nextMove.png").getImage().getScaledInstance(90, 70, Image.SCALE_DEFAULT));        
    public static String defaultGameFile = "resources/defaultGame.txt";
    public static final String chessmanPath = "resources/img/";        
    
}