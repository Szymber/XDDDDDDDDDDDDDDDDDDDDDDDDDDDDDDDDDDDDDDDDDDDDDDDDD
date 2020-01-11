package View;

import View.Menu.*;
import View.GameBoard.*;
import Controller.Controller;
import Controller.Properties;

import java.awt.*;
import javax.swing.JFrame;



@SuppressWarnings("serial")
public class GameWindow extends JFrame implements IGameWindow {

    GameBoardPanel gameBoardPanel;
    MenuPanel menuPanel;
            
    public GameWindow() {
        super("My cool game");
        Controller.setGameWindow(this);
        setResizable(false);
        setSize(Properties.windowWidth, Properties.windowHeight);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 2));
        
        gameBoardPanel = new GameBoardPanel();
        menuPanel = new MenuPanel();
        showInfo();
        menuPanel.getTopMenuPanel().getPrevMoveButton().setEnabled(false);
        menuPanel.getTopMenuPanel().getNextMoveButton().setEnabled(false);
        menuPanel.getTopMenuPanel().getSaveGameButton().setEnabled(false);
        add(gameBoardPanel);
        add(menuPanel);
        setVisible(true);
    }

    public void updateGameWindow() {
        remove(menuPanel);        
        remove(gameBoardPanel);
        gameBoardPanel = new GameBoardPanel();
        menuPanel.refresh();
        add(gameBoardPanel);
        add(menuPanel);
        
        repaint();
        revalidate();
    }

    public void winGame() {
        menuPanel.getTopMenuPanel().getPrevMoveButton().setEnabled(false);
        menuPanel.getTopMenuPanel().getNextMoveButton().setEnabled(false);
        menuPanel.getTopMenuPanel().getSaveGameButton().setEnabled(false);
        gameBoardPanel.disableFields();
    }

    public void showInfo() {
        menuPanel.showInfo(Controller.getTextStyle(), Controller.getMessage());
    }
}