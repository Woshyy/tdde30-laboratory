package se.liu.chrwa634.tetris;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TetrisMenuBar implements ActionListener
{
    private JMenuBar menuBar;
    private JMenuItem exit;
    private JMenuItem reset;
    private JMenuItem gameSettings;

    public TetrisMenuBar() {

	menuBar = new JMenuBar();

	final JMenu options = new JMenu("Options");
	gameSettings = new JMenuItem("Settings");
	options.add(gameSettings);
	gameSettings.addActionListener(this);
	menuBar.add(options);

	final JMenu close = new JMenu("Close");
	reset = new JMenuItem("Reset");
	close.add(reset);
	reset.addActionListener(this);

	exit = new JMenuItem("Exit");
	close.add(exit);
	exit.addActionListener(this);
	menuBar.add(close);
    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }

    @Override public void actionPerformed(final ActionEvent e) {
	if (e.getSource().equals(exit)) {
	    Object[] options = {"Yes", "No"};
	    int optionAnswer = JOptionPane.showOptionDialog(
	    	null,
		"Are you sure?",
		"Exit",
		JOptionPane.YES_NO_OPTION,
		JOptionPane.QUESTION_MESSAGE,
		null,
		options,
		options[1]
	    );
	    if(optionAnswer == 0) {
		System.exit(0);
	    }
	}
	else if (e.getSource().equals(reset)) {
	    System.out.println("Reset");
	}
	else if (e.getSource().equals(gameSettings)) {
	    System.out.println("Settings");
	}
    }
}
