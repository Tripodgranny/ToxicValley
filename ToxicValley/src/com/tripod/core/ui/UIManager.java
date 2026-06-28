package com.tripod.core.ui;

import com.tripod.core.ui.menu.MenuGroup;

public class UIManager {
  
  public static UIManager uiManager = getInstance();
  
  private MenuGroup menuGroup;
  
  public static UIManager getInstance() {
    if (uiManager == null) {
      uiManager = new UIManager();
    }
    
    return uiManager;
  }
  
  public void register(MenuGroup menuGroup) {
    this.menuGroup = menuGroup;
  }
  
  public void deregister() {
    this.menuGroup = null;
  }
  
  public void update() {
    if (menuGroup == null)
      return;
    
    if (menuGroup.getIsOpen())
      menuGroup.update();
    
  }

}
