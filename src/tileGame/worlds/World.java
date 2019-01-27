/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tileGame.worlds;

import java.awt.Graphics;
import tileGame.Game;
import tileGame.Handler;
import tileGame.tiles.Tile;
import tileGame.utils.Utils;

/**
 *
 * @author Antoine Ho
 */
public class World {
    
    private Handler handler;
    private int width, height;
    private int spawnX, spawnY;
    private int[][] tiles;
    
    public World(Handler handler, String path) {
        this.handler = handler;
        loadWorld(path);
    }
    
    public void tick() {
        
    }
    
    public void render(Graphics g) {
        int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.DEF_TILE_WIDTH);
        int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.DEF_TILE_WIDTH+1);
        int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.DEF_TILE_HEIGHT);;
        int yEnd = (int) Math.min(width, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.DEF_TILE_HEIGHT+1);
        
        for (int i = yStart; i < yEnd; i++) {
            for (int j = xStart; j < xEnd; j++) {
                getTile(j, i).render(g, (int) (j * Tile.DEF_TILE_WIDTH - handler.getGameCamera().getxOffset()), 
                        (int) (i * Tile.DEF_TILE_HEIGHT - handler.getGameCamera().getyOffset()));
            }
        }
    }
    
    public Tile getTile(int x, int y) {
        Tile t;
        try{
            t = Tile.tiles[tiles[x][y]];
        } catch(ArrayIndexOutOfBoundsException e){
            t = null;
        }
        
        if (t == null) {
            return Tile.groundTile;
        }
        
        return t;
    }
    
    private void loadWorld(String path) {
        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        spawnX = Utils.parseInt(tokens[2]);
        spawnY = Utils.parseInt(tokens[3]);
        
        tiles = new int[width][height];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                tiles[j][i] = Utils.parseInt(tokens[(j+i*width) + 4]);
            }
        }
    }
}
