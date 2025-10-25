package me.khabir.algos;

import me.khabir.entity.Task;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class RateMonotonic extends EDFAlgorithm {

    @Override
    public Schedulability isSchedulable(List<Task> tasks) {
        if (tasks.stream().allMatch(task -> task.getDeadline() == task.getPeriod())) {
            double utilization = 0.0;
            int n = tasks.size();
            for (Task task : tasks) {
                utilization += (double) task.getCapacity() / task.getPeriod();
            }
            double bound = n * (Math.pow(2, 1.0 / n) - 1);
            if (utilization <= bound) {
                return Schedulability.SCHEDULABLE;
            } else {
                List<Task> tasksSorted = sortBySmallerPeriod(tasks);
                int t = 0;
                for (int i = 1; i <= tasks.size(); i++) {
                    System.out.printf("Pour i = %d (task P%d)%n", i, i);
                    while (true) {
                        int current_wi = wi(i, t, tasksSorted);
                        System.out.printf("\tw%d(%d) = %d", i, t, current_wi);
                        if (current_wi > tasks.get(i-1).getDeadline())
                            return Schedulability.NOT_SCHEDULABLE;
                        if (t == current_wi) {
                            System.out.println(" OK");
                            break;
                        }
                        System.out.println();
                        t = current_wi;
                    }
                }
                return Schedulability.SCHEDULABLE;
            }
        } else {
            return Schedulability.NOT_SCHEDULABLE;
        }
    }

    /*
     * i: is the iteration, from 1, to size of tasks
     * t: is the current time instant
     */
    public int wi(int i, int t, List<Task> tasks) {
        int sum = 0;
        for (int j = 0; j < i - 1; j++) {
            Task taskJ = tasks.get(j);
            sum += (int) (Math.ceil((double) t / taskJ.getPeriod()) * taskJ.getCapacity());
        }
        sum += tasks.get(i - 1).getCapacity();
        return sum;
    }

    private List<Task> sortBySmallerPeriod(List<Task> tasks) {
        return tasks.stream().sorted(Comparator.comparing(Task::getPeriod)).toList();
    }


    /*
        * Returns the task with the earliest deadline at time t
     */
    private Task getTaskWithEarliestDeadline(List<Task> tasks, int t) {
        // 1. sort the task by priority (smaller period -> higher priority)
        // 2. select the highest priority task that has remaining capacity
        List<Task> sortedTasks = sortBySmallerPeriod(tasks); // todo: cache it
        for (Task task : sortedTasks) {
            if (task.getCurrentCapacity() > 0) {
                return task;
            }
        }
        return null;
    }
}
