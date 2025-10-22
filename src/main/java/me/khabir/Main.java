package me.khabir;

import me.khabir.edf.CachedScheduler;
import me.khabir.edf.EDFAlgorithm;
import me.khabir.edf.Schedulability;
import me.khabir.edf.Scheduler;
import me.khabir.entity.Task;
import me.khabir.ui.GanttTaskDrawer;
import me.khabir.ui.TaskDrawer;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        List<Task> tasks = List.of(
                new Task("A", 6, 20, 20),
                new Task("B", 2, 5, 5),
                new Task("C", 3, 10, 10)
        );

        List<Task> tasksWithDeadlineMiss = List.of(
                new Task("A", 3, 7, 20),
                new Task("B", 2, 4, 5),
                new Task("C", 1, 8, 10)
        );

        Scheduler edf = new CachedScheduler(new EDFAlgorithm());

        switch (edf.isSchedulable(tasks)) {
            case SCHEDULABLE -> System.out.println("The task set is schedulable under EDF.");
            case NOT_SCHEDULABLE -> System.out.println("The task set is not schedulable under EDF.");
            case INDETERMINATE -> System.out.println("The task set may or may not be schedulable under EDF.");
        }

        if (edf.isSchedulable(tasks) == Schedulability.NOT_SCHEDULABLE) {
            return;
        }
        Map<Integer, Task> schedule = null;
        Map<Integer, Task> schedule2 = null;

        schedule = edf.schedule(tasks); // only compute one time
        schedule2 = edf.schedule(tasksWithDeadlineMiss);

        TaskDrawer drawer = new GanttTaskDrawer();
        drawer.draw(edf.schedule(tasks));
        TaskDrawer drawer2 = new GanttTaskDrawer();
        drawer2.draw(schedule2);
    }
}

