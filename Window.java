import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends JFrame {

    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;

    public Window() {
        setTitle("Underground!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(new Dimension(WIDTH, HEIGHT));
        setLocationRelativeTo(null);
        setVisible(true);
    }


}
