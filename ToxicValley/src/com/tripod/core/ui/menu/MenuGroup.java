package com.tripod.core.ui.menu;

import java.util.ArrayList;

/**
 * A group of Menus which renders the menu based on index.
 */
public class MenuGroup {
  
  private ArrayList<Menu> menus;
  private int menuIndex = 0;
  private boolean isOpen = true;
  
  public MenuGroup(ArrayList<Menu> menus) { 
    this.menus = menus;
  }
  
  public void addMenu(Menu menu) {
    this.menus.add(menu);
  }
  
  public Menu getOpenMenu() {
    return menus.get(menuIndex);
  }
  
  public int getMenuIndex() {
    return menuIndex;
  }
  
  public void setMenuIndex(int menuIndex) {
    this.menuIndex = menuIndex;
  }
  
  public boolean getIsOpen() {
    return isOpen;
  }
  
  public void setIsOpen(boolean isOpen) {
    this.isOpen = isOpen;
  }
  
  public void update() {
    menus.stream().forEach(m -> m.update());
  }

}
