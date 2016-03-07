/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MovieUILogic;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author kw
 */
public class VideoFileLoaderSaver {

    private String pathName;
    IMDB_Miner miner;

    public VideoFileLoaderSaver(String pathName, FileLoader fileLoader) {
        this.pathName = pathName;
        miner = new IMDB_Miner(fileLoader);
    }
    
    public void setNewPathName(String pathName){
        this.pathName = pathName;
    }

    public void makeBackupFile() {
        //copys the successful save into this file
    }

    // public String loadFiles() {
    //    return "videoFiles_UI.txt";
    //}
    public String loadBackup() {
        String backUpFile = "videoFiles_UI_backup.txt";
        return backUpFile;
    }

    public void saveVideoFiles(ArrayList<VideoFile> videoFiles, String saveFileName, String directory) {
        //title
        //year
        //rating
        //views
        //genres
        //gather boolean data
        //isMove or series
        //System.out.println("save Started");
        System.out.println("saving to: " + directory + "\\" + saveFileName);
   //     FileWriter writer;
   BufferedWriter writer;
        try {
           // writer = new FileWriter(directory + "\\" + saveFileName);
           writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(directory+"\\"+saveFileName), "UTF-8"));

            for (int i = 0; i < videoFiles.size(); i++) {
                //System.out.println("write line: " + x);
                System.out.println("saving: " + videoFiles.get(i).toString());
                writer.write(videoFiles.get(i).getTitle() + "\n");
                //added file name
                
        //edited here to only save end part
                writer.write(videoFiles.get(i).getPath().replace(pathName, "")+"\n");
                writer.write(videoFiles.get(i).getYear() + "\n");
                writer.write(videoFiles.get(i).getRating() + "\n");
                writer.write(videoFiles.get(i).getPlaycount() + "\n");
                writer.write(videoFiles.get(i).getGenres().toString() + "\n");
                writer.write(videoFiles.get(i).dataIsGathered() + "\n");
                writer.write(videoFiles.get(i).isMovie()+"\n");
                writer.write(videoFiles.get(i).getFileSize()+"\n");
            }
            writer.close();
        } catch (Exception e) {
            System.out.println("save failed");
        }
    }

    public ArrayList<VideoFile> loadVideoFiles(String fileName) {
        //builds the arraylist of video files from the saved file for return
        // String fileName = loadFiles();
        ArrayList<String> loadedVideoFileString = new ArrayList();
        File loadFile = new File(pathName + "\\" + fileName);
        Scanner reader;
        try {
            reader = new Scanner(loadFile, "UTF-8");
            int i = 0;
            while (reader.hasNextLine()) {
                
                loadedVideoFileString.add(reader.nextLine());
                i++;
            }
            System.out.println("loaded file line count: "+i);
            reader.close();

        } catch (Exception e) {
            //create the loadfile
            
        }
        
        return interpretLoad(loadedVideoFileString);
    }

    public ArrayList<VideoFile> interpretLoad(ArrayList<String> fileString) {
        //title
        //year
        //rating
        //views
        //genres
        //gather boolean data
        //System.out.println("Interpreter: building new VideoFile List, filesString size: "+fileString.size());
        ArrayList<VideoFile> videoFilesLoaded = new ArrayList();
        
        for (int i = 0; i < fileString.size(); i += 9) {
            //System.out.println("fileString 0: "+fileString.get(i));
            //System.out.println("fileString 1: "+fileString.get(i+1));
            VideoFile file = new VideoFile(pathName, fileString.get(i).trim()); //creates a new Video file with (pathname, title)
            
            
            file.setPath(fileString.get(i+1).trim());
            //add file name
            file.setYear(Integer.parseInt(fileString.get(i + 2).trim()+""));
            file.setRating(Double.parseDouble(fileString.get(i + 3).trim()));
            //System.out.println("got rating: "+file.getRating());
            file.setPlaycount(Integer.parseInt(fileString.get(i + 4).trim()));
            //System.out.println("got playcount: "+file.getPlaycount());
            addGenresString(fileString.get(i + 5).trim(), file);
            //System.out.println("got Genres: "+file.getGenres().toString());
            file.setDataGathered(Boolean.parseBoolean(fileString.get(i + 6)));
            file.setIsMovie(Boolean.parseBoolean(fileString.get(i+7)));
            file.setFileSize(Integer.parseInt(fileString.get(i+8)));
            System.out.println("adding: "+file.getTitle());
            videoFilesLoaded.add(file);
        }
        return videoFilesLoaded;
    }

    public void addGenresString(String genres, VideoFile file) {
        //ArrayList<MovieGenre> genresList = new ArrayList(Arrays.asList());
        

        Scanner reader = new Scanner(genres);
        while (reader.hasNext()) {
            file.addGenre(miner.genreChecker(reader.next()));
        }
    }
}
