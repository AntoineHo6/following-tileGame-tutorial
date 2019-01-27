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
    
    public Player(Game game, float x, float y, int width, int height) {
        super(game, x, y, width, height);
    }

    @Override
    public void tick() {
        getInput();
        move();
    }
    
    private void getInput() {
        xMove = 0;
        yMove = 0;
        
        if (game.getKeyManager().up) {
            yMove = -speed;
        }
        if (game.getKeyManager().down) {
            yMove = speed;
        }
        if (game.getKeyManager().left) {
            xMove = -speed;
        }
        if (game.getKeyManager().right) {
            xMove = speed;
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.player, (int) x, (int) y, width, height, null);
    }
    
}
