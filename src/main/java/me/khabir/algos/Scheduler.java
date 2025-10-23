package me.khabir.algos;

import me.khabir.entity.Task;

import java.util.List;
import java.util.Map;

public interface Scheduler {
    Schedulability isSchedulable(List<Task> tasks);

    Map<Integer, Task> schedule(List<Task> tasks);
}
