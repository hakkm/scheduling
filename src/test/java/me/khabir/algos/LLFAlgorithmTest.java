package me.khabir.algos;

import me.khabir.entity.Task;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LLFAlgorithmTest {
    private LLFAlgorithm llf;

    @BeforeEach
    void setUp() {
        llf = new LLFAlgorithm();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testLaxity1() {
        int laxity = llf.getDt(0, new Task("R", 2, 4, 5));
        assertEquals(2, laxity);
    }
    @Test
    void testLaxity2() {
        int laxity = llf.getDt(1, new Task("R", 2, 4, 5));
        assertEquals(1, laxity);
    }
    @Test
    void testLaxity3() {
        int laxity = llf.getDt(5, new Task("R", 2, 4, 5));
        assertEquals(2, laxity);
        int laxity2 = llf.getDt(5, new Task("R2", 1, 8, 10));
        assertEquals(2, laxity2);
    }
    @Test
    void testLaxityWithCapacityEaten() {
        Task t = new Task("R", 3, 7, 20);
        t.setCurrentCapacity(2);
        int laxity = llf.getDt(3, t);
        assertEquals(2, laxity);
    }
    @Test
    void testLaxityWithCapacityEaten1() {
        Task t = new Task("B", 2, 4, 5);
        t.setCurrentCapacity(1);
        int laxity = llf.getDt(6, t);
        assertEquals(2, laxity);
    }


    @Test
    void testSchedule() {
        List<Task> tasks = List.of(
                new Task("A", 3, 7, 20),
                new Task("B", 2, 4, 5),
                new Task("C", 1, 8, 10)
        );
        var schedule = llf.schedule(tasks);
        System.out.println(schedule);
        assertNotNull(schedule);
        assertEquals("B", schedule.get(0).getName());
        assertEquals("B", schedule.get(1).getName());
        assertEquals("A", schedule.get(2).getName());
        assertEquals("A", schedule.get(3).getName());
        assertEquals("A", schedule.get(4).getName());
        assertEquals("B", schedule.get(5).getName());
        assertEquals("C", schedule.get(6).getName());
    }
}