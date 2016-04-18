package MovieUILogic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CancelClickListener implements ActionListener {

    boolean running;
    
    public CancelClickListener(boolean running){
        this.running = running;
    }
    
    public boolean getRunning(){
        //System.out.println("returning running: "+running);
        return running;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        running = false;
        System.out.println("running set false in clickListener");
    }
}
