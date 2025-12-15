package ru.nsu.ekovalenko4.markdown;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

/**
 * Unit test for Main.
 */
class MainTest {
    @Test
    void main() {
        assertDoesNotThrow(() -> Main.main(null));
    }
}