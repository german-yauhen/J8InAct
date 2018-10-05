package com.inaction.practise.bean;

public class SingletonExample {

  private static SingletonExample INSTANCE;

  private SingletonExample() {}

  public static synchronized SingletonExample getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new SingletonExample();
    }
    return INSTANCE;
  }

  public static SingletonExample getInstanceWithDoubleCheckLocking() {
    if (INSTANCE == null) {
      synchronized (SingletonExample.class) {
        if (INSTANCE == null) {
          INSTANCE = new SingletonExample();
        }
      }
    }
    return INSTANCE;
  }


}
