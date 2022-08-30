package se.liu.chrwa634.tetris.statuspanel;

import se.liu.chrwa634.tetris.Board;
import se.liu.chrwa634.tetris.BoardListener;

import javax.swing.*;
import java.awt.*;

public class CounterPanel implements BoardListener
{
    private static final int WIDTH = 80;
    private static final int HEIGHT = 30;

    private Board board;
    private JLabel label = null;

    public CounterPanel(Board board) {
        this.board = board;
        label = new JLabel();

        board.addBoardListener(this);

        label.setBackground(Color.WHITE);
        label.setOpaque(true);
        label.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        label.setVerticalTextPosition(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);

        boardChanged();
    }

    public JLabel getLabel() {
        return label;
    }

    @Override public void boardChanged() {
        label.setText("Counter: " + board.getPolyCounter());
        label.repaint();
    }
}
