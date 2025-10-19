package me.khabir.ui;

import me.khabir.entity.Task;

import java.util.Map;

public interface TaskDrawer {
  void draw(Map<Integer, Task> schedule);
}
