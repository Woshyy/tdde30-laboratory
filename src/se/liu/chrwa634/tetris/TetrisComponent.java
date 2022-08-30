package se.liu.chrwa634.tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TetrisComponent extends JComponent implements BoardListener
{
    private static final int BLOCK_SIZE = 25;

    private Board board;
    private Dimension preferredSize;

    public TetrisComponent(final Board board) {
        this.board = board;
        this.preferredSize = new Dimension(BLOCK_SIZE * board.getWidth(), BLOCK_SIZE * board.getHeight());

        board.addBoardListener(this);

        final InputMap in = this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        in.put(KeyStroke.getKeyStroke("LEFT"), "LEFT");
        in.put(KeyStroke.getKeyStroke("RIGHT"), "RIGHT");
        in.put(KeyStroke.getKeyStroke("UP"), "UP");
        in.put(KeyStroke.getKeyStroke("DOWN"), "DOWN");
        in.put(KeyStroke.getKeyStroke("ESCAPE"), "PAUSE");

        final ActionMap act = this.getActionMap();
        act.put("LEFT", new MoveAction(Direction.LEFT));
        act.put("RIGHT", new MoveAction(Direction.RIGHT));
        act.put("UP", new RotateAction(Direction.RIGHT));
        act.put("DOWN", new RotateAction(Direction.LEFT));
        act.put("PAUSE", new PauseAction());
    }

    @Override public Dimension getPreferredSize() {
	return preferredSize;
    }

    @Override protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        final Graphics2D g2d = (Graphics2D) g;
        int margin = board.getMargin();

        for (int x = 0; x < board.getWidth(); x++) {
            for (int y = 0; y < board.getHeight(); y++) {
                TetrisViewer.draw(g2d, board.getBlock(x + margin, y + margin), x, y, BLOCK_SIZE);
            }
        }

        Poly poly = board.getFalling();
        if (poly != null){
            for (int x = 0; x < poly.getWidth(); x++) {
                for (int y = 0; y < poly.getHeight(); y++) {
                    SquareType block = poly.getBlock(x, y);
                    if (!block.equals(SquareType.EMPTY)) {
                        TetrisViewer.draw(g2d, poly.getBlock(x, y), poly.getX() + x, poly.getY() + y, BLOCK_SIZE);
                    }

                }
            }
        }
    }

    @Override public void boardChanged() {
        this.repaint();
    }

    private class MoveAction extends AbstractAction {
        private Direction direction;
        private MoveAction(Direction direction) {
            this.direction = direction;
        }

        @Override public void actionPerformed(final ActionEvent e) {
            board.move(direction);
        }
    }

    private class RotateAction extends AbstractAction {
        private Direction direction;
        private RotateAction(Direction direction) {
            this.direction = direction;
        }
        @Override public void actionPerformed(final ActionEvent e) {board.rotate(direction);}
    }

    private class PauseAction extends AbstractAction {
        @Override public void actionPerformed(final ActionEvent e) {
            if (!board.getGameOver()) {
                board.setPause(!board.getPause());
            }
        }
    }
}
