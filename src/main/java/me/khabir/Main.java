package me.khabir;

import me.khabir.edf.EDFAlgorithm;
import me.khabir.edf.Schedulability;
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

        EDFAlgorithm edf = new EDFAlgorithm(tasks);

        switch (edf.isSchedulable()) {
            case SCHEDULABLE -> System.out.println("The task set is schedulable under EDF.");
            case NOT_SCHEDULABLE -> System.out.println("The task set is not schedulable under EDF.");
            case INDETERMINATE -> System.out.println("The task set may or may not be schedulable under EDF.");
        }

        if (edf.isSchedulable() == Schedulability.NOT_SCHEDULABLE) {
            return;
        }
        Map<Integer, Task> schedule = edf.getScheduledTasks();

        TaskDrawer drawer = new GanttTaskDrawer();
        drawer.draw(schedule);
    }
}

