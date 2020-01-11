package View.Menu;

import View.Menu.BottomMenuType;
import Controller.Properties;
import Model.*;
import Controller.Controller;
import Controller.MyEvent;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import javax.swing.border.Border;
import javax.swing.JList;
import java.util.ArrayList;


@SuppressWarnings("serial")
public class BottomMenuPanel extends JPanel {
    private JPanel componentsPanel;
    private JSlider sizeX;
    private JSlider sizeY;
    private JSlider pawnNumber;
    private JList<String> pawnList;
    private JButton fields[][];


    BottomMenuPanel() {
        super();
        setLayout(new GridLayout(1,1));
        componentsPanel = new JPanel();
        add(componentsPanel);
        refresh();
    }

    public void refresh() {
        remove(componentsPanel);
        componentsPanel = new JPanel();

        BottomMenuType bottomMenuType = Controller.getBottomMenuType();
        if (bottomMenuType == BottomMenuType.GamePropertiesSelecting) {
            GamePropertiesSelecting();
        } else if (bottomMenuType == BottomMenuType.UnavailableFieldsSelecting) {
            UnavailableFieldsSelecting();
        } else if (bottomMenuType == BottomMenuType.PawnsPlacing) {
            PawnsPlacing();
        }
        //else [Default] - empty panel

        add(componentsPanel);
    }

