/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MovieUILogic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author kw
 */
public class FileNameCleaner {

    //this class will contain methods to clean files and return likely titles
    private ArrayList<Character> excludeChar;
    private ArrayList<String> excludeString;
    private MovieOrSeriesClassifier classifier;

    public FileNameCleaner() {
        excludeChar = new ArrayList(Arrays.asList('.', '-', '[', ']', '_'));
        excludeString = new ArrayList(Arrays.asList("dubb", "dub", "x264", "BluRay", "AAC", "WEB", "HDRip", "YIFY", "1080p", "1080", "720p", "720",
                "AAC", "title", "mp4", "Hdtv", "Asap", "Xvid", "Lol", "Dvdrip", "Divx", "disc 1",
                "h264", "Brrip", "Extended Cut", "Dominion", "deluxe edition", "final cut", "rf20", "dvd", "tamilrockers", "www", ".net"));
        classifier = new MovieOrSeriesClassifier();
    }

    public VideoFile createVideoFile(String filePath, int fileSize) {
        System.out.println("FileNameCleaner, createVideoFile, Path: " + filePath);
        //go through from the beginning
        String fileName = getFileName(filePath);
        int year = getYear(filePath);
        //System.out.println("year: " + year);
        String title = getTitle(filePath, year + "");
        //System.out.println("title: " + title);
        //return new VideoFile(pathName, fileName, year, title);
        //System.out.println("FileName: " + fileName);
        boolean isMovie = classifier.scanFile(filePath);
        System.out.println("FileCleaner, create VideoFile, isMovie set to: " + isMovie);
        return new VideoFile(filePath, fileName, year, title, isMovie, fileSize);
    }

    public String removeFileFormat(String input) {
        //54 lego movie
        ArrayList<String> formats = new ArrayList(Arrays.asList(".mkv", ".mov", ".avi"));
        for (String format : formats) {
            if (input.substring(input.length() - 4, input.length()).equals(format)) {
                return input.substring(0, input.length() - 4);
            }
        }
        return input;
    }

    public String doExcludes(String input) {
        String checkTitle = input;
        for (int i = 0; i < excludeString.size(); i++) {
            //System.out.println("removing: " + excludeString.get(i));
            checkTitle = checkTitle.replace(excludeString.get(i).toLowerCase(), "");
            //System.out.println("new check: " + checkTitle);
        }
        return checkTitle;
    }

    public boolean possibleTitle(String input) {
        //check if the input could have a title in it
        //System.out.println("checking for possible title: " + input);
        String checkTitle = removeFileFormat(input);
        checkTitle = doExcludes(checkTitle);

        checkTitle = cleanString(checkTitle);
        //System.out.println("checking length " + checkTitle.length());
        if (checkTitle.length() > 3) {
            //System.out.println("returning TRUE for possible title: " + checkTitle);
            return true;
        }
        //System.out.println("returning FALSE for possible title: " + checkTitle);
        return false;
    }

    public String getTitle(String filePath, String year) {
        String buildTitle = "";
        String preClean = filePath.toLowerCase();
        //System.out.println("building title");

        if (possibleTitle(getFileName(preClean))) { //if the filename has a possible title
            preClean = removeFileFormat(preClean);
            preClean = doExcludes(preClean);
            preClean = cleanString(preClean);
            preClean = getFileName(preClean);
        } else {
            System.out.println("getting parent of: " + filePath);
            preClean = getParentFolder(filePath);
            //System.out.println("   checking parent: "+preClean);
            preClean = doExcludes(preClean);
            preClean = cleanString(preClean);
        }
        //System.out.println("Get Title starting with after possible: " + preClean);

        for (int i = 0; i < preClean.length(); i++) {
            if (checkChar(preClean.charAt(i) + "")) {
                //System.out.println("adding: ("+preClean.charAt(i)+")");
                buildTitle += preClean.charAt(i);
            }

            //if it hits the year part of the title
            //System.out.println("buildTitle after checkChar: " + buildTitle);
            if (buildTitle.length() > 5) {
                if (buildTitle.substring(buildTitle.length() - 4, buildTitle.length()).equals(year)) {
                    buildTitle = buildTitle.replace(year, "");
                    return capitalizeFirst(buildTitle);
                }
            }
        }

        //System.out.println("returned title: " + buildTitle);
        return capitalizeFirst(buildTitle);
    }

