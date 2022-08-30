package se.liu.chrwa634.tetris;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class StartScreen
{
    public static final int BORDER_SIZE = 100;
    public static final int TIME_OPEN_MS = 3000;

    public static void show() {
	final URL startImage = ClassLoader.getSystemResource("images/tetris_start_screen.png");
	ImageIcon tetrisIcon = new ImageIcon(startImage);
	JLabel label = new JLabel(tetrisIcon);

	JFrame frame = new JFrame("Tetris");


	frame.setLayout(new BorderLayout());
	frame.add(label, BorderLayout.CENTER);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(new Dimension(tetrisIcon.getIconWidth() + BORDER_SIZE, tetrisIcon.getIconHeight() + BORDER_SIZE));
	frame.setVisible(true);
	frame.setLocationRelativeTo(null);

	try {
	    Thread.sleep(TIME_OPEN_MS);
	} catch (InterruptedException ignored) {
	    Thread.currentThread().interrupt();
	}

	frame.setVisible(false);
	frame.dispose();
    }
}
