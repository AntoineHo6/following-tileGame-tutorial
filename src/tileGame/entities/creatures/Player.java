/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tileGame.entities.creatures;

import gfx.Assets;
import java.awt.Graphics;
import tileGame.Game;

/**
 *
 * @author Antoine Ho
 */
public class Player extends Creature {
    
    private Game game;
    
    public Player(Game game, float x, float y) {
        super(x, y);
        this.game = game;
    }

    @Override
    public void tick() {
        if (game.getKeyManager().up) {
            y -= 3;
        }
        else if (game.getKeyManager().down) {
            y += 3;
        }
        else if (game.getKeyManager().left) {
            x -= 3;
        }
        else if (game.getKeyManager().right){
            x += 3;
        }
        
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.player, (int) x, (int) y, null);
    }
    
}
