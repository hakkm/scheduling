package me.khabir.edf;

import me.khabir.entity.Task;

import java.util.Map;
import java.util.Optional;

public interface SchedulingAlgo {
   Schedulability isSchedulable(Task[] tasks);
    Map<Integer, Task> getScheduledTasks();
}
