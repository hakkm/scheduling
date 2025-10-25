package me.khabir.algos;

import me.khabir.entity.Task;

import java.util.Comparator;
import java.util.List;

public class DeadlineMonotonic extends RateMonotonic {

    private List<Task> sortBySmallerDeadline(List<Task> tasks) {
        return tasks.stream().sorted(Comparator.comparing(Task::getPeriod)).toList();
    }

    private Task getTaskWithEarliestDeadline(List<Task> tasks, int t) {
        // 1. sort the task by priority (smaller deadline -> higher deadline)
        // 2. select the highest priority task that has remaining capacity
        List<Task> sortedTasks = sortBySmallerDeadline(tasks); // todo: cache it
        for (Task task : sortedTasks) {
            if (task.getCurrentCapacity() > 0) {
                return task;
            }
        }
        return null;
    }
}
