import javax.swing.*;
import java.awt.*;

public class Keyboard extends JFrame {
    private JFrame f = new JFrame("Keyboard");

    private JPanel keyboard = new JPanel();

    private static final String[][] key = {
            {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M"},
            {"N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"}
    };

    public Keyboard() {
        keyboard.setLayout(new GridBagLayout());

        JPanel pRow;
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.WEST;
        c.weightx = 1d;

        for (int row = 0; row < key.length; ++row) {
            pRow = new JPanel(new GridBagLayout());

            c.gridy = row;

            for (int col = 0; col < key[row].length; ++col)
                pRow.add(new JButton(key[row][col]));

            keyboard.add(pRow, c);
        }

        f.add(keyboard);
    }

    public void launch() {
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(true);
    }

    public static void main(String[] args) {
        Keyboard ui = new Keyboard();
        ui.launch();
    }
}