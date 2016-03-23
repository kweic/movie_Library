/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MovieUILogic;

/**
 *
 * @author kw
 */
public class MovieOrSeriesClassifier {

    public MovieOrSeriesClassifier() {
        System.out.println("MOVIE or SERIES created by: "+super.toString());

    }

    public void classify(VideoFile video) {
        //returns Movie or Series

        if (scanFile(video.getPath())) { // true == isMovie
           // System.out.println(video.getTitle() + " set to true");
            System.out.println("setting "+video.getTitle()+" as movie.");
            video.setIsMovie(true);
        }
        //System.out.println(video.getTitle() + " set to false");
        System.out.println("setting "+video.getTitle()+" as SERIES");
        video.setIsMovie(false);
    }

    public boolean scanFile(String input) {
        //spit out S00E00 length parts of string
        //return false if not a movie
        //return true if it gets past the tests

        int decrease = 2;
        //lengths
        //S00E00
        //s0E00
        //s0e0
        //s00

        while (decrease <= 6) {
            String lcFile = input.toLowerCase();
            for (int i = 0; i < lcFile.length() - decrease; i++) {
                if (checkPiece(lcFile.substring(i, i + decrease))) {
                    //System.out.println(lcFile.substring(i, i + decrease));
                    //System.out.println("     not movie"+input);
                    return false;
                }
            }
            decrease+=4;
        }
        //spit out S0E00 length
        return true;
    }

    public boolean checkPiece(String input) {
        //System.out.println("checking :  " + input);
        // s[0-9][0-9]e[0-9][0-9]|s[0-9]e[0-9][0-9]|s[0-9]e[0-9]|s([0-9]?)
        return input.matches("s[0-9]|season");
    }
}
