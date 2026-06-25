package com.tripod.core;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {

  private static final long serialVersionUID = 1L;
  
  private static final String TITLE = "Toxic Valley";
  
  public static final int WIDTH = 800;
  public static final int HEIGHT = 600;
  
  private static final int SCALE = 4;
  private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
  private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
  
  private static Thread thread;
  private static volatile boolean running = false;
  
  private static Screen screen;
  
  public static void main(String args[]) {
    System.out.println("Running");
    
    JFrame frame = new JFrame(TITLE);
    Game game = new Game();
    
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setResizable(false);
    frame.setSize(WIDTH, HEIGHT);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
    frame.add(game);
    
    screen = new Screen(WIDTH, HEIGHT);
    
    thread = new Thread(game);
    thread.start();
    
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
    
    screen.render(0, 0, pixels);
    
    g.drawImage(image, 0, 0, getWidth() * SCALE, getHeight() * SCALE, null);
    
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
