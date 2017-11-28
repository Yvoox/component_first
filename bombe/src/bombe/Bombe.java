
package bombe;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Bombe extends Canvas
        implements Runnable{
        private boolean alive = true;
        private long delay = 100;//1000
        private int timeLeft = 10;
        private ArrayList listeners = new ArrayList();
        private Image bombe, explosion;
        
    public Bombe(){
        super.setSize(40, 40);
    }

    @Override
    public void run() {


                while (alive) {
            try {
                Thread.sleep(delay);
                if(this.getTimeLeft()>0){
                this.setTimeLeft(this.getTimeLeft()-1);}
                else{
                    this.boom();
                }
                System.out.println(this.getTimeLeft());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void start() {
        new Thread(this).start(); 
    }
    
    public void boom() {
        alive = false;
        this.repaint();
    }
    
    public long getDelay(){
        return delay;
    }
    
    public void setDelay(long delay){
        this.delay=delay;
    }
    
    public int getTimeLeft(){
        return timeLeft;
    }
    
    public void setTimeLeft(int timeLeft){
        this.timeLeft=timeLeft;
    }


     public void paint(Graphics g) {
         super.paint(g);
         
            try {
                bombe = ImageIO.read(getClass().getClassLoader().getResource("./res/bombe.png"));
                explosion = ImageIO.read(getClass().getClassLoader().getResource("./res/explosion.png"));
                //System.out.println(explosion.toString());
            } catch (IOException ex) {
                Logger.getLogger(Bombe.class.getName()).log(Level.SEVERE, null, ex);
            }
                       
             if(alive){
                 //g.drawString("Bombe", 0, 15);
                g.drawImage(bombe, 0, 0, this);
                
  
             }
            else{
                //g.drawString("Boom", 0, 15);
                g.drawImage(explosion, 0, 0, this);
            }

         

    }
    
}
