/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tileGame.tiles;

import gfx.Assets;
import java.awt.Graphics;

/**
 *
 * @author Antoine Ho
 */
public class BushTile extends Tile {

    public BushTile(int id) {
        super(Assets.bush, id);
    }
    
    @Override
    public void render(Graphics g, int x, int y) {
        g.drawImage(texture, x, y, 32, 16, null);
    }
    
    @Override
    public boolean isSolid() {
        return false;
    }
}
