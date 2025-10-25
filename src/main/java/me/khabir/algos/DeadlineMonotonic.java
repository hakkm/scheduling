package me.khabir.algos;

import me.khabir.entity.Task;

import java.util.Comparator;
import java.util.List;

public class DeadlineMonotonic extends RateMonotonic {

    @Override
    protected List<Task> sortByPriority(List<Task> tasks) {
        return tasks.stream().sorted(Comparator.comparing(Task::getDeadline)).toList();
    }

}
