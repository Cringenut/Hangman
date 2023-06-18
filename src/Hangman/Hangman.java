package Hangman;

import Subclasses.Game;
import Subclasses.Keyboard;
import Subclasses.Picture;

import javax.swing.*;
import java.awt.*;

public class Hangman extends JFrame {
    Hangman() {
        initializeGUI();
    }

    private Keyboard keyboard;
    private Game game;
    private Picture picture;


    public Keyboard getKeyboard() {
        return keyboard;
    }
    public Game getGame() {
        return game;
    }
    public Picture getPicture() {
        return picture;
    }

    private void initializeGUI() {
        setTitle("Hangman");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create main sub objects
        this.keyboard = new Keyboard(this);
        this.game = new Game(this);
        this.picture = new Picture();
        this.game.giveHints();


        // Create the three sub panels
        JPanel picturePanel = new JPanel();

        picturePanel.setPreferredSize(new Dimension(200, 200));

        // Set the background colors for the panels
        picturePanel.setBackground(Color.RED);

        // Set the layout for the frame
        setLayout(new BorderLayout());

        // Create a container panel to hold the two panels on top
        JPanel topContainerPanel = new JPanel(new BorderLayout());
        topContainerPanel.add(picture.getPicturePanel(), BorderLayout.WEST);
        topContainerPanel.add(game.getGamePanel(), BorderLayout.EAST);

        // Add the container panel and the third panel to the frame
        add(topContainerPanel, BorderLayout.NORTH);
        add(keyboard.getKeyboardPanel(), BorderLayout.CENTER);

        pack();
        setVisible(true);
    }

    public void restartGame() {
        String[] args = new String[0]; // Or String[] args = {};
        main(args);
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Hangman();
            }
        });
    }
}
