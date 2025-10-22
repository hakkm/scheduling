package me.khabir.edf;

import me.khabir.entity.Task;
import me.khabir.entity.TaskSet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CachedScheduler implements Scheduler {


    private final Scheduler scheduler;
    private final Map<TaskSet, Map<Integer, Task>> cache = new HashMap<>();

    public CachedScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    public Schedulability isSchedulable(List<Task> tasks) {
        return scheduler.isSchedulable(tasks);
    }

    @Override
    public Map<Integer, Task> schedule(List<Task> tasks) {
        TaskSet taskSet = new TaskSet(tasks);
        if (cache.containsKey(taskSet)) {
            System.out.println("Cache hit for task set: " + taskSet);
            return cache.get(taskSet);
        }
        Map<Integer, Task> schedule = scheduler.schedule(tasks);
        cache.put(taskSet, schedule);
        return schedule;
    }
}