    private void GamePropertiesSelecting() {        
        componentsPanel.setLayout(new GridLayout(3, 1));

            JPanel sizePanel = new JPanel();
                JLabel sizePanelInfo = new JLabel();
                sizePanelInfo.setText("Select size of new game board");
                sizePanelInfo.setPreferredSize(new Dimension(Properties.windowWidth/2, 30));
                sizePanelInfo.setHorizontalAlignment(SwingConstants.CENTER);   
            sizePanel.add(sizePanelInfo);
                
                JPanel sizeXPanel = new JPanel();
                sizeXPanel.setLayout(new GridLayout(2,1));
                    JLabel sizeXInfo = new JLabel("Rows:");
                    sizeXInfo.setHorizontalAlignment(SwingConstants.CENTER);   
                sizeXPanel.add(sizeXInfo);
                    sizeX = new JSlider(JSlider.HORIZONTAL, 3, 8, 4);
                    sizeX.setMajorTickSpacing(1);
                    sizeX.setPaintTicks(true);
                    sizeX.setPaintLabels(true);
                sizeXPanel.add(sizeX);
            sizePanel.add(sizeXPanel);
                    
            JPanel sizeYPanel = new JPanel();
                sizeYPanel.setLayout(new GridLayout(2,1));
                    JLabel sizeYInfo = new JLabel("Cols:");
                    sizeYInfo.setHorizontalAlignment(SwingConstants.CENTER);   
                sizeYPanel.add(sizeYInfo);
                    sizeY = new JSlider(JSlider.HORIZONTAL, 3, 8, 4);
                    sizeY.setMajorTickSpacing(1);
                    sizeY.setPaintTicks(true);
                    sizeY.setPaintLabels(true);
                    sizeYPanel.add(sizeY);
            sizePanel.add(sizeYPanel);;
        
        componentsPanel.add(sizePanel);

            JPanel pawnPanel = new JPanel();
                JPanel pawnTypePanel = new JPanel();            
                pawnTypePanel.setLayout(new GridLayout(2,1));
                JLabel pawnTypeInfo = new JLabel("Select pawn: ");
                    pawnTypeInfo.setHorizontalAlignment(SwingConstants.CENTER);   
                pawnTypePanel.add(pawnTypeInfo);
                    pawnList = new JList<String>(Properties.pawnList);
                    pawnList.setSelectedIndex(0);
                pawnTypePanel.add(pawnList);
            pawnPanel.add(pawnTypePanel);
            
                JPanel pawnNumberPanel = new JPanel();            
                pawnNumberPanel.setLayout(new GridLayout(2,1));
                    JLabel numberInfo = new JLabel("Number of each kolor: ");
                    numberInfo.setHorizontalAlignment(SwingConstants.CENTER);   
                pawnNumberPanel.add(numberInfo);
                pawnNumber = new JSlider(JSlider.HORIZONTAL, 1, 2, 2);
                pawnNumber.setMajorTickSpacing(1);
                pawnNumber.setPaintLabels(true);   
                pawnNumberPanel.add(pawnNumber); 
            pawnPanel.add(pawnNumberPanel);
        
        componentsPanel.add(pawnPanel);
    
        
        JPanel confirmButtonPanel = new JPanel();
        JButton confirmButton = new JButton("Confirm");
            confirmButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Controller.handleMyEvent(MyEvent.UnavailableFieldsSelecting);
                    }
                }
            );
            confirmButtonPanel.add(confirmButton);
    
        componentsPanel.add(confirmButtonPanel);
    }

    private void UnavailableFieldsSelecting() {
        int sizeX = this.sizeX.getValue();
        int sizeY = this.sizeY.getValue();
            
        Controller.getGridCreator().setSize(new Vector2(sizeX, sizeY));
        Controller.getGridCreator().setUnavailableFields(new ArrayList<Vector2>());
        
        Controller.getGameCreator().setPawnType(pawnList.getSelectedValue());    
        Controller.getGameCreator().setpawnNumber(pawnNumber.getValue() * 2);

            
        componentsPanel.setLayout(new FlowLayout());

            JLabel selectUnavailableInfo = new JLabel("Select unavailable fields (max " + (int)(sizeX*sizeY*0.2) +") for new game");
                selectUnavailableInfo.setHorizontalAlignment(SwingConstants.CENTER);   
                selectUnavailableInfo.setPreferredSize(new Dimension(Properties.windowWidth/2, 30));
        componentsPanel.add(selectUnavailableInfo);

            JPanel smallBoard = new JPanel();
            smallBoard.setLayout(new GridLayout(sizeX, 1));
            
            fields = new JButton[sizeX][sizeY];
            
            for (int i = 0; i < sizeX; i++) {
                JPanel smallBoardRow = new JPanel();
                smallBoardRow.setLayout(new GridLayout(1, sizeY)); 
                for (int j = 0; j < sizeY; j++) {
                    fields[i][j] = new JButton();
                    fields[i][j].setBackground(((i+j) % 2) == 0 ? Properties.darkFieldColor : Properties.lightFieldColor);
                    (fields[i][j]).addActionListener(new AddUnavailableField(i, j));
                    fields[i][j].setBorder(Properties.defaultSmallButtonBorder);
                    fields[i][j].setPreferredSize(new Dimension(40,40));
                    smallBoardRow.add(fields[i][j]);
                }
                smallBoard.add(smallBoardRow);
            }
        componentsPanel.add(smallBoard);

            JPanel confirmButtonPanel = new JPanel();
            confirmButtonPanel.setPreferredSize(new Dimension(Properties.windowWidth/2, 60));
            JButton confirmButton = new JButton("Confirm");
            confirmButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Controller.handleMyEvent(MyEvent.PawnsPlacing);
                    }
                }
            );
            confirmButtonPanel.add(confirmButton);
        componentsPanel.add(confirmButtonPanel);            
    }

    private void PawnsPlacing() {
        int rows = Controller.getGridCreator().getSize().getX();
        int cols = Controller.getGridCreator().getSize().getY();
        componentsPanel.setLayout(new FlowLayout());
                        
            JLabel placePawnsInfo = new JLabel("Place pawns for new game (" + Controller.getGameCreator().getPawnNumber() / 2 + "x White, then " + Controller.getGameCreator().getPawnNumber() / 2 + "x Black)");
                placePawnsInfo.setHorizontalAlignment(SwingConstants.CENTER);   
                placePawnsInfo.setPreferredSize(new Dimension(Properties.windowWidth/2, 30));   
        componentsPanel.add(placePawnsInfo);

        JPanel smallBoard = new JPanel();
        smallBoard.setLayout(new GridLayout(rows, 1));
        
        fields = new JButton[rows][cols];
        for (int i = 0; i < rows; i++) {
            JPanel smallBoardRow = new JPanel();
            smallBoardRow.setLayout(new GridLayout(1, cols));            
            for (int j = 0; j < cols; j++) {
                fields[i][j] = new JButton();
                fields[i][j].setBackground(((i+j) % 2) == 0 ? Properties.darkFieldColor : Properties.lightFieldColor);
                (fields[i][j]).addActionListener(new PawnsPlacing(i, j));
                fields[i][j].setBorder(Properties.defaultSmallButtonBorder);
                fields[i][j].setPreferredSize(new Dimension(40,40));
                smallBoardRow.add(fields[i][j]);
            }
            smallBoard.add(smallBoardRow);
        }
        for (Vector2 unavailableField : Controller.getGridCreator().getUnavailableFields()) {
            for(ActionListener al : fields[unavailableField.getX()][unavailableField.getY()].getActionListeners())
                fields[unavailableField.getX()][unavailableField.getY()].removeActionListener(al);
            fields[unavailableField.getX()][unavailableField.getY()].setBackground(Properties.unavailableFieldColor);
        }
        componentsPanel.add(smallBoard);        
    } 
    
    public class AddUnavailableField implements ActionListener {
        int i, j;
        public AddUnavailableField(int i, int j) { this.i = i; this.j = j; }
        public void actionPerformed(ActionEvent e) {
            if (Controller.getGridCreator().getUnavailableFields().size() + 1 < 0.2 * Controller.getGridCreator().getSize().getX() * Controller.getGridCreator().getSize().getY()) {
                for(ActionListener al : fields[i][j].getActionListeners())
                    fields[i][j].removeActionListener(al);
                fields[i][j].addActionListener(new RemoveUnavailableField(i, j));
                Controller.getGridCreator().addUnavailableField(new Vector2(i, j));
                fields[i][j].setBackground(Properties.unavailableFieldColor);
            } else {
                Controller.handleMyEvent(MyEvent.CanNotAddUnavailableField);
            }

        }
    }

    public class RemoveUnavailableField implements ActionListener {
        int i, j;
        public RemoveUnavailableField(int i, int j) { this.i = i; this.j = j; }
        public void actionPerformed(ActionEvent e) {
            Controller.getGridCreator().removeUnavailableField(new Vector2(i, j));
            for(ActionListener al : fields[i][j].getActionListeners())
                fields[i][j].removeActionListener(al);
            fields[i][j].addActionListener(new AddUnavailableField(i, j));
            fields[i][j].setBackground(((i + j) % 2 == 0) ? Properties.darkFieldColor : Properties.lightFieldColor);
        }
    }
    
    public class PawnsPlacing implements ActionListener {
        int i, j;
        PawnsPlacing(int i, int j) { this.i = i; this.j = j; }

        public void actionPerformed(ActionEvent e) {
            int pawnsPlaced = Controller.getGameCreator().addPawnPosition(new Vector2(i, j));
            int pawnNumber = Controller.getGameCreator().getPawnNumber();

            Border border = Properties.smallBorderWhitePosition;
            
            if (pawnsPlaced == pawnNumber)
                Controller.handleMyEvent(MyEvent.NewCustomGame);
            else if (pawnsPlaced > pawnNumber / 2)
                border = Properties.smallBorderBlackPosition;
            
            for(ActionListener al : fields[i][j].getActionListeners())
                fields[i][j].removeActionListener(al);
            fields[i][j].setBorder(border);
        }
    }
}