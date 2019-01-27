/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.jj
 */
package tileGame.states;

import java.awt.Graphics;
import tileGame.Game;
import tileGame.Handler;
import tileGame.entities.creatures.Player;
import tileGame.worlds.World;

/**
 *
 * @author Antoine Ho
 */
public class GameState extends State{
    
    private Player player;
    private World world;
    
    public GameState(Handler handler) {
        super(handler);
        world = new World(handler, "res/worlds/world1.txt");
        handler.setWorld(world);
        player = new Player(handler, 100, 100, 16, 32);
    }
    
    @Override
    public void tick() {
        world.tick();
        player.tick();
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
        player.render(g);
    }
    
}
