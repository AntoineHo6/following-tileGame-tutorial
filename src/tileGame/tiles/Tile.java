/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tileGame.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Antoine Ho
 */
public class Tile {
    
    public static Tile[] tiles = new Tile[256];
    public static Tile groundTile = new GroundTile(0);
    public static Tile brickTile = new BrickTile(1);
    public static Tile bushTile = new BushTile(2);
    public static Tile skyTile = new SkyTile(3);
    
    public static final int DEF_TILE_WIDTH = 16, DEF_TILE_HEIGHT = 16;
    
    protected BufferedImage texture;
    protected final int id;
    
    public Tile(BufferedImage texture, int id) {
        this.texture = texture;
        this.id = id;
        
        tiles[id] = this;
    }
    
    public void tick() {
        
    }
    
    public void render(Graphics g, int x, int y) {
        g.drawImage(texture, x, y, DEF_TILE_WIDTH, DEF_TILE_HEIGHT, null);
    }
    
    public boolean isSolid() {
        return true;
    }
    
    public int getId() {
        return id;
    }
}
