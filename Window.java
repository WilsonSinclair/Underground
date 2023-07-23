import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends JFrame {


    public Window() {
        setTitle("Underground!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GamePanel gamePanel = new GamePanel();
        add(gamePanel);
        pack();

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
