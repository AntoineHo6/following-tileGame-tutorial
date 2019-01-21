/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.jj
 */
package tileGame.states;

import gfx.Assets;
import java.awt.Graphics;
import tileGame.Game;
import tileGame.entities.creatures.Player;

/**
 *
 * @author Antoine Ho
 */
public class GameState extends State{
    
    private Player player;
    
    public GameState(Game game) {
        super(game);
        player = new Player(game, 100, 100);
    }
    
    @Override
    public void tick() {
        player.tick();
    }

    @Override
    public void render(Graphics g) {
        player.render(g);
    }
    
}
