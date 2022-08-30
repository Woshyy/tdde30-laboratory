package se.liu.chrwa634.tetris;

import se.liu.chrwa634.tetris.statuspanel.CounterPanel;
import se.liu.chrwa634.tetris.statuspanel.FallHandlerPanel;
import se.liu.chrwa634.tetris.statuspanel.ScorePanel;

import javax.swing.*;
import java.awt.*;
import java.util.EnumMap;

public class TetrisViewer
{
    private static final EnumMap<SquareType, Color> COLOR_MAP = createColorMap();


    public static void show(Board board) {

        JFrame frame = new JFrame("Tetris");
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());

        //Creates the tetris component add add it to the frame.
	TetrisComponent tetrisComponent = new TetrisComponent(board);
	frame.add(tetrisComponent, BorderLayout.CENTER);

	JPanel infoPanel = new JPanel();
	infoPanel.setLayout(new BorderLayout());
	infoPanel.setBackground(Color.BLUE);
	infoPanel.setOpaque(true);

	//Adding the score panel.
	ScorePanel scorePanel = new ScorePanel(board);
	infoPanel.add(scorePanel.getLabel(), BorderLayout.CENTER);

	CounterPanel counterPanel = new CounterPanel(board);
	infoPanel.add(counterPanel.getLabel(), BorderLayout.SOUTH);

	FallHandlerPanel fallHandlerPanel = new FallHandlerPanel(board);
	infoPanel.add(fallHandlerPanel.getLabel(), BorderLayout.NORTH);

	frame.add(infoPanel, BorderLayout.EAST);
	frame.setSize(tetrisComponent.getPreferredSize());
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setLocationRelativeTo(null);

	TetrisMenuBar menuBar = new TetrisMenuBar();
	frame.setJMenuBar(menuBar.getMenuBar());

	frame.pack();
	frame.setVisible(true);
    }

    private static EnumMap<SquareType, Color> createColorMap() {
	EnumMap<SquareType, Color> colorMap = new EnumMap<>(SquareType.class);

	colorMap.put(SquareType.EMPTY, Color.BLACK);
	colorMap.put(SquareType.I, Color.CYAN);
	colorMap.put(SquareType.O, Color.ORANGE);
	colorMap.put(SquareType.T, Color.MAGENTA);
	colorMap.put(SquareType.S, Color.GREEN);
	colorMap.put(SquareType.Z, Color.RED);
	colorMap.put(SquareType.J, Color.BLUE);
	colorMap.put(SquareType.L, Color.PINK);

	return colorMap;
    }

    public static void draw(final Graphics2D g2d, SquareType block, int x, int y, int blockSize) {
        //making the square
	g2d.setColor(COLOR_MAP.get(block));
	g2d.fillRect(x * blockSize, y * blockSize, blockSize, blockSize);

	//drawing the other ring of the square
        if ((block != SquareType.EMPTY) && (block != SquareType.OUTSIDE)) {
	    g2d.setColor(Color.WHITE);
	}
        else {
            g2d.setColor(Color.GRAY);
	}
	g2d.drawRect(x * blockSize, y * blockSize, blockSize, blockSize);
    }

}

