package com.tripod.core.ui.menu;

import java.util.ArrayList;

import com.tripod.core.ui.button.Button;

/**
 * A Menu which renders it's components
 */
public class Menu {
  
  private MenuGroup menuGroup;
  
  private ArrayList<Button> buttons;
  
  public Menu(ArrayList<Button> list) {
    this.buttons = list;
  }

  public MenuGroup getMenuGroup() {
    return menuGroup;
  }

  public void setMenuGroup(MenuGroup menuGroup) {
    this.menuGroup = menuGroup;
  }

  public ArrayList<Button> getButtons() {
    return buttons;
  }

  public void setButtons(ArrayList<Button> buttons) {
    this.buttons = buttons;
  }
  
  public void update() {
    buttons.stream().forEach(b -> b.update());
  }
  
}
