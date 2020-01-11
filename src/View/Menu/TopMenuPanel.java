package View.Menu;



import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import View.TextStyle;
import Controller.Controller;
import Controller.MyEvent;
import Controller.Properties;
import View.TxtFileChooser;




@SuppressWarnings("serial")
public class TopMenuPanel extends JPanel {
    
    private JButton prevMove;
    private JButton nextMove; 

    private JButton newDefault;
    private JButton newCustom;
    
    private JButton saveGame;
    private JButton loadGame;
    
    private JTextArea info;

    TopMenuPanel() {
        JPanel historyPanel = new JPanel();
        historyPanel.setPreferredSize(new Dimension(Properties.windowWidth/2, 100));
            prevMove = new JButton(Properties.prevMoveIcon);
            prevMove.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Controller.handleMyEvent(MyEvent.MoveBack);
                    }
                }
            );
        historyPanel.add(prevMove);
            nextMove = new JButton(Properties.nextMoveIcon);
            nextMove.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Controller.handleMyEvent(MyEvent.MoveForward);
                    }
                }
            );
        historyPanel.add(nextMove);
            

        JPanel newGamePanel = new JPanel();
        newGamePanel.setPreferredSize(new Dimension(Properties.windowWidth/2, 60));
            newDefault = new JButton("New default game");
            newDefault.setPreferredSize(new Dimension(200, 50));
            newDefault.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Controller.setFilename(Properties.defaultGameFile);
                        Controller.handleMyEvent(MyEvent.LoadGame);
                    }
                }
            );
        newGamePanel.add(newDefault);
            newCustom = new JButton("New custom game");
            newCustom.setPreferredSize(new Dimension(200, 50));
            newCustom.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Controller.handleMyEvent(MyEvent.GamePropertiesSelecting);
                    }
                }
            );
        newGamePanel.add(newCustom);

            JPanel saveLoadGamePanel = new JPanel();
            saveLoadGamePanel.setPreferredSize(new Dimension(Properties.windowWidth/2, 60));
                saveGame = new JButton("Save game");
                saveGame.setPreferredSize(new Dimension(200, 50));
                saveGame.addActionListener(new FileHandle(MyEvent.SaveGame));
            saveLoadGamePanel.add(saveGame);
                loadGame = new JButton("Load game");
                loadGame.setPreferredSize(new Dimension(200, 50));
                loadGame.addActionListener(new FileHandle(MyEvent.LoadGame));
            saveLoadGamePanel.add(loadGame);
            
            
            JPanel infoPanel = new JPanel();
            infoPanel.setPreferredSize(new Dimension(Properties.windowWidth/2, 90));
                info = new JTextArea(6, 35);
                info.setPreferredSize(new Dimension(Properties.windowWidth/3, 75));
                info.setLineWrap(true);
                info.setEditable(false);
            infoPanel.add(info);
    
            add(historyPanel);
            add(newGamePanel);
            add(saveLoadGamePanel);
            add(infoPanel);
    }

    public void showInfo(TextStyle textStyle, String message) {
        info.setText(message);
        info.setFont(textStyle.getFont());
        info.setForeground(textStyle.getColor());
    }

    public class FileHandle implements ActionListener {
        MyEvent e;
        FileHandle(MyEvent e) { this.e = e; }
        public void actionPerformed(ActionEvent e) {
            String filename = new TxtFileChooser().getFilename();
            if (filename != null)
                if (filename.endsWith(".txt")) {
                    Controller.setFilename(filename);
                    Controller.handleMyEvent(this.e);
                } else {
                    Controller.handleMyEvent(MyEvent.InvalidFilename);
                }
            //else file choosing was canceled (no event) 
        }
    }


    public JButton getPrevMoveButton() { return prevMove; }
    public JButton getNextMoveButton() { return nextMove; }
    public JButton getSaveGameButton() { return saveGame; }
}