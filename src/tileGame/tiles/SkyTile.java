/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tileGame.tiles;

import gfx.Assets;

/**
 *
 * @author Antoine Ho
 */
public class SkyTile extends Tile{
    
    public SkyTile(int id) {
        super(Assets.sky, id);
    }
    
    @Override
    public boolean isSolid() {
        return false;
    }
}
