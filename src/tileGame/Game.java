/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tileGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

/**
 *
 * @author Antoine Ho
 */
public class Game implements Runnable{
    
    private Display display;
    public int WIDTH, HEIGHT;
    public String TITLE;
    private Thread thread;
    private boolean running = false;
    
    private BufferStrategy bs;
    private Graphics g;
    
    public Game(String title, int width, int height){
        this.WIDTH = width;
        this.HEIGHT = height;
        this.TITLE = title;
    }
    
    private void init() {
        display = new Display(TITLE, WIDTH, HEIGHT);
    }
    
    private void tick() {
        
    }
    
    private void render() {
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        
        g = bs.getDrawGraphics();
        
        // Clear screen (always done before drawing)
        g.clearRect(0, 0, WIDTH, HEIGHT);
        
        // Draw Here
        
        
        
        // End Drawing
        
        bs.show();
        g.dispose();
    }

    @Override
    public void run() {
        init();
        
        while(running) {
            tick();
            render();
        }
        
        stop();     // in case thread doesn't stop.
    }
    
    public synchronized void start() {
        if (running) return;
        
        running = true;
        thread = new Thread(this);
        thread.start();
    }
    
    public synchronized void stop() {
        if (!running) return;
        
        running = false;
        try {
            thread.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
