package com.tripod.core.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Holds SpriteSheet Information.
 */
public class SpriteSheet {

  public final int WIDTH;
  public final int HEIGHT;
  public int[] pixels;

  public static final SpriteSheet GAME_SHEET = new SpriteSheet(
      "/sprites/ToxicValleyWorldMap.png");
  public static final SpriteSheet BUTTONS_SHEET = new SpriteSheet(
      "/sprites/buttons.png");

  public SpriteSheet(String path) {
    BufferedImage image;

    try {
      image = ImageIO.read(SpriteSheet.class.getResource(path));

      WIDTH = image.getWidth();
      HEIGHT = image.getHeight();

      pixels = new int[WIDTH * HEIGHT];
      image.getRGB(0, 0, WIDTH, HEIGHT, pixels, 0, WIDTH);

    } catch (IOException e) {
      throw new RuntimeException("Failed to load sprite sheet: " + path, e);
    }
  }
}