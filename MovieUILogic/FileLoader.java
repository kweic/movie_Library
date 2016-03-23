/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MovieUILogic;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author kw
 */
public class FileLoader {

    private FileNameCleaner fileCleaner;
    FileListGenerator movieLists;
    IMDB_Miner IMDBminer;
    boolean imageDirectoryFound;
    //ArrayList<BufferedImage> imagesList;
    //ArrayList<ImageIcon> imageIconList;
    //ArrayList<ImageOrganizer> imageOrganizerList;
    //private String directory;
    private double imageScale;
    String absolutePath;
    String imageDirectory;
    String originalDirectory;
    ArrayList<VideoFile> originalList;
    ArrayList<VideoFile> newFiles;

    public FileLoader(String imageDirectory) {

        //FileLoader gathers all the video files from the directory specified
        //it puts all of the files found into an arrayList
        //it returns this arrayList of files to whoever called it
        //it makes calls to FileNameCleaner to form a list of VideoFiles
        absolutePath = new File("").getAbsolutePath();
        //imageOrganizerList = new ArrayList();
        imageDirectoryFound = false;
        //imageIconList = new ArrayList();
        //imagesList = new ArrayList();
        movieLists = new FileListGenerator();
        imageScale = .8;
        fileCleaner = new FileNameCleaner();
        //IMDBminer = new IMDB_Miner(this);
        this.imageDirectory = imageDirectory;
        originalList = new ArrayList();
        newFiles = new ArrayList(); //new files saved seperately so the list to check before adding doesn't get longer

        // this.directory = directory;
    }
    
    public void clearStoredLists(){
        originalList.clear();
        newFiles.clear();
    }

    public void setIMDBMiner(IMDB_Miner miner) {
        this.IMDBminer = miner;
    }

    public boolean checkForVideoFileSave(String directory, String fileName) {
        File folder = new File(directory + "\\" + fileName);
        System.out.println("checking for: " + folder);
        System.out.println("folder: " + folder.isFile());
        return folder.isFile();
    }

    public void setDirectory(String fileDirectory) {
        originalDirectory = fileDirectory;
    }

