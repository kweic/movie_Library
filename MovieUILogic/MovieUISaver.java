
package MovieUILogic;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MovieUISaver {

    //Methods that will make it possible to save:
    //Directory
    //genre selected
    //view settings Title, year, play count, genres, image scale
    private String absolutePath;
    private String directory;
    private String saverDirectory;
    private String menuGenre;
    private boolean year;
    private boolean title;
    private boolean playCount;
    private boolean genres;
    private boolean ratings;
    private double imageScale;

    public MovieUISaver(String saverDirectory) {
        this.saverDirectory = saverDirectory;
        directory = "Not Set";
        menuGenre = "Movies";
        year = true;
        title = true;
        ratings = false;
        playCount = false;
        genres = false;
        imageScale = .7;
        absolutePath = new File("").getAbsolutePath();
    }

    public ArrayList<String> settingsList() {
        ArrayList<String> settingsList = new ArrayList();
        settingsList.add(directory);
        settingsList.add(imageScale + "");
        settingsList.add(menuGenre + "");
        settingsList.add(year + "");
        settingsList.add(title + "");
        settingsList.add(ratings + "");
        settingsList.add(playCount + "");
        settingsList.add(genres + "");

        return settingsList;
    }

    public void setImageScale(double imageScale) {
        this.imageScale = imageScale;
    }

    public void setRatingsView(boolean ratings) {
        this.ratings = ratings;
    }

    public void setGenresView(boolean genres) {
        this.genres = genres;
    }

    public void setPlayCountView(boolean playCount) {
        this.playCount = playCount;
    }

    public void setTitleView(boolean title) {
        this.title = title;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public void setMenuGenre(String genreSelected) {
        this.menuGenre = genreSelected;
    }

    public void setYearView(boolean year) {
        this.year = year;
    }

    public double getImageScale() {
        return imageScale;
    }

    public String getMovieDirectory() {
        return directory;
    }

    public String getMenuGenre() {
        return menuGenre;
    }

    public boolean getYearViewSetting() {
        return year;
    }

    public boolean getTitleViewSetting() {
        return title;
    }

    public boolean getRatingViewSetting() {
        return ratings;
    }

    public boolean getPlayCountSetting() {
        return playCount;
    }
    
    public boolean getGenreViewSetting(){
        return genres;
    }

    public ArrayList<String> settingNamesList() {
        ArrayList<String> settingNamesList = new ArrayList(Arrays.asList("Directory", "ImageScale", "MenuGenre", "Year show", "Titles show", "Rating Show", "Count show", "Genres show"));
        return settingNamesList;
    }

    public void saveUIState(String saveName) {
        //System.out.println("SAVED");
        //needs directory, current genre, view settings
        System.out.println("saving UI to: "+absolutePath+""+saverDirectory+"/"+saveName);
        
        if(!checkDirectory(absolutePath+""+saverDirectory)){
            System.out.println("making new file");
            makeSaveDirectory();
        }
        
        try {
            FileWriter writer = new FileWriter(absolutePath+saverDirectory+"/"+saveName);
            writer.write(settingsList().get(0) + "\n");
            for (int x = 1; x < settingsList().size(); x++) {
                //System.out.println("write line: " + x);
                writer.write(formatSaveLine(settingNamesList().get(x)) + "" + settingsList().get(x) + "\n");

            }
            writer.close();
        } catch (Exception e) {
            System.out.println("save UI failed");
        }
    }
    
    public boolean checkDirectory(String directory){
        File file = new File(directory);
        System.out.println(directory+" exists: "+file.exists());
        return file.exists();
    }

    public void loadState(String fileName) {
        //System.out.println("LOAD STATE");
        // if file exists load the UI save state
        ArrayList<String> loadFileList = new ArrayList();
        System.out.println("reading from: "+absolutePath+saverDirectory+"/"+fileName);
        File loadFile = new File(absolutePath+saverDirectory+"/"+fileName);
        try {
            Scanner reader = new Scanner(loadFile);
            loadFileList.add(reader.nextLine()); //load the directory
            while (reader.hasNextLine()) {
                loadFileList.add(reader.nextLine());
            }
            reader.close();
            readLoadFile(loadFileList);
        } catch (Exception e) {
            //create the loadfile
        }
    }

    public void makeSaveDirectory() {
        System.out.println("creating new directory: "+absolutePath+saverDirectory);
        new File(absolutePath+saverDirectory).mkdirs();
    }

    public String formatSaveLine(String input) {
        String builtInput = input;
        while (builtInput.length() < 20) {
            builtInput += ".";
        }
        return builtInput;
    }

    public void readLoadFile(ArrayList<String> loadedFile) {
        //System.out.println("  reading load, size: " + loadedFile.size());
        //System.out.println(" printed load: "+loadedFile);
        //save order to read
        //1 Directory to save VideoFile objects
        //image scale

        directory = loadedFile.get(0);

        for (int i = 1; i < loadedFile.size(); i++) {
            //System.out.println(loadedFile.get(i).substring(20, loadedFile.get(i).length()));
            disperseSave(i, loadedFile.get(i).substring(20, loadedFile.get(i).length()));

        }
    }

    public void disperseSave(int i, String line) {
        //System.out.println("disperse save: " + line);
        if (i == 0) {
            //directory = line;
        } else if (i == 1) {
            imageScale = Double.parseDouble(line);
        } else if (i == 2) {
            menuGenre = line;
            //System.out.println("Menu Genre: " + menuGenre);
        } else if (i == 3) {
            year = Boolean.parseBoolean(line);
        } else if (i == 4) {
            title = Boolean.parseBoolean(line);
        } else if (i == 5) {
            ratings = Boolean.parseBoolean(line);
        } else if (i == 6) {
            playCount = Boolean.parseBoolean(line);
        } else if (i == 7) {
            genres = Boolean.parseBoolean(line);
        }
    }

}
