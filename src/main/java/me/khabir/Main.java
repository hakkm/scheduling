package me.khabir;

import me.khabir.algos.*;
import me.khabir.entity.Task;
import me.khabir.ui.GanttTaskDrawer;
import me.khabir.ui.TaskDrawer;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        List<Task> tasks = List.of(
                new Task("A", 3,20, 20),
                new Task("B", 2, 5, 5),
                new Task("C", 3, 10, 10)
        );
        List<Task> rm_tasks = List.of(
                new Task("A", 2,10, 10),
                new Task("B", 4, 15, 15),
                new Task("C", 10, 35, 35)
        );

        List<Task> tasksWithDeadlineMiss = List.of(
                new Task("A", 3, 7, 20),
                new Task("B", 2, 4, 5),
                new Task("C", 1, 8, 10)
        );

        Scheduler rm = new CachedScheduler(new RateMonotonic());

        switch (rm.isSchedulable(tasks)) {
            case SCHEDULABLE -> {
                Map<Integer, Task> schedule = rm.schedule(tasks);
                TaskDrawer drawer = new GanttTaskDrawer();
                drawer.draw(schedule);
            }
            case NOT_SCHEDULABLE -> {
                System.out.println("The task set is not schedulable using Rate Monotonic Scheduling.");
            }

            case INDETERMINATE -> {
                System.out.println("Schedulability could not be determined.");
            }
        }
    }
}

