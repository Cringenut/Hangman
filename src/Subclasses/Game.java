package Subclasses;

import Hangman.Hangman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

public class Game extends JFrame {

    private String[] words = {"hangman", "guess", "ananas"};
    private String selectedWord;
    private StringBuilder currentGuess;
    private int remainingAttempts;

    private final JPanel gamePanel;
    private Hangman hangman;
    private JTextField guessField;
    private JLabel wordLabel;

    public Game(Hangman hangman) {
        this.hangman = hangman;
        gamePanel = new JPanel();
        gamePanel.setPreferredSize(new Dimension(400, 100));
        gamePanel.setBackground(Color.GREEN);

        this.selectedWord = "ananas";
        this.selectedWord = this.selectedWord.toUpperCase(Locale.ROOT);
        currentGuess = new StringBuilder("_".repeat(selectedWord.length()));
        remainingAttempts = 6;

        wordLabel = new JLabel();
        setLabelText();
        wordLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));

        guessField = new JTextField();
        guessField.setPreferredSize(new Dimension(100, 20));
        gamePanel.add(this.guessField);
        gamePanel.add(this.wordLabel);
    }

    private void setLabelText() {
        StringBuilder finalString = new StringBuilder();

        finalString.append("<html>");
        for (int i = 0; i < currentGuess.length(); i++) {
            finalString.append(currentGuess.charAt(i));
            finalString.append("&nbsp;");
        }
        finalString.append("<html>");

        wordLabel.setText(finalString.toString());
    }

    public void processGuess(String guess) {
        if (selectedWord.contains(guess)) {
            for (int i = 0; i < selectedWord.length(); i++) {
                if (selectedWord.charAt(i) == guess.charAt(0)) {
                    this.currentGuess.setCharAt(i, guess.charAt(0));
                }
            }
            setLabelText();

            if (!currentGuess.toString().contains("_")) {
                JOptionPane.showMessageDialog(this, "Congratulations! You won!");
            }
        }
        else {
            remainingAttempts--;

            if (remainingAttempts == 0) {
                JOptionPane.showMessageDialog(this, "Game Over! The word was: " + selectedWord.toLowerCase(Locale.ROOT));
            }
        }
    }

    public JPanel getGamePanel() {
        return gamePanel;
    }


}
