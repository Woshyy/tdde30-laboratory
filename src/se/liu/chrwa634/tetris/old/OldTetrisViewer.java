package se.liu.chrwa634.tetris.old;

import se.liu.chrwa634.tetris.Board;

import javax.swing.*;
import java.awt.*;

public class OldTetrisViewer
{
    public static void show(Board board) {
        JFrame frame = new JFrame("Tetris");
        JTextArea textArea = new JTextArea(BoardToTextConverter.convertToText(board), board.getHeight(), board.getWidth());
	frame.setLayout(new BorderLayout());
	frame.add(textArea, BorderLayout.CENTER);
	textArea.setFont(new Font("Monospaced", Font.PLAIN, 20));
	frame.pack();
	frame.setVisible(true);
    }
}
