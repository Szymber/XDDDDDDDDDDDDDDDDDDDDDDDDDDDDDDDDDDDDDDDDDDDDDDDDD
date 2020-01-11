package View.GameBoard;

import Controller.Controller;
import Controller.Properties;
import Model.Chessman.*;
import Model.*;
import Model.Board.*;
import Controller.MyEvent;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Font;

@SuppressWarnings("serial")
public class GameBoardPanel extends JPanel {
    
    private JButton[][] fields;

    public GameBoardPanel() {
        super();   
        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(Properties.windowWidth / 2 - 10, Properties.windowHeight - 10));
        
        JPanel board = new JPanel();
        if (Controller.getGame() != null) {
            board.setLayout(new GridLayout(Controller.getGame().getGrid().getSize().getX(), 1));
            createFields();
            for (JButton[] row : fields) {
                JPanel boardRow = new JPanel();
                boardRow.setLayout(new GridLayout(1, Controller.getGame().getGrid().getSize().getY()));
                for (JButton field : row) {
                    boardRow.add(field);
                }
                board.add(boardRow);
            }
        }
        add(board);
    }

    private void createFields() {
        if (Controller.getGame() != null) {
            Grid grid = Controller.getGame().getGrid();
            int rows = grid.getSize().getX();
            int cols = grid.getSize().getY(); 
            Chessman[] chessPieces = Controller.getGame().getChessPieces();

            fields = new JButton[rows][cols];
            // field is always a square
            int a = Properties.windowWidth / 2 / cols;
            int b = (Properties.windowHeight - 10) / rows;
            int dim = a < b ? a : b;      
            
            fields = new JButton[rows][cols];
            for (int i = 0; i < rows; i++)
                for (int j = 0; j < cols; j++) {
                    if (grid.getField(new Vector2(i,j)).isAvailable()) {
                        fields[i][j] = new JButton("" + (grid.getField(new Vector2(i,j)).getSign()));
                        fields[i][j].setFont(new Font("Arial", Font.BOLD, 30));
                        fields[i][j].setVerticalAlignment (SwingConstants.BOTTOM) ;
                        fields[i][j].setVerticalTextPosition(SwingConstants.BOTTOM);
                        fields[i][j].setHorizontalTextPosition(SwingConstants.CENTER);         
                        fields[i][j].setBackground(((i+j) % 2) == 0 ? Properties.darkFieldColor : Properties.lightFieldColor);
                        (fields[i][j]).addActionListener(new SelectBoardField(i, j));
                    } else {
                        fields[i][j] = new JButton();
                        fields[i][j].setBackground(Properties.unavailableFieldColor);
                        (fields[i][j]).addActionListener(new UnavailableFieldClicked());                
                    }
                    fields[i][j].setBorder(Properties.defaultButtonBorder);
                    fields[i][j].setPreferredSize(new Dimension(dim - 5, dim - 5));
                }
            for (Chessman chessman : chessPieces) {
                fields[chessman.getPosition().getX()][chessman.getPosition().getY()].setIcon(chessman.getIcon(dim/2, dim/2));
            }        
        }
    }

    public void disableFields() {
        for (JButton[] fieldsRow : fields) 
            for (JButton field : fieldsRow) 
                for( ActionListener al : field.getActionListeners() )
                    field.removeActionListener(al);
    }

    class UnavailableFieldClicked implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Controller.handleMyEvent(MyEvent.UnavailableFieldSelected);
            
        }
    }

    class SelectBoardField implements ActionListener {
        int i, j;
        SelectBoardField(int i, int j) { this.i = i; this.j = j; };
        public void actionPerformed(ActionEvent e) {
            if (Controller.getMoveCreator().getFrom() == null) { // 1st pressed field
                if (!Controller.getGame().getGrid().getField(new Vector2(i, j)).isEmpty()) {
                    Controller.getMoveCreator().setFrom(new Vector2(i, j));
                    Controller.handleMyEvent(MyEvent.PawnSelected);
                    fields[i][j].setBorder(Properties.checkedButtonBorder);
                    for( ActionListener al : fields[i][j].getActionListeners() )
                        fields[i][j].removeActionListener(al);
                    fields[i][j].addActionListener(new UnselectBoardField(i , j));
                } else {
                    Controller.handleMyEvent(MyEvent.EmptyFieldSelected);
                }
            } else { // 2nd pressed field
                Controller.getMoveCreator().setTo(new Vector2(i ,j));
                Controller.handleMyEvent(MyEvent.TryToMove);
            }
        }
    }

    class UnselectBoardField implements ActionListener {
        int i, j;
        UnselectBoardField(int i, int j) { this.i = i; this.j = j; };
        public void actionPerformed(ActionEvent e) {
            Controller.getMoveCreator().setFrom(null);
            Controller.handleMyEvent(MyEvent.PawnUnselected);
            fields[i][j].setBorder(Properties.defaultButtonBorder);
            for(ActionListener al : fields[i][j].getActionListeners())
                fields[i][j].removeActionListener(al);
            fields[i][j].addActionListener(new SelectBoardField(i, j));
        }
    }
}