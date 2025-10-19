package me.khabir.ui;

import me.khabir.entity.Task;

import java.util.Map;

public class ConsoleTaskDrawer implements TaskDrawer {
  @Override
  public void draw(Map<Integer, Task> schedule) {
    for (Map.Entry<Integer, Task> entry : schedule.entrySet()) {
      Integer time = entry.getKey();
      Task task = entry.getValue();
      if (task != null) {
        System.out.println("Time " + time + ": Task " + task.getName());
      } else {
        System.out.println("Time " + time + ": Idle");
      }
    }
  }
}
