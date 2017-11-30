
package bombe;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Bombe extends Canvas
        implements Runnable{
        private boolean alive = true;
        private long delay = 1000;//1000
        private int time = 10;
        private int timeLeft = 10;
        private ArrayList listeners = new ArrayList();
        private Image bombe, explosion, bombe_fat;
        
    public Bombe(){
        super.setSize(60, 60);
    }

    @Override
    public void run() {


                while (alive) {
            try {
                
                Thread.sleep(delay);
                if(this.getTimeLeft()>0){
                    this.repaint();
                this.setTimeLeft(this.getTimeLeft()-1);
                if(this.getTimeLeft()<4){
                    fireTempEvent(new TempEvent(this));
                }
                System.out.println(this.getTimeLeft());}
                else{
                    this.boom();
                }
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void start() {
        new Thread(this).start(); 
    }
    
    public void restart(){
        alive = true;
        timeLeft=time;
        this.repaint();
    }
    
    
    public void boom() {
        alive = false;
        this.repaint();
    }
    
    public void stop(){
        alive=false;
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
    
    public int getTime(){
        return time;
    }
    
    public void setTime(int time){
        this.time=time;
    }
    


     public void paint(Graphics g) {
         super.paint(g);
         
            try {
                bombe = ImageIO.read(getClass().getClassLoader().getResource("./res/bombe.png"));
                bombe_fat = ImageIO.read(getClass().getClassLoader().getResource("./res/bombe_fat.png"));
                explosion = ImageIO.read(getClass().getClassLoader().getResource("./res/explosion.png"));
                //System.out.println(explosion.toString());
            } catch (IOException ex) {
                Logger.getLogger(Bombe.class.getName()).log(Level.SEVERE, null, ex);
            }
                       
             if(getTimeLeft()<=10 && getTimeLeft()>3){
                 //g.drawString("Bombe", 0, 15);
                g.drawImage(bombe, 0, 0, this);
             }
             if(getTimeLeft()>=1 && getTimeLeft()<=3){
                 g.drawImage(bombe_fat, 0, 0, this);
             }
            if(getTimeLeft()==0){
                //g.drawString("Boom", 0, 15);
                g.drawImage(explosion, 0, 0, this);
            }

         

    }
     
         public void addTempListener(TempListener l) {
        listeners.add(l);
    }
    public void removeTempListener(TempListener l) {
        listeners.remove(l);
    }
    private void fireTempEvent(TempEvent e) {
        //System.out.println(getTimeLeft());
         Iterator i = listeners.iterator();
        while(i.hasNext())
            ((TempListener)i.next()).temp(e);
    }
    
    public void onClick(){       
        timeLeft=time;
        //fireTempEvent(new TempEvent(this));

    }
    

    
}
