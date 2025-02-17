import java.awt.Cursor;

import javax.swing.JFrame;

public class Window extends JFrame {

    public final String title = "Underground!";

    public Window() {
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        GamePanel gamePanel = new GamePanel(this);
        add(gamePanel);
        pack();

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
