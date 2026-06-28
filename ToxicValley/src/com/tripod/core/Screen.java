package com.tripod.core;

import java.util.ArrayList;

import com.tripod.core.graphics.Sprite;
import com.tripod.core.ui.button.Button;
import com.tripod.core.ui.menu.MenuGroup;

public class Screen {

  public final int X;
  public final int Y;
  public final int WIDTH;
  public final int HEIGHT;
  public int[] pixels;

  public Screen(int X, int Y, int WIDTH, int HEIGHT) {
    this.X = X;
    this.Y = Y;
    this.WIDTH = WIDTH;
    this.HEIGHT = HEIGHT;
    this.pixels = new int[WIDTH * HEIGHT];
  }

  public void render(int[] pixels) {
    for (int yp = 0; yp < HEIGHT; yp++) {
      int screenY = yp + Y;

      if (screenY < 0 || screenY >= Game.HEIGHT)
        continue;

      for (int xp = 0; xp < WIDTH; xp++) {
        int screenX = xp + X;

        if (screenX < 0 || screenX >= Game.WIDTH)
          continue;

        pixels[screenX + screenY * Game.WIDTH] = this.pixels[xp + yp * WIDTH];
      }
    }
  }

  public void renderSprite(int xPos, int yPos, Sprite sprite) {

    int[] spritePixels = sprite.getPixels();

    for (int y = 0; y < sprite.getHeight(); y++) {
      int screenY = y + yPos;

      if (screenY < 0 || screenY >= HEIGHT)
        continue;

      for (int x = 0; x < sprite.getWidth(); x++) {
        int screenX = x + xPos;

        if (screenX < 0 || screenX >= WIDTH)
          continue;

        int color = spritePixels[x + y * sprite.getWidth()];

        // Example: treat magenta as transparent
        if (color == 0xFFFF00FF)
          continue;

        pixels[screenX + screenY * WIDTH] = color;
      }
    }

  }

  public void renderMenuGroup(int xPos, int yPos, MenuGroup menuGroup) {
    ArrayList<Button> buttons = menuGroup.getOpenMenu().getButtons();
    buttons.stream()
        .forEach(b -> renderSprite(b.getX(), b.getY(), b.getSprite()));
  }

}
