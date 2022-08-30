package se.liu.chrwa634.tetris.statuspanel;

import se.liu.chrwa634.tetris.Board;
import se.liu.chrwa634.tetris.BoardListener;

import javax.swing.*;
import java.awt.*;

public class FallHandlerPanel implements BoardListener
{
    private final static int WIDTH = 80;
    private final static int HEIGHT = 30;

    private Board board;
    private JLabel label = new JLabel();

    public FallHandlerPanel(Board board) {
	this.board = board;

	board.addBoardListener(this);

	label.setBackground(Color.RED);
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
	label.setText(board.getFallHandler().getName());
	label.repaint();
    }
}
