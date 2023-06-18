package Hangman;

import Subclasses.Game;
import Subclasses.Keyboard;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class Hangman extends JFrame {
    Hangman() {
        initializeGUI();
    }

    private Keyboard keyboard;
    private Game game;

    public Game getGame() {
        return game;
    }

    private void initializeGUI() {
        setTitle("Hangman");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        // Create main sub objects
        Keyboard keyboard = new Keyboard(this);
        game = new Game(this);

        // Create the three sub panels
        JPanel picturePanel = new JPanel();

        picturePanel.setPreferredSize(new Dimension(200, 200));

        // Set the background colors for the panels
        picturePanel.setBackground(Color.RED);


        // Set the layout for the frame
        setLayout(new BorderLayout());

        // Create a container panel to hold the two panels on top
        JPanel topContainerPanel = new JPanel(new BorderLayout());
        topContainerPanel.add(picturePanel, BorderLayout.WEST);
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
