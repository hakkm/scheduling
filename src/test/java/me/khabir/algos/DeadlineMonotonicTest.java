package me.khabir.algos;

import me.khabir.entity.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class DeadlineMonotonicTest {

    @Test
    void testTaskExecutionOrder() {

        List<Task> tasks = List.of(
                new Task("A", 4, 10, 10),
                new Task("B", 4, 15, 15),
                new Task("C", 10, 35, 35)
        );

        Scheduler dm = new DeadlineMonotonic();

        if (dm.isSchedulable(tasks) != Schedulability.SCHEDULABLE) {
            Assertions.fail("Task set should be schedulable");
        }

        Map<Integer, Task> schedule = dm.schedule(tasks);

        // Expected order of execution
        String[] expectedOrder = {
                "A","A","A","A",
                "B","B","B","B",
                "C","C",
                "A","A","A","A",
                "C",
                "B","B","B","B",
                "C",
                "A","A","A","A",
                "C","C","C","C","C","C"
        }; // there is more but this is enough as it repeats

        for (int i = 0; i < expectedOrder.length; i++) {
            Task actualTask = schedule.get(i);
            String actualName = actualTask != null ? actualTask.getName() : null;
            assertEquals(expectedOrder[i], actualName, "Mismatch at time " + i);
        }
    }

}