package me.khabir.edf;

import me.khabir.entity.Task;

import java.util.List;
import java.util.Map;

public class CachedScheduler implements Scheduler {

    private final Scheduler scheduler;
    private Map<Integer, Task> cachedSchedule = null;

    public CachedScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    public Schedulability isSchedulable(List<Task> tasks) {
        return scheduler.isSchedulable(tasks);
    }

    @Override
    public Map<Integer, Task> schedule(List<Task> tasks) {
        if (cachedSchedule != null) {
            return cachedSchedule;
        }
        cachedSchedule = scheduler.schedule(tasks);
        return cachedSchedule;
    }
}
