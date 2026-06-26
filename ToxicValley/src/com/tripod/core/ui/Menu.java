package com.tripod.core.ui;

import java.util.ArrayList;

/**
 * A Menu which renders it's components
 */
public abstract class Menu {
  
  private MenuGroup menuGroup;
  
  private String title;
  private int index;
  private ArrayList<Button> buttons;
  
  public abstract void render();
  

}
