package me.khabir;

import me.khabir.edf.EDFAlgorithm;
import me.khabir.entity.Task;
import me.khabir.ui.GanttTaskDrawer;
import me.khabir.ui.TaskDrawer;

import java.util.List;
import java.util.Map;

public class Main {
  public static void main(String[] args) {
    Task[] tasks = {new Task("A", 3, 7, 20), new Task("B", 2, 4, 5), new Task("C", 1, 8, 10)};

    EDFAlgorithm edf = new EDFAlgorithm(List.of(tasks));

    if (edf.isSchedulable(tasks)) {
      Map<Integer, Task> schedule = edf.getScheduledTasks();

      TaskDrawer drawer = new GanttTaskDrawer();
      drawer.draw(schedule);
    } else {
      System.out.println("The task set is not schedulable under EDF.");
    }
  }
}

