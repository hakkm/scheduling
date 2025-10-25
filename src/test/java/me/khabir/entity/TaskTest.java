package me.khabir.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

class TaskTest {


    @Test
    void testInvalidTaskValues() {
        assertThrows(IllegalArgumentException.class, () -> new Task("R", -1, 5, 5));
        assertThrows(IllegalArgumentException.class, () -> new Task("R", 0, 5, 5));
    }
}