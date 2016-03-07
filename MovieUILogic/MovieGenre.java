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
public enum MovieGenre {
    ACTION("Action"),SCIFI("Sci-Fi"), ANIMATION("Animation"), 
    DRAMA("Drama"), FANTASY("Fantasy"), FAMILY("Family"), 
    HORROR("Horror"),CRIME("Crime"), HISTORY("History"), 
    WESTERNS("Western"), SPORT("Sport"), WAR("War"), 
    GAMESHOWS("Game-Show"), BIOGRAPHIES("Biography"), ADVENTURE("Adventure"), 
    COMEDIES("Comedy"), THRILLERS("Thriller"), MYSTERY("Mystery"), 
    ROMANCE("Romance"),DOCUMENTARIES("Documentary"), MUSICALS("Musical"), 
    MUSIC("Music"), REALITYTV("Reality-TV"), TALKSHOWS("Talk-Show"), 
    NEWS("News"), FILMNOIR("Film-Noir"), ADULT("Adult"), NONE("None");

    private String genre;

    private MovieGenre(String genre) {
        this.genre = genre;
    }

    public String getGenre() {
        return this.genre;
    }
    
    public String toString(){
        return getGenre();
    }
}
