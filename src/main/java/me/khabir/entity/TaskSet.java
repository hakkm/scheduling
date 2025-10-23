package me.khabir.entity;

import java.util.List;
import java.util.Objects;

/**
 * A wrapper class for a set of tasks to be used as a key in caching.
 */
public class TaskSet {
    private final List<Task> tasks;
    public TaskSet(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof TaskSet taskSet)) return false;
        return Objects.equals(getTasks(), taskSet.getTasks());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getTasks());
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
