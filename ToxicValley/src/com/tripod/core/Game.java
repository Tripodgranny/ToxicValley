package com.tripod.core;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.tripod.core.graphics.Sprite;
import com.tripod.core.graphics.SpriteSheet;

public class Game extends Canvas implements Runnable {

  private static final long serialVersionUID = 1L;
  
  private static final String TITLE = "Toxic Valley";
  public static final int WIDTH = 256;
  public static final int HEIGHT = 224;
  private static final int SCALE = 3;
  private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
  private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
  private static Thread thread;
  private static volatile boolean running = false;
  
  private Screen screen;
  private Sprite sprite;
  
  public static void main(String args[]) {
    System.out.println("Running");
    
    JFrame frame = new JFrame(TITLE);
    Game game = new Game();
    
    game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
    frame.add(game);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setResizable(false);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
   
    thread = new Thread(game);
    thread.start();
    
  }
  
  public Game() {
    init();
  }
  
  private void init() {
    screen = new Screen(0, 0, WIDTH, HEIGHT);
    
    sprite = new Sprite(SpriteSheet.GAME_SHEET);
  }
  
  private void render() {
    
    BufferStrategy bs = this.getBufferStrategy();
    if (bs == null) {
      createBufferStrategy(3);
      return;
    }
    
    Graphics g = bs.getDrawGraphics();
    for (int i = 0; i < WIDTH*HEIGHT; i++) {
      pixels[i] = 0;
    }
    
    screen.renderSprite(0, 0, sprite);
    screen.render(pixels);
    
    Graphics2D g2 = (Graphics2D) g;
    g2.setRenderingHint(
        RenderingHints.KEY_INTERPOLATION,
        RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR
    );
    
    g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
    
    g.dispose();
    bs.show();
    
  }
  
  private void tick() {
    
  }
  
  @Override
  public void run() {
      System.out.println("Thread started");
      running = true;
      long lastTime = System.nanoTime();
      double amountOfTicks = 60.0;
      double nsPerTick = 1000000000 / amountOfTicks;
      double amountOfFrames = 60.0;
      double nsPerFrame = 1000000000 / amountOfFrames;
      double deltaTicks = 0;
      double deltaFrames = 0;
      long timer = System.currentTimeMillis();
      int frames = 0;
      int updates = 0;

      while (running) {
          long now = System.nanoTime();
          deltaTicks += (now - lastTime) / nsPerTick;
          deltaFrames += (now - lastTime) / nsPerFrame;
          lastTime = now;
          while (deltaTicks >= 1) {
              tick();
              updates++;
              deltaTicks--;
          }
          if (deltaFrames >= 1) {
              render();
              frames++;
              deltaFrames--;
          }
          try {
              Thread.sleep(1);
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
          if (System.currentTimeMillis() - timer > 1000) {
              timer += 1000;
              System.out.println("FPS: " + frames + " | UPS: " + updates);
              frames = 0;
              updates = 0;
          }
      }
  }

}
