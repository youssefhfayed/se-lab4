package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class TorpedoStoreTest {

    @Test
    void constructor_setsInitialCountAndNonEmptyState() {
        TorpedoStore store = new TorpedoStore(2, 0.0);

        assertEquals(2, store.getTorpedoCount());
        assertFalse(store.isEmpty());
    }

    @Test
    void zeroCountStoreIsEmpty() {
        TorpedoStore store = new TorpedoStore(0, 0.0);

        assertEquals(0, store.getTorpedoCount());
        assertTrue(store.isEmpty());
    }

    @Test
    void fire_successfullyConsumesRequestedTorpedoes() {
        TorpedoStore store = new TorpedoStore(2, 0.0);

        assertTrue(store.fire(1));
        assertEquals(1, store.getTorpedoCount());
        assertFalse(store.isEmpty());
    }

    @Test
    void fire_failurePreservesTorpedoes() {
        TorpedoStore store = new TorpedoStore(2, 1.0);

        assertFalse(store.fire(1));
        assertEquals(2, store.getTorpedoCount());
        assertFalse(store.isEmpty());
    }

    @Test
    void fire_rejectsZeroTorpedoes() {
        TorpedoStore store = new TorpedoStore(2, 0.0);

        assertThrows(IllegalArgumentException.class, () -> store.fire(0));
        assertEquals(2, store.getTorpedoCount());
    }

    @Test
    void fire_rejectsMoreThanAvailable() {
        TorpedoStore store = new TorpedoStore(2, 0.0);

        assertThrows(IllegalArgumentException.class, () -> store.fire(3));
        assertEquals(2, store.getTorpedoCount());
    }
}
