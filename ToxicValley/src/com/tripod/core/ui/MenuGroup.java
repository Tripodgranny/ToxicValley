package com.tripod.core.ui;

import java.util.Set;

/**
 * A group of Menus which renders the menu based on index.
 */
public abstract class MenuGroup {
  
  Set<Menu> menus;
  
  public abstract void render();

}
