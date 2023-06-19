package Subclasses;

import Hangman.Hangman;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

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

        gamePanel = new JPanel(new BorderLayout());
        gamePanel.setPreferredSize(new Dimension(400, 100));
        gamePanel.setBackground(Color.WHITE);

        generateSelectedWord();
        this.selectedWord = selectedWord.toUpperCase(Locale.ROOT);
        currentGuess = new StringBuilder("_".repeat(selectedWord.length()));
        remainingAttempts = 6;

        wordLabel = new JLabel("The Label", SwingConstants.CENTER);
        setLabelText();
        wordLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));

        JButton restartButton = new JButton("Restart button");
        restartButton.setText("Restart");
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hangman.restartGame();
            }
        });

        gamePanel.add(this.wordLabel, BorderLayout.CENTER);
        gamePanel.add(restartButton, BorderLayout.SOUTH);
    }

    private void setLabelText() {
        StringBuilder finalString = new StringBuilder();

        // Using HTML tags to format text and make spaces visible
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
            // Replace underscore with right guess
            for (int i = 0; i < selectedWord.length(); i++) {
                if (selectedWord.charAt(i) == guess.charAt(0)) {
                    this.currentGuess.setCharAt(i, guess.charAt(0));
                }
            }
            setLabelText();

            // If no underscore left show
            if (!currentGuess.toString().contains("_")) {
                if(JOptionPane.showConfirmDialog(null, "Congratulations! You won!", "You won!", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE) == 0)
                {
                    hangman.restartGame();
                }
                else {
                    System.exit(0);
                }
            }
        }
        else {
            remainingAttempts--;
            hangman.getPicture().changePicture(remainingAttempts);

            if (remainingAttempts == 0) {

                if(JOptionPane.showConfirmDialog(null, "Game Over! The word was: " + selectedWord.toLowerCase(Locale.ROOT), "You lost!", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE) == 0)
                {
                    hangman.restartGame();
                }
                else {
                    System.exit(0);
                }
            }
        }
    }

    public void giveHints() {
        TreeMap<Character, Integer> letterCountMap = new TreeMap<>();

        // Count the occurrence of each letter
        for (char c : selectedWord.toCharArray()) {
            if (Character.isLetter(c)) {
                c = Character.toLowerCase(c); // Ignore case
                letterCountMap.put(c, letterCountMap.getOrDefault(c, 0) + 1);
            }
        }

        if(letterCountMap.size() < 3) {
            return;
        }

        // Sort the map by letter (key) in ascending order
        List<Map.Entry<Character, Integer>> sortedEntries = new ArrayList<>(letterCountMap.entrySet());
        Collections.sort(sortedEntries, Comparator.comparing(Map.Entry::getKey));

        this.hangman.getKeyboard().clickKeyAtLetter(letterCountMap.lastKey());
    }

    private void generateSelectedWord() {
        // Database connection details
        String url = "jdbc:mysql://localhost:3306/words_db";
        String username = "root";
        String password = "";

        // SQL query to fetch a random record
        String query = "SELECT word FROM word ORDER BY RAND() LIMIT 1";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                selectedWord = rs.getString("word");
            } else {
                // Set selectedWord to "ananas" if no records were found
                System.out.println("No records found.");
                selectedWord = "ananas";
            }
        } catch (SQLException e) {
            // Set selectedWord to "ananas" if no database was found and
            e.printStackTrace();
            selectedWord = "ananas";
        }
    }

    public JPanel getGamePanel() {
        return gamePanel;
    }
}
