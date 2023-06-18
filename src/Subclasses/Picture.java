package Subclasses;

import Hangman.Hangman;

import javax.swing.*;
import java.awt.*;

public class Picture extends JFrame {

    private JPanel picturePanel;
    private Hangman hangman;
    private ImageIcon imageIcon;

    public Picture() {
        picturePanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Draw the image on the panel if the imageIcon is not null
                if (imageIcon != null) {
                    g.drawImage(imageIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        changePicture(6);
        picturePanel.setPreferredSize(new Dimension(200, 200));
    }

    public void changePicture(int remainingAttempts) {
        String url = "../Images/hangman" + remainingAttempts + ".jpg";

        imageIcon = new ImageIcon(Picture.class.getResource(url));
        picturePanel.repaint();
    }

    private void drawImageOnPanel() {

    }

    public JPanel getPicturePanel() {
        return picturePanel;
    }
}
