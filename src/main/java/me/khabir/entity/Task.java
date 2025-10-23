package me.khabir.entity;

import java.util.Objects;

public class Task {
    String name;
    int capacity;
    int deadline;
    int period;
    int currentCapacity;

    public Task(String name, int Capacity, int Deadline, int Period) {
        this.name = name;
        this.capacity = Capacity;
        this.deadline = Deadline;
        this.period = Period;
        this.currentCapacity = Capacity;
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
