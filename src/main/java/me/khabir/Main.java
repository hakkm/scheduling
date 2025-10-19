package me.khabir;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Task[] tasks = {new Task('A', 3, 7, 20), new Task('B', 2, 4, 5), new Task('C', 1, 8, 10)};

        EDFAlgorithm edf = new EDFAlgorithm(List.of(tasks));

        if (edf.isSchedulable(tasks)) {
            edf.schedule(tasks);
        } else {
            System.out.println("The task set is not schedulable under EDF.");
        }
    }
}

class EDFAlgorithm {

    private List<Task> tasks;


    public EDFAlgorithm(List<Task> tasks) {
        this.tasks = tasks;
    }

    public boolean isSchedulable(Task[] tasks) {
        // Implement EDF schedulability test
        return true;
    }

    public void schedule(Task[] tasks) {
        int hyperPeriod = tasksLCM(tasks);

        for (int t = 0; t < hyperPeriod; t++) {
            for (Task task : tasks) {
                if (t % task.period == 0) {
                    task.currentCapacity = task.capacity;
                }
            }
            Task currentTask = getTaskWithEarliestDeadline(t);
            if (currentTask != null) {
                System.out.println("At time " + t + ", executing task " + currentTask.name);
                currentTask.currentCapacity--;
            } else {
                System.out.println("At time " + t + ", CPU is idle");
            }
        }
    }

    private Task getTaskWithEarliestDeadline(int t) {
        Task earliestTask = null;
        int earliestDeadline = Integer.MAX_VALUE;

        for (Task task : tasks) {
            int dt = getDt(t, task);
            if (dt < earliestDeadline && task.currentCapacity > 0) {
                earliestDeadline = dt;
                earliestTask = task;
            }
        }

        return earliestTask;
    }

    private int getRi(int t, Task task) {
        // return partie entiere de t/task.T
        return t / task.period;
    }

    public int getDt(int t, Task task) {
        return task.deadline - (t - getRi(t, task) * task.period);
    }

    private int tasksLCM(Task[] tasks) {
        int ppcm = tasks[0].period;
        for (int i = 1; i < tasks.length; i++) {
            ppcm = MathFunctions.lcm(ppcm, tasks[i].period);
        }
        return ppcm;
    }

    private int lcm(int a, int b) {
        return a * (b / gcd(a, b));
    }

    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}

class Task {
    char name;
    int capacity;
    int deadline;
    int period;
    int currentCapacity;

    public Task(char name, int Capacity, int Deadline, int Period) {
        this.name = name;
        this.capacity = Capacity;
        this.deadline = Deadline;
        this.period = Period;
        this.currentCapacity = Capacity;
    }
}


class MathFunctions {
    public static int lcm(int a, int b) {
        return a * (b / gcd(a, b));
    }

    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}