    public ArrayList<VideoFile> gatherFiles(String file) {
        //originalDirectory = file;
        System.out.println("new gather Files: " + file);
      //  ArrayList<VideoFile> files = filesList;
        
        // String inputFile = file;
        //File folder = new File("\\\\TV-PC\\3TBa_Video");
        File folder = new File(file);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                //System.out.println("File "+listOfFiles[i].getName());

                //files.add(listOfFiles[i].getPath()); //return the whole path name
                if (fileCheck(listOfFiles[i].getName())) {
                    //files.add(new VideoFile(listOfFiles[i].getPath(), listOfFiles[i].getName()));
                    int fileSize = (int) ((listOfFiles[i].length() / 1024) / 1024);
                    if (!findDuplicates(listOfFiles[i].getPath())) {
                        System.out.println("gather Files, getPath: " + listOfFiles[i].getPath());
                        System.out.println("Gather, replace: " + listOfFiles[i].getPath().replace(originalDirectory, ""));
                        newFiles.add(fileCleaner.createVideoFile(listOfFiles[i].getPath().replace(originalDirectory, ""), fileSize));
                    }
                }
            } else if (listOfFiles[i].isDirectory()) {
                //System.out.println("Directory "+listOfFiles[i].getName());
                //System.out.println(inputFile+listOfFiles[i].getName());
                gatherFiles(file + "\\" + listOfFiles[i].getName());

            }
        }
 //       System.out.println("gather files, new List, size: "+newFiles.size());
       // newFiles = movieLists.generateUniqueBySize(newFiles);
 //       System.out.println("size after generating unique by size: "+newFiles.size());
        return movieLists.generateUniqueBySize(newFiles);
    }
    
    public ArrayList<VideoFile> combineLists(ArrayList<VideoFile> newFiles){
        for(VideoFile newFile: newFiles){
            originalList.add(newFile);
        }
        return originalList;
    }

    public boolean findDuplicates(String file) {
  //      System.out.println("checking for duplicate: " + file);
        for (VideoFile movie : originalList) {
   //         System.out.println("comparing to path: "+movie.getPath());
            if (file.toLowerCase().contains(movie.getPath().toLowerCase())) {
    //            System.out.println("duplicate FOUND");
                return true;
            }
        }
     //   System.out.println("new File FOUND");
        return false;
    }

    public ArrayList<VideoFile> updateGatherFiles(String file, ArrayList<VideoFile> filesList) {
        originalList = filesList;
        originalDirectory = file;
        
        //ArrayList<VideoFile> oldListUpdated = filesList;
     //  ArrayList<VideoFile> updatedGrab = movieLists.generateUniqueBySize(gatherFiles(file)); //grabs a fresh batch of files

        //go through and check if they're already contained in the old one
       //for (VideoFile movie : updatedGrab) {
       //     if (!checkExistingFile(filesList, movie.getPath().trim())) { //if it's not in the list already
        //        System.out.println("NEW MOVIE ADDED: " + movie.getTitle());
//
        //        filesList.add(movie);
        //    }
     //  }
        return movieLists.generateUniqueBySize(combineLists(gatherFiles(file)));
    }

    public boolean checkExistingFile(ArrayList<VideoFile> oldList, String fileFullPath) {
        for (VideoFile movie : oldList) {
            System.out.println("fileLoader, checkExistingFile: " + movie.getPath() + " compar: " + fileFullPath);
            if (fileFullPath.trim().equals(movie.getPath().trim())) {

                System.out.println("equals");
                return true; //returns true saying that the file already exists in the previous list
            }
        }
        System.out.println(fileFullPath + "found not equal to any");
        return false;
    }

    public boolean checkDirectory(String directory) {
        File file = new File(directory);
        System.out.println(directory + " IN FILE LOADER exists: " + file.exists());
        return file.exists();
    }

    public void placeBlankImage() {
        //grabs blank image from next to the program
        //saves it into the new folder for images created next to the program
        System.out.println("placeBlankImage in: " + imageDirectory);

        try {
            BufferedImage bi = ImageIO.read(new File(absolutePath + "/noImageAvailable.jpg"));
            System.out.println("loaded blank: " + absolutePath + "/noImageAvailable.jpg");
            File outputfile = new File(absolutePath + "/" + imageDirectory + "/noImageAvailable.jpg");

            ImageIO.write(bi, "jpg", outputfile);

            System.out.println("saved blank image: " + outputfile);
        } catch (IOException e) {
            System.out.println("save image failed");

        }
    }

    /*2-22
    public void loadImagesIntoVideoFiles(ArrayList<VideoFile> videoFiles){
        for(VideoFile movie: videoFiles){
            
        }
        
    }
     */
    public void sizeImages(VideoFile file, double imageScale) {
        //        xx//move this to new method for creating icons
        this.imageScale = imageScale;

        if (!file.getHasImage()) {
            System.out.println("doesn't have image");
        }
        Image resizeImage;
        if (imageScale != 1) {
          //  System.out.println("sizing: " + file.getTitle());
            resizeImage = file.getBufferedImage().getScaledInstance((int) (file.getBufferedImage().getWidth() * imageScale), (int) (file.getBufferedImage().getHeight() * imageScale), BufferedImage.SCALE_SMOOTH);
        } else {
            resizeImage = file.getBufferedImage();
        }

        ImageIcon resizedIcon = new ImageIcon(resizeImage);
        file.setImageIcon(resizedIcon);
    }

    public void loadImage(VideoFile file) {
        System.out.println("FileLoader, LoadImage " + file.getTitle());
        String path = absolutePath + "/images";

        if (!imageDirectoryFound) {
            if (!checkDirectory(path)) {
                System.out.println("making new directory: " + path);
                new File(path).mkdirs();
                placeBlankImage();
            }
        }
        imageDirectoryFound = true;

        BufferedImage img = null;
//        String noImagePath = "src/images/noImageAvailable.jpg";
        String noImagePath = "noImageAvailable.jpg";
        //System.out.println("trying to get: " + fileName);
        try {
            //img = ImageIO.read(new File(fileName)); //read files
            System.out.println("looking for picture: " + path + "/" + file.getVideoImageFile().trim() + ".jpg");
            img = ImageIO.read(new File(path + "/" + file.getVideoImageFile().trim() + ".jpg"));
            file.setBufferedImage(img);
            file.setHasImage(true);
        } catch (IOException e) {
            //img = no cover image
            System.out.println("failed to find image");
            file.setHasImage(false);
            try {
                img = ImageIO.read(new File(noImagePath));
                file.setBufferedImage(img);
                System.out.println("image set to NO image.");
            } catch (IOException x) {
                System.out.println("No image and no blank");
            }
        }
    }

    public void saveImage(String fileLink) {
        System.out.println("saveImage: " + fileLink);
        try {
            BufferedImage bi = IMDBminer.getImage(fileLink);
            File outputfile = new File(absolutePath + "" + imageDirectory + "/" + IMDBminer.getSaveLink());
            System.out.println("saving from imdb to: " + absolutePath + "" + imageDirectory + "/" + IMDBminer.getSaveLink());
            ImageIO.write(bi, "jpg", outputfile);
            System.out.println("saved image: " + fileLink);
        } catch (IOException e) {
            System.out.println("save image failed");
            try {
                BufferedImage bi = ImageIO.read(new File(absolutePath + "/noImageAvailable.jpg"));
                File outputfile = new File(absolutePath + "" + imageDirectory + "/" + IMDBminer.getSaveLink());
                ImageIO.write(bi, "jpg", outputfile);
                System.out.println("saved as NO IMAGE");
            } catch (Exception x) {

            }
        }
    }

    public ImageIcon returnImage(VideoFile file, double imageScale) {
        //ImageOrganizer currentSet = findFile(pathName);
        if (this.imageScale != imageScale) {
            //if (i == imageOrganizerList.size() - 1) {
            //    this.imageScale = imageScale;
            //System.out.println("imageScales "+this.imageScale+" new "+imageScale);
            //}

            BufferedImage img = file.getBufferedImage();
            Image resizedImage = img.getScaledInstance((int) (img.getWidth() * imageScale), (int) (img.getHeight() * imageScale), BufferedImage.SCALE_SMOOTH);
            ImageIcon resizedIcon = new ImageIcon(resizedImage);
            //imageIconList.remove(i);
            //imageIconList.add(i, resizedIcon);
            file.setImageIcon(resizedIcon);
            //System.out.println("icon list size: "+imageIconList.size());
            return resizedIcon;
        }
        //System.out.println("i: "+i+" size: "+imageIconList.size());
        return file.getImageIcon();
    }

    public boolean fileCheck(String fileName) {
        //check if it's what I want
        // .mp4, .mkv, .avi, !.jpg,
        //run it through the gauntlet of checks ready to set off false
        //if it doesn't trigger any return true
        ArrayList<String> includes = new ArrayList(Arrays.asList(".mp4", ".mkv", ".avi"));
        ArrayList<String> excludes = new ArrayList(Arrays.asList(".jpg"));
        boolean check = false;

        for (String hasThis : includes) { //check for one of the key file formats
            if (fileName.contains(hasThis)) {
                check = true;
                break;
            }
        }

        for (String doesntHave : excludes) { // if it has any of these, void the original assesment and return false
            if (fileName.contains(doesntHave)) {
                return false;
            }
        }

        return check;
    }
}
