package me.khabir.entity;

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

  public void setCurrentCapacity(int currentCapacity) {
    this.currentCapacity = currentCapacity;
  }

  public int getCapacity() {
    return capacity;
  }

  public int getCurrentCapacity() {
    return currentCapacity;
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
}
