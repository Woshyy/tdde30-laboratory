package se.liu.chrwa634.tetris;

import se.liu.chrwa634.tetris.highscore.Highscore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GameOverProcedure implements ActionListener
{
    private static final int WIDTH = 400;
    private static final int HEIGHT = 200;
    private static final int BUTTON_WIDTH = 100;
    private static final int BUTTON_HEIGHT = 30;
    private static final int TEXT_SIZE = 40;

    private Board board;
    private JFrame frame = new JFrame();
    private JButton submitPlay;
    private JButton submitClose;
    private JTextField textField;

    public GameOverProcedure(Board board) {
        this.board = board;

        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
        frame.setSize(new Dimension(WIDTH, HEIGHT));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textField = createTextField();

        frame.add(createGameOverLabel(), BorderLayout.NORTH);
        frame.add(textField, BorderLayout.CENTER);
        frame.add(createButtonPanel(), BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    @Override public void actionPerformed(final ActionEvent e) {
        if (e.getSource().equals(submitClose)) {
            putHighscoreInList(textField.getText());
            System.exit(0);
	}
        else if (e.getSource().equals(submitPlay)) {
            putHighscoreInList(textField.getText());
	    board.resetBoard(board.getWidth(), board.getHeight());
            frame.dispose();
	}
    }

    private void putHighscoreInList(String highscoreName) {
        int score = board.getScore();
        try {
            board.putHighscore(new Highscore(score, highscoreName));
        } catch (IOException ex) {
            Object[] options = {"Yes", "No"};
            ex.printStackTrace();
            String message = ex.getMessage() + ", do you want to try again?";
            int optionChosen = JOptionPane.showOptionDialog(
                    frame,
                    message,
                    "Error Saving",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.ERROR_MESSAGE,
                    null,
                    options,
                    options[1]);
            if (optionChosen == 0) {
                putHighscoreInList(highscoreName);
            }
        }
    }

    private JPanel createButtonPanel()
    {
        JPanel buttonsPanel = new JPanel();

        buttonsPanel.setLayout(new FlowLayout());

        submitPlay = new JButton("Submit and play again!");
        submitPlay.setSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        submitPlay.setVerticalTextPosition(JButton.CENTER);
        submitPlay.addActionListener(this);

        submitClose = new JButton("Submit and close.");
        submitClose.setSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        submitClose.setVerticalTextPosition(JButton.CENTER);
        submitClose.addActionListener(this);

        buttonsPanel.add(submitPlay);
        buttonsPanel.add(submitClose);
        return buttonsPanel;
    }

    private JTextField createTextField() {
        JTextField text = new JTextField();
        text.setText("Please input your name");
        return text;
    }

    private JLabel createGameOverLabel() {
        JLabel gameOver = new JLabel("GAME OVER");
        gameOver.setHorizontalAlignment(JLabel.CENTER);
        gameOver.setBackground(Color.RED);
        gameOver.setOpaque(true);
        gameOver.setFont(new Font("MV Boli", Font.BOLD, TEXT_SIZE));
        gameOver.setForeground(Color.WHITE);
        return gameOver;
    }
}
