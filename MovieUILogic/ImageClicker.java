/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MovieUILogic;

import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.JLabel;


public class ImageClicker {

    private VideoFile videoFile;
    private String directory;

    public ImageClicker(String directory, VideoFile videoFile) {
        this.directory = directory;
        this.videoFile = videoFile;
    }

    public JLabel addListener(JLabel cover) {

        cover.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                //System.out.println(message);
                videoFile.movePlaycount(1);
                openFile();
            }

        });
        return cover;
    }

    public void openFile() {
        try {
            Desktop.getDesktop().open(new File(directory + "" + videoFile.getPath()));
        } catch (Exception e) {

        }
    }

}
