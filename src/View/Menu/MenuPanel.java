package View.Menu;

import View.*;


import java.awt.*;
import javax.swing.*;


import Controller.Controller;


@SuppressWarnings("serial")
public class MenuPanel extends JPanel {

    private TopMenuPanel topMenuPanel;
    private BottomMenuPanel bottomMenuPanel;

    public MenuPanel() {
        super();
        setLayout(new GridLayout(2, 1));
        
        topMenuPanel = new TopMenuPanel();
        bottomMenuPanel = new BottomMenuPanel();
            
        add(topMenuPanel);
        add(bottomMenuPanel);
    }
    
    public void showInfo(TextStyle textStyle, String message) {
        topMenuPanel.showInfo(textStyle, message);
    }

    public void refresh() {
        bottomMenuPanel.refresh();
        if (Controller.getGame() == null) {
            topMenuPanel.getPrevMoveButton().setEnabled(false);
            topMenuPanel.getNextMoveButton().setEnabled(false);
            topMenuPanel.getSaveGameButton().setEnabled(false);
        } else {
            topMenuPanel.getPrevMoveButton().setEnabled(true);
            topMenuPanel.getNextMoveButton().setEnabled(true);
            topMenuPanel.getSaveGameButton().setEnabled(true);
        }
        repaint();
        revalidate();
    }

    public TopMenuPanel getTopMenuPanel() {
        return topMenuPanel;
    }
}


