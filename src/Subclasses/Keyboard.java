package Subclasses;

import Hangman.Hangman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;

public class Keyboard extends JFrame {

    private final JPanel keyboardPanel;
    private Hangman hangman;

    // Letters to be displayed on the keyboard
    String[] letters = {
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
            "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
    };

    public Keyboard(Hangman hangman) {
        this.hangman = hangman;
        this.keyboardPanel = new JPanel((new GridLayout(2, 13)));
        this.keyboardPanel.setPreferredSize(new Dimension(600, 100));
        this.keyboardPanel.setBackground(Color.WHITE);

        // Loop to create buttons for each letter
        for (String letter : letters) {
            JButton button = new JButton(letter);
            button.setFont(new Font("Arial", Font.PLAIN, 10));
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String letter = button.getText();
                    hangman.getGame().processGuess(letter);
                    button.setEnabled(false);
                }
            });

            keyboardPanel.add(button);
        }

        add(keyboardPanel, BorderLayout.CENTER);
    }

    public JPanel getKeyboardPanel() {
        return keyboardPanel;
    }

    private JButton getButtonAtIndex(int index) {
        Component[] components = keyboardPanel.getComponents();
        if (index >= 0 && index < components.length && components[index] instanceof JButton) {
            return (JButton) components[index];
        }
        return null;
    }

    public void clickKeyAtLetter(Character letter) {
        // Find a button by its letter and click it
        int index = Arrays.asList(letters).indexOf(letter.toString().toUpperCase(Locale.ROOT));
        this.getButtonAtIndex(index).doClick();
    }

}