package com.inaction.practise.bean;

import java.util.Arrays;
import java.util.List;

public enum Category {
  PORTRAIT, LANDSCAPE, CITY_LANDSCAPE, SKY, NATURE, SCIENCE, DOCUMENTARY, AUTO, DEVICE;

  public static List<Category> categories() {
    return Arrays.asList(Category.values());
  }
}
