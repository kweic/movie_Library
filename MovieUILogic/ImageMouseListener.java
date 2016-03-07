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
public class ImageMouseListener {
    String filePath;
    public ImageMouseListener(String filePath){
        this.filePath = filePath;
    }
    
    public void printTest(){
        System.out.println("clicked: "+filePath);
    }
}
