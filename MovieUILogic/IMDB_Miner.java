/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MovieUILogic;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import javax.imageio.ImageIO;

/**
 *
 * @author kw
 */
public class IMDB_Miner {

    //private String fileLink;
    ArrayList<MovieGenre> genres;
    private String saveLink;
    private int sizeFactor;
    FileLoader fileLoader;
    String absolutePath;

    public IMDB_Miner(FileLoader fileLoader) {
        //this.fileLink = fileLink;
        absolutePath = new File("").getAbsolutePath();
        genres = new ArrayList(Arrays.asList(
                MovieGenre.ACTION, MovieGenre.SCIFI, MovieGenre.ANIMATION,
                MovieGenre.DRAMA, MovieGenre.FANTASY, MovieGenre.FAMILY,
                MovieGenre.HORROR, MovieGenre.CRIME, MovieGenre.HISTORY,
                MovieGenre.WESTERNS, MovieGenre.SPORT, MovieGenre.WAR,
                MovieGenre.GAMESHOWS, MovieGenre.BIOGRAPHIES, MovieGenre.ADVENTURE,
                MovieGenre.COMEDIES, MovieGenre.THRILLERS, MovieGenre.MYSTERY,
                MovieGenre.ROMANCE, MovieGenre.DOCUMENTARIES, MovieGenre.MUSICALS,
                MovieGenre.MUSIC, MovieGenre.REALITYTV, MovieGenre.TALKSHOWS,
                MovieGenre.NEWS, MovieGenre.FILMNOIR, MovieGenre.ADULT));
        sizeFactor = 6;
        this.fileLoader = fileLoader;
    }

    public void setImageDownloadSize(int sizeFactor) {
        //set how big the saved images will be
        this.sizeFactor = sizeFactor;
    }

    public String convertTitle(String title) {
        //adds seperation with + to title like imdb prefers
        String rebuildTitle = "";
        for (int i = 0; i < title.length(); i++) {
            if (title.charAt(i) == ' ') {
                rebuildTitle += "+";
            } else {
                rebuildTitle += title.charAt(i);
            }
        }
        System.out.println("returning: " + rebuildTitle);
        return rebuildTitle;
    }

    public void gatherIMDBData(VideoFile videofile) {
        System.out.println("gatherIMDBData: " + videofile.getTitle());
        if (!videofile.dataIsGathered()) {
            downloadDvdCoverAndData(sizeFactor, videofile);
            
        } else if (!videofile.getHasImage()) {
            
            downloadDvdCoverAndData(sizeFactor, videofile);
        }
    }

    public MovieGenre genreChecker(String input) {

        MovieGenre reportGenre = MovieGenre.NONE;
        //System.out.println("checking genre: " + input);

        for (MovieGenre genre : genres) {
            //System.out.println("Genre print: " + genre.getGenre());
            if (input.contains(genre.toString())) {
                //System.out.println("Genre found string: " + genre.toString());
                reportGenre = genre;
            }
        }

        return reportGenre;
    }

    public void getGenresDate(String imdbLink, VideoFile videoFile) {
        //ArrayList<MovieGenre> genres = new ArrayList();
        String pageLink = "http://www.imdb.com/" + imdbLink;
        //System.out.println("pageLink " + pageLink);

        try {
            System.out.println("searching " + pageLink + " for " + videoFile.getTitle() + " genres & date");
            URL imdb = new URL(pageLink);
            BufferedReader in = new BufferedReader(new InputStreamReader(imdb.openStream()));

            String inputLine = "";
            boolean run = true;
            int genreFound = 0;
            //Scanner reader = new Scanner(inputLine);
            while (run && (inputLine = in.readLine()) != null) {

                if (inputLine.contains("/genre/") || inputLine.contains("release") || inputLine.contains("/year/") || inputLine.contains("based on")) {
                    Scanner reader = new Scanner(inputLine);
                    while (reader.hasNext()) {
                        String currentLine = reader.next();
                        //System.out.println("current: "+currentLine);
                        if (currentLine.contains("/genre/")) {
                            //System.out.println(currentLine);
                            videoFile.addGenre(genreChecker(currentLine));
                            //genres.add(genreChecker(currentLine));
                            genreFound++;
                            //currentLine = currentLine.substring(5, currentLine.length() - 1); //trims the html from the filename
                            //in.close();
                            //reader.close();

                            //return resizeImageURL(currentLine, sizeFactor);
                            //break;
                        } else if (currentLine.contains("release") && genreFound > 1) {
                            //System.out.println("  quitting");
                            reader.close();
                            run = false;
                            break;
                        } else if (currentLine.contains("/year/")) {
                            System.out.println("year: " + currentLine.substring(12, 16));
                            if (yearCheck(currentLine.substring(12, 16))) {
                                videoFile.setYear(Integer.parseInt(currentLine.substring(12, 16)));
                                System.out.println("year found: " + videoFile.getYear());
                            }
                        } else if (ratingCheck(currentLine)) {
                            System.out.println("Rating: " + currentLine.substring(7, currentLine.length()));
                            videoFile.setRating(Double.parseDouble(currentLine.substring(7, currentLine.length())));
                            videoFile.setDataGathered(true);
                            System.out.println("Rating saved: " + videoFile.getRating());
                        }
                    }
                }
            }
        } catch (Exception e) {
            //System.out.println("failed url");
            //return "no link";
        }
        //return "no link";

    }

    public boolean ratingCheck(String input) {
        return input.matches("title=\"[0-9].[0-9]");
    }

