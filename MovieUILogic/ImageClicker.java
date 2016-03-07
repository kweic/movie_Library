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

/**
 *
 * @author kw
 */
public class ImageClicker {

    private VideoFile videoFile;
 //   private JLabel label;
    private String directory;

    public ImageClicker(String directory, VideoFile videoFile) {
        this.directory = directory;
       // this.label = label;
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
        //File f = new File(message);
        //Desktop.open(f);
        try{
        Desktop.getDesktop().open(new File(directory+""+videoFile.getPath()));
        }catch(Exception e){
            
        }
    }
/*
    public void sizeUpLabel() {
        label.setSize(label.getWidth() + 10, label.getHeight() + 10);
    }

    public void resizeOnClick() {

        label.setSize(label.getWidth() - 10, label.getHeight() - 10);
        try {
            Thread.sleep(1000);
        } catch (Exception e) {

        }

    }
*/
}
