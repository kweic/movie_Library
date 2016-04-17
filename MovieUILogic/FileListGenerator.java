/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MovieUILogic;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author kw
 */
public class FileListGenerator {

    public FileListGenerator() {

    }

    public ArrayList<VideoFile> sortRating(ArrayList<VideoFile> list) {
        //saves highest rating checking through the whole list, adds it to new list if it's the highest
        double currentHighRating = -1;
        
            VideoFile currentHighest = list.get(0);
            ArrayList<VideoFile> sortedByRating = new ArrayList();
            while (sortedByRating.size() != list.size()) {
                //currentHighest = list.get(0);
                currentHighRating = -1;
                for (VideoFile movie : list) {

                    if ((movie.getRating() >= currentHighRating) && !sortedByRating.contains(movie)) {
                        currentHighest = movie;
                        currentHighRating = movie.getRating();
                        //System.out.println("new highest: " + currentHighest.getTitle() + " " + currentHighest.getRating());
                    }
                }
                //System.out.println("highest added.");
                sortedByRating.add(currentHighest);
            }

            return sortedByRating;
        
    }

    public ArrayList<VideoFile> sortYear(ArrayList<VideoFile> list) {
        int currentHighYear = 0;
        VideoFile currentHighest = list.get(0);
        ArrayList<VideoFile> sortedByYear = new ArrayList();
        boolean allZeroes = false;
        while (!allZeroes && sortedByYear.size() != list.size()) {
            currentHighYear = 0;
            allZeroes = true;
            for (VideoFile movie : list) { //go through and add all the highest years first
                if ((movie.getYear() != 0 && movie.getYear() >= currentHighYear) && !sortedByYear.contains(movie)) {
                    allZeroes = false;
                    currentHighest = movie;
                    currentHighYear = movie.getYear();
                    System.out.println("new highest: " + currentHighest.getTitle() + " " + currentHighest.getYear());
                }
            }
            sortedByYear.add(currentHighest);
        }
        
        for(VideoFile movie: list){ //add all remaining zeroes in quickly
            if(!sortedByYear.contains(movie)){
                sortedByYear.add(movie);
            }
        }
        return sortedByYear;
    }

    public ArrayList<VideoFile> sortViews(ArrayList<VideoFile> list) {
        int currentHighViewCount = -1;
        VideoFile currentHighest = list.get(0);
        ArrayList<VideoFile> sortedByViews = new ArrayList();
        while (sortedByViews.size() != list.size()) {
            currentHighViewCount = -1;
            for (VideoFile movie : list) {

                if ((movie.getPlaycount() >= currentHighViewCount) && !sortedByViews.contains(movie)) {
                    currentHighest = movie;
                    currentHighViewCount = movie.getPlaycount();
                    //System.out.println("new highest: " + currentHighest.getTitle() + " " + currentHighest.getPlaycount());
                }
            }
            //System.out.println("highest added.");
            sortedByViews.add(currentHighest);
        }
        return sortedByViews;
    }

    public ArrayList<VideoFile> lexicoSort(ArrayList<VideoFile> list) {
        Collections.sort(list);
        return list;
    }

    public ArrayList<VideoFile> generateUniqueBySize(ArrayList<VideoFile> list) {
        //go through the list, looking for the highest sized version of the file if it repeats
        ArrayList<VideoFile> rebuiltList = new ArrayList();
        boolean add = false;

        for (int i = 0; i < list.size(); i++) { //pick a file from the list
            add = false;
            for (int n = 0; n < rebuiltList.size(); n++) { //go through all the files in the new list
                if (checkTitles(list.get(i), rebuiltList.get(n))) { // if the file from the list is the same name as a file in the new list
                    if (list.get(i).getFileSize() > rebuiltList.get(n).getFileSize()) { //compare fileSize if they're the same title
                        //if the file from the list is bigger than the file already in the list
                        //remove it from the list, add the new one
                        //System.out.println("removing size: " + rebuiltList.get(n).getFileSize() + " replacing with size: " + list.get(i).getFileSize());
                        rebuiltList.remove(n);
                        rebuiltList.add(list.get(i));
                        add = true;
                    } else {
                        //counts as an add so it doesn't add at the end if size bigger check failed
                        add = true;
                    }
                }

            }
            if (!add) { // if it went all the way through without finding another file the same name and wasn't already added
                rebuiltList.add(list.get(i));
            }
        }

        return rebuiltList;
    }

    public boolean checkTitles(VideoFile oldlist, VideoFile newList) {
        return oldlist.getTitle().equals(newList.getTitle());
    }
}
