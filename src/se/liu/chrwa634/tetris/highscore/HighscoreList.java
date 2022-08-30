package se.liu.chrwa634.tetris.highscore;

import com.google.gson.Gson;

import javax.swing.*;
import java.io.*;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represent a list of previous achieved highscores of players. The class will save the highscore list everytime a highscore
 * is added.
 */
public class HighscoreList
{
    private static final int MAX_AMOUNT_SHOWN = 10;
    private List<Highscore> highscores= new ArrayList<>();
    private ScoreComparator scoreComparator = new ScoreComparator();
    private static final String TEMP_FILE_ADDRESS = System.getProperty("user.home") + File.separator + "tetris_highScore_temp.json";
    private static final String REAL_FILE_ADDRESS = System.getProperty("user.home") + File.separator + "tetris_highScore.json";

    /**
     * This method add a highscore object to the list. The method would also immediately sort the list so that the first
     * object in the ArrayList is the best highscore.
     * @param highscore The highscore that would be added to the highscore list.
     */
    public void putHighscore(Highscore highscore) {
        highscores.add(highscore);
        highscores.sort(scoreComparator);
    }

    /**
     * This method saves the highscore list to a json file in the users home directory.
     * @throws IOException when reading or writing to the json file fails.
     */
    public void saveHighscoreList() throws IOException{
        Gson gson = new Gson();
        String json = gson.toJson(this);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter((TEMP_FILE_ADDRESS))))
        {
            bw.write(json);
            bw.close();

            //Delete the old file.
            File oldSave = new File(REAL_FILE_ADDRESS);
            if (oldSave.delete()) {
                System.out.println("Old save file deleted.");
            }

            //Rename the temporary file.
            File newSave = new File(TEMP_FILE_ADDRESS);
            if (!newSave.renameTo(new File(REAL_FILE_ADDRESS))) {
                System.out.println("Something went wrong when trying to rename the file.");
            }
        }
    }

    public static HighscoreList loadHighscoreList() {
        while (true) {
            try (FileReader reader = new FileReader(REAL_FILE_ADDRESS)) {
                Gson gson = new Gson();
                return gson.fromJson(reader, HighscoreList.class);
            } catch (IOException ex) {
                Object[] options = { "Try Again", "Create a new highscore List" };
                String message = ex.getMessage() + "Do you want to try again or create a new Highscore list?";
                int optionChosen =
                        JOptionPane.showOptionDialog(null, message, "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null,
                                                     options, options[1]);
                if (optionChosen == 1) {
                    return new HighscoreList();
                }
            }
        }
    }

    @Override public String toString() {
        return "HighscoreList{" + "highscores=" + highscores + '}';
    }

    public void drawString() {
        System.out.println("%%% HIGHSCORES %%%");

        for (int i = 0; i < highscores.size(); i++) {
            int placement = i + 1;
            System.out.println(placement + ". " + highscores.get(i));

            if (i >= MAX_AMOUNT_SHOWN - 1) {
                break;
            }
        }
    }
}
