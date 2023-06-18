package Hangman;

import Subclasses.Keyboard;

import javax.swing.*;
import java.awt.*;

public class Hangman extends JFrame {
    Hangman() {
        initializeGUI();
    }

    private void initializeGUI() {
        setTitle("Hangman");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        // Create main sub objects
        Keyboard keyboard = new Keyboard(this);

        // Create the three sub panels
        JPanel picturePanel = new JPanel();
        JPanel gamePanel = new JPanel();
        JPanel keyboardPanel = keyboard.getKeyboardPanel();

        picturePanel.setPreferredSize(new Dimension(200, 200));
        gamePanel.setPreferredSize(new Dimension(400, 200));


        // Set the background colors for the panels
        picturePanel.setBackground(Color.RED);
        gamePanel.setBackground(Color.GREEN);

        // Set the layout for the frame
        setLayout(new BorderLayout());

        // Create a container panel to hold the two panels on top
        JPanel topContainerPanel = new JPanel(new BorderLayout());
        topContainerPanel.add(picturePanel, BorderLayout.WEST);
        topContainerPanel.add(gamePanel, BorderLayout.EAST);

        // Add the container panel and the third panel to the frame
        add(topContainerPanel, BorderLayout.NORTH);
        add(keyboardPanel, BorderLayout.CENTER);

        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(100, 20));
        gamePanel.add(textField);


        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Hangman();
            }
        });
    }
}
