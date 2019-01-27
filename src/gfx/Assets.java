/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gfx;

import java.awt.image.BufferedImage;

/**
 *
 * @author Antoine Ho
 */
public class Assets {
    
    private static final int charWidth = 16, charHeight = 32;
    private static final int TILE_WIDTH = 16, TILE_HEIGHT = 16;
    
    public static BufferedImage player, ground, brick,
            bush, sky;

    // Loads everything for the game
    public static void init() {
        SpriteSheet charSheet = new SpriteSheet(ImageLoader.loadImage("/textures/mario&luigi.png"));
        SpriteSheet tileSheet = new SpriteSheet(ImageLoader.loadImage("/textures/tileSet.png"));
        
        player = charSheet.crop(80, 1, charWidth, charHeight);
        ground = tileSheet.crop(0, 0, TILE_WIDTH, TILE_HEIGHT);
        brick = tileSheet.crop(16, 0, TILE_WIDTH, TILE_HEIGHT);
        bush = tileSheet.crop(184, 144, 32, TILE_HEIGHT);
        sky = tileSheet.crop(48, 336, TILE_WIDTH, TILE_HEIGHT);
    }

}
