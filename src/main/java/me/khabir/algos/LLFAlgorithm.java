package me.khabir.algos;

import me.khabir.entity.Task;

public class LLFAlgorithm extends EDFAlgorithm {

    @Override
    public int getDt(int t, Task task) {
        return getDi(t, task) - (t + task.getCurrentCapacity());
    }

    private int getDi(int t, Task task) {
        int k = t / task.getPeriod();
        return k * task.getPeriod() + task.getDeadline();
    }
}
