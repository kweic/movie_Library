/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MovieUILogic;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author kw
 */
public class VideoFile implements Comparable<VideoFile>{

    private String pathName;
    //private String fullPath;
    private String fileName;
    private String title;
    private double rating;
    private int year;
    private int playCount;
    private boolean isMovie;
    private boolean hasImage;
    private boolean dataGathered;
    private boolean hasListener;
    private ArrayList<MovieGenre> genre;
    private BufferedImage bufferedImage;
    private ImageIcon imageIcon;
    int fileSize;
    private JLabel cover;
    // private String movieOrSeries;

    public VideoFile(String pathName, String fileName) {
        this(pathName, fileName, 0, fileName, true, 0);
    }

    public VideoFile(String pathName, String fileName, String title) {
        this(pathName, fileName, 0, title, true, 0);
    }

    public VideoFile(String pathName, String fileName, int year, String title, boolean isMovie, int fileSize) {
        this.pathName = pathName;
        this.fileName = fileName;
        //this.fullPath = pathName+""+fileName;
        this.title = title;
        this.playCount = 0;
        this.year = year;
        this.rating = 0;
        this.isMovie = isMovie;
        this.fileSize = fileSize;
        hasListener = false;
        genre = new ArrayList();
        genre.add(MovieGenre.NONE);
        System.out.println("new VIDEOFILE created: "+title);
    }
    
    public void setCover(int width, int height){
        cover = new JLabel(getImageIcon());
        cover.setBounds(0, 0, width, height);
        cover.setSize(width, height);
        hasListener = false;       
    }
    
    public void setHasListener(){
        hasListener = true;
    }
    
    public JLabel getCover(){
        return cover;
    }
    
    public void setBufferedImage(BufferedImage bufferedImage){
        this.bufferedImage = bufferedImage;
      //  hasImage = true;
   //     System.out.println(title+" has Image: "+hasImage);
    }
    
    public BufferedImage getBufferedImage(){
        return bufferedImage;
    }
    
    public void setImageIcon(ImageIcon imageIcon){
        this.imageIcon = imageIcon;
    }
    
    public ImageIcon getImageIcon(){
        return imageIcon;
    }
    
    public void setDataGathered(boolean dataGathered){
        //this can be turned on after gathering
        //turned back off if the title or year is changed
        this.dataGathered = dataGathered;
    }
    
    public boolean dataIsGathered(){
        return dataGathered;
    }

    public int getFileSize() {
        return fileSize;
    }
    
    public String getFileName(){
        return fileName;
    }
    
    public void setFileName(String fileName){
        this.fileName = fileName;
    }
    
    public boolean getHasImage(){
        return hasImage;
    }
    
    public void setHasImage(boolean setHasImage){
        hasImage = setHasImage;
    }

    public void setFileSize(int size) {
        fileSize = size;
    }

    public String getPath() {
        return pathName;
    }
    
    public void setPath(String path){
        this.pathName = path;
    }
    
    //public String getFullPath(){
    //    return fullPath;
    //}

    public String getVideoImageFile() {
        //if(year == 0){
            return title.trim();
        //}else{
        //    return title+""+year;
       // }
    }

    public void addGenreList(ArrayList<MovieGenre> genres) {
        if (this.genre == null) {
            this.genre = genres;
        } else {
            for (MovieGenre genre : genres) {
                addGenre(genre);
            }
        }
       
    }

    public void addGenre(MovieGenre genre) {
        if (this.genre == null) {
            this.genre = new ArrayList();
        }
        if (!this.genre.contains(genre)) {
            this.genre.add(genre);
        }
         if(this.genre.size() > 1 && this.genre.contains(MovieGenre.NONE)){
            this.genre.remove(MovieGenre.NONE);
        }
        
    }
    
    public boolean getHasListener(){
        return hasListener;
    }
    

    public ArrayList<MovieGenre> getGenres() {
        return genre;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIsMovie(boolean isMovie) {
        this.isMovie = isMovie;
    }

    public boolean isMovie() {
        return isMovie;
    }

    public String getTitle() {
        return title;
    }
    
    public void setPlaycount(int plays){
        playCount = plays;
    }

    public void movePlaycount(int move) {
        playCount += move;
    }

    public void resetPlaycount() {
        playCount = 0;
    }

    public int getPlaycount() {
        return playCount;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getRating() {
        return rating;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public String toString() {
        return title + " (" + year + ")";
    }

    @Override
    public int compareTo(VideoFile o) {
        return this.getTitle().compareTo(o.getTitle());
    }

}
