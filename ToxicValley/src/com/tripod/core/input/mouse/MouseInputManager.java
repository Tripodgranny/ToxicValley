package com.tripod.core.input.mouse;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class MouseInputManager
    implements
      MouseListener,
      MouseMotionListener,
      MouseWheelListener {
  
  private static MouseInputManager mouseInputManager = null;

  // Track standard mouse buttons (Left, Middle, Right)
  private static final int NUM_BUTTONS = 5;
  private static final boolean[] buttons = new boolean[NUM_BUTTONS];
  private static final boolean[] lastButtons = new boolean[NUM_BUTTONS];

  // Mouse coordinates and wheel movement
  private static int mouseX, mouseY;
  private static int scrollRotation;

  public static MouseInputManager getInstance() {
    if (mouseInputManager == null) {
      mouseInputManager = new MouseInputManager();
    }
    
    return mouseInputManager;
  }

  private MouseInputManager() {
    mouseX = 0;
    mouseY = 0;
    scrollRotation = 0;
  }

  /**
   * Call this at the VERY END of your game loop update cycle. It syncs the last
   * frame's state with the current frame.
   */
  public void update() {
    System.arraycopy(buttons, 0, lastButtons, 0, NUM_BUTTONS);
    scrollRotation = 0; // Reset scroll after the frame processes it
  }

  // --- Public API for your Game Loop ---

  public int getX() {
    return mouseX;
  }
  
  public int getY() {
    return mouseY;
  }
  
  public Point getPosition() {
    return new Point(mouseX, mouseY);
  }
  
  public int getScroll() {
    return scrollRotation;
  }

  /** Returns true if the button is currently being held down */
  public boolean isButtonDown(int buttonCode) {
    if (buttonCode < 0 || buttonCode >= NUM_BUTTONS)
      return false;
    return buttons[buttonCode];
  }

  /** Returns true only on the exact frame the button was pressed */
  public boolean isButtonClicked(int buttonCode) {
    if (buttonCode < 0 || buttonCode >= NUM_BUTTONS)
      return false;
    return buttons[buttonCode] && !lastButtons[buttonCode];
  }

  /** Returns true only on the exact frame the button was released */
  public boolean isButtonReleased(int buttonCode) {
    if (buttonCode < 0 || buttonCode >= NUM_BUTTONS)
      return false;
    return !buttons[buttonCode] && lastButtons[buttonCode];
  }

  // --- MouseListener Implementation ---

  @Override
  public void mousePressed(MouseEvent e) {
    if (e.getButton() < NUM_BUTTONS) {
      buttons[e.getButton()] = true;
    }
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    if (e.getButton() < NUM_BUTTONS) {
      buttons[e.getButton()] = false;
    }
  }

  @Override
  public void mouseClicked(MouseEvent e) {
  } // Handled manually via updates

  @Override
  public void mouseEntered(MouseEvent e) {
  }

  @Override
  public void mouseExited(MouseEvent e) {
  }

  // --- MouseMotionListener Implementation ---

  @Override
  public void mouseMoved(MouseEvent e) {
    mouseX = e.getX();
    mouseY = e.getY();
  }

  @Override
  public void mouseDragged(MouseEvent e) {
    mouseX = e.getX();
    mouseY = e.getY();
  }

  // --- MouseWheelListener Implementation ---

  @Override
  public void mouseWheelMoved(MouseWheelEvent e) {
    scrollRotation = e.getWheelRotation(); // Negative = scroll up, Positive =
                                           // scroll down
  }
}