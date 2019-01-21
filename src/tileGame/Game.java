/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tileGame;

import display.Display;
import gfx.Assets;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import tileGame.input.KeyManager;
import tileGame.states.GameState;
import tileGame.states.MenuState;
import tileGame.states.State;

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
    
    // States
    private State gameState;
    private State menuState;
    
    // Inputs
    private KeyManager keyManager;
    
    
    public Game(String title, int width, int height){
        this.WIDTH = width;
        this.HEIGHT = height;
        this.TITLE = title;
        keyManager = new KeyManager();
    }
    
    private void init() {
        display = new Display(TITLE, WIDTH, HEIGHT);
        display.getFrame().addKeyListener(keyManager);
        Assets.init();
        
        gameState = new GameState(this);
        menuState = new MenuState(this);
        State.setState(gameState);
    }
    
    private void tick() {
        keyManager.tick();
        
        if (State.getState() != null) {
            State.getState().tick();
        }
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
        
        if (State.getState() != null) {
            State.getState().render(g);
        }
        
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
    
    public KeyManager getKeyManager() {
        return keyManager;
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
