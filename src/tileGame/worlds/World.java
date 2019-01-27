/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tileGame.worlds;

import java.awt.Graphics;
import tileGame.Game;
import tileGame.tiles.Tile;
import tileGame.utils.Utils;

/**
 *
 * @author Antoine Ho
 */
public class World {
    
    private Game game;
    private int width, height;
    private int spawnX, spawnY;
    private int[][] tiles;
    
    public World(Game game, String path) {
        this.game = game;
        loadWorld(path);
    }
    
    public void tick() {
        
    }
    
    public void render(Graphics g) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                getTile(j, i).render(g, j * Tile.DEF_TILE_WIDTH, i * Tile.DEF_TILE_HEIGHT);
            }
        }
    }
    
    public Tile getTile(int x, int y) {
        Tile t = Tile.tiles[tiles[x][y]];
        
        if (t == null) {
            return Tile.brickTile;
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
