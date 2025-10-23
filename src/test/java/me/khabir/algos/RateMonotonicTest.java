package me.khabir.algos;

import me.khabir.entity.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

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

}