    public boolean yearCheck(String year) {
        return year.matches("[12][90][0-9][0-9]");
    }

    public void downloadDvdCoverAndData(int sizeFactor, VideoFile videoFile) {
        //String yearS;
        //if (year == 0) {
        //    yearS = "";
        //} else {
        //    yearS = year + "";
        //}
        this.saveLink = videoFile.getTitle() + ".jpg";
        this.sizeFactor = sizeFactor;
        if (!videoFile.getHasImage()) {
            System.out.println(videoFile.getTitle() + " has no image, downloading image");
            fileLoader.saveImage(branchLinks(videoFile)); //returns the cleaned link, and sends to save
        } else {
            branchLinks(videoFile);
        }

        //branchLinks(title, videoFile);
    }

    public String getSaveLink() {
        return saveLink;
    }

    public String branchLinks(VideoFile videoFile) {
        //returns the link to the image
        //uses convertTitle to remove blank spaces and replace with "+" for the url
        //uses resizeImageURL to change the size parameters in the url into what I want
        String imdbSearch = "http://www.imdb.com/find?ref_=nv_sr_fn&q=" + convertTitle(videoFile.getTitle()) + "&s=all";

        try {

            URL imdb = new URL(imdbSearch);
            BufferedReader in = new BufferedReader(new InputStreamReader(imdb.openStream()));

            String inputLine = "";
            //Scanner reader = new Scanner(inputLine);
            boolean linkFound = false;
            while ((inputLine = in.readLine()) != null) {

                if (inputLine.contains("_AL_.jpg")) {
                    Scanner reader = new Scanner(inputLine);
                    while (reader.hasNext()) {
                        String currentLine = reader.next();
                        if (currentLine.contains("_AL_.jpg")) {
                            currentLine = currentLine.substring(5, currentLine.length() - 1); //trims the html from the filename
                            in.close();
                            reader.close();
                            //System.out.println("print " + resizeImageURL(currentLine, sizeFactor));
                            return resizeImageURL(currentLine, sizeFactor);
                            //break;
                        } else if (!linkFound && currentLine.contains("_tt_1\"")) {
                            System.out.println("file line " + currentLine.substring(7, currentLine.length() - 1));
                            getGenresDate(currentLine.substring(7, currentLine.length() - 1), videoFile);

                            linkFound = true;
                        }

                    }
                }
            }
        } catch (Exception e) {
            //System.out.println("failed url");
            //return "no link";
        }
        return "no link";
    }

    public String resizeImageURL(String urlOriginal, int sizeFactor) {
        System.out.println("resizing URL image " + urlOriginal);

        String appendUX = "V1_UX" + 32 * sizeFactor + "_CR0,0," + 32 * sizeFactor + "," + 44 * sizeFactor + "_AL_.jpg";
        String appendUY = "V1_UY" + 44 * sizeFactor + "_CR12,0," + 32 * sizeFactor + "," + 44 * sizeFactor + "_AL_.jpg";
        //default ._V1_UX32_CR0,0,32,44_AL_.jpg
        //Scanner reader = new Scanner(urlOriginal);
        String urlResize = urlOriginal;
        //char checkChar = ' ';
        //String partV1 = "";

        //System.out.println(urlResize.substring(0, urlResize.length() - 29));
        //System.out.println(urlResize.substring(urlResize.length() - 24, urlResize.length() - 22)); //UY or UX
        //System.out.println(urlResize.charAt(urlResize.length() - 17)); //first cropping value
        if (urlResize.substring(urlResize.length() - 24, urlResize.length() - 22).equals("UX")) {
            //return urlResize.substring(0, urlResize.length() - 29) + appendUX;
            System.out.println("returning resize link: " + cropLink(urlOriginal) + appendUX);
            return cropLink(urlOriginal) + appendUX;
        } else {
            //return urlResize.substring(0, urlResize.length() - 29) + appendUY;
            System.out.println("returning resize link: " + cropLink(urlOriginal) + appendUY);
            return cropLink(urlOriginal) + appendUY;
        }
    }

    public String cropLink(String originalImageLink) {
        System.out.println("cropping: " + originalImageLink);
        String croppedLink = "";
        for (int i = 0; i < originalImageLink.length(); i++) {
            croppedLink += originalImageLink.charAt(i);
            if (originalImageLink.substring(i, i + 3).equals("_V1")) {
                //System.out.println("checking: " + originalImageLink.substring(i, i+3));
                break;
            }
        }
        System.out.println("returning cropped link: " + croppedLink);
        return croppedLink;
    }

    public BufferedImage getImage(String fileLink) {
        //gets the specified image from online source
        System.out.println("getting image from link: " + fileLink);
        BufferedImage dvdCase;

        try {
            dvdCase = ImageIO.read(new URL(fileLink));
            System.out.println("returning new image");
            return dvdCase;
        } catch (IOException e) {
            //implement something to return a blank case with maybe the title one it or try another source
            System.out.println("get image failed");
            try {
                return ImageIO.read(new File(absolutePath + "/noImageAvailable.jpg"));
            } catch (Exception x) {
                System.out.println("failed to return the blank Image too");
            }
            return null;
        }

    }

    /*
    public void saveImage(String imageDirectory, String fileLink) {
        System.out.println("saving from imdb to: "+imageDirectory+""+fileLink);
        try {
            BufferedImage bi = getImage(fileLink);
            File outputfile = new File(saveLink);
            ImageIO.write(bi, "jpg", outputfile);
            System.out.println("saved image: " + fileLink);
        } catch (IOException e) {
            System.out.println("save image failed");
        }
    }
     */
}
