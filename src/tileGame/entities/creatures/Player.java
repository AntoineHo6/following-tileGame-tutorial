/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tileGame.entities.creatures;

import gfx.Assets;
import java.awt.Graphics;
import tileGame.Handler;

/**
 *
 * @author Antoine Ho
 */
public class Player extends Creature {
    
    public Player(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
    }

    @Override
    public void tick() {
        getInput();
        move();
        handler.getGameCamera().centerOnEntity(this);
    }
    
    private void getInput() {
        xMove = 0;
        yMove = 0;
        
        if (handler.getKeyManager().up) {
            yMove = -speed;
        }
        if (handler.getKeyManager().down) {
            yMove = speed;
        }
        if (handler.getKeyManager().left) {
            xMove = -speed;
        }
        if (handler.getKeyManager().right) {
            xMove = speed;
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.player, (int) (x - handler.getGameCamera().getxOffset()), 
                (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }
    
}
