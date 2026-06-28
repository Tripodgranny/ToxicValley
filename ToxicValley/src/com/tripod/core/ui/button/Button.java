package com.tripod.core.ui.button;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import com.tripod.core.Game;
import com.tripod.core.graphics.Sprite;
import com.tripod.core.input.mouse.MouseInputManager;

public class Button {
  
  int id;
  int x, y, startY, width, height;
  Sprite sprite;
  Rectangle bounds;
  
  private boolean wasOnButtonLastFrame = false;
  private boolean onButton = false;
  
  private double animationTimer = 0.0;
  
  public Button(int x, int y, Sprite sprite) {
    this.sprite = sprite;
    this.x = x;
    this.y = y;
    this.startY = y;
    bounds = new Rectangle(x*Game.SCALE, y*Game.SCALE, sprite.getWidth()*Game.SCALE, sprite.getHeight()*Game.SCALE);
  }
  
  public void selected() {
    System.out.println("Selected");
  }

  public void onEnter() {
    sprite.setFrame(1);
  }

  public void onExit() {
    sprite.setFrame(0);
  }

  public void onHover() {
    animationTimer += Game.deltaTime * 10.0; 
    y = (int) (startY + Math.sin(animationTimer) * 5);
  }
  
  public Sprite getSprite() {
    return sprite;
  }
  
  public int getX() {
    return x;
  }
  
  public int getY() {
    return y;
  }
  
  public void update() {
    bounds.setRect(x*Game.SCALE, y*Game.SCALE, sprite.getWidth()*Game.SCALE, sprite.getHeight()*Game.SCALE);
    onButton = 
        bounds.contains(MouseInputManager.getInstance().getPosition());
    
    if (onButton && wasOnButtonLastFrame) {
      onHover();
      if (MouseInputManager.getInstance().isButtonClicked(MouseEvent.BUTTON1)) {
        selected();
      }
    }
    
    if (onButton && !wasOnButtonLastFrame) {
      onEnter();
      wasOnButtonLastFrame = true;
    }

    if (!onButton && wasOnButtonLastFrame) {
      onExit();
      wasOnButtonLastFrame = false;
    }
    
  }

}
