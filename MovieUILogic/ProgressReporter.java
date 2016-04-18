package MovieUILogic;


import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.WindowConstants;

/**
 *
 * @author kw
 */
public class ProgressReporter implements Runnable {

    private JFrame frame;
    JProgressBar pBar;
    JLabel text;
    JButton cancelButton;
    static boolean running;
    private CancelClickListener cancelListener;

    @Override
    public void run() {
        frame = new JFrame();
        frame.setSize(400,280);
        frame.setPreferredSize(new Dimension(400, 280));
        running = true;
        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH); //fullscreen
        //frame.setUndecorated(true); //no icons

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane());
        addListeners();
        frame.pack();
        frame.setVisible(true);
    }

    public void updateProgressBar(int input) {
        pBar.setValue(input);
        frame.repaint();
    }

    public boolean stillRunning() {
       return cancelListener.getRunning();
      // return running;
    }

    public void updateText(String input) {
        //System.out.println("input: "+input);
        text.setText(input);
    }

    private void createComponents(Container container) {
        //container.setLayout(new GridLayout(3, 1));

        
        JLabel descrip = new JLabel("Loading Images");
        descrip.setLocation(10, 20);
        descrip.setSize(200,30);
        
        text = new JLabel("Hi!");
        text.setForeground(Color.BLACK);
        text.setSize(300,50);
        text.setLocation(10, 100);
        //text.setAlignmentY(0);
        //text.setLocation(0,0);
        
        pBar = new JProgressBar();
        pBar.setSize(360, 30);
        pBar.setLocation(10, 50);

        cancelButton = new JButton("Cancel");
        cancelListener = new CancelClickListener(running);
        cancelButton.addActionListener(cancelListener);
        cancelButton.setSize(200,50);
        cancelButton.setLocation(80, 150);
        
        //cancelButton.setLocation(0,0);

        
        JPanel uiPanel = new JPanel();
        uiPanel.setSize(400,300);
        uiPanel.setForeground(Color.red);
        uiPanel.setLayout(null);
        
        uiPanel.add(descrip);
        uiPanel.add(text);
        uiPanel.add(pBar);
        
        uiPanel.add(cancelButton);
        container.add(uiPanel);
        //container.add(pBar);

        //container.add(text);
        //container.add(cancelButton);
        //uiPanel.add(text);
        //uiPanel.setBackground(Color.DARK_GRAY);

        //container.add(uiPanel);
        //frame.addKeyListener(new KeyboardListener(drawingboard, figure));
    }

    private void addListeners() {
    }
    
    public void closeWindow(){
        frame.dispose();
    }

    public JFrame getFrame() {
        return frame;
    }
}