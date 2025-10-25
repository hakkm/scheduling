package me.khabir.algos;

import me.khabir.entity.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RateMonotonicTest {
    RateMonotonic rm;

    @BeforeEach
    void setUp() {
        rm = new RateMonotonic();
    }

    @Test
    void testWi() {
        List<Task> tasks = List.of(
                new Task("A", 40, 100, 100),
                new Task("B", 40, 150, 150),
                new Task("C", 100, 350, 350)
        );

        int wi = rm.wi(1, 0, tasks);
        assertEquals(40, wi);
        assertEquals(80, rm.wi(2, 40, tasks));
        assertEquals(80, rm.wi(2, 80, tasks));

        assertEquals(300, rm.wi(3, 260, tasks));
        assertEquals(300, rm.wi(3, 300, tasks));
    }

    @Test
    void isSchedulable() {
        List<Task> tasks = List.of(
                new Task("A", 40, 100, 100),
                new Task("B", 40, 150, 150),
                new Task("C", 100, 350, 350)
        );
        Schedulability s = rm.isSchedulable(tasks);
        assertEquals(Schedulability.SCHEDULABLE, s);
    }

    @Test
    void testBoundarySchedulability() {
        List<Task> tasks = List.of(
                new Task("A", 1, 4, 4),   // 0.25
                new Task("B", 2, 5, 5)    // 0.4 → total = 0.65 < 0.828
        );
        Schedulability s = rm.isSchedulable(tasks);
        assertEquals(Schedulability.SCHEDULABLE, s);
    }


    @Test
    void testUnschedulableHighUtilization() {
        List<Task> tasks = List.of(
                new Task("A", 2, 4, 4),   // U = 0.5
                new Task("B", 3, 5, 5)    // U = 0.6 → total = 1.1 > 1
        );
        Schedulability s = rm.isSchedulable(tasks);
        assertEquals(Schedulability.NOT_SCHEDULABLE, s);
    }

    @Test
    void testTasksWithEqualPeriods() {
        List<Task> tasks = List.of(
                new Task("A", 2, 10, 10),
                new Task("B", 3, 10, 10),
                new Task("C", 1, 10, 10)
        );
        Schedulability s = rm.isSchedulable(tasks);
        assertEquals(Schedulability.SCHEDULABLE, s);
    }

    @Test
    void testMultipleTasks() {
        List<Task> tasks = List.of(
                new Task("A", 1, 5, 5),
                new Task("B", 1, 10, 10),
                new Task("C", 2, 20, 20),
                new Task("D", 2, 40, 40)
        );
        Schedulability s = rm.isSchedulable(tasks);
        assertEquals(Schedulability.SCHEDULABLE, s);
    }

    @Test
    void testEmptyTaskList() {
        List<Task> tasks = List.of();
        Schedulability s = rm.isSchedulable(tasks);
        assertEquals(Schedulability.SCHEDULABLE, s, "Empty set should be trivially schedulable");
    }

    @Test
    void testExactUtilizationOne() {
        List<Task> tasks = List.of(
                new Task("A", 2, 4, 4),  // 0.5
                new Task("B", 2, 4, 4),  // 0.5
                new Task("C", 1, 100, 100) // negligible
        );
        Schedulability s = rm.isSchedulable(tasks);
        assertEquals(Schedulability.NOT_SCHEDULABLE, s);
    }

    @Test
    void testInvalidTaskValues() {
        List<Task> tasks = List.of(
                new Task("A", 0, 10, 10),
                new Task("B", -1, 5, 5)
        );
        assertThrows(IllegalArgumentException.class, () -> rm.isSchedulable(tasks));
    }
}