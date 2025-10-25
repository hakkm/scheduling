package me.khabir.entity;

import java.util.Objects;

public class Task {
    String name;
    int capacity;
    int deadline;
    int period;
    int currentCapacity;

    public Task(String name, int capacity, int deadline, int period) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive. Given: " + capacity);
        }
        if (deadline <= 0) {
            throw new IllegalArgumentException("Deadline must be positive. Given: " + deadline);
        }
        if (period <= 0) {
            throw new IllegalArgumentException("Period must be positive. Given: " + period);
        }
        this.name = name;
        this.capacity = capacity;
        this.deadline = deadline;
        this.period = period;
        this.currentCapacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getCurrentCapacity() {
        return currentCapacity;
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                '}';
    }

    public void setCurrentCapacity(int currentCapacity) {
        this.currentCapacity = currentCapacity;
    }

    public int getDeadline() {
        return deadline;
    }

    public int getPeriod() {
        return period;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Task task)) return false;
        return getCapacity() == task.getCapacity() && getDeadline() == task.getDeadline() && getPeriod() == task.getPeriod() && Objects.equals(getName(), task.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getCapacity(), getDeadline(), getPeriod());
    }
}
