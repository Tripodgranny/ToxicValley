package com.tripod.core;

import java.util.Random;

public class Screen {
  
  public final int WIDTH;
  public final int HEIGHT;
  public int[] pixels;
  
  public Screen(int WIDTH, int HEIGHT) {
    this.WIDTH = WIDTH;
    this.HEIGHT = HEIGHT;
    this.pixels = new int[WIDTH * HEIGHT];
    
    Random random = new Random();
    
    for (int i = 0; i < WIDTH*HEIGHT; i++) {
      pixels[i] = random.nextInt();
    }
  }
  
  public void render(int x, int y, int[] pixels) { 
    for (int yy = y; yy < HEIGHT; yy++) {
      for (int xx = x; xx < WIDTH; xx++) {
        pixels[xx + yy * WIDTH] = this.pixels[xx + yy * WIDTH];
      }
    }
  }

}
