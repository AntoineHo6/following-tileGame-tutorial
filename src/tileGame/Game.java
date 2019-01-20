/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tileGame;

import display.Display;
import gfx.Assets;
import gfx.ImageLoader;
import gfx.SpriteSheet;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

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
        Assets.init();
    }
    
    int x = 0;
    
    private void tick() {
        x += 1;
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
        
        g.drawImage(Assets.ground, x, 10, null);
        
        // End Drawing
        
        bs.show();
        g.dispose();
    }

    @Override
    public void run() {
        init();
        
        int fps = 60;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;
        
        while(running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;
            
            if (delta >= 1) {
                tick();
                render();
                ticks++;
                delta--;
            }
            
            if (timer >= 1000000000){
                System.out.println("ticks and Frames: " + ticks);
                ticks = 0;
                timer = 0;
            }
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
