package me.khabir.algos;

import me.khabir.entity.Task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EDFAlgorithm implements Scheduler {


    public Schedulability isSchedulable(List<Task> tasks) {
        // if di == pi for all i. => use the the condition with pi in the bottom is <=> to ordoncability by EDF
        // else if di < pi for some i => use the condition with di in the bottom is => to ordoncability by EDF, but not if and only if
        if (tasks.stream().allMatch(task -> task.getDeadline() == task.getPeriod())) {
            double utilization = 0.0;
            utilization = tasks.stream()
                    .mapToDouble(task -> (double) task.getCapacity() / (double) task.getPeriod())
                    .sum();
            if (utilization <= 1.0) {
                return Schedulability.SCHEDULABLE;
            } else {
                return Schedulability.NOT_SCHEDULABLE;
            }
        } else {
            double charge = 0.0;
            charge = tasks.stream()
                    .mapToDouble(task -> (double) task.getCapacity() / (double) task.getDeadline())
                    .sum();
            if (charge <= 1)
                return Schedulability.SCHEDULABLE;

            double utilisation = 0.0;
            utilisation = tasks.stream()
                    .mapToDouble(task -> (double) task.getCapacity() / (double) task.getPeriod())
                    .sum();
            if (utilisation > 1)
                return Schedulability.NOT_SCHEDULABLE;
            return Schedulability.INDETERMINATE;
        }
    }

    public Map<Integer, Task> schedule(List<Task> tasks) {
        Map<Integer, Task> scheduledTasks = new HashMap<>();
        int hyperPeriod = tasksLCM(tasks);

        for (int t = 0; t < hyperPeriod; t++) {
            for (Task task : tasks) {
                // reinitialize the current capacity if we hit the period
                if (t % task.getPeriod() == 0) {
                    task.setCurrentCapacity(task.getCapacity());
                }
            }
            Task currentTask = getTaskWithEarliestDeadline(tasks, t);
            if (currentTask != null) {
                scheduledTasks.put(t, currentTask);
                currentTask.setCurrentCapacity(currentTask.getCurrentCapacity() - 1);
            } else {
                scheduledTasks.put(t, null); // Idle time
            }
        }
        return scheduledTasks;
    }


    private Task getTaskWithEarliestDeadline(List<Task> tasks, int t) {
        Task earliestTask = null;
        int earliestDeadline = Integer.MAX_VALUE;

        for (Task task : tasks) {
            int dt = getDt(t, task);
            if (dt < earliestDeadline && task.getCurrentCapacity() > 0) {
                earliestDeadline = dt;
                earliestTask = task;
            }
        }

        return earliestTask;
    }

    private int getRi(int t, Task task) {
        // return partie entiere de t/task.T
        return t / task.getPeriod() * task.getPeriod();
    }

    public int getDt(int t, Task task) {
        return task.getDeadline() - (t - getRi(t, task));
    }

    private int tasksLCM(List<Task> tasks) {
        int lcm = tasks.get(0).getPeriod();
        for (int i = 1; i < tasks.size(); i++) {
            lcm = lcm(lcm, tasks.get(i).getPeriod());
        }
        return lcm;
    }

    private int lcm(int a, int b) {
        return a * (b / gcd(a, b));
    }

    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}
