package me.khabir.algos;

import me.khabir.entity.Task;

import java.util.List;
import java.util.Map;

public class RateMonotonic implements Scheduler {
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
                // t < Di
                return Schedulability.INDETERMINATE;
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
        for (int j = 0 ; j < i -1; j++) {
            Task taskJ = tasks.get(j);
            sum += (int) (Math.ceil((double) t / taskJ.getPeriod()) * taskJ.getCapacity());
        }
        sum += tasks.get(i-1).getCapacity();
        return sum;
    }

    private List<Task> sortBySmallerPeriod(List<Task> tasks) {
        tasks.sort((t1, t2) -> Integer.compare(t1.getPeriod(), t2.getPeriod()));
        return tasks;
    }

    @Override
    public Map<Integer, Task> schedule(List<Task> tasks) {
        return Map.of();
    }
}