    public String capitalizeFirst(String input) {
        //System.out.println("capitalizing first");
        Scanner reader = new Scanner(input);
        String capitalized = "";
        String nextInput;
        while (reader.hasNext()) {
            nextInput = reader.next();
            if (possibleWord(nextInput)) {
                if (checkFirstLetter(nextInput)) { //if the first char is a letter, capitalized it
                    capitalized += (nextInput.charAt(0) + "").toUpperCase() + nextInput.substring(1, nextInput.length()) + " ";
                } else {
                    capitalized += nextInput + " ";
                }
            }
        }
        if (capitalized.length() < 2) {
            return input;
        }

        return capitalized.substring(0, capitalized.length() - 1); //remove the space from the end
    }

    public boolean checkFirstLetter(String input) {
        //System.out.println("checking first letter");
        if ((input.charAt(0) + "").matches("[a-z]")) {
            return true;
        }
        return false;
    }

    public boolean possibleWord(String input) {
        //helps throw away words that have numbers in them, and therefore are not words
        return input.matches("[a-z]{1,14}|[0-9]{1,4}");
    }

    public boolean checkChar(String input) {
        //System.out.println("char check: ("+input+")");
        return input.matches("[a-z]|[0-9]|[ ]");
    }

    public boolean checkYear(String input) {
        //System.out.println("year check");
        if (input.equals("1080")) {
            return false;
        }
        return input.matches("[12][90][0-9][0-9]");
    }

    public int getYear(String input) {
        //goes through combinations of 4 characters looking for a year
        //starts far enough that titles like "2001 space oddysy" wont be broken
        for (int i = 3; i < input.length() - 4; i++) {
            //System.out.println("checking for year: " + input.substring(i, i + 4));
            if (checkYear(input.substring(i, i + 4))) {
                //System.out.println("found year");
                return Integer.parseInt(input.substring(i, i + 4));
            }
        }
        return 0;
    }

    public String cleanString(String input) {
        String cleanedString = input;
        for (int i = 0; i < excludeChar.size(); i++) {
            //System.out.println("removing: " + excludeChar.get(i));
            cleanedString = cleanedString.replace(excludeChar.get(i), ' ');
            //System.out.println("cleaned: " + cleanedString);
        }
        //System.out.println("Cleaned String final: " + cleanedString);
        return cleanedString;
    }

    public String getFileName(String filePath) {
        //go from the back and stop building file at first backslash

        String cleanedFile = "";

        int i = filePath.length() - 1;
        while (filePath.charAt(i) != '\\') {
            // System.out.println("char at: "+parentFolder.charAt(i));
            cleanedFile = filePath.charAt(i) + "" + cleanedFile;
            i--;
        }
        return cleanedFile;
    }

    public String getFileWithParent(String input) {
        int slashCount = 0;
        String cleanedString = "";
        for (int i = input.length() - 1; slashCount < 2; i--) {
            if (input.charAt(i) == '\\' || input.charAt(i) == '/') {
                slashCount++;
            } else if (i == 0) {
                return input;
            }
            cleanedString = input.charAt(i) + "" + cleanedString;
        }
        return cleanedString;
    }

    public String getParentFolder(String pathName) {
        //System.out.println("  getting parent from: "+pathName);
        String parentFolder = pathName.replace("\\" + getFileName(pathName), ""); //remove the fileName
        String cleanedFile = "";
        parentFolder = parentFolder.toLowerCase();

        if(parentFolder.length() == 0){
            return pathName;
        }
        int i = parentFolder.length() - 1;
        while (parentFolder.charAt(i) != '\\') {
            // System.out.println("char at: "+parentFolder.charAt(i));
            cleanedFile = parentFolder.charAt(i) + "" + cleanedFile;
            i--;
            if (i == 1) {
                System.out.println("reached 1");
            }
            if (i == 0) {
                System.out.println("readed 0");
            }
        }
        return cleanedFile;
    }

}
