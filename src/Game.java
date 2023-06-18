import javax.swing.*;
import java.awt.*;

public class Game extends JFrame {
    Game() {
        initializeGUI();
    }

    private void generateKeyboardPanel() {

    }

    private void initializeGUI() {
        setTitle("Hangman");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create the three panels
        JPanel picturePanel = new JPanel();
        JPanel gamePanel = new JPanel();
        JPanel panel3 = new JPanel((new GridLayout(2, 10)));
        picturePanel.setPreferredSize(new Dimension(200, 200));
        gamePanel.setPreferredSize(new Dimension(300, 200));
        panel3.setPreferredSize(new Dimension(500, 200));

        // Set the background colors for the panels
        picturePanel.setBackground(Color.RED);
        gamePanel.setBackground(Color.GREEN);
        panel3.setBackground(Color.YELLOW);

        // Set the layout for the frame
        setLayout(new BorderLayout());

        // Create a container panel to hold the two panels on top
        JPanel topContainerPanel = new JPanel(new BorderLayout());
        topContainerPanel.add(picturePanel, BorderLayout.WEST);
        topContainerPanel.add(gamePanel, BorderLayout.EAST);

        // Add the container panel and the third panel to the frame
        add(topContainerPanel, BorderLayout.NORTH);
        add(panel3, BorderLayout.CENTER);

        for (int i = 0; i < 5; i++)
        {
            JButton button = new JButton();
            button.setPreferredSize(new Dimension(40, 40));
            panel3.add(button);
        }

        pack();
        setVisible(true);
    }
}
