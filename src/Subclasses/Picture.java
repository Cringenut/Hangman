package Subclasses;

import Hangman.Hangman;

import javax.swing.*;
import java.awt.*;

public class Picture extends JFrame {

    private final JPanel picturePanel;
    private Hangman hangman;

    public Picture() {
        picturePanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Load the image from a URL or file
                ImageIcon imageIcon = new ImageIcon("https://picsum.photos/200/300"); // Replace with your image URL or file path

                // Draw the image on the panel
                g.drawImage(imageIcon.getImage(), 0, 0, 123, 142, this);
            }
        };

        picturePanel.setPreferredSize(new Dimension(200, 200));
    }

    public JPanel getPicturePanel() {
        return picturePanel;
    }
